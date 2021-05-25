
package modelo;

import entidades.Autor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AutorData {
    private Connection con;
    
    public AutorData(Conexion c){
        con = c.getConnection();
    }
    
    public void agregarAutor (Autor autor){  
        String sql = "INSERT INTO autor (nombre_autor,apellido_autor,dni_autor,fech_nac,nacionalidad) VALUES(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, autor.getNombreAutor());
            ps.setString(2, autor.getApellidoAutor());
            ps.setInt(3, autor.getDni());
            ps.setDate(4, Date.valueOf(autor.getFecha_nac()));
            ps.setString(5, autor.getNacionalidad());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                autor.setId_autor(rs.getInt(1));
            }
            else{
                JOptionPane.showMessageDialog(null, "No se pudo obtener el id de su autor");
            }
            
            ps.close();
            JOptionPane.showMessageDialog(null, "Su autor ha sido agregado!");          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error a la hora de registrar autor!");
        }    
    }  //FUNCIONA
    
    public void modificarLector(Autor autor){
        String sql = "UPDATE autor SET nombre_autor=?,apellido_autor=?,dni_autor=?,fech_nac=?,nacionalidad=? WHERE id_autor=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);        
     
            ps.setString(1, autor.getNombreAutor());
            ps.setString(2, autor.getApellidoAutor());
            ps.setInt(3, autor.getDni());
            ps.setDate(4, Date.valueOf(autor.getFecha_nac()));
            ps.setString(5, autor.getNacionalidad());
            ps.setInt(6, autor.getId_autor());

            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Su Autor ha sido modificado!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error a la hora de modificar autor!");
        } 
    }  //FUNCIONA
    
    
    
}
