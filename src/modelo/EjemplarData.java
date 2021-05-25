
package modelo;

import entidades.Ejemplar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EjemplarData {
    private Connection con;   

    public EjemplarData (Conexion c){
        con = c.getConnection();
    }
    
    public void agregarEjemplares (Ejemplar ejemplar, int cant){
        String sql = "INSERT INTO ejemplar (id_libro,estado) VALUES (?,?)";
       
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            for(int i = 0; i < cant ; i++){
                ps.setInt(1, ejemplar.getLibro().getId_libro());
                ps.setString(2, "Disponible");
                
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                
                if (rs.next()){
                    ejemplar.setId_ejemplar(rs.getInt(1));
                }
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(EjemplarData.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }  //FUNCIONA
    
    public void actualizarEjemplar (Ejemplar ejemplar){
        String sql = "UPDATE ejemplar SET estado = ?,id_libro = ? WHERE id_ejemplar=?";
        
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, ejemplar.getEstado());
            ps.setInt(2, ejemplar.getLibro().getId_libro());
            ps.setInt(3, ejemplar.getId_ejemplar());
        
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Se ha actualizado su ejemplar!");
        } catch (SQLException ex) {
            Logger.getLogger(EjemplarData.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void actualizarEstado (Ejemplar ejemplar,String estado){
        String sql = "UPDATE ejemplar SET estado =? WHERE id_ejemplar=?";
        
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, estado);
            ps.setInt(2, ejemplar.getId_ejemplar());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }        
    }  //FUNCIONA
    
    public boolean ejemplarDisponible (int id_ejemplar) {
        String sql = "SELECT estado FROM ejemplar WHERE ejemplar.id_ejemplar = ?";
        boolean est = false;
        String aux;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt (1, id_ejemplar);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            aux = rs.getString(1);
            
            if (aux.equalsIgnoreCase("Disponible")){
                est = true;
            }
                      
        } catch (SQLException ex) {
            Logger.getLogger(EjemplarData.class.getName()).log(Level.SEVERE, null, ex);
        }
      return est;    
    }  //FUNCIONA
    
}
