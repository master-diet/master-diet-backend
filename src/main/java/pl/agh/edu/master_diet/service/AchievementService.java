package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.edu.master_diet.core.model.database.Achievement;
import pl.agh.edu.master_diet.core.model.database.AchievementActivityItem;
import pl.agh.edu.master_diet.core.model.database.AchievementProductItem;
import pl.agh.edu.master_diet.core.model.database.Activity;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.UserAchievement;
import pl.agh.edu.master_diet.core.model.rest.AchievementSetResponse;
import pl.agh.edu.master_diet.core.model.rest.AchievementsResponse;
import pl.agh.edu.master_diet.core.model.shared.AchievementType;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.core.model.shared.UserActivityParameters;
import pl.agh.edu.master_diet.repository.AchievementActivityItemRepository;
import pl.agh.edu.master_diet.repository.AchievementProductItemRepository;
import pl.agh.edu.master_diet.repository.AchievementRepository;
import pl.agh.edu.master_diet.repository.UserAchievementRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AchievementService {

    private static final int DAYS_BETWEEN_LOGGING_IN_TO_SCORE_POINTS = 1;
    private static final Float POINTS_FOR_LOGGING_IN_A_ROW = 1.0f;
    private static final Float STANDARD_PORTION_SIZE = 100.0f;

    private final UserAchievementRepository userAchievementRepository;
    private final ConversionService conversionService;
    private final AchievementRepository achievementRepository;
    private final AchievementProductItemRepository achievementProductItemRepository;
    private final AchievementActivityItemRepository achievementActivityItemRepository;
    private final ProductService productService;
    private final ActivityService activityService;
    private final UserService userService;

    @Transactional
    public AchievementSetResponse getUserAchievements(final Long userId) {
        List<UserAchievement> userAchievementList = userAchievementRepository.findByUserId(userId);
        List<Achievement> achievementList = achievementRepository.findAll();
        Set<AchievementsResponse> userAchievementResponses = userAchievementList.stream()
                .map(conversionService::convert)
                .collect(Collectors.toSet());

        Set<AchievementsResponse> responsesSet = achievementList.stream()
                .map(conversionService::convert)
                .collect(Collectors.toSet());

        userAchievementResponses.addAll(responsesSet);
        return AchievementSetResponse.builder()
                .achievementsResponseList(userAchievementResponses)
                .build();
    }

    @Transactional
    public void updateProductAchievementStatus(final Long userId, final RecentProductParameters productParameters) {
        updateNutrientsAchievementStatus(userId, productParameters);

        List<Achievement> uncompletedUserAchievements = achievementRepository
                .findUncompletedUserAchievements(AchievementType.PRODUCTS, userId);
        List<Long> uncompletedAchievementIds = uncompletedUserAchievements.stream()
                .map(Achievement::getId)
                .collect(Collectors.toList());
        List<AchievementProductItem> achievementProductItems = achievementProductItemRepository
                .findByAchievementIdIn(uncompletedAchievementIds);
        Product consumedProduct = productService.getProductById(productParameters.getProductId());

        for (AchievementProductItem achievementProductItem : achievementProductItems) {
            if (achievementProductItem.getProduct().getId().equals(consumedProduct.getId())) {
                float scoredPoints = calculateScoredPoints(productParameters, consumedProduct);
                addAchievementScoredPoints(achievementProductItem.getAchievement(), scoredPoints, userId);
            }
        }
    }

    @Transactional
    public void updateActivityAchievementStatus(final Long userId, final UserActivityParameters activityParameters) {
        List<Achievement> uncompletedAchievements = achievementRepository
                .findUncompletedUserAchievements(AchievementType.ACTIVITIES, userId);
        List<Long> uncompletedAchievementIds = uncompletedAchievements.stream()
                .map(Achievement::getId)
                .collect(Collectors.toList());
        List<AchievementActivityItem> achievementActivityItems = achievementActivityItemRepository
                .findByAchievementIdIn(uncompletedAchievementIds);
        Activity performedActivity = activityService.getActivityById(activityParameters.getActivityId());

        for (AchievementActivityItem achievementActivityItem : achievementActivityItems) {
            if (achievementActivityItem.getActivity().getId().equals(performedActivity.getId())) {
                float scoredPoints = calculateScoredPoints(activityParameters);
                addAchievementScoredPoints(achievementActivityItem.getAchievement(), scoredPoints, userId);
            }
        }
    }

    @Transactional
    public void updateLoggingInAchievementStatus(final Long userId,
                                                 final LocalDateTime lastLoginDate,
                                                 final LocalDateTime newLoginDate) {
        List<Achievement> uncompletedAchievements = achievementRepository
                .findUncompletedUserAchievements(AchievementType.LOGGING_IN, userId);

        Long daysBetweenLoginDates = getDaysBetweenLoginDates(lastLoginDate, newLoginDate);

        for (Achievement uncompletedAchievement : uncompletedAchievements) {
            if (daysBetweenLoginDates == null || daysBetweenLoginDates > DAYS_BETWEEN_LOGGING_IN_TO_SCORE_POINTS) {
                setAchievementScoredPoints(uncompletedAchievement, POINTS_FOR_LOGGING_IN_A_ROW, userId);
            } else if (daysBetweenLoginDates == DAYS_BETWEEN_LOGGING_IN_TO_SCORE_POINTS) {
                addAchievementScoredPoints(uncompletedAchievement, POINTS_FOR_LOGGING_IN_A_ROW, userId);
            }
        }
    }

    private void updateNutrientsAchievementStatus(final Long userId, final RecentProductParameters productParameters) {
        List<AchievementType> nutrientsAchievementTypes = Arrays.asList(AchievementType.CALORIES,
                AchievementType.CARBOHYDRATES,
                AchievementType.FAT,
                AchievementType.PROTEINS);

        for (AchievementType achievementType : nutrientsAchievementTypes) {
            List<Achievement> uncompletedAchievements = achievementRepository
                    .findUncompletedUserAchievements(achievementType, userId);
            Product consumedProduct = productService.getProductById(productParameters.getProductId());
            Float consumedNutrients = calculateNutrientValueForAchievementType(productParameters,
                    consumedProduct,
                    achievementType);

            for (Achievement uncompletedAchievement : uncompletedAchievements) {
                addAchievementScoredPoints(uncompletedAchievement, consumedNutrients, userId);
            }
        }
    }

    private void addAchievementScoredPoints(final Achievement achievement,
                                            final Float scoredPoints,
                                            final Long userId) {
        Optional<UserAchievement> userAchievement = userAchievementRepository
                .findByUserIdAndAchievementId(userId, achievement.getId());

        if (userAchievement.isPresent()) {
            updateAchievementProgress(userAchievement.get(), scoredPoints);
        } else {
            insertUserAchievement(scoredPoints, achievement, userId);
        }
    }

    private void setAchievementScoredPoints(final Achievement achievement,
                                            final Float scoredPoints,
                                            final Long userId) {
        Optional<UserAchievement> userAchievement = userAchievementRepository
                .findByUserIdAndAchievementId(userId, achievement.getId());

        if (userAchievement.isPresent()) {
            setAchievementProgress(userAchievement.get(), scoredPoints);
        } else {
            insertUserAchievement(scoredPoints, achievement, userId);
        }
    }

    private void updateAchievementProgress(final UserAchievement userAchievement, final Float scoredPoints) {
        Float currentPoints = userAchievement.getProgress() + scoredPoints;
        Float maxPoints = userAchievement.getAchievement().getCompleteCondition();
        if (currentPoints > maxPoints) {
            currentPoints = maxPoints;
        }
        userAchievement.setProgress(currentPoints);
        setDateIfCompleted(userAchievement, scoredPoints);
        userAchievementRepository.save(userAchievement);
    }

    private void setAchievementProgress(final UserAchievement userAchievement, final Float scoredPoints) {
        userAchievement.setProgress(scoredPoints);
        setDateIfCompleted(userAchievement, scoredPoints);
        userAchievementRepository.save(userAchievement);
    }

    private void insertUserAchievement(final Float scoredPoints, final Achievement achievement, final Long userId) {
        UserAchievement userAchievement = UserAchievement.builder()
                .achievement(achievement)
                .user(userService.getUserById(userId))
                .progress(scoredPoints)
                .build();
        setDateIfCompleted(userAchievement, scoredPoints);
        userAchievementRepository.save(userAchievement);
    }

    private Float calculateScoredPoints(final RecentProductParameters productParameters, final Product product) {
        return (productParameters.getPortion() / product.getDefaultValue()) * productParameters.getAmount();
    }

    private Float calculateScoredPoints(final UserActivityParameters activityParameters) {
        return activityParameters.getAmount() * activityParameters.getTime();
    }

    private Long getDaysBetweenLoginDates(final LocalDateTime lastLoginDateTime,
                                          final LocalDateTime newLoginDateTime) {
        if (lastLoginDateTime == null || newLoginDateTime == null) {
            return null;
        }
        final LocalDate lastLoginDate = lastLoginDateTime.toLocalDate();
        final LocalDate newLoginDate = newLoginDateTime.toLocalDate();
        return DAYS.between(lastLoginDate, newLoginDate);
    }

    private Float calculateNutrientValueForAchievementType(final RecentProductParameters productParameters,
                                                           final Product product,
                                                           final AchievementType achievementType) {
        Float parameterValueForDefaultPortion;
        switch (achievementType) {
            case CALORIES:
                parameterValueForDefaultPortion = product.getCalories();
                break;
            case CARBOHYDRATES:
                parameterValueForDefaultPortion = product.getCarbohydrates();
                break;
            case FAT:
                parameterValueForDefaultPortion = product.getFat();
                break;
            case PROTEINS:
                parameterValueForDefaultPortion = product.getProteins();
                break;
            default:
                throw new IllegalArgumentException("Cannot get product parameter value for achievement type:"
                        + achievementType);
        }

        Float portion = productParameters.getPortion();
        Float amount = productParameters.getAmount();
        return (portion / STANDARD_PORTION_SIZE) * amount * parameterValueForDefaultPortion;
    }

    private void setDateIfCompleted(final UserAchievement userAchievement, final Float currentPoints) {
        if (currentPoints >= userAchievement.getAchievement().getCompleteCondition()) {
            userAchievement.setCompletedDate(LocalDateTime.now());
        }
    }
}
