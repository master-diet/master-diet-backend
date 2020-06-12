package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.ActivityLevel;
import pl.agh.edu.master_diet.core.model.shared.Goal;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;
import pl.agh.edu.master_diet.repository.UserPlanRepository;
import pl.agh.edu.master_diet.repository.UserRepository;

import java.time.LocalDate;
import java.time.Period;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserPlanService {

    private static final Integer WEIGHT_COEFFICIENT_DEMAND = 10;
    private static final Double HEIGHT_COEFFICIENT_DEMAND = 6.25;
    private static final Integer AGE_COEFFICIENT_DEMAND = 5;
    private static final Double MIN_PROTEIN_COEFFICIENT = 1.57;
    private static final Double MAX_PROTEIN_COEFFICIENT = 2.0;
    private static final Double PROTEIN_ENERGY_PER_GRAM = 4.0;
    private static final Double FAT_ENERGY_PER_GRAM = 9.0;
    private static final Double CARBOHYDRATES_ENERGY_PER_GRAM = 4.0;


    private final UserPlanRepository userPlanRepository;
    private final UserRepository userRepository;

    public UserPlanResponse calculateAndSaveUsersPlan(UserParameters userParameters) {
        UserPlan userPlan = calculateUsersPlan(userParameters);
        User user = userRepository.getOne(userParameters.getUserId());
        userPlan.setUser(user);
        userPlanRepository.save(userPlan);

        return userPlan.map2UserPlanResponse();
    }

    public UserPlan calculateUsersPlan(UserParameters userParameters) {
        double caloricDemand = calculateCaloricDemand(userParameters);

        double proteinDemand = calculateProteinDemand(userParameters);
        double caloricDemandLeft = subCaloriesObtainNutrients(caloricDemand, PROTEIN_ENERGY_PER_GRAM, proteinDemand);
        double fatDemand = calculateFatDemand(userParameters, caloricDemand);
        caloricDemandLeft = subCaloriesObtainNutrients(caloricDemandLeft, FAT_ENERGY_PER_GRAM, fatDemand);
        double carbohydratesDemand = calculateCarbohydratesDemand(caloricDemandLeft);

        return UserPlan.builder()
                .calories((int) caloricDemand)
                .proteins((int) proteinDemand)
                .fat((int) fatDemand)
                .carbohydrates((int) carbohydratesDemand)
                .activityLevel(userParameters.getActivityLevel())
                .currentWeight(userParameters.getWeight())
                .goal(userParameters.getGoal())
                .build();
    }

    private double calculateCarbohydratesDemand(double caloricDemandLeft) {
        return caloricDemandLeft / CARBOHYDRATES_ENERGY_PER_GRAM;
    }

    private double calculateFatDemand(UserParameters userParameters, double caloricDemand) {
        return caloricDemand * userParameters.getFatPreferencesPercentage() / FAT_ENERGY_PER_GRAM;
    }

    private double subCaloriesObtainNutrients(double leftCaloricDemand, double nutrientEnergyPerGram, double quantity) {
        return leftCaloricDemand - nutrientEnergyPerGram * quantity;
    }

    private double calculateProteinDemand(UserParameters userParameters) {
        double proteinCoefficient;
        ActivityLevel activityLevel = userParameters.getActivityLevel();
        Goal goal = userParameters.getGoal();
        if (goal == Goal.GAIN) {
            proteinCoefficient = MAX_PROTEIN_COEFFICIENT;
        } else if (activityLevel == ActivityLevel.SEDENTARY || activityLevel == ActivityLevel.LIGHT) {
            proteinCoefficient = MIN_PROTEIN_COEFFICIENT;
        } else {
            proteinCoefficient = MAX_PROTEIN_COEFFICIENT;
        }
        return proteinCoefficient * userParameters.getWeight();
    }

    private double calculateCaloricDemand(UserParameters userParameters) {
        int age = getAgeFromBirthDate(userParameters.getBirthDate());
        double weight = userParameters.getWeight();
        double weightDemand = WEIGHT_COEFFICIENT_DEMAND * weight;
        double heightDemand = HEIGHT_COEFFICIENT_DEMAND * weight;
        double ageDemand = AGE_COEFFICIENT_DEMAND * age;
        int genderDemand = userParameters.getGender().getValue();
        double userBMR = weightDemand + heightDemand - ageDemand + genderDemand;
        double activityFactor = userParameters.getActivityLevel().getValue();
        double goalCaloriesCorrection = userParameters.getGoal().getValue() * userBMR;
        return userBMR * activityFactor + goalCaloriesCorrection;
    }

    private int getAgeFromBirthDate(LocalDate birthDate) {
        return Period.between(birthDate, birthDate).getYears();
    }
}
