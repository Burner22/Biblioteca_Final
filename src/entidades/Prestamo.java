
package entidades;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    int idPrestamo;
    Lector lector;
    Ejemplar ejemplar;
    boolean estado;
    LocalDate fecha_prestamo;

    public Prestamo() {
    }

    public Prestamo(int idPrestamo, Lector lector, Ejemplar ejemplar) {
        this.idPrestamo = idPrestamo;
        this.lector = lector;
        this.ejemplar = ejemplar;
    }

    public Prestamo(Lector lector, Ejemplar ejemplar) {
        this.lector = lector;
        this.ejemplar = ejemplar;
    }


    public Prestamo(Lector lector, Ejemplar ejemplar, boolean estado, LocalDate fecha_prestamo) {
        this.lector = lector;
        this.ejemplar = ejemplar;
        this.estado = estado;
        this.fecha_prestamo = fecha_prestamo;
    }

    public Prestamo(int idPrestamo, Lector lector, Ejemplar ejemplar, boolean estado, LocalDate fecha_prestamo) {
        this.idPrestamo = idPrestamo;
        this.lector = lector;
        this.ejemplar = ejemplar;
        this.estado = estado;
        this.fecha_prestamo = fecha_prestamo;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(LocalDate fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    @Override
    public String toString() {
        return "Prestamo: " + idPrestamo + "\n lector=" + lector + "\n ejemplar=" + ejemplar + "\n estado=" + estado + "\n fecha_prestamo=" + fecha_prestamo;
    }
    
    
    
}
