
package entidades;

import java.time.LocalDate;


public class Multa {
    private int id_multa;
    private Prestamo prestamo;
    private LocalDate fecha_fin, fecha_inicio;

    public Multa() {
    }

    public Multa(int id_multa) {
        this.id_multa = id_multa;
    }

    public Multa(Prestamo prestamo, LocalDate fecha_inicio) {
        this.prestamo = prestamo;
        this.fecha_inicio = fecha_inicio;
    }
     
    public Multa(Prestamo prestamo,LocalDate fecha_fin, LocalDate fecha_inicio) {
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.prestamo = prestamo;
    }

    public Multa(int id_multa,Prestamo prestamo, LocalDate fecha_fin, LocalDate fecha_inicio) {
        this.id_multa = id_multa;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.prestamo = prestamo;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    
    public int getId_multa() {
        return id_multa;
    }

    public void setId_multa(int id_multa) {
        this.id_multa = id_multa;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
}
