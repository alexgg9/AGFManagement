package AGFPromotions.ManagementFights.test;
import java.sql.SQLException;
import java.text.ParseException;

import AGFPromotions.ManagementFights.model.DAO.*;
import AGFPromotions.ManagementFights.model.domain.*;
import AGFPromotions.ManagementFights.model.enums.Background;
import AGFPromotions.ManagementFights.model.enums.Genero;
import AGFPromotions.ManagementFights.model.enums.Modalidad;
import AGFPromotions.ManagementFights.utils.Utils;

import java.sql.Date;

public class TestDAOS {
	public static void main(String[] args) throws ParseException {
		
		
		Peleador p = new Peleador("1212","Ilia","Topuria",26,Genero.MALE,66, 170,"13-0-0","Georgia",Background.GRAPPLING);
		Peleador p1 = new Peleador("1313","Brian","Ortega",38,Genero.MALE,66, 172,"15-4-0","EEUU",Background.GRAPPLING);
		Peleador p2 = new Peleador("1414","Yair","Rodriguez",30,Genero.MALE,66, 185,"14-3-0","Mexico",Background.STRIKING);
		Matchmaker m = new Matchmaker("12345678C","Cesar","Alonso","WOW","CesarAlonso10","pass1");
		
		Date d = null;
		
		PeleadorDAO pdao = new PeleadorDAO();
		MatchmakerDAO mdao = new MatchmakerDAO();
		EventoDAO edao = new EventoDAO();
		
		try {
			pdao.save(p1);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
