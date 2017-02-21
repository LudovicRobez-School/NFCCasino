package fr.Players.Provider;

/**
 * Created by rl613611 on 17/01/2017.
 */
public class PlayerProvider {
	
	private static Map<String, Shape> allCustomers = new HashMap<String, Shape>();

    public static List<Shape> getAllShapes() {
		try(Dabebase db = DataBaseAccess.getDbConnection() )
        return new ArrayList<Shape>(allShapes.values());
    }
}
