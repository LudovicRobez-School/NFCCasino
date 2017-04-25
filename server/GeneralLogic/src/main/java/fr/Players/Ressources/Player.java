package fr.Players.Ressources;

import fr.Chips.Providers.ChipGenerator;
import fr.Chips.Ressources.Chip;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rl613611 on 17/01/2017.
 */
public class Player {

    String lastName;
    String firstName;
    int solde;
    Map<Chip,Integer> chips;

    public Player(String lastName, String firstName, int solde) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.solde = solde;
        chips = ChipGenerator.generateChips(solde);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public Map<Chip, Integer> getChips() {
        return chips;
    }

    public void setChips(Map<Chip, Integer> chips) {
        this.chips = chips;
    }
}
