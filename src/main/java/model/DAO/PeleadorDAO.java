package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.ConnectionMySQL;
import model.domain.Evento;
import model.domain.Peleador;

public class PeleadorDAO implements DAO{

	private final static String FINDALL = "SELECT * FROM peleador";
	private final static String FINDBYID ="SELECT * from peleador WHERE dni=?";
	private final static String INSERT ="INSERT INTO peleador (dni,nombre,apellidos,edad,genero,peso,altura,record,pais,background) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final static String UPDATE ="UPDATE peleador SET nombre=?, apellidos=?, peso=?, record=? WHERE dni=?";
	
	private Connection conn;
	public PeleadorDAO(Connection conn) {
		this.conn = conn;
	}
	public PeleadorDAO() {
		this.conn=ConnectionMySQL.getConnect();
	}

	
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<Peleador> findAll() throws SQLException {
		List<Peleador> result = new ArrayList();
		try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
			try(ResultSet res = pst.executeQuery()){
				while(res.next()) {
					Peleador p = new Peleador();
					p.setDni(res.getString("dni"));
					p.setNombre(res.getString("nombre"));
					p.setApellidos(res.getString("apellidos"));
					p.setEdad(res.getInt("edad"));
					p.setPeso(res.getInt("peso"));
					p.setAltura(res.getInt("altura"));
					p.setPais(res.getString("pais"));
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
					result.setPeso(res.getInt("peso"));
					result.setAltura(res.getInt("altura"));
					result.setPais(res.getString("pais"));
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
					pst.setObject(5, entity.getGenero());
					pst.setInt(6, entity.getPeso());
					pst.setInt(7, entity.getAltura());
					pst.setString(8, entity.getRecord());
					pst.setString(9, entity.getPais());
					pst.setObject(10, entity.getBackgroud());
					pst.executeUpdate();
					
				}	
				
			}
		}
		
		return result;
	}

	public void delete(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object save(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
