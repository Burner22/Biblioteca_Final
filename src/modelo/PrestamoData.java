
package modelo;

import entidades.Ejemplar;
import entidades.Lector;
import entidades.Multa;
import entidades.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class PrestamoData {
    private Connection con;
    
    public PrestamoData (Conexion c){
        con = c.getConnection();
    }
    
    public void registrarPrestamo (Prestamo prestamo){
        boolean mult = buscarMulta(prestamo.getLector().getId_lector());
        boolean prest = prestados(prestamo.getLector().getId_lector());
        boolean dispo = ejemDisponible(prestamo.getEjemplar().getId_ejemplar());      
        boolean saldado = prestamoXFecha(prestamo.getLector().getId_lector());
        
        try {
            if(mult && prest && dispo && saldado){
                String sql = "INSERT INTO prestamos (id_lector,id_ejemplar,estado,fecha_prestamo) VALUES (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, prestamo.getLector().getId_lector());
                ps.setInt(2, prestamo.getEjemplar().getId_ejemplar());
                ps.setBoolean(3, prestamo.getEstado());
                ps.setDate(4, Date.valueOf(prestamo.getFecha_prestamo()));
                ps.executeUpdate();
                ps.close();
                
                Conexion con = new Conexion();
                EjemplarData eje = new EjemplarData (con);
                eje.actualizarEstado(prestamo.getEjemplar(),"Prestado");
          
                JOptionPane.showMessageDialog(null, "Su prestamo se ha registrado!");
            }
            else{
                if (!saldado){
                   JOptionPane.showMessageDialog(null, "Usted tiene multas pendientes");
                }
                else if (!dispo){
                    JOptionPane.showMessageDialog(null, "El ejemplar ha sido prestado!");
                }
                else if (!prest && !mult){
                    JOptionPane.showMessageDialog(null, "Usted adeuda libros y se encuentra con multas!");
                }
                else if (!prest){
                    JOptionPane.showMessageDialog(null, "Usted adeuda 3 libros!");
                }
                
                else if (!mult){
                    JOptionPane.showMessageDialog(null, "Usted se encuentra con multas!");
                }             
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar su prestamo");
            ex.getLocalizedMessage();
        }       
    }  
    
    public void modificarPrestamo (Prestamo prestamo){
        String sql = "UPDATE prestamos SET estado=? WHERE id_prestamo=?";
    
        try {
            PreparedStatement ps = con.prepareStatement(sql);
                   
            ps.setBoolean(1, false);
            ps.setInt(2, prestamo.getIdPrestamo());
            
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Se ha modificado su prestamo!");
            
             Conexion con = new Conexion();
             EjemplarData eje = new EjemplarData (con);
             eje.actualizarEstado(prestamo.getEjemplar(),"Disponible");
            
            int i = (int)ChronoUnit.DAYS.between(prestamo.getFecha_prestamo(), LocalDate.now());
            
            if(i > 30){
                i -= 30;
                Multa multa = new Multa(prestamo.getLector(), LocalDate.now().plusDays(i*2) ,LocalDate.now());
                MultaData mul = new MultaData(con);
                mul.agregarMulta(multa);
                JOptionPane.showMessageDialog(null, "Se le pondra una multa de "+i*2+" dias por su tardanza");
            }
            else{
                JOptionPane.showMessageDialog(null, "Ha entregado su ejemplar a tiempo!");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar su prestamo!");
        }
    
    }
    
    public void anularPrestamo (Prestamo prestamo){
        String sql = "UPDATE prestamos SET estado WHERE prestamos.id_prestamo=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setBoolean(1, false);
            ps.setInt(2, prestamo.getIdPrestamo());
            
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Se ha modificado su prestamo!");
            
             Conexion con = new Conexion();
             EjemplarData eje = new EjemplarData (con);
             eje.actualizarEstado(prestamo.getEjemplar(),"Disponible");
        
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public boolean buscarMulta (int id_lector){
        Conexion con = new Conexion();
        MultaData mul = new MultaData (con);
        return mul.buscarMulta(id_lector);
    }   //FUNCIONA
    
    public boolean ejemDisponible (int id_ejemplar){
        Conexion con = new Conexion ();
        EjemplarData eje = new EjemplarData(con);
        return eje.ejemplarDisponible(id_ejemplar);
    }  //FUNCIONA
    
    public boolean prestamoXFecha (int id_lector){   
        Conexion con = new Conexion ();
        MultaData mul = new MultaData (con);
        return mul.prestamoXFecha(id_lector);        
    }   //FUNCIONA
    
    public boolean prestados (int id_lector){
        String sql = "SELECT id_lector FROM prestamos WHERE prestamos.id_lector = ? AND estado = 1";
        boolean pres = true;
        int i = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_lector);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                i++;
            }
            if (i >=3){
                pres = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
       return pres;  
    }  //FUNCIONA

    public List <Prestamo> prestamoXFecha (LocalDate loc){
        ArrayList <Prestamo> pres = new ArrayList <> ();
        Prestamo prestamo;
        String sql = "SELECT * FROM prestamos WHERE fecha_prestamo=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setDate(1, Date.valueOf(loc));
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                prestamo = new Prestamo (new Lector(),new Ejemplar());
                prestamo.setIdPrestamo(rs.getInt(rs.getInt(1)));
                prestamo.getLector().setId_lector(rs.getInt(2));
                prestamo.getEjemplar().setId_ejemplar(rs.getInt(3));
                prestamo.setEstado(rs.getBoolean(4));
                prestamo.setFecha_prestamo(rs.getDate(5).toLocalDate());
                
                pres.add(prestamo);
            }       
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pres;
    }
    
    public List <Prestamo> prestamosVigentes (){
        ArrayList <Prestamo> pres = new ArrayList <> ();
        Prestamo prestamo;
      
        String sql = "SELECT * FROM prestamos WHERE estado=1";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);        
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                prestamo = new Prestamo (new Lector(),new Ejemplar());
                prestamo.setIdPrestamo(rs.getInt(rs.getInt(1)));
                prestamo.getLector().setId_lector(rs.getInt(2));
                prestamo.getEjemplar().setId_ejemplar(rs.getInt(3));
                prestamo.setEstado(rs.getBoolean(4));
                prestamo.setFecha_prestamo(rs.getDate(5).toLocalDate());
                
                pres.add(prestamo);               
            }
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pres;
    }   //FUNCIONA

    public List <Lector> prestamosVencidos (){
        ArrayList <Lector> venci = new ArrayList<>();
        String sql = "SELECT lector.id_lector,lector.nombre_lector,lector.apellido_lector,lector.dni_lector,lector.dire_lector FROM lector JOIN prestamos ON lector.id_lector=prestamos.id_lector JOIN ejemplar ON ejemplar.id_ejemplar=prestamos.id_ejemplar AND ejemplar.estado='Retraso'";
        Lector lector;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                lector = new Lector();
                lector.setId_lector(rs.getInt(1));
                lector.setNombreLector(rs.getString(2));
                lector.setApellidoLector(rs.getString(3));
                lector.setDniLector(rs.getInt(4));
                lector.setDireLector(rs.getString(5));
                
                venci.add(lector);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return venci;
    }  //FUNCIONA
    
    public List <Lector> multasActivasXMes (int mes){
        ArrayList <Lector> mult = new ArrayList <> ();
        String sql = "SELECT lector.id_lector,lector.nombre_lector,lector.apellido_lector,lector.dni_lector,lector.dire_lector FROM lector JOIN multa ON lector.id_lector = multa.id_lector WHERE MONTH(multa.fecha_inicio) ='"+mes+"'";
        Lector lector;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                lector = new Lector();
                lector.setId_lector(rs.getInt(1));
                lector.setNombreLector(rs.getString(2));
                lector.setApellidoLector(rs.getString(3));
                lector.setDniLector(rs.getInt(4));
                lector.setDireLector(rs.getString(5));
                
                mult.add(lector);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mult;
    }  //FUNCIONA
    
}
