
package modelo;

import entidades.Multa;
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

public class MultaData {
    private Connection con;
    
    public MultaData(Conexion c){
        con = c.getConnection();
    }
    
    public void agregarMulta (Multa multa){
        String sql = "INSERT INTO multa (id_lector, fecha_fin, fecha_inicio) VALUES (?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, multa.getLector().getId_lector());
            ps.setDate(2, Date.valueOf(multa.getFecha_fin()));
            ps.setDate(3, Date.valueOf(multa.getFecha_inicio()));
            
            ps.executeUpdate();
            ps.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(MultaData.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
   public boolean buscarMulta (int id_lector){
       String sql = "SELECT * FROM multa WHERE id_lector=?";
       boolean i = true;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id_lector);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                i = false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MultaData.class.getName()).log(Level.SEVERE, null, ex);
        }     
       return i;
   }    

   public boolean prestamoXFecha (int id_lector){
       String sql = "SELECT fecha_fin FROM multa WHERE multa.id_lector = ?";
       ArrayList <Date> aux = new ArrayList <> ();
       boolean multaSaldada = false;
       try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id_lector);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                aux.add(rs.getDate(1));
            }
            
            
            for (int f = 0; f<aux.size();f++){
            
            if (aux.get(f).toLocalDate().isBefore(LocalDate.now())){
                multaSaldada = true;
            }    
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MultaData.class.getName()).log(Level.SEVERE, null, ex);
        }
       return multaSaldada;
   }
   
   public void anularMulta (int id_multa){
       String sql = "UPDATE multa SET fecha_fin=? WHERE multa.id_multa=?";   
       
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, id_multa);
            
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Se ha anulado su multa!");
            
        } catch (SQLException ex) {
            Logger.getLogger(MultaData.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
   }
   
}
