package AGFPromotions.ManagementFights.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import AGFPromotions.ManagementFights.model.connection.ConnectionMySQL;
import AGFPromotions.ManagementFights.model.domain.Matchmaker;

import java.util.ArrayList;

public class MatchmakerDAO implements DAO<Matchmaker>{
	
	private final static String FINDALL = "SELECT * FROM matchmaker LIMIT 15";
	private final static String FINDBYID = "SELECT * from matchmaker WHERE dni_matchmaker=?";
	private final static String FINDBYUSERNAME = "SELECT * from matchmaker WHERE usuario=?";
	private final static String INSERT = "INSERT INTO matchmaker (dni_matchmaker,nombre,apellidos,promotora,usuario,contraseña) VALUES (?,?,?,?,?,?)";
	private final static String DELETE= "DELETE FROM matchmaker WHERE dni_matchmaker = ?";
	private final static String UPDATE = "UPDATE matchmaker SET nombre=?, apellidos=?, promotora=?, usuario=?, contraseña=? WHERE dni_matchmaker=?";
	
	private Connection conn;
	
	public MatchmakerDAO(Connection conn) {
		this.conn = conn;
	}
	public MatchmakerDAO() {
		this.conn=ConnectionMySQL.getConnect();
	}
	
	
	@Override
	public void close() throws Exception {
		conn.close();
		
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
					m.setUsuario(res.getString("usuario"));
					result.add(m);
				}
			}
			
		}
		return result;
	}

	@Override
	public Matchmaker findByDni(String id) throws SQLException {
		Matchmaker result = null;
		try(PreparedStatement pst=this.conn.prepareStatement(FINDBYID)){
			pst.setString(1, id);
			try(ResultSet res = pst.executeQuery()){
				if(res.next()) {
					result = new Matchmaker();
					result.setDni(res.getString("dni_matchmaker"));
					result.setNombre(res.getString("nombre"));
					result.setApellidos(res.getString("apellidos"));
					result.setPromotora(res.getString("promotora"));
					result.setUsuario(res.getString("usuario"));
				}
			}
		}
		return result;
	}
	
	@Override
	public Matchmaker findByUsername(String username) throws SQLException {
		Matchmaker result = null;
		try(PreparedStatement pst=this.conn.prepareStatement(FINDBYUSERNAME)){
			pst.setString(1, username);
			try(ResultSet res = pst.executeQuery()){
				if(res.next()) {
					result = new Matchmaker();
					result.setDni(res.getString("dni_matchmaker"));
					result.setNombre(res.getString("nombre"));
					result.setApellidos(res.getString("apellidos"));
					result.setPromotora(res.getString("promotora"));
					result.setUsuario(res.getString("usuario"));
				}
			}
		}
		return result;
	}

	@Override
	public Matchmaker save(Matchmaker entity) throws SQLException {
		Matchmaker result = new Matchmaker();
		
		if(entity!=null) {
			
			Matchmaker m = findByDni(entity.getDni());
			Matchmaker mUsername = findByUsername(entity.getUsuario());
			if(m == null && mUsername == null) {
				try(PreparedStatement pst=this.conn.prepareStatement(INSERT)){
					pst.setString(1, entity.getDni());
					pst.setString(2, entity.getNombre());
					pst.setString(3, entity.getApellidos());
					pst.setString(4, entity.getPromotora());
					pst.setString(5, entity.getUsuario());
					pst.setString(6, entity.getPassword());
					pst.executeUpdate();
				}
			}else {
				
				try(PreparedStatement pst=this.conn.prepareStatement(UPDATE)){
					pst.setString(1, entity.getNombre());
					pst.setString(2, entity.getApellidos());
					pst.setString(3, entity.getPromotora());
					pst.setString(4, entity.getUsuario());
					pst.setString(5, entity.getPassword());
					pst.setString(6, entity.getDni());
					pst.executeUpdate();
				}
			}
		}
		return result;
	}

	@Override
	public void delete(Matchmaker entity) throws SQLException {
		if (entity != null) {
	        try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
	            pst.setString(1, entity.getDni());
	            pst.executeUpdate();
	        }
		}
		
	}

	public String checkLogin(String usuario, String password) throws SQLException {
		String query = "SELECT dni_matchmaker FROM matchmaker WHERE usuario = ? AND contraseña = ?";
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setString(1, usuario);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getString("dni_matchmaker");
			}
		}
		return null;
	}

}
