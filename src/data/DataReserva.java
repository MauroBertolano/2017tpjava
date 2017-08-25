package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Elemento;
import entidades.Reserva;
import entidades.TipoElemento;



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
					"insert into reserva(hora,fecha,detalle,idElemento) values (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, r.getHora());
			stmt.setDate(2, r.getFecha());
			stmt.setString(3, r.getDetalle());
			stmt.setInt(4, r.getElemento().getId());
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
    					"delete from reserva where id=?"
    							
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
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select e.idElemento,e.nombreElemento,tp.cantMax from elemento e inner join tipoelemento tp on e.id=tp.id where e.id=? and e.idElemento not in (select e.idElemento from reserva r inner join elemento e on r.idElemento=e.idElemento where fecha=? and hora=? );");
			stmt.setInt(1,res.getElemento().getTipo().getId());
			stmt.setDate(2,res.getFecha());
			stmt.setInt(3,res.getHora());
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
	
}
   