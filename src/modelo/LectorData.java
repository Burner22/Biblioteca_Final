
package modelo;

import entidades.Lector;
import entidades.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LectorData {
    private Connection con;
    
    public LectorData(Conexion c){
        con = c.getConnection();
    }
    
    public void agregarLector (Lector lector){
        String sql = "INSERT INTO lector (nombre_lector,apellido_lector,dni_lector,dire_lector,estado_lector) VALUES (?,?,?,?,?)";   
    
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                  
            ps.setString(1, lector.getNombreLector());
            ps.setString(2, lector.getApellidoLector());
            ps.setInt(3, lector.getDniLector());
            ps.setString(4, lector.getDireLector());
            ps.setBoolean(5, lector.getEstado_lector());
            
            ps.executeUpdate();
            //Crear un ResultSet con las claves generadas.
            //Añadir el id generado por la base de datos al lector con .setId_lector.
            ps.close();
            JOptionPane.showMessageDialog(null,"Se ha agregado su lector!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar lector!");
        } 
    }  //FUNCIONA
    
    public void actualizarLector (Lector lector){
        String sql = "UPDATE lector SET nombre_lector=?,apellido_lector=?,dni_lector=?,dire_lector=?,estado_lector=? WHERE id_lector=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//Se esta actualizando un lector, no hace falta pedir de vuelta las keys de la base de datos.
                  
            ps.setString(1, lector.getNombreLector());
            ps.setString(2, lector.getApellidoLector());
            ps.setInt(3, lector.getDniLector());
            ps.setString(4, lector.getDireLector());
            ps.setBoolean(5, lector.getEstado_lector());
            ps.setInt(6, lector.getId_lector());
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Se ha actualizado su Lector");
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar lector!");//Cambiar mensaje a "Error al actualizar un lector".
        } 
    }   //FUNCIONA
    
    public Lector buscarLector(int id_lector){//Sugerencia: buscar a un lector por dni y no por id.
        String query="SELECT * FROM lector WHERE id_lector=?";
        Lector lector=null;
        try{
            PreparedStatement ps=con.prepareStatement(query);
            
            ps.setInt(1,id_lector);
            
            ResultSet rs=ps.executeQuery();
            
            //Suponiendo que usemos el id_lector para buscar a un lector, no seria mejor usar un if?. 
            //El id representa a un unico elemento en una tabla, por lo tanto, devolveria un solo elemento. 
            //por qué usar un while solo para que itere una sola vez?
            while(rs.next()){
                lector=new Lector();
                lector.setId_lector(rs.getInt("id_lector"));
                lector.setNombreLector(rs.getString("nombre_lector"));
                lector.setApellidoLector(rs.getString("apellido_lector"));
                lector.setDniLector(rs.getInt("dni_lector"));
                lector.setDireLector(rs.getString("dire_lector"));
                lector.setEstado_lector(rs.getBoolean("estado_lector"));
            }
            
            //Mostrar un mensaje si el lector que se busca no existe en la tabla lector.
            ps.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "error al buscar lector");
        }
        return lector;
    }//funciona
    
    public void cambiarEstadoLector(int id_lector, boolean estado){
        String query="UPDATE lector SET estado_lector=? WHERE id_lector=?";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setBoolean(1, estado);
            ps.setInt(2, id_lector);
            
            ps.executeUpdate();
            ps.close();
            //Mostrar un mensaje que indique si el lector ha sido activado o a sido dado de baja;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "error al buscar lector");
        }
        
    }//funciona 
    
    //Si contiene una multa con mas de 90 dias de retraso, se cambia el estado del lector a inactivo
    public void chequeoEstadoLector(){
       String sql = "SELECT multa.id_prestamo,fecha_inicio FROM multa,prestamos WHERE multa.id_prestamo = prestamos.id_prestamo";
       TreeMap <Integer,LocalDate> aux = new TreeMap <> ();
       Set <Integer> aux2 = aux.keySet();
       try {
            PreparedStatement ps = con.prepareStatement(sql);
       
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                aux.put(rs.getInt(1),rs.getDate(2).toLocalDate());
            }
            long esp;    
            for (Integer it: aux2){
                esp = ChronoUnit.DAYS.between(aux.get(it), LocalDate.now());
                System.out.println(esp);
                if(esp > 90){
                    Conexion con = new Conexion();
                    PrestamoData pre = new PrestamoData(con);
                    Prestamo preAux = pre.buscarPrestamo(it);
                    cambiarEstadoLector(preAux.getLector().getId_lector(),false);
                }   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MultaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
