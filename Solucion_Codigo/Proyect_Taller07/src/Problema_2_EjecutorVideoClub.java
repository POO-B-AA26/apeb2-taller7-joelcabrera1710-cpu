
import java.util.Arrays;

/**
 * Problema02: Un videoclub dispone de una serie de películas que pueden estar 
 * en DVD (con capacidad en Gb.) o en VHS (una sola cinta por película y con
 * cierto tipo de cinta magnetica). De las películas interesa guardar el título,
 * el autor, el año de edición y el idioma (o los idiomas, en caso de DVD). 
 * El precio de alquiler de las películas varía en función del tipo de película. 
 * Las DVD siempre son 10% mas caras que las de VHS.
 * @author Joel Cabrera
 * @version 1.0
 */
import java.util.Arrays;

class Pelicula {
    public String titulo;
    public String autor;
    public int anio;

    public Pelicula(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + '}';
    }
}

class Soporte {
    public Pelicula peli;
    public int cantidad;
    public double precio; 
    public double costoAlquiler;

    public Soporte(Pelicula peli, int cantidad, double precio) {
        this.peli = peli;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public double calcularCostoAlquiler() {
        this.costoAlquiler = this.cantidad * this.precio;
        return this.costoAlquiler;
    }

    @Override
    public String toString() {
        return "peli=" + peli + ", cantidad=" + cantidad + ", precio=" + precio + ", costoAlquiler=" + costoAlquiler;
    }
}

class Dvd extends Soporte {
    public double capacidadGb;
    public String[] idiomas;
    public double porceRecargo = 10.0; 

    public Dvd(double capacidadGb, String[] idiomas, Pelicula peli, int cantidad, double precio) {
        super(peli, cantidad, precio * 1.10); 
        this.capacidadGb = capacidadGb;
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "Dvd{" + super.toString() + ", capacidadGb=" + capacidadGb + "GB, recargo=" + porceRecargo + "%, idiomas=" + Arrays.toString(idiomas) + "}";
    }
}

class Vhs extends Soporte {
    public String tipoCinta;
    public String idioma;

    public Vhs(String tipoCinta, String idioma, Pelicula peli, int cantidad, double precio) {
        super(peli, cantidad, precio);
        this.tipoCinta = tipoCinta;
        this.idioma = idioma;
    }

    @Override
    public String toString() {
       return "Vhs{" + super.toString() + ", tipoCinta=" + tipoCinta + ", idioma=" + idioma + "}";
    }
}

public class Problema_2_EjecutorVideoClub {
    public static void main(String[] args) {
        String[] idiomasDvd = {"Espanol", "Ingles"};
        Pelicula peli = new Pelicula("Spiderman", "MikeTowers", 2026);
        
        Dvd dvd1 = new Dvd(4.7, idiomasDvd, peli, 2, 10);
        dvd1.calcularCostoAlquiler();
        System.out.println(dvd1);
        
        Vhs vhs1 = new Vhs("Cromo", "Español", peli, 2, 10          );
        vhs1.calcularCostoAlquiler();
        System.out.println(vhs1);
    }
}
/**
* run:
* Dvd{peli=Pelicula{titulo=Spiderman, autor=MikeTowers, anio=2026}, cantidad=2, precio=11.0, costoAlquiler=22.0, capacidadGb=4.7GB, recargo=10.0%, idiomas=[Espanol, Ingles]}
* Vhs{peli=Pelicula{titulo=Spiderman, autor=MikeTowers, anio=2026}, cantidad=2, precio=10.0, costoAlquiler=20.0, tipoCinta=Cromo, idioma=Espa�ol}
* BUILD SUCCESSFUL (total time: 0 seconds)
 */