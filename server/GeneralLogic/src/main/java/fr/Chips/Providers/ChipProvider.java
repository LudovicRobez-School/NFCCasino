package fr.Chips.Providers;

import fr.Chips.Ressources.Chip;
import fr.Data.Services.DataBaseAccess;
import fr.Data.Services.DataBaseAccessImpl;
import fr.Chips.Exceptions.ChipException;
import java.util.Map;

/**
 * Created by rl613611 on 20/03/2017.
 */
public class ChipProvider {

    private static final String UPDATE_CHIPS_AT_PLAYER  = "";
    private static final String ADD_CHIPS_AT_PLAYER = "";
    private static final String CHIPS_AT_PLAYER_QUERY = "";


    public static Map<String, String> findChipsById(String id) {
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()){
            return db.findOneAsMap(String.format(CHIPS_AT_PLAYER_QUERY, id));
        }
        catch (Exception e){
            throw new ChipException("Error while findChipsById()", e);
        }
    }

    public static Map<String, String> addChips(int playerId,int solde){
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()){
            Map<Chip,Integer> chips = ChipGenerator.generateChips(solde);
            return db.findOneAsMap(String.format(ADD_CHIPS_AT_PLAYER, player.getLastName(), player.getFirstName(), player.getSolde()));
        }
        catch (Exception e){
            throw new ChipException("Error while addChips()", e);
        }
    }

    public static boolean changeChip(){

    }

}
