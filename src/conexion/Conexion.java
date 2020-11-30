package conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexion {
    public Connection connect(){
        Connection conn = null;
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:D:\\ZIROJAVA-SegundoCiclo\\Examen Unidad 2\\DataBasesZ\\DataBases.db";
            if(conn == null){
                conn = DriverManager.getConnection(url);
                System.out.println("> La conexión se realizo correctamente...100%");
            }else{
                System.out.println("Err: Ya existe una conexión");
            }
        }catch (SQLException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error en la conexión" +e.getMessage());
        }
        return conn ;
    }
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}