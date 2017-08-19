package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Elemento;
import entidades.TipoElemento;

public class DataElemento {
	
	public Elemento getByNombre(Elemento el)throws Exception {
		Elemento ele = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select idElemento, nombreElemento, nombre,id from elemento e inner join tipoelemento tp on e.idTipoElemento=tp.id where nombre=?");
			stmt.setString(1, el.getNombre());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				ele = new Elemento();
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

	public void add(Elemento ele) throws Exception {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into elemento(nombreElemento,idTipoElemento) values (?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, ele.getNombre());
			stmt.setInt(2, ele.getTipo().getId());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				ele.setId(keyResultSet.getInt(1));
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

	public void remove(Elemento ele)throws Exception{
    		PreparedStatement stmt=null;
    		try {
    			stmt=FactoryConexion.getInstancia().getConn()
    					.prepareStatement(
    					"delete from elemento where nombre=?"
    							
    					);
    			stmt.setString(1, ele.getNombre());
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

	public void modificar(Elemento ele)throws Exception {
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update elemento set nombre=?,idTipoElemento=? where id=?"							
					);
			stmt.setString(1, ele.getNombre());
			stmt.setInt(2, ele.getTipo().getId());
			stmt.executeUpdate();   	
		} catch (SQLException e) {
			throw e;
		}
		try {
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
