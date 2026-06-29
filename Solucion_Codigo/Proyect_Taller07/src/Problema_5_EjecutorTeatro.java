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
 * @version 3.0
 */
import java.util.ArrayList;

class Zona {

    public String nZona;
    public int cantidadLocalidades;
    public double precioNormal, precioAbonado;

    public Zona(String nZona, int cantidadLocalidades, double precioNormal, double precioAbonado) {
        this.nZona = nZona;
        this.cantidadLocalidades = cantidadLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;

    }

    public boolean asignarAsiento() {
        if (this.cantidadLocalidades > 0) {
            this.cantidadLocalidades--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Zona{nZona=" + nZona + ", cantidadLocalidades=" + cantidadLocalidades
                + ", precioNormal=" + precioNormal + ", precioAbonado=" + precioAbonado + '}';
    }
}

class Entrada {

    public int ID, cantidadEntradas;
    public Zona zona;
    public String nombreComprador;
    public double costoEntrada;

    public Entrada(int ID, int cantidadEntradas, Zona zona, String nombreComprador, double costoEntrada) {
        this.ID = ID;
        this.cantidadEntradas = cantidadEntradas;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
        this.costoEntrada = costoEntrada;
    }

    public double calcularCostoEntrada() {
        this.costoEntrada = this.cantidadEntradas * this.zona.precioNormal;
        return this.costoEntrada;
    }

    @Override
    public String toString() {
        return "Entrada{ID=" + ID + ", cantidadEntradas=" + cantidadEntradas
                + ", zona=" + zona + ", nombreComprador=" + nombreComprador
                + ", costoEntrada=" + costoEntrada + '}';
    }
}

class EntradaNormal extends Entrada {

    public EntradaNormal(int ID, int cantidadEntradas, Zona zona, String nombreComprador, double costoEntrada) {
        super(ID, cantidadEntradas, zona, nombreComprador, costoEntrada);
    }
}

class EntradaReducida extends Entrada {

    public double porcentajeRebaja;

    public EntradaReducida(double porcentajeRebaja, int ID, int cantidadEntradas, Zona zona, String nombreComprador, double costoEntrada) {
        super(ID, cantidadEntradas, zona, nombreComprador, costoEntrada);
        this.porcentajeRebaja = porcentajeRebaja;
    }

    @Override
    public double calcularCostoEntrada() {
        double precioConRebaja = this.zona.precioNormal - (this.zona.precioNormal * (this.porcentajeRebaja / 100));
        this.costoEntrada = precioConRebaja * this.cantidadEntradas;
        return this.costoEntrada;
    }

    @Override
    public String toString() {
        return super.toString() + ", porcentajeRebaja=" + porcentajeRebaja + "}";
    }
}

class EntradaAbonado extends Entrada {

    public EntradaAbonado(int ID, int cantidadEntradas, Zona zona, String nombreComprador, double costoEntrada) {
        super(ID, cantidadEntradas, zona, nombreComprador, costoEntrada);
    }

    @Override
    public double calcularCostoEntrada() {
        this.costoEntrada = this.cantidadEntradas * this.zona.precioAbonado;
        return this.costoEntrada;
    }
}

class Boleteria {

    public ArrayList<Zona> zonas;
    public ArrayList<Entrada> listaEntrada;

    public Boleteria() {
        this.zonas = new ArrayList<>();
        this.listaEntrada = new ArrayList<>();
        inicializarEntradas();
    }

    public void inicializarEntradas() {
        zonas.add(new Zona("Principal", 200, 25, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70, 40));
        zonas.add(new Zona("Central", 400, 20.0, 14));
        zonas.add(new Zona("Lateral", 100, 15.5, 10));
    }

    public String notificacion(int ID, String nombreZona, int tipoEntrada, String nombreEspectador) {
        Zona zonaSeleccionada = null;
        for (Zona z : zonas) {
            if (z.nZona.equalsIgnoreCase(nombreZona)) {
                zonaSeleccionada = z;
                break;
            }
        }
        if (zonaSeleccionada == null) {
            return "Notificacion --- (No existe esa zona) ";
        }
        if (!zonaSeleccionada.asignarAsiento()) {
            return "Notificacion --- (Esa zona ya esta completa)";
        }
        Entrada entrada = null;
        if (tipoEntrada == 1) {
            entrada = new EntradaNormal(ID, 1, zonaSeleccionada, nombreEspectador, 0);
        } else if (tipoEntrada == 2) {
            entrada = new EntradaReducida(15, ID, 1, zonaSeleccionada, nombreEspectador, 0);
        } else if (tipoEntrada == 3) {
            entrada = new EntradaAbonado(ID, 1, zonaSeleccionada, nombreEspectador, 0);
        } else {
            return "Notificacion --- (No valido)";
        }
        entrada.calcularCostoEntrada();
        listaEntrada.add(entrada);
        String mensaje = "Exito de generar entrada: " + ID + " con un precio de " + entrada.costoEntrada;
        return mensaje;

    }

    public String consultarEntrada(int IDBuscar) {
        for (Entrada e : listaEntrada) {
            if (e.ID == IDBuscar) {
                return e.toString();
            }
        }
        return "Notificacion --- (No se encontro ese ID)";
    }    
}


public class Problema_5_EjecutorTeatro {

    public static void main(String[] args) {
        Boleteria boleteria = new Boleteria();
        System.out.println("   ****GENERAR ENTRADAS****   ");
        System.out.println(boleteria.notificacion(1, "Principal", 1, "Sofia"));
        System.out.println(boleteria.notificacion(2, "PalcoB", 2, "Emily"));
        System.out.println(boleteria.notificacion(3, "Central", 3, "Allyson"));
        System.out.println(boleteria.notificacion(4, "Lateral", 4, "Ana"));

        System.out.println("   *****CONSULTAR ENTRADAS*****   ");
        System.out.println(boleteria.consultarEntrada(1));
        System.out.println(boleteria.consultarEntrada(2));
        System.out.println(boleteria.consultarEntrada(3));
        System.out.println(boleteria.consultarEntrada(4));
    }
}
/**
 * run:
   ****GENERAR ENTRADAS****   
Exito de generar entrada: 1 con un precio de 25.0
Exito de generar entrada: 2 con un precio de 59.5
Exito de generar entrada: 3 con un precio de 14.0
Notificacion --- (No valido)
   *****CONSULTAR ENTRADAS*****   
Entrada{ID=1, cantidadEntradas=1, zona=Zona{nZona=Principal, cantidadLocalidades=199, precioNormal=25.0, precioAbonado=17.5}, nombreComprador=Sofia, costoEntrada=25.0}
Entrada{ID=2, cantidadEntradas=1, zona=Zona{nZona=PalcoB, cantidadLocalidades=39, precioNormal=70.0, precioAbonado=40.0}, nombreComprador=Emily, costoEntrada=59.5}, porcentajeRebaja=15.0}
Entrada{ID=3, cantidadEntradas=1, zona=Zona{nZona=Central, cantidadLocalidades=399, precioNormal=20.0, precioAbonado=14.0}, nombreComprador=Allyson, costoEntrada=14.0}
Notificacion --- (No se encontro ese ID)
BUILD SUCCESSFUL (total time: 0 seconds)

 */