
package entidades;

public class Lector {
   int id_lector, dniLector;
   String nombreLector, apellidoLector, direLector;

    public Lector() {
    }

    public Lector(int id_lector) {
        this.id_lector = id_lector;
    }

    public Lector(int dniLector, String nombreLector, String apellidoLector, String direLector) {
        this.dniLector = dniLector;
        this.nombreLector = nombreLector;
        this.apellidoLector = apellidoLector;
        this.direLector = direLector;
    }

    public Lector(int id_lector, int dniLector, String nombreLector, String apellidoLector, String direLector) {
        this.id_lector = id_lector;
        this.dniLector = dniLector;
        this.nombreLector = nombreLector;
        this.apellidoLector = apellidoLector;
        this.direLector = direLector;
    }

    public int getId_lector() {
        return id_lector;
    }

    public void setId_lector(int id_lector) {
        this.id_lector = id_lector;
    }

    public int getDniLector() {
        return dniLector;
    }

    public void setDniLector(int dniLector) {
        this.dniLector = dniLector;
    }

    public String getNombreLector() {
        return nombreLector;
    }

    public void setNombreLector(String nombreLector) {
        this.nombreLector = nombreLector;
    }

    public String getApellidoLector() {
        return apellidoLector;
    }

    public void setApellidoLector(String apellidoLector) {
        this.apellidoLector = apellidoLector;
    }

    public String getDireLector() {
        return direLector;
    }

    public void setDireLector(String direLector) {
        this.direLector = direLector;
    } 

    @Override
    public String toString() {
        return "Lector: "+ id_lector + "\n dniLector=" + dniLector + "\n nombreLector=" + nombreLector + "\n apellidoLector=" + apellidoLector + "\n direLector=" + direLector;
    }

    
    
    
    
}
