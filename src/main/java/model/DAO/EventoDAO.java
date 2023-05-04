package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.ConnectionMySQL;
import model.domain.Evento;
import model.domain.Peleador;


public class EventoDAO implements DAO<Evento>{
	
	private final static String FINDALL = "SELECT * FROM evento";
	private final static String FINDBYID = "SELECT * from evento WHERE ID_evento=?";
	private final static String INSERT = "INSERT INTO evento (ID_evento,nombre,recinto,ciudad,pais,modalidad,fecha) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final static String UPDATE = "UPDATE evento SET ID_evento=?, nombre=?, recinto=?, ciudad=?, pais=?, modalidad=?, fecha=? WHERE dni=?";
	private final static String DELETE= "DELETE FROM peleador WHERE ID_evento = ?";
	
	
	private Connection conn;
	
	public EventoDAO(Connection conn) {
		this.conn = conn;
	}
	public EventoDAO() {
		this.conn=ConnectionMySQL.getConnect();
	}
	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List findAll() throws SQLException {
		List<Evento> result = new ArrayList();
		try(PreparedStatement pst=this.conn.prepareStatement(FINDALL)){
			try(ResultSet res = pst.executeQuery()){
				while(res.next()) {
					Evento e = new Evento();
					e.setId_evento(res.getInt("ID_evento"));
					e.setNombre(res.getString("nombre"));
					e.setRecinto(res.getString("recinto"));
					e.setCiudad(res.getString("ciudad"));
					e.setPais(res.getString("pais"));
					e.setModalidad(res.getString("modalidad"));
					e.setFecha(res.getDate("fecha"));
					PeleadorDAO pdao = new PeleadorDAO(this.conn);
					Peleador p1 = pdao.findByDni(res.getString("dni_peleador1"));
					e.setF1(p1);
					Peleador p2 = pdao.findByDni(res.getString("dni_peleador1"));
					e.setF1(p2);
					result.add(e);
				}
			}
		}
		return result;
	}
	
	public Evento findById(int id) throws SQLException {
		Evento result = null;
		try(PreparedStatement pst=this.conn.prepareStatement(FINDBYID)){
			pst.setInt(1, id);
			try(ResultSet res = pst.executeQuery()){
				if(res.next()) {
					Evento e = new Evento();
					e.setId_evento(res.getInt("ID_evento"));
					e.setNombre(res.getString("nombre"));
					e.setRecinto(res.getString("recinto"));
					e.setCiudad(res.getString("ciudad"));
					e.setPais(res.getString("pais"));
					e.setModalidad(res.getString("modalidad"));
					e.setFecha(res.getDate("fecha"));
					PeleadorDAO pdao = new PeleadorDAO(this.conn);
					Peleador p1 = pdao.findByDni(res.getString("dni_peleador1"));
					e.setF1(p1);
					Peleador p2 = pdao.findByDni(res.getString("dni_peleador2"));
					e.setF1(p2);
					result = e;
				}
			}
		}
		return result;
	}

	@Override
	public Evento save(Evento entity) throws SQLException {
		Evento result = new Evento();
		if(entity!=null) {
			Evento e = findById(entity.getId_evento());
			PeleadorDAO pdao = new PeleadorDAO(this.conn);
			Peleador myF = pdao.findByDni(entity.getF1().getDni());
			Peleador myF2 = pdao.findByDni(entity.getF2().getDni());
			if(e == null) {
				if(myF == null && myF2 == null)
					pdao.save(entity.getF1());
				try(PreparedStatement pst=this.conn.prepareStatement(INSERT)){
					pst.setInt(1, entity.getId_evento());
					pst.setString(2, entity.getNombre());
					pst.setString(3, entity.getRecinto());
					pst.setString(4, entity.getCiudad());
					pst.setString(5, entity.getPais());
					pst.setString(6, entity.getModalidad());
					pst.setDate(7, (Date) entity.getFecha());
					pst.setString(8, entity.getF1().getDni());
					pst.setString(9, entity.getF2().getDni());
					pst.executeUpdate();
				}
			}else {
				if(myF == null && myF2 == null)
					pdao.save(entity.getF1());
				try(PreparedStatement pst=this.conn.prepareStatement(UPDATE)){
					pst.setInt(1, entity.getId_evento());
					pst.setString(2, entity.getNombre());
					pst.setString(3, entity.getRecinto());
					pst.setString(4, entity.getCiudad());
					pst.setString(5, entity.getPais());
					pst.setString(6, entity.getModalidad());
					pst.setDate(6, (Date) entity.getFecha());
					pst.executeUpdate();				
				}
			}
		}
		return result;
	}

	@Override
	public void delete(Evento entity) throws SQLException {
		if(entity!=null){
			try(PreparedStatement pst=this.conn.prepareStatement(DELETE)){
				pst.setInt(0, entity.getId_evento());
				pst.executeUpdate();			}
		}
		
	}
	@Override
	public Evento findByDni(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
