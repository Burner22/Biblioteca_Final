
package modelo;

import entidades.Autor;
import entidades.Ejemplar;
import entidades.Lector;
import entidades.Libro;
import entidades.Multa;
import entidades.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
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
    //Chequea que no tenga mas de 3 libros, chequea las multas saldadas, chequea que el lector este activo, chequea que el ejemplar este disponible
    public void registrarPrestamo (Prestamo prestamo){
        boolean porFecha = prestamoXFecha(prestamo.getLector().getId_lector());
        boolean prest = prestados(prestamo.getLector().getId_lector());
        boolean dispo = ejemDisponible(prestamo.getEjemplar().getId_ejemplar());      
        boolean taActivo = lectorActivo(prestamo.getLector().getId_lector());
        try {
            if(prest && dispo && porFecha && taActivo){
                String sql = "INSERT INTO prestamos (id_lector,id_ejemplar,estado,fecha_prestamo) VALUES (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, prestamo.getLector().getId_lector());
                ps.setInt(2, prestamo.getEjemplar().getId_ejemplar());
                ps.setBoolean(3, prestamo.getEstado());
                ps.setDate(4, Date.valueOf(prestamo.getFecha_prestamo()));
                ps.executeUpdate();
                
                ps.close();
                
                //Sugerencia: setear el id al prestamo.
                Conexion con = new Conexion();
                EjemplarData eje = new EjemplarData (con);
                eje.actualizarEstado(prestamo.getEjemplar(),"Prestado");
          
                JOptionPane.showMessageDialog(null, "Su prestamo se ha registrado!");
            }
            else{
                if(!dispo){
                    JOptionPane.showMessageDialog(null, "Dicho ejemplar no esta disponible!");
                }
                else if(!prest && !porFecha){
                    JOptionPane.showMessageDialog(null, "Usted adeuda 3 libros y tiene multas!");
                }
                else if (!prest){
                    JOptionPane.showMessageDialog(null, "Usted ya adeuda 3 libros!");
                }
                else if (!porFecha){
                    JOptionPane.showMessageDialog(null, "Usted tiene multas!");
                }
                else if(!taActivo){
                    JOptionPane.showMessageDialog(null, "Usted ha sido dado de baja, regularice su situacion");
                }
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar su prestamo");
           
        }       
    }  
    
    //Modifica el prestamo y dependiendo del estado del ejemplar agrega la fecha_fin de la multa, ya que ya hay una fecha_inicio que se puso en chequeoEstado(EjemplarData)
    public void modificarPrestamo (Prestamo prestamo){//Sugerencia: cambiar el nombre a "entregarPrestamo".
        String sql = "UPDATE prestamos SET estado=? WHERE id_prestamo=?";
    
        try {
            PreparedStatement ps = con.prepareStatement(sql);
                   
            ps.setBoolean(1, false);
            ps.setInt(2, prestamo.getIdPrestamo());
            
            ps.executeUpdate();
  
            JOptionPane.showMessageDialog(null, "Se ha modificado su prestamo!");
    
            Conexion con = new Conexion(); 
            EjemplarData eje = new EjemplarData (con);
            if("Retraso".equals(eje.estadoEjemplar(prestamo.getEjemplar().getId_ejemplar()))){
                MultaData mul = new MultaData (con);
                mul.fechaFinal(prestamo.getIdPrestamo(), LocalDate.of(2021, Month.JUNE,7));
            }
            
            eje.actualizarEstado(prestamo.getEjemplar(),"Disponible");
            ps.close(); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar su prestamo!");
        }    
    }
    
    //Anula el prestamo poniendo el prestamo en falso
    public void anularPrestamo (Prestamo prestamo){
        String sql = "UPDATE prestamos SET estado WHERE prestamos.id_prestamo=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setBoolean(1, false);
            ps.setInt(2, prestamo.getIdPrestamo());
            
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Se ha modificado su prestamo!");//Cambiar mensaje a "Se ha anuluado su prestamo!".
            
             Conexion con = new Conexion();
             EjemplarData eje = new EjemplarData (con);
             eje.actualizarEstado(prestamo.getEjemplar(),"Disponible");
        
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    //Chequea que el ejemplar este disponible
    public boolean ejemDisponible (int id_ejemplar){
        Conexion con = new Conexion ();
        EjemplarData eje = new EjemplarData(con);
        return eje.ejemplarDisponible(id_ejemplar);
    }  
    
    //Chequea que las multas esten saldadas
    public boolean prestamoXFecha (int id_lector){   
        Conexion con = new Conexion ();
        MultaData mul = new MultaData (con);
        return mul.prestamoXFecha(id_lector);        
    }   
    
    //Chequea que no tenga mas de 3 libros
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
    }  
    
    //Chequea que el lector este activo y sin ninguna deuda mayor a 3 meses
    public boolean lectorActivo (int id_lector){
        String sql = "SELECT estado_lector FROM lector WHERE id_lector =?";
        boolean taActivo = true;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id_lector);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                if(rs.getBoolean(1) == false){
                    taActivo = false;
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return taActivo;
    }  
    
    //Busca un prestamo a traves del id_prestamo
    public Prestamo buscarPrestamo (int id_prestamo){
        String sql = "SELECT * FROM prestamos WHERE id_prestamo =?";
        Prestamo prestamo = new Prestamo(new Lector(),new Ejemplar());//Sugerencia: crear un prestamo que sea igual a null;
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//No hace falta pedir las keys de la bd.
            ps.setInt(1,id_prestamo);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                //Y luego crear un nuevo prestamo en el if con new Prestamo(new Lector(),new Ejemplar()).
                prestamo.setIdPrestamo(rs.getInt(1));
                prestamo.getLector().setId_lector(rs.getInt(2));
                prestamo.getEjemplar().setId_ejemplar(rs.getInt(3));
                prestamo.setEstado(rs.getBoolean(4));
                prestamo.setFecha_prestamo(rs.getDate(5).toLocalDate());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prestamo;
    }

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
        ArrayList <Prestamo> pres = new ArrayList <> ();//Sugerencia: cambiar el nombre del arrayList a "pVigentes".
        Prestamo prestamo;
      
        String sql = "SELECT prestamos.id_prestamo,prestamos.id_ejemplar,prestamos.estado, prestamos.fecha_prestamo,ejemplar.id_libro,ISBN,nombre,editorial,año,tipo,libro.id_autor, nombre_autor,apellido_autor,fech_nac,autor.dni_autor,nacionalidad,prestamos.id_lector,lector.id_lector,lector.nombre_lector, lector.apellido_lector,lector.dni_lector,lector.dire_lector,lector.estado_lector FROM prestamos,ejemplar, libro,autor,lector WHERE prestamos.id_ejemplar=ejemplar.id_ejemplar AND prestamos.id_lector=lector.id_lector AND ejemplar.id_libro=libro.id_libro AND libro.id_autor=autor.id_autor AND prestamos.estado=1";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);        
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                prestamo = new Prestamo();
                Ejemplar ejemplar=new Ejemplar();
                Lector lector=new Lector();
                Libro libro=new Libro();
                Autor autor=new Autor();
                
                autor.setId_autor(rs.getInt("id_autor"));
                autor.setNombreAutor(rs.getString("nombre_autor"));
                autor.setApellidoAutor(rs.getString("apellido_autor"));
                autor.setDni(rs.getInt("dni_autor"));
                autor.setFecha_nac(LocalDate.parse(rs.getString("fech_nac")));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                
                libro.setAutor(autor);
                libro.setId_libro(rs.getInt("id_libro"));
                libro.setISBN(rs.getInt("ISBN"));
                libro.setNombre(rs.getString("nombre"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setAño(rs.getInt("año"));
                libro.setTipo(rs.getString("tipo"));
                
                ejemplar.setEstado(rs.getString("estado"));
                ejemplar.setId_ejemplar(rs.getInt("id_ejemplar"));
                ejemplar.setLibro(libro);
                
                lector.setId_lector(rs.getInt("id_lector"));
                lector.setNombreLector(rs.getString("nombre_lector"));
                lector.setApellidoLector(rs.getString("apellido_lector"));
                lector.setDniLector(rs.getInt("dni_lector"));
                lector.setDireLector(rs.getString("dire_lector"));
                lector.setEstado_lector(rs.getBoolean("estado_lector"));
                
                prestamo.setLector(lector);
                prestamo.setEjemplar(ejemplar);
                prestamo.setIdPrestamo((rs.getInt("id_prestamo")));
                prestamo.setEstado(rs.getBoolean("estado"));
                prestamo.setFecha_prestamo(rs.getDate("fecha_prestamo").toLocalDate());
                
                pres.add(prestamo);               
            }
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pres;
    }   

    public List <Lector> prestamosVencidos (){
        ArrayList <Lector> venci = new ArrayList<>();//Sugerencia: cambiar nombre del arrayList a "lectoresPVencidos".
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
    }  
    
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
    }  
    
//    public boolean buscarMulta (int id_prestamo){
//        String sql = "SELECT * FROM multa WHERE multa.id_prestamo = ?";
//        boolean hayMulta = true;
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            
//            ps.setInt(1, id_prestamo);
//            
//            ResultSet rs = ps.executeQuery();
//            
//            if(rs.next()){
//                hayMulta = false;
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(MultaData.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return hayMulta;
//    }
    
}
