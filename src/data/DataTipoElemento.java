package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.TipoElemento;

public class DataTipoElemento {

	public TipoElemento getByNombre(TipoElemento tipEl) throws Exception {
		TipoElemento tp = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select id, nombre, cantMax from tipoelemento where nombre=?");
			stmt.setString(1, tipEl.getNombre());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				tp = new TipoElemento();
				tp.setId(rs.getInt("id"));
				tp.setNombre(rs.getString("nombre"));
				tp.setCantMax(rs.getInt("cantMax"));
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
		return tp;
	}

	public void add(TipoElemento tp) throws Exception {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into tipoelemento(nombre,cantMax) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, tp.getNombre());
			stmt.setInt(2, tp.getCantMax());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				tp.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException /* | AppDataException */ e) {
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

	public void remove(TipoElemento tp) throws Exception {
		PreparedStatement stmt = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("delete from tipoelemento where nombre=?"

			);
			stmt.setString(1, tp.getNombre());
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

	public void modificar(TipoElemento tp) throws Exception {
		PreparedStatement stmt = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("update tipoelemento set nombre=?,cantMax=? where id=?");
			stmt.setString(1, tp.getNombre());
			stmt.setInt(2, tp.getCantMax());
			stmt.setInt(3, tp.getId());
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

	public ArrayList<TipoElemento> getAll() throws Exception {
		ArrayList<TipoElemento> tipos = new ArrayList<TipoElemento>();
		TipoElemento tp = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from tipoelemento");
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					tp = new TipoElemento();
					tp.setId(rs.getInt("id"));
					tp.setNombre(rs.getString("nombre"));
					tp.setCantMax(rs.getInt("cantMax"));
					tipos.add(tp);
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
		return tipos;
	}

}
