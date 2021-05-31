
package modelo;

import entidades.Lector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LectorData {
    private Connection con;
    
    public LectorData(Conexion c){
        con = c.getConnection();
    }
    
    public void agregarLector (Lector lector){
        String sql = "INSERT INTO lector (nombre_lector,apellido_lector,dni_lector,dire_lector) VALUES (?,?,?,?)";   
    
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                  
            ps.setString(1, lector.getNombreLector());
            ps.setString(2, lector.getApellidoLector());
            ps.setInt(3, lector.getDniLector());
            ps.setString(4, lector.getDireLector());
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"Se ha agregado su lector!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar lector!");
        } 
    }  //FUNCIONA
    
    public void actualizarLector (Lector lector){
        String sql = "UPDATE lector SET nombre_lector=?,apellido_lector=?,dni_lector=?,dire_lector=? WHERE id_lector=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                  
            ps.setString(1, lector.getNombreLector());
            ps.setString(2, lector.getApellidoLector());
            ps.setInt(3, lector.getDniLector());
            ps.setString(4, lector.getDireLector());
            ps.setInt(5, lector.getId_lector());
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Se ha actualizado su Lector");
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar lector!");
        } 
    }   //FUNCIONA
    
    public Lector buscarLector(int id_lector){
        String query="SELECT * FROM lector WHERE id_lector=?";
        Lector lector=new Lector();
        try{
            PreparedStatement ps=con.prepareStatement(query);
            
            ps.setInt(1,id_lector);
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                lector.setId_lector(rs.getInt("id_lector"));
                lector.setNombreLector(rs.getString("nombre_lector"));
                lector.setApellidoLector(rs.getString("apellido_lector"));
                lector.setDniLector(rs.getInt("dni_lector"));
                lector.setDireLector(rs.getString("dire_lector"));
                lector.setEstado_lector(rs.getBoolean("estado_lector"));
            }
            
            ps.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "error al buscar lector");
        }
        return lector;
    }//funciona
    
    public void cambiarEstadoLector(int id_lector, boolean estado){
        String query="UPDATE lector SET estado_lector=? WHERE id_lector=?";
        try{
            PreparedStatement ps=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, estado);
            ps.setInt(2, id_lector);
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "error al buscar lector");
        }
        
    }//funciona 
}
