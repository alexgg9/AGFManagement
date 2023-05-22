package AGFPromotions.ManagementFights.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AGFPromotions.ManagementFights.model.connection.ConnectionMySQL;
import AGFPromotions.ManagementFights.model.domain.Evento;
import AGFPromotions.ManagementFights.model.domain.Matchmaker;
import AGFPromotions.ManagementFights.model.domain.Peleador;
import AGFPromotions.ManagementFights.model.enums.Modalidad;


public class EventoDAO implements DAO<Evento>{
	
	private final static String FINDALL = "SELECT * FROM evento LIMIT 15";
	private final static String FINDBYID = "SELECT * from evento WHERE ID_evento=?";
	private final static String INSERT = "INSERT INTO evento (ID_evento,nombre,recinto,ciudad,pais,modalidad,fecha,dni_peleador1,dni_peleador2,dni_matchmaker) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final static String UPDATE = "UPDATE evento SET nombre=?, recinto=?, ciudad=?, pais=?, modalidad=?, fecha=?, dni_peleador1=? , dni_peleador2=?  WHERE ID_evento=?";
	private final static String DELETE= "DELETE FROM evento WHERE ID_evento = ?";
	
	
	private Connection conn;
	
	public EventoDAO(Connection conn) {
		this.conn = conn;
	}
	public EventoDAO() {
		this.conn=ConnectionMySQL.getConnect();
	}
	
	@Override
	public void close() throws Exception {
		conn.close();
		
	}
	/**
	 * Función que devuelde todos los Eventos de la base de datos
	 */
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
					e.setModalidad(Modalidad.valueOf(res.getString("modalidad")));
					e.setFecha(res.getDate("fecha"));
					PeleadorDAO pdao = new PeleadorDAO(this.conn);
					Peleador p1 = pdao.findByDni(res.getString("dni_peleador1"));
					e.setF1(p1);
					Peleador p2 = pdao.findByDni(res.getString("dni_peleador2"));
					e.setF2(p2);
					MatchmakerDAO mdao = new MatchmakerDAO(this.conn);
					Matchmaker m1 = mdao.findByDni(res.getString("dni_matchmaker"));
					e.setM1(m1);
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
					e.setModalidad(Modalidad.valueOf(res.getString("modalidad")));
					e.setFecha(res.getDate("fecha"));
					PeleadorDAO pdao = new PeleadorDAO(this.conn);
					Peleador p1 = pdao.findByDni(res.getString("dni_peleador1"));
					e.setF1(p1);
					Peleador p2 = pdao.findByDni(res.getString("dni_peleador2"));
					e.setF2(p2);
					MatchmakerDAO mdao = new MatchmakerDAO(this.conn);
					Matchmaker m1 = mdao.findByDni(res.getString("dni_matchmaker"));
					e.setM1(m1);
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
			if(e == null) {
				try(PreparedStatement pst=this.conn.prepareStatement(INSERT)){
					pst.setInt(1, entity.getId_evento());
					pst.setString(2, entity.getNombre());
					pst.setString(3, entity.getRecinto());
					pst.setString(4, entity.getCiudad());
					pst.setString(5, entity.getPais());
					pst.setString(6, entity.getModalidad().name());
					pst.setDate(7, (Date) entity.getFecha());
					pst.setString(8, entity.getF1().getDni());
					pst.setString(9, entity.getF2().getDni());
					pst.setString(10, entity.getM1().getDni());
					pst.executeUpdate();
				}
			}else {
				try(PreparedStatement pst=this.conn.prepareStatement(UPDATE)){
					pst.setString(1, entity.getNombre());
					pst.setString(2, entity.getRecinto());
					pst.setString(3, entity.getCiudad());
					pst.setString(4, entity.getPais());
					pst.setString(5, entity.getModalidad().name());
					pst.setDate(6, (Date) entity.getFecha());
					pst.setString(7, entity.getF1().getDni());
					pst.setString(8, entity.getF2().getDni());
					pst.setInt(9, entity.getId_evento());
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
				pst.setInt(1, entity.getId_evento());
				pst.executeUpdate();			}
		}
		
	}
	@Override
	public Evento findByDni(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Matchmaker findByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
