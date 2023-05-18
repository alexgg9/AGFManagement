package AGFPromotions.ManagementFights.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AGFPromotions.ManagementFights.model.connection.ConnectionMySQL;
import AGFPromotions.ManagementFights.model.domain.Matchmaker;
import AGFPromotions.ManagementFights.model.domain.Peleador;
import AGFPromotions.ManagementFights.model.enums.Background;
import AGFPromotions.ManagementFights.model.enums.Genero;

public class PeleadorDAO implements DAO<Peleador>{

	private final static String FINDALL = "SELECT * FROM peleador";
	private final static String FINDBYID = "SELECT * from peleador WHERE dni=?";
	private final static String INSERT = "INSERT INTO peleador (dni,nombre,apellidos,edad,genero,peso,altura,record,pais,background) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final static String UPDATE = "UPDATE peleador SET nombre=?,apellidos=?,edad=?,genero=?,peso=?,altura=?,record=?,pais=?,background=? WHERE dni=?";
	private final static String DELETE= "DELETE FROM peleador WHERE dni = ?";
	
	private Connection conn;
	
	public PeleadorDAO(Connection conn) {
		this.conn = conn;
	}
	public PeleadorDAO() {
		this.conn=ConnectionMySQL.getConnect();
	}

	
	public void close() throws Exception {
		conn.close();
		
	}

	public List<Peleador> findAll() throws SQLException {
		List<Peleador> result = new ArrayList<Peleador>();
		try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
			try(ResultSet res = pst.executeQuery()){
				while(res.next()) {
					Peleador p = new Peleador();
					p.setDni(res.getString("dni"));
					p.setNombre(res.getString("nombre"));
					p.setApellidos(res.getString("apellidos"));
					p.setEdad(res.getInt("edad"));
					p.setGenero(Genero.valueOf(res.getString("genero")));
					p.setPeso(res.getInt("peso"));
					p.setAltura(res.getInt("altura"));
					p.setPais(res.getString("pais"));
					p.setRecord(res.getString("record"));
					p.setBackground(Background.valueOf(res.getString("background")));
					result.add(p);
				}
			}
		}
		return result;
	}

	public Peleador findByDni(String id) throws SQLException {
		Peleador result = null;
		try(PreparedStatement pst=this.conn.prepareStatement(FINDBYID)){
			pst.setString(1, id);
			try(ResultSet res = pst.executeQuery()){
				if(res.next()) {
					result = new Peleador();
					result.setDni(res.getString("dni"));
					result.setNombre(res.getString("nombre"));
					result.setApellidos(res.getString("apellidos"));
					result.setEdad(res.getInt("edad"));
					result.setGenero(Genero.valueOf(res.getString("genero")));
					result.setPeso(res.getInt("peso"));
					result.setAltura(res.getInt("altura"));
					result.setPais(res.getString("pais"));
					result.setBackground(Background.valueOf(res.getString("background")));
				}
			}
		}
		return result;
	}

	public Peleador save(Peleador entity) throws SQLException {
		Peleador result = new Peleador();
		if(entity!=null) {
			Peleador p = findByDni(entity.getDni());
			if(p == null) {
				
				try(PreparedStatement pst=this.conn.prepareStatement(INSERT)){
					pst.setString(1, entity.getDni());
					pst.setString(2, entity.getNombre());
					pst.setString(3, entity.getApellidos());
					pst.setInt(4, entity.getEdad());
					pst.setString(5, entity.getGenero().name());
					pst.setInt(6, entity.getPeso());
					pst.setInt(7, entity.getAltura());
					pst.setString(8, entity.getRecord());
					pst.setString(9, entity.getPais());
					pst.setString(10, entity.getBackground().name());
					pst.executeUpdate();
					
				}	
				
			}else {
				
				try(PreparedStatement pst=this.conn.prepareStatement(UPDATE)){
					pst.setString(1, entity.getNombre());
					pst.setString(2, entity.getApellidos());
					pst.setInt(3, entity.getEdad());
					pst.setString(4, entity.getGenero().name());
					pst.setInt(5, entity.getPeso());
					pst.setInt(6, entity.getAltura());
					pst.setString(7, entity.getRecord());
					pst.setString(8, entity.getPais());
					pst.setString(9, entity.getBackground().name());
					pst.setString(10, entity.getDni());
					pst.executeUpdate();
				}
			}
		}
		return result;
	}

	public void delete(Peleador entity) throws SQLException {
		if (entity != null) {
	        try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
	            pst.setString(1, entity.getDni());
	            pst.executeUpdate();
	        }
		}
	}
	@Override
	public Matchmaker findByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
