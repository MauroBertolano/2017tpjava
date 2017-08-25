package data;

import java.util.ArrayList;
import java.sql.*;
import entidades.Persona;
import util.AppDataException;
import util.PersonaInvalida;

public class DataPersona {
	
	public ArrayList<Persona> getAll() {
		ArrayList<Persona> pers = new ArrayList<Persona>();
		try {
			Statement stmt = FactoryConexion.getInstancia().getConn().createStatement();
			ResultSet rs = stmt.executeQuery("select * from persona");
			if (rs != null) {
				// rs conjunto fila y columnas, rs.next mueve fila; ResultSet
				// conjunto de resultados
				// ORM herramientas que lo usan solo -> hybernate(mas usado de
				// Java)
				while (rs.next()) {
					Persona p = new Persona();
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getString("dni"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					p.setUser(rs.getString("usuario"));
					pers.add(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FactoryConexion.getInstancia().releaseConn();
		return pers;
	}

	public Persona getByDni(Persona per)throws Exception {
		Persona p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// el ? para detectar ....; si pone ' agrega \' para no concatenar
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select id, nombre, apellido, dni, habilitado,usuario from persona where dni=?");
			stmt.setString(1, per.getDni());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Persona();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setDni(rs.getString("dni"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				p.setUser(rs.getString("usuario"));
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
		return p;
	}

	public void add(Persona p) throws Exception {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into persona(dni, nombre, apellido, habilitado, usuario, contraseña) values (?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, p.getDni());
			stmt.setString(2, p.getNombre());
			stmt.setString(3, p.getApellido());
			stmt.setBoolean(4, p.getHabilitado());
			stmt.setString(5, p.getUser());
			stmt.setString(6, p.getPsw());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				p.setId(keyResultSet.getInt(1));
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

	public void remove(Persona p)throws Exception{
    		PreparedStatement stmt=null;
    		try {
    			stmt=FactoryConexion.getInstancia().getConn()
    					.prepareStatement(
    					"delete from persona where dni=?"						
    					);
    			stmt.setString(1, p.getDni());
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

	public void modificar(Persona p)throws Exception {
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update persona set dni=?,nombre=?,apellido=?,habilitado=?,usuario=?,contraseña=? where id=?"							
					);
			stmt.setString(1, p.getDni());
			stmt.setString(2, p.getNombre());
			stmt.setString(3, p.getApellido());
			stmt.setBoolean(4, p.getHabilitado());
			stmt.setString(5, p.getUser());
			stmt.setString(6, p.getPsw());
			stmt.setInt(7, p.getId());
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

	public Persona getByUsuario(Persona per)throws Exception {
		Persona p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select contraseña from persona where usuario=?");
			stmt.setString(1, per.getUser());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Persona();
				p.setPsw(rs.getString("contraseña"));
			}else{
				throw new PersonaInvalida("Usuario no existe");
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
		return p;
	}
	
}
    		
