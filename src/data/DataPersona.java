package data;

import java.util.ArrayList;
import java.sql.*;
import entidades.Persona;

public class DataPersona {
    public ArrayList<Persona> getAll(){
        ArrayList<Persona> pers=new ArrayList<Persona>();
        try {
        Statement stmt = FactoryConexion.getInstancia().getConn().createStatement();
        ResultSet rs = stmt.executeQuery("select * from persona");
        if(rs!=null){
        	// rs conjunto fila y columnas, rs.next mueve fila; ResultSet conjunto de resultados
        	// ORM herramientas que lo usan solo -> hybernate(mas usado de Java)
            while(rs.next()){
                Persona p = new Persona();
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setDni(rs.getString("dni"));
                p.setHabilitado(rs.getBoolean("habilitado"));
                pers.add(p);
            }
        }
        } catch(SQLException e){
            e.printStackTrace();
        }
        FactoryConexion.getInstancia().releaseConn();
        return pers;
    }
        
        public Persona getByDni(Persona per){
        	Persona p = null;
        	PreparedStatement stmt = null;
        	ResultSet rs= null;
        	try {
        		//el ?  para detectar  ....; si pone ' agrega \' para no concatenar
        	stmt= FactoryConexion.getInstancia().getConn().prepareStatement("select nombre, apellido, dni, habilitado from persona where dni=?");
            stmt.setString(1, per.getDni());
            rs = stmt.executeQuery();
            if (rs!=null && rs.next()){
            		p=new Persona();
            		p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setDni(rs.getString("dni"));
                    p.setHabilitado(rs.getBoolean("habilitado"));
            	}
        	} catch (SQLException e){
        		e.printStackTrace();
        	}
        	
        	try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	return p;
        }
    }
