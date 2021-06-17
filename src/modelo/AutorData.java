
package modelo;

import entidades.Autor;
import entidades.Lector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
            JOptionPane.showMessageDialog(null, "Su autor ha sido agregado!");//Este mensaje deberia aparecer despues de que se settea el id al autor.     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error a la hora de registrar autor!");
        }    
    }  //FUNCIONA
    
    public void modificarAutor(Autor autor){
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
    
    public Autor buscarLector(String nombre,String apellido){//Cambiar el nombre del metodo a buscarAutor.
        String query="SELECT * FROM autor WHERE apellido_autor LIKE ? AND nombre_autor LIKE ?";
        Autor autor=new Autor();
        try{
            PreparedStatement ps=con.prepareStatement(query);
          
            ps.setString(1,apellido);
            ps.setString(2,nombre);
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                autor.setId_autor(rs.getInt(1));
                autor.setNombreAutor(rs.getString(2));
                autor.setApellidoAutor(rs.getString(3));
                autor.setDni(rs.getInt(4));
                autor.setFecha_nac(LocalDate.parse(rs.getString(5)));
                autor.setNacionalidad(rs.getString(6));

            }
            
            ps.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "error al buscar el autor");
        }
        return autor;
    }//funciono solo usando los numero en los get del resulset
    
    public List<Autor> getAll(){
        List<Autor> l_autores=new ArrayList();
        String query="SELECT * FROM autor";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                Autor autor=new Autor();
                autor.setId_autor(rs.getInt(1));
                autor.setNombreAutor(rs.getString(2));
                autor.setApellidoAutor(rs.getString(3));
                autor.setDni(rs.getInt(4));
                autor.setFecha_nac(LocalDate.parse(rs.getString(5)));
                autor.setNacionalidad(rs.getString(6));
                l_autores.add(autor);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"No se consiguio la lista de Autores");
        }
        return l_autores;
    }//retorna todos los autores dentro de la tabla autor, funciona
    
    public Autor buscarAutorUnico(int id_autor){
        Autor autor = null;
        String sql = "SELECT * FROM autor WHERE id_autor = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_autor);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                autor = new Autor();
                autor.setId_autor(rs.getInt(1));
                autor.setNombreAutor(rs.getString(2));
                autor.setApellidoAutor(rs.getString(3));
                autor.setDni(rs.getInt(4));
                autor.setFecha_nac(LocalDate.parse(rs.getString(5)));
                autor.setNacionalidad(rs.getString(6));
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en buscar autor unico!" + ex.getMessage());
        }
        return autor;
    }
}
