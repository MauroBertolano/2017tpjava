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
        return pers;
    }
}
