
package entidades;

import java.time.LocalDate;

public class Autor {
    int id_autor, dniAutor;
    String nombreAutor,apellidoAutor,nacionalidad;
    LocalDate fecha_nac;

    public Autor() {
    }

    public Autor(int id_autor) {
        this.id_autor = id_autor;
    }
 
    public Autor(int dniAutor, String nombreAutor, String apellidoAutor, String nacionalidad, LocalDate fecha_nac) {
        this.dniAutor = dniAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.nacionalidad = nacionalidad;
        this.fecha_nac = fecha_nac;
    }

    public Autor(int id_autor, int dniAutor, String nombreAutor, String apellidoAutor, String nacionalidad, LocalDate fecha_nac) {
        this.id_autor = id_autor;
        this.dniAutor = dniAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.nacionalidad = nacionalidad;
        this.fecha_nac = fecha_nac;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public int getDni() {
        return dniAutor;
    }

    public void setDni(int dniAutor) {
        this.dniAutor = dniAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }
  
}
