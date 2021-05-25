
package entidades;

import java.time.LocalDate;


public class Multa {
   int id_multa;
   Lector lector;
   LocalDate fecha_fin, fecha_inicio;

    public Multa() {
    }

    public Multa(int id_multa) {
        this.id_multa = id_multa;
    }
     
    public Multa(Lector lector, LocalDate fecha_fin, LocalDate fecha_inicio) {
        this.lector = lector;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
    }

    public Multa(int id_multa, Lector lector, LocalDate fecha_fin, LocalDate fecha_inicio) {
        this.id_multa = id_multa;
        this.lector = lector;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
    }

    public int getId_multa() {
        return id_multa;
    }

    public void setId_multa(int id_multa) {
        this.id_multa = id_multa;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
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
