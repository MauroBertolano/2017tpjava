package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entidades.Elemento;
import entidades.Reserva;
import entidades.TipoElemento;
import util.AppDataException;



public class DataReserva {
	
	/*public Reserva getById(Reserva r)throws Exception {
		Reserva res = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select idReserva, fecha, hora, detalle, idElemento from reserva r inner join elemento e on r.idElemento=e.idElemento where idReserva=?");
			stmt.setInt(1, r.getId());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				res = new Reserva();
				res.setId(rs.getInt("idElemento"));
				res.setNombre(rs.getString("nombreElemento"));
				res.setTipo(new TipoElemento());
				res.getTipo().setNombre(rs.getString("nombre"));
				res.getTipo().setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			throw e;
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}*/

	public void add(Reserva r) throws Exception {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into reserva(horaDesde,horaHasta,fecha,detalle,idElemento,idPersona) values (?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, r.getHoraDesde());
			stmt.setInt(2, r.getHoraHasta());
			stmt.setDate(3, r.getFecha());
			stmt.setString(4, r.getDetalle());
			stmt.setInt(5, r.getElemento().getId());
			stmt.setInt(6, CuentaLogeada.getUsuario().getId() );
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				r.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException /*| AppDataException*/ e) {			
			throw e;
		}
		try {
			if (keyResultSet != null)
				keyResultSet.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Reserva r)throws Exception{
    		PreparedStatement stmt=null;
    		try {
    			stmt=FactoryConexion.getInstancia().getConn()
    					.prepareStatement(
    					"delete from reserva where idReserva=?"    							
    					);
    			stmt.setInt(1, r.getId());
    			stmt.executeUpdate();   	
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		try {
    			if (stmt != null)
    				stmt.close();
    			FactoryConexion.getInstancia().releaseConn();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		}
	public ArrayList<Elemento> getDisponibles(Reserva res) throws Exception {
		ArrayList<Elemento> eles = new ArrayList<Elemento>();
		Elemento ele = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select e.idElemento,e.nombreElemento,tp.cantMax from elemento e inner join tipoelemento tp on e.id=tp.id where e.id=? "
					+ "and e.idElemento not in (select e.idElemento from reserva r inner join elemento e on r.idElemento=e.idElemento "
					+ "where r.fecha=? and ((? between r.horaDesde and r.horaHasta) or (? between r.horaDesde and r.horaHasta) or (?<r.horaDesde and ?>r.horaHasta)));");
			stmt.setInt(1,res.getElemento().getTipo().getId());
			stmt.setDate(2,res.getFecha());
			stmt.setInt(3,res.getHoraDesde());
			stmt.setInt(4,res.getHoraHasta());
			stmt.setInt(5,res.getHoraDesde());
			stmt.setInt(6,res.getHoraHasta());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ele = new Elemento();
					ele.setTipo(new TipoElemento());
					ele.setId(rs.getInt("e.idElemento"));
					ele.setNombre(rs.getString("e.nombreElemento"));
					ele.getTipo().setCantMax(rs.getInt("tp.cantMax"));
					eles.add(ele);
				}
			}
			if(eles.isEmpty()){
			throw new AppDataException("No hay elementos disponibles");}
		} catch (SQLException e) {
			throw e;
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eles;
	}

	public ArrayList<Reserva> getAll()throws Exception {
		ArrayList<Reserva> res = new ArrayList<Reserva>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String fecha = sdf.format(date);
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select r.idReserva,r.fecha,r.horaDesde,r.horaHasta,e.idElemento,e.nombreElemento,tp.nombre,r.detalle "
					+ "from reserva r inner join elemento e on r.idElemento=e.idElemento inner join tipoelemento tp on tp.id=e.id where r.fecha>=current_date and r.idPersona=?;");
//			stmt.setString(1, fecha);
			stmt.setInt(1, CuentaLogeada.getUsuario().getId());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Reserva r = new Reserva();
					r.setElemento(new Elemento());
					r.getElemento().setTipo(new TipoElemento());
					r.setId(rs.getInt("r.idReserva"));
					r.setFecha(rs.getDate("r.fecha"));
					r.setHoraDesde(rs.getInt("r.horaDesde"));	
					r.setHoraHasta(rs.getInt("r.horaHasta"));	
					r.getElemento().setId(rs.getInt("e.idElemento"));
					r.getElemento().setNombre(rs.getString("e.nombreElemento"));
					r.getElemento().getTipo().setNombre(rs.getString("tp.nombre"));
					r.setDetalle(rs.getString("r.detalle"));
					res.add(r);
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
   