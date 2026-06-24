/**
 * Problema 05: Sistema de Gestión de Ventas de Entradas para Teatro
 * Se desea gestionar la venta de entradas para un espectáculo en un teatro.
 * El patio de butacas del teatro se divide en varias zonas, cada una identificada
 * por su nombre (Principal, PalcoB, Central, Lateral).
 * * Para realizar la compra de una entrada, el espectador indica la zona que desea
 * y presenta el documento que justifique si tiene algún tipo de descuento.
 * Al momento de la compra se asigna un identificador único (un número entero)
 * que permitirá identificar la entrada en todas las operaciones posteriores.
 * * Una entrada tiene como datos asociados su identificador, la zona a la que
 * pertenece y el nombre del comprador.
 * * Los precios de las entradas dependen de la zona y del tipo de entrada:
 * - Entradas normales: Precio normal de la zona elegida sin descuento.
 * - Entradas reducidas (estudiantes/pensionistas): Rebaja del 15% sobre el precio normal.
 * - Entradas abonado: Precio establecido para abonados de la zona elegida
 * @author Joel Cabrera
 * @version 1.0
 */
class Zona {
    public String nom;
    public int cantLoc;
    public double precNormal;
    public double precAbonado;

    public Zona(String nom, int cantLoc, double precNormal, double precAbonado) {
        this.nom = nom;
        this.cantLoc = cantLoc;
        this.precNormal = precNormal;
        this.precAbonado = precAbonado;
    }

    @Override
    public String toString() {
        return "Zona: " + nom + " - Precio normal: " + precNormal + " - Precio abonado: " + precAbonado;
    }
}

abstract class Entrada {
    public int id;
    public Zona z;
    public String nomComprador;

    public Entrada(int id, Zona z, String nomComprador) {
        this.id = id;
        this.z = z;
        this.nomComprador = nomComprador;
    }

    public abstract double calcularPrecio();

    @Override
    public String toString() {
        return "ID: " + id 
                + "\nComprador: " + nomComprador 
                + "\nZona: " + z.nom 
                + "\nPrecio: " + calcularPrecio();
    }
}

class EntradaNormal extends Entrada {

    public EntradaNormal(int id, Zona z, String nomComprador) {
        super(id, z, nomComprador);
    }

    @Override
    public double calcularPrecio() {
        return z.precNormal;
    }

    @Override
    public String toString() {
        return "Entrada Normal\n" + super.toString();
    }
}

class EntradaReducida extends Entrada {

    public EntradaReducida(int id, Zona z, String nomComprador) {
        super(id, z, nomComprador);
    }

    @Override
    public double calcularPrecio() {
        return z.precNormal * 0.85;
    }

    @Override
    public String toString() {
        return "Entrada Reducida\n" + super.toString();
    }
}

class EntradaAbonado extends Entrada {

    public EntradaAbonado(int id, Zona z, String nomComprador) {
        super(id, z, nomComprador);
    }

    @Override
    public double calcularPrecio() {
        return z.precAbonado;
    }

    @Override
    public String toString() {
        return "Entrada Abonado\n" + super.toString();
    }
}

public class Problema_5_EjecutorTeatro {

    public static void main(String[] args) {

        Zona principal = new Zona("Principal", 200, 25.0, 17.5);
        Zona palcob = new Zona("PalcoB", 40, 70.0, 40.0);
        Zona central = new Zona("Central", 400, 20.0, 14.0);
        Zona lateral = new Zona("Lateral", 100, 15.5, 10.0);

        System.out.println("Venta de Entradas");
        System.out.println();

        Entrada e1 = new EntradaNormal(1, principal, "Joel Cabrera");
        System.out.println(e1);
        System.out.println();

        Entrada e2 = new EntradaReducida(2, central, "Fernando Rielo");
        System.out.println(e2);
        System.out.println();

        Entrada e3 = new EntradaAbonado(3, palcob, "Consuelo Putaicela");
        System.out.println(e3);
        System.out.println();
        
        Entrada e4 = new EntradaNormal(4, lateral, "Alejandro Sanz");
        System.out.println(e4);
    }
}
/**
 * run:
Venta de Entradas

Entrada Normal
ID: 1
Comprador: Joel Cabrera
Zona: Principal
Precio: 25.0

Entrada Reducida
ID: 2
Comprador: Fernando Rielo
Zona: Central
Precio: 17.0

Entrada Abonado
ID: 3
Comprador: Consuelo Putaicela
Zona: PalcoB
Precio: 40.0

Entrada Normal
ID: 4
Comprador: Alejandro Sanz
Zona: Lateral
Precio: 15.5
BUILD SUCCESSFUL (total time: 0 seconds)
 */