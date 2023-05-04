package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.connection.ConnectionMySQL;
import model.domain.Matchmaker;

public class MatchmakerDAO implements DAO<Matchmaker>{
	
	private final static String FINDALL = "SELECT * FROM matchmaker";
	private final static String FINDBYID = "SELECT * from matchmaker WHERE dni_matchmaker=?";
	
	private Connection conn;
	
	public MatchmakerDAO(Connection conn) {
		this.conn = conn;
	}
	public MatchmakerDAO() {
		this.conn=ConnectionMySQL.getConnect();
	}
	
	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Matchmaker> findAll() throws SQLException {
		List<Matchmaker> result = new ArrayList<Matchmaker>();
		try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
			try(ResultSet res = pst.executeQuery()){
				while(res.next()) {
					Matchmaker m = new Matchmaker();
					m.setDni(res.getString("dni_matchmaker"));
					m.setNombre(res.getString("nombre"));
					m.setApellidos(res.getString("apellidos"));
					m.setPromotora(res.getString("promotora"));
					result.add(m);
				}
			}
			
		}
		return result;
	}

	@Override
	public Matchmaker findByDni(String id) throws SQLException {
		Matchmaker result = null;
		try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
			pst.setString(1, id);
			try(ResultSet res = pst.executeQuery()){
				if(res.next()) {
					result = new Matchmaker();
					result.setDni(res.getString("dni"));
					result.setNombre(res.getString("nombre"));
					result.setApellidos(res.getString("apellidos"));
					result.setPromotora(res.getString("promotora"));
				}
			}
		}
		return result;
	}

	@Override
	public Matchmaker save(Matchmaker entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Matchmaker entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	

}
