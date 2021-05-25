
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    private final String base = "biblio_final"; //Nombre de mi base de datos
    private final String url = "jdbc:mysql://localhost:3306/"+base ; //URL donde esta mi base de datos //"driver jdbc que se usa":mysql// etc.
    private final String user = "root";  //Nombre de usuario (default)
    private final String pass = ""; //Contraseña de usuario (default)
    private Connection con;  // 1° Paso: Declaro variable conexion
    
    public Connection getConnection () {
        if(con == null){   //Si con esta nulo, cargo los driver jdbc 
            try{
               Class.forName("org.mariadb.jdbc.Driver"); //2° Paso: Declaro driver que se va a usar
               con = (Connection) DriverManager.getConnection(url, user, pass); //3° Paso: Establezco Conexion
            }
            
            catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "Error de drivers de conexion"); 
            }
            catch(SQLException e){      
                JOptionPane.showMessageDialog(null, "Error de conexion"); //Si no establece conexion larga excepcion              
            }       
                  
        }
       return con; 
    }




}
