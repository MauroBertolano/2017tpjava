package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Reserva;
import entidades.TipoElemento;



public class DataReserva {
	
	public Reserva getById(Reserva r)throws Exception {
		Elemento ele = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select idElemento, nombreElemento, nombre,id from elemento e inner join tipoelemento tp on e.idTipoElemento=tp.id where nombre=?");
			stmt.setString(1, r.getId());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				ele = new Reserva();
				ele.setId(rs.getInt("idElemento"));
				ele.setNombre(rs.getString("nombreElemento"));
				ele.setTipo(new TipoElemento());
				ele.getTipo().setNombre(rs.getString("nombre"));
				ele.getTipo().setId(rs.getInt("id"));
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
		return ele;
	}

	public void add(Reserva r) throws Exception {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into reserva(idTipoElemento,idElemento,fecha,hora,detalle) values (?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, r.getTipoElemento().getId());
			stmt.setInt(2, r.getElemento().getId());
			stmt.setInt(3, r.getHora());
			stmt.setDate(4, r.getFecha());
			stmt.setString(5, r.getDetalle());
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

	

	public ArrayList<Reserva> getAll()throws Exception {
		ArrayList<Reserva> res = new ArrayList<Reserva>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select e.idElemento,e.nombreElemento,tp.id,tp.nombre,tp.cantMax from elemento e inner join tipoelemento tp on e.id=tp.id");
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Reserva r = new Reserva();
					ele.setTipo(new TipoElemento());
					ele.setId(rs.getInt("e.idElemento"));
					ele.setNombre(rs.getString("e.nombreElemento"));
					ele.getTipo().setId(rs.getInt("tp.id"));	
					ele.getTipo().setNombre(rs.getString("tp.nombre"));
					ele.getTipo().setCantMax(rs.getInt("tp.cantMax"));				
					elems.add(ele);
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
		return elems;
	}
	
}
   