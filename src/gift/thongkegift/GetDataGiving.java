package gift.thongkegift;

import database.SQLConnection;

public class GetDataGiving {
	public static void getGivingList() {
		SQLConnection.ConnectData();
		String sql = "";
		SQLConnection.DisconnectData();
	}
}
