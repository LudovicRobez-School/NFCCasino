package fr.Chips.Providers;

import fr.Chips.Ressources.Chip;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rl613611 on 20/03/2017.
 */

public class ChipGenerator {

    public static Map<Chip,Integer> generateChips(int solde){
        Map<Chip,Integer> chips = new HashMap<Chip,Integer>();
        int numberChip;
        for(Chip chip:  Chip.values()){
            switch (chip) {
                case WHITE:
                    numberChip = solde / chip.getValue();
                    chips.put(chip, numberChip);
                    break;
                case RED:
                    numberChip = solde / chip.getValue();
                    numberChip = (int) Math.round(numberChip / 1.5);
                    chips.put(chip, numberChip);
                    solde = solde - numberChip * chip.getValue();
                    break;
                case BLUE:
                    numberChip = solde / chip.getValue();
                    chips.put(chip, numberChip / 2);
                    solde = solde - (numberChip / 2) * chip.getValue();
                    break;
                case GREEN:
                    numberChip = solde / chip.getValue();
                    chips.put(chip, numberChip / 2);
                    solde = solde - (numberChip / 2) * chip.getValue();
                    break;
                case ORANGE:
                    numberChip = solde / chip.getValue();
                    chips.put(chip, numberChip / 2);
                    solde = solde - (numberChip / 2) * chip.getValue();
                    break;
                case BLACK:
                    numberChip = solde / chip.getValue();
                    chips.put(chip, numberChip / 2);
                    solde = solde - (numberChip / 2) * chip.getValue();
                    break;
                default:

                    break;
            }
        }
        return chips;
    }

    public static void getChange(Chip chip, String playerId ){
        switch (chip){
            case RED:
                ChipProvider.changeChip("red","white",5);
                break;
            case BLUE:
                ChipProvider.changeChip("red","white",5);
                break;
            case GREEN:
                ChipProvider.changeChip("green","",5);
                break;
            case ORANGE:
                ChipProvider.changeChip("orange","green",2);
                break;
            case BLACK:
                ChipProvider.changeChip("black","orange",2);
                break;
        }
    }
}
