package test;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
import model.DAO.*;
import model.domain.*;

public class TestDAOS {
	public static void main(String[] args) throws ParseException {
		
		Peleador p = new Peleador("1212","Ilia","Topuria",26,"Male",66, 170,"13-0-0","Georgia","BJJ");
		Peleador p1 = new Peleador("1313","Josh","Emmet",38,"Male",66, 172,"15-4-0","EEUU","Box");
		Matchmaker m = new Matchmaker("12345678C","Cesar","Alonso","WOW");
		
		Date d = null;
		
		Evento ev = new Evento(2,"Fight Night","Apex","Las Vegas","EEUU","MMA",d,p,p1,m);
		
		PeleadorDAO pdao = new PeleadorDAO();
		MatchmakerDAO mdao = new MatchmakerDAO();
		EventoDAO edao = new EventoDAO();
		
		try {
			edao.save(ev);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
