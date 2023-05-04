package test;
import java.sql.SQLException;

import model.DAO.*;
import model.domain.Peleador;
public class TestDAOS {
	public static void main(String[] args) {
		
		Peleador p = new Peleador("1212","Ilia","Topuria",26,"Male",66, 170,"13-0-0","Georgia","BJJ");
		
		PeleadorDAO pdao = new PeleadorDAO();
		
		try {
			pdao.delete(p);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
