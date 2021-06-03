
package entidades;

import java.util.Objects;

public class Ejemplar {
    int id_ejemplar;
    Libro libro;
    String estado;

    public Ejemplar() {
    }

    public Ejemplar(Libro libro) {
        this.libro = libro;
    }

    public Ejemplar(int id_ejemplar) {
        this.id_ejemplar = id_ejemplar;
    }

    public Ejemplar(Libro libro, String estado) {
        this.libro = libro;
        this.estado = estado;
    }

    public Ejemplar(int id_ejemplar, Libro libro) {
        this.id_ejemplar = id_ejemplar;
        this.libro = libro;
    }
    
    public Ejemplar(int id_ejemplar, Libro libro, String estado) {
        this.id_ejemplar = id_ejemplar;
        this.libro = libro;
        this.estado = estado;
    }

    public int getId_ejemplar() {
        return id_ejemplar;
    }

    public void setId_ejemplar(int id_ejemplar) {
        this.id_ejemplar = id_ejemplar;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return " id_ejemplar=" + id_ejemplar;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ejemplar other = (Ejemplar) obj;
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.estado);
        return hash;
    }
    
    
    
}
