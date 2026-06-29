/**
 * Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. 
 * Los datos personales de los trabajadores son nombre y apellidos, dirección y 
 * DNI. Además, existen diferentes tipos de trabajadores:
 * Fijos Mensuales: que cobran una cantidad fija al mes.
 * Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
 * Por Horas: cobran un precio por cada una de las horas que han realizado durante
 * el mes. El precio es fijo para las primeras 40 horas y es otro para las horas
 * realizadas a partir de la 40 hora mensual.
 * Jefe: cobra un sueldo fijo (no hay que calcularlo). 
 * Cada empleado tiene obligatoriamente un jefe 
 * (exceptuando los jefes que no tienen ninguno).
 * El programa debe permitir dar de alta a trabajadores, así como fijar horas 
 * o ventas realizadas e imprimir la nómina correspondiente al final de mes.
 * @author Joel Cabrera
 * @version 2.0
 */
class Trabajador {
    public String nombre, apellidos, direccion, DNI;
    public double sueldo;

    public Trabajador(String nombre, String apellidos, String direccion, String DNI, double sueldo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.DNI = DNI;
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Trabajador{nombre=" + nombre + ", apellidos=" + apellidos +
               ", direccion=" + direccion + ", DNI=" + DNI + ", sueldo=" + sueldo + '}';
    }
}

class TrabajadorFijo extends Trabajador {
    public TrabajadorFijo(String nombre, String apellidos, String direccion, String DNI, double sueldo) {
        super(nombre, apellidos, direccion, DNI, sueldo);
    }

    public double calcularSueldo() {
        return this.sueldo;
    }

    @Override
    public String toString() {
        return super.toString() + " TrabajadorFijo{}";
    }
}

class TrabajadorComisionista extends Trabajador {
    public int ventas;
    public double porcentaje;

    public TrabajadorComisionista(String nombre, String apellidos, String direccion, String DNI,
                                   double sueldo, int ventas, double porcentaje) {
        super(nombre, apellidos, direccion, DNI, sueldo);
        this.ventas = ventas;
        this.porcentaje = porcentaje;
    }

    public double calcularSueldo() {
        return this.sueldo + (this.ventas * this.porcentaje);
    }

    @Override
    public String toString() {
        return super.toString() + " TrabajadorComisionista{ventas=" + ventas + ", porcentaje=" + porcentaje + '}';
    }
}

class TrabajadorPorHoras extends Trabajador {
    public int horas;
    public double precioHora, precioHoraExtra;

    public TrabajadorPorHoras(String nombre, String apellidos, String direccion, String DNI,
                               int horas, double precioHora, double precioHoraExtra) {
        super(nombre, apellidos, direccion, DNI, 0);
        this.horas = horas;
        this.precioHora = precioHora;
        this.precioHoraExtra = precioHoraExtra;
    }

    public double calcularSueldo() {
        if (horas <= 40)
            return horas * precioHora;
        else
            return (40 * precioHora) + ((horas - 40) * precioHoraExtra);
    }

    @Override
    public String toString() {
        return super.toString() + " TrabajadorPorHoras{horas=" + horas +
               ", precioHora=" + precioHora + ", precioHoraExtra=" + precioHoraExtra + '}';
    }
}

class Jefe extends Trabajador {
    public Jefe(String nombre, String apellidos, String direccion, String DNI, double sueldo) {
        super(nombre, apellidos, direccion, DNI, sueldo);
    }

    public double calcularSueldo() {
        return this.sueldo;
    }

    @Override
    public String toString() {
        return super.toString() + " Jefe{}";
    }
}

public class Problema_4_EjecutarSistemaNomina {
    public static void main(String[] args) {

        TrabajadorFijo tf = new TrabajadorFijo("Joel", "Cabrera", "Bellavista", "A123", 1500.0);
        System.out.println(tf);
        System.out.println("Sueldo: " + tf.calcularSueldo());

        TrabajadorComisionista tc = new TrabajadorComisionista("Kiara", "Salome", "Centro", "B456", 800, 50, 10);
        System.out.println(tc);
        System.out.println("Sueldo: " + tc.calcularSueldo());

        TrabajadorPorHoras th = new TrabajadorPorHoras("Maria", "Wieckserki", "Norte", "C789", 45, 5, 8);
        System.out.println(th);
        System.out.println("Sueldo: " + th.calcularSueldo());

        Jefe jefe = new Jefe("Allyson", "Cartuche", "Sur", "D012", 3000);
        System.out.println(jefe);
        System.out.println("Sueldo: " + jefe.calcularSueldo());
    }
}
/**
 * Trabajador{nombre=Joel, apellidos=Cabrera, direccion=Bellavista, DNI=A123, sueldo=1500.0} TrabajadorFijo{}
Sueldo: 1500.0
Trabajador{nombre=Kiara, apellidos=Salome, direccion=Centro, DNI=B456, sueldo=800.0} TrabajadorComisionista{ventas=50, porcentaje=10.0}
Sueldo: 1300.0
Trabajador{nombre=Maria, apellidos=Wieckserki, direccion=Norte, DNI=C789, sueldo=0.0} TrabajadorPorHoras{horas=45, precioHora=5.0, precioHoraExtra=8.0}
Sueldo: 240.0
Trabajador{nombre=Allyson, apellidos=Cartuche, direccion=Sur, DNI=D012, sueldo=3000.0} Jefe{}
Sueldo: 3000.0
BUILD SUCCESSFUL (total time: 0 seconds)

 */