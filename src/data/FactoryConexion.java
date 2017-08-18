package data;
import java.sql.*;
public class FactoryConexion {
       
        private String driver="com.mysql.jdbc.Driver()";
        private String host="localhost";
        private String port="3306";
        private String user="root";
        private String password="";
        private String db="java2017";
        private static FactoryConexion instancia;
       
        private Connection conn;
        private int cantConn=0;
        //Singleton constructor privado y variable static privada y metodo statico para obtener unica instancia
        private FactoryConexion(){
            try {
                Class.forName(driver);
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
            	if(conn==null || conn.isClosed()){
               //parametrizado
                conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password);
            	}
            } catch (SQLException e) {
                e.printStackTrace();
            }
            cantConn++;
			return conn;
        }
        public void releaseConn(){
        	try {
        		cantConn--;
        		if (cantConn==0){
				conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
}