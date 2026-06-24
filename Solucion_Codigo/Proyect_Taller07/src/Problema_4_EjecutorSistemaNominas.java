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
 * @version 1.0
 */
import java.util.Arrays;

class Trabajador {

    public String nombre, apellidos, direccion,dni;
    public double sueldo;

    public Trabajador(String nombre, String apellidos, String direccion, String dni, double sueldo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.sueldo = sueldo;
    }

    public double calcularTotalSueldo() {
        return sueldo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellidos
                + "\nDNI: " + dni
                + "\nDireccion: " + direccion
                + "\nSueldo base: " + sueldo;
    }
}

class TrabajadorFijoMensual extends Trabajador {

    public TrabajadorFijoMensual(String nombre, String apellidos, String direccion, String dni, double sueldo) {
        super(nombre, apellidos, direccion, dni, sueldo);
    }

    @Override
    public double calcularTotalSueldo() {
        return sueldo;
    }

    @Override
    public String toString() {
        return "**** Trabajador Fijo Mensual ****"
                + "\n" + super.toString()
                + "\nTotal nomina:" + calcularTotalSueldo();
    }
}

class TrabajadorComisionista extends Trabajador {

    public int ventas;
    public double porcentaje;

    public TrabajadorComisionista(String nombre, String apellidos, String direccion, String dni, double sueldo, int ventas, double porcentaje) {
        super(nombre, apellidos, direccion, dni, sueldo);
        this.ventas = ventas;
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularTotalSueldo() {
        return sueldo+(ventas * porcentaje / 100);
    }

    @Override
    public String toString() {
        return "**** Trabajador Comisionista ****"
                + "\n" + super.toString()
                + "\nVentas: " + ventas
                + "\nPorcentaje comision: " + porcentaje 
                + "\nTotal nomina: " + calcularTotalSueldo();
    }
}

class TrabajadorPorHoras extends Trabajador {

    public double precio;
    public double horasTrabajadas;
    public double precioHorasExtra;

    public TrabajadorPorHoras(String nombre, String apellidos, String direccion, String dni, double sueldo, double precio, double horasTrabajadas, double precioHorasExtra) {
        super(nombre, apellidos, direccion, dni, sueldo);
        this.precio = precio;
        this.horasTrabajadas = horasTrabajadas;
        this.precioHorasExtra = precioHorasExtra;
    }

    @Override
    public double calcularTotalSueldo() {
        if (horasTrabajadas <= 40) {
            return horasTrabajadas * precio;
        } else {
            return (40 * precio) + ((horasTrabajadas - 40) * precioHorasExtra);
        }
    }

    @Override
    public String toString() {
        return "****Trabajador Por Horas***"
                + "\n" + super.toString()
                + "\nHoras trabajadas: " + horasTrabajadas
                + "\nPrecio hora normal: " + precio
                + "\nPrecio hora extra: " + precioHorasExtra
                + "\nTotal nomina: " + calcularTotalSueldo();
    }
}

class Jefe extends Trabajador {

    public Jefe(String nombre, String apellidos, String direccion, String dni, double sueldo) {
        super(nombre, apellidos, direccion, dni, sueldo);
    }

    @Override
    public double calcularTotalSueldo() {
        return sueldo;
    }

    @Override
    public String toString() {
        return "**** Jefe ****"
                + "\n" + super.toString()
                + "\nTotal nomina: " + calcularTotalSueldo();
    }
}

public class Problema_4_EjecutorSistemaNominas {
    public static void main(String[] args) {

        Jefe jefe = new Jefe("Juan ", "Luis ", "Punzara", "123", 1500);

        TrabajadorFijoMensual fijo = new TrabajadorFijoMensual(
                "Daniel", "Caraguay", "Tebaida", "456", 700);

        TrabajadorComisionista comisionista = new TrabajadorComisionista(
                "Maria", "Juana", "Belen", "789", 250, 100, 9);

        TrabajadorPorHoras porHoras = new TrabajadorPorHoras(
                "Kiara", "Condoy", "Eltermnial", "107", 670, 5, 60, 8);

        System.out.println(jefe);
        System.out.println();
        System.out.println(fijo);
        System.out.println();
        System.out.println(comisionista);
        System.out.println();
        System.out.println(porHoras);
    }
}
/**
 * run:
**** Jefe ****
Nombre: Juan  Luis 
DNI: 123
Direccion: Punzara
Sueldo base: 1500.0
Total nomina: 1500.0

**** Trabajador Fijo Mensual ****
Nombre: Daniel Caraguay
DNI: 456
Direccion: Tebaida
Sueldo base: 700.0
Total nomina:700.0

**** Trabajador Comisionista ****
Nombre: Maria Juana
DNI: 789
Direccion: Belen
Sueldo base: 250.0
Ventas: 100
Porcentaje comision: 9.0
Total nomina: 259.0

****Trabajador Por Horas***
Nombre: Kiara Condoy
DNI: 107
Direccion: Eltermnial
Sueldo base: 670.0
Horas trabajadas: 60.0
Precio hora normal: 5.0
Precio hora extra: 8.0
Total nomina: 360.0
BUILD SUCCESSFUL (total time: 0 seconds)
 */

