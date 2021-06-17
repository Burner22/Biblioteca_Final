
package modelo;

import entidades.Ejemplar;
import entidades.Libro;
import entidades.Multa;
import entidades.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
            JOptionPane.showMessageDialog(null, "Se han agregado sus ejemplares");
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar sus ejemplares");
        }      
    }  //Agrega una X cantidad de ejemplares de X libro
    
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
    }   //Actualiza el ejemplar en general
    
    public void actualizarEstado (Ejemplar ejemplar,String estado){
        String sql = "UPDATE ejemplar SET estado =? WHERE id_ejemplar=?";
        
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, estado);
            ps.setInt(2, ejemplar.getId_ejemplar());
            
            ps.executeUpdate();
            ps.close();
            //Mostrar un mensaje que notifique que el estado del ejemplar fue actualizado.
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }        
    }  //Actualiza el estado del ejemplar atraves de String
    
    public boolean ejemplarDisponible (int id_ejemplar) {
        String sql = "SELECT estado FROM ejemplar WHERE ejemplar.id_ejemplar = ?";
        boolean est = false;
        String aux;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt (1, id_ejemplar);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
              aux = rs.getString(1);  
              if (aux.equalsIgnoreCase("Disponible") || aux == null){
                est = true;
              }
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(EjemplarData.class.getName()).log(Level.SEVERE, null, ex);
        }
      return est;    
    }  //Me devuelve si el ejemplar esta disponible
    
    public void chequeoEstado (){
        String sql = "SELECT id_prestamo,fecha_prestamo from prestamos,ejemplar WHERE prestamos.id_ejemplar = ejemplar.id_ejemplar AND prestamos.estado=1 AND ejemplar.estado = 'Prestado'";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int i = (int) ChronoUnit.DAYS.between(rs.getDate(2).toLocalDate(), LocalDate.now());
                if(i > 30){
                    Conexion con = new Conexion();
                    PrestamoData pre = new PrestamoData(con);
                    Prestamo prestamo = pre.buscarPrestamo(rs.getInt(1));
                    actualizarEstado(prestamo.getEjemplar(),"Retraso");
                    Multa multa = new Multa (prestamo, LocalDate.now());
                    MultaData mul = new MultaData (con);
                    mul.agregarMulta(multa);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EjemplarData.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }   //Boton para refresar estado del ejemplar
    
    public String estadoEjemplar(int id_ejemplar){
        String sql = "SELECT estado FROM ejemplar WHERE id_ejemplar=?";
        String aux = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id_ejemplar);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                aux = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EjemplarData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }  //Devuelvo un string con el estado del ejemplar
    
    public Ejemplar buscarEjemplar(int id_ejemplar){
        Ejemplar ejemplar = null;
        String sql = "SELECT * FROM ejemplar WHERE id_ejemplar = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_ejemplar);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Conexion conex = new Conexion();
                LibroData ld = new LibroData(conex);
                ejemplar = new Ejemplar();
                ejemplar.setId_ejemplar(rs.getInt(1));
                Libro libro = ld.buscarLibroUnico(rs.getInt(2));
                ejemplar.setLibro(libro);
                ejemplar.setEstado(rs.getString(3));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en buscar ejemplar unico!" + ex.getMessage());
        }
        return ejemplar;
    }
}
