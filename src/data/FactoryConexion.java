package data;
import java.sql.*;
public class FactoryConexion {
       
        private static FactoryConexion instancia;
       
        private Connection conn;
       
        private FactoryConexion(){
            try {
                Class.forName("com.mysql.jdbc.Driver()");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
           
        }       
        public static FactoryConexion getInstancia(){
            if(FactoryConexion.instancia == null){
                FactoryConexion.instancia = new FactoryConexion();
            }
            return FactoryConexion.instancia;
        }
        public Connection getConn(){
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java2017?user=java&password=java");
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
			return conn;
        }
}