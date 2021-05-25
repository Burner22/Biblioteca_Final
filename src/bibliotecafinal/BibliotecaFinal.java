
package bibliotecafinal;

import entidades.Autor;
import entidades.Ejemplar;
import entidades.Lector;
import entidades.Libro;
import entidades.Prestamo;
import java.sql.Connection;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import modelo.AutorData;
import modelo.Conexion;
import modelo.EjemplarData;
import modelo.LectorData;
import modelo.LibroData;
import modelo.MultaData;
import modelo.PrestamoData;

public class BibliotecaFinal {

    public static void main(String[] args) {
        Conexion con = new Conexion();
        LibroData ld = new LibroData(con);
        AutorData ad = new AutorData(con);
        EjemplarData ed = new EjemplarData (con);
        LectorData led = new LectorData (con);
        PrestamoData pre = new PrestamoData(con);
        
        Autor autor = new Autor (1);
        //Autor autor = new Autor (41710461,"Roberto","Acevedo","Argentina",LocalDate.of(1999, Month.FEBRUARY,8));
        //ad.agregarAutor(autor);
        //ad.modificarLector(autor);
        
        Libro libro = new Libro (1);
        //Libro libro = new Libro(1,autor,25,2010,"LocoMania","Salamandra","Aventura");
        //ld.agregarLibro(libro);
        //ld.actualizarLibro(libro);
    
       
        //Ejemplar ejemplar = new Ejemplar (libro);
        //ed.agregarEjemplares(ejemplar, 6);
        Ejemplar ejemplar = new Ejemplar (7,libro);
        //ed.actualizarEstado(ejemplar, "Disponible");
        //System.out.println(ed.ejemplarDisponible(7));
        
        Lector lector = new Lector (1);
        //Lector lector = new Lector (41123456,"Nani","Chan","Modulo 12");
        //led.agregarLector(lector);
        //Lector lector = new Lector (1,41710461,"Fiero","San","Modulo 12");
        //led.actualizarLector(lector);        
        
        //Prestamo prestamo = new Prestamo (lector,ejemplar,true,LocalDate.of(2021, Month.APRIL,21));
        //pre.registrarPrestamo(prestamo);
        Prestamo prestamo = new Prestamo (3,lector,ejemplar,true,LocalDate.of(2021, Month.APRIL,21));
        //pre.modificarPrestamo(prestamo);
        
        //System.out.println(pre.buscarMulta(1));
        //System.out.println(pre.prestados(1));
        //System.out.println(pre.ejemDisponible(12));
        //System.out.println(pre.prestamoXFecha(1));
        
        Iterator it = pre.prestamoXFecha(LocalDate.of(2021, Month.APRIL,21)).iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    
}
