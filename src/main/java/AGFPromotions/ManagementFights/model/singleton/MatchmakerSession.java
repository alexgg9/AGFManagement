package AGFPromotions.ManagementFights.model.singleton;

import AGFPromotions.ManagementFights.model.domain.Matchmaker;

public class MatchmakerSession {
	private static Matchmaker user=null;
	public static void login(String dni,String usuario) {
		user = new Matchmaker(dni,usuario);
	}
	public static void logout() {
		user = null;
	}
	public static boolean isLogged() {
		return user==null?false:true;
	}
	public static Matchmaker getUser() {
		return user;
	}
}






