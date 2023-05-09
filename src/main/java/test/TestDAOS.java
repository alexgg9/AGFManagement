package test;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
import model.DAO.*;
import model.domain.*;
import model.enums.Background;
import model.enums.Genero;
import model.enums.Modalidad;
import utils.Utils;

public class TestDAOS {
	public static void main(String[] args) throws ParseException {
		
		Peleador p = new Peleador("1212","Ilia","Topuria",26,Genero.MALE,66, 170,"13-0-0","Georgia",Background.GRAPPLING);
		Peleador p1 = new Peleador("1313","Josh","Emmet",38,Genero.MALE,66, 172,"15-4-0","EEUU",Background.STRIKING);
		Peleador p2 = new Peleador("1414","Yair","Rodriguez",30,Genero.MALE,66, 185,"14-3-0","Mexico",Background.STRIKING);
		Matchmaker m = new Matchmaker("12345678C","Cesar","Alonso","WOW","CesarAlonso10","pass1");
		
		Date d = null;
		
		Evento ev = new Evento(2,"Fight Night","Apex","Las Vegas","EEUU",Modalidad.MMA,d,p,p1,m);
		
		PeleadorDAO pdao = new PeleadorDAO();
		MatchmakerDAO mdao = new MatchmakerDAO();
		EventoDAO edao = new EventoDAO();
		
		try {
			mdao.save(m);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
