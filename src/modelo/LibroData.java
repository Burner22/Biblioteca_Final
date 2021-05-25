
package modelo;

import entidades.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LibroData {
    private Connection con;
    
    public LibroData(Conexion c){
        con = c.getConnection();
    }
    
    public void agregarLibro(Libro libro){
        String sql = "INSERT INTO libro (id_autor,ISBN,nombre,editorial,año,tipo) VALUES(?,?,?,?,?,?)";
        
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
            ps.setInt(1, libro.getAutor().getId_autor());
            ps.setInt(2, libro.getISBN());
            ps.setString(3, libro.getNombre());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getAño());
            ps.setString(6, libro.getTipo());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                libro.setId_libro(rs.getInt(1));
            }
            else{
                JOptionPane.showMessageDialog(null, "No se pudo obtener el id de su libro");
            }
        
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Se ha agregado su libro con exito!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar su libro!");
        } 
    }   //FUNCIONA
    
    public void actualizarLibro (Libro libro){
        String sql = "UPDATE libro SET id_autor=?,ISBN=?,nombre=?,editorial=?,año=?,tipo=? WHERE id_libro=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, libro.getAutor().getId_autor());
            ps.setInt(2, libro.getISBN());
            ps.setString(3, libro.getNombre());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getAño());
            ps.setString(6, libro.getTipo());
            ps.setInt(7, libro.getId_libro());
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Su libro ha sido actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(LibroData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }   //FUNCIONA
    
    
    
}
