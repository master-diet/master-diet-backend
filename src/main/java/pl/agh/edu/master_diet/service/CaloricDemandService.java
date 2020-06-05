package pl.agh.edu.master_diet.service;

import pl.agh.edu.master_diet.core.model.shared.UserParameters;

public class CaloricDemandService {

    public int calculateAndSaveUsersCaloricDemand(UserParameters userParameters) {

        int caloricDemand = calculateCaloricDemand(userParameters);

        return caloricDemand;
    }

    public int calculateCaloricDemand(UserParameters userParameters) {

    }
}
