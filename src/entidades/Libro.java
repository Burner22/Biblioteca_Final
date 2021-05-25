
package entidades;

public class Libro {
    int id_libro,ISBN,año;
    Autor autor;
    String nombre,editorial,tipo;

    public Libro() {
    }

    public Libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public Libro(Autor autor, int ISBN, int año, String nombre, String editorial, String tipo) {
        this.autor = autor;
        this.ISBN = ISBN;
        this.año = año;
        this.nombre = nombre;
        this.editorial = editorial;
        this.tipo = tipo;
    }

    public Libro(int id_libro, Autor autor, int ISBN, int año, String nombre, String editorial, String tipo) {
        this.id_libro = id_libro;
        this.autor = autor;
        this.ISBN = ISBN;
        this.año = año;
        this.nombre = nombre;
        this.editorial = editorial;
        this.tipo = tipo;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
