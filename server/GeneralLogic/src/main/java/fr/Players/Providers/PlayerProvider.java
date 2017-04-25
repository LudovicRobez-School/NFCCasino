package fr.Players.Providers;

import fr.Chips.Providers.ChipProvider;
import fr.Data.Services.DataBaseAccess;
import fr.Data.Services.DataBaseAccessImpl;
import fr.Players.Exceptions.*;
import fr.Players.Ressources.Player;

import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by rl613611 on 17/01/2017.
 */
public class PlayerProvider {
    private static final String UPDATE_PLAYER = "";
    private static final String ADD_PLAYER = "";
    private static final String PLAYER_QUERY = "";


    public static Map<String, String> findPlayerById(String id) {
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()){
            return db.findOneAsMap(String.format(PLAYER_QUERY, id));
        }
        catch (Exception e){
            throw new PlayerException("Error while findPlayerById()", e);
        }
    }

    public static Map<String, String> addPlayer(String lastName, String firstName, int solde){
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()){
            Player player =  new Player(lastName, firstName, solde);
            return db.findOneAsMap(String.format(ADD_PLAYER, player.getLastName(), player.getFirstName(), player.getSolde()));
        }
        catch (Exception e){
            throw new PlayerException("Error while addPlayer()", e);
        }
    }

    public static ResultSet updatePlayer(String lastName, String firstName, int solde){
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()){
            return db.query(String.format(UPDATE_PLAYER, lastName, firstName, solde));
        }
        catch (Exception e){
            throw new PlayerException("Error while setInfoPlayer()", e);
        }
    }
}
