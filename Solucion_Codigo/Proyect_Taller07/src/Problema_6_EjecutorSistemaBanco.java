/**
 * Problema 06: El banco UN BANCO mantiene las cuentas de varios clientes.
 * Los datos que describen a cada una de las cuentas consisten en el número
 * de cuenta, el nombre del cliente y el balance actual. Escriba una clase 
 * para implementar dicha cuenta bancaria. El método constructor debe aceptar 
 * como parámetros el número de cuenta y el nombre. Debe proporcionarse métodos
 * para depositar o retirar una cantidad de dinero y obtener el balance actual.
 * El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS.
* Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero),
* pero una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre
* la cantidad que tenga la cuenta de ahorros. Este interés se suma al balance.
* Escriba clases para describir cada uno de estos tipos de cuentas, haciendo un
* máximo uso de la herencia. La clase de la cuenta de ahorros debe proporcionar
* un método que sea invocado para calcular el interés. Además, el banco está
* pensando en implementar una cuenta PLATINO que viene siendo similar a los
* otros dos tipos anteriores de cuentas bancarias, ésta tiene el interés del 
* 10%, sin cargos ni castigos por sobregiro.
 * @author Joel Cabrera
 * @version 1.0
 */
class Cuenta {

    public String numCuenta;
    public String nomCliente;
    public double balance;

    public Cuenta(String numCuenta, String nomCliente) {
        this.numCuenta = numCuenta;
        this.nomCliente = nomCliente;
        this.balance = 0.0;
    }

    public void depositar(double cantidad) {
        balance += cantidad;
        System.out.println("Deposito de " + cantidad + " realizado. Balance actual: " + balance);
    }

    public void retirar(double cantidad) {
        balance -= cantidad;
        System.out.println("Retiro de " + cantidad + " realizado. Balance actual: " + balance);
    }

    public double getBalanceActual() {
        return balance;
    }

    @Override
    public String toString() {
        return "Cuenta: " + numCuenta
                + "\nCliente: " + nomCliente
                + "\nBalance: " + balance;
    }
}

class Cheques extends Cuenta {

    public boolean sob;

    public Cheques(String numCuenta, String nomCliente, double balance, boolean sob) {
        super(numCuenta, nomCliente);
        this.balance = balance;
        this.sob = sob;
    }

    @Override
    public void retirar(double cantidad) {
        if (!sob && balance - cantidad < 0) {
            System.out.println("Error");
            return;
        }
        balance -= cantidad;
        System.out.println("Retiro de " + cantidad + " realizado, ahora tiene " + balance);
    }

    @Override
    public String toString() {
        return "Cuenta Cheques\n" + super.toString() + "\nPermite sobregiro: " + sob;
    }
}

class Ahorro extends Cuenta {

    public double intAhorro;

    public Ahorro(String numCuenta, String nomCliente, double balance, double intAhorro) {
        super(numCuenta, nomCliente);
        this.balance = balance;
        this.intAhorro = intAhorro;
    }

    @Override
    public void retirar(double cantidad) {
        if (balance - cantidad < 0) {
            System.out.println("Error: Cuenta de ahorros no puede tener balance negativo.");
            return;
        }
        balance -= cantidad;
        System.out.println("Retiro de " + cantidad + " realizado. Balance actual: " + balance);
    }

    public double calcularInteres() {
        if (balance > 0) {
            double intGanado = balance * intAhorro;
            balance += intGanado;
            System.out.println("Interes calculado: " + intGanado + " - Nuevo balance: " + balance);
            return intGanado;
        }
        System.out.println("Interes calculado: 0.0 - Nuevo balance: " + balance);
        return 0.0;
    }

    @Override
    public String toString() {
        return "Cuenta Ahorros\n" + super.toString() + "\nTasa de interes: " + intAhorro;
    }
}

class Platino extends Cuenta {

    public double intPlatino = 0.10;

    public Platino(String numCuenta, String nomCliente, double balance) {
        super(numCuenta, nomCliente);
        this.balance = balance;
    }

    @Override
    public void retirar(double cantidad) {
        balance -= cantidad;
        System.out.println("Retiro de " + cantidad + " realizado. Balance actual: " + balance);
    }

    public double calcularInteres() {
        if (balance > 0) {
            double intGanado = balance * intPlatino;
            balance += intGanado;
            System.out.println("Interes Platino: " + intGanado + " - Nuevo balance: " + balance);
            return intGanado;
        }
        System.out.println("Interes Platino: 0.0 - Nuevo balance: " + balance);
        return 0.0;
    }

    @Override
    public String toString() {
        return "Cuenta Platino\n" + super.toString() + "\nTasa de interes: " + intPlatino;
    }
}

public class Problema_6_EjecutorSistemaBanco {

    public static void main(String[] args) {
        Cheques cheque1 = new Cheques("1234", "Maria Sofia", 1000.0, true);
        Ahorro ah1 = new Ahorro("5627", "Juan Velasco", 200.0, 0.10);
        Platino p1 = new Platino("8907", "Guillermo Guajala", 1600.0);

        System.out.println("Estado Inicial");
        System.out.println();
        System.out.println(cheque1);
        System.out.println();
        System.out.println(ah1);
        System.out.println();
        System.out.println(p1);
        System.out.println();

        System.out.println("Operaciones");
        System.out.println();

        System.out.println("Prueba Cheques");
        cheque1.depositar(200.0);
        cheque1.retirar(800.0);
        System.out.println();

        System.out.println("Prueba Ahorros");
        ah1.depositar(500.0);
        ah1.retirar(2000.0);
        ah1.calcularInteres();
        System.out.println();

        System.out.println("Prueba Platino");
        p1.depositar(1000.0);
        p1.retirar(4000.0);
        p1.calcularInteres();
        System.out.println();

        System.out.println("Estado Final");
        System.out.println();
        System.out.println(cheque1);
        System.out.println();
        System.out.println(ah1);
        System.out.println();
        System.out.println(p1);
    }
}
/**
 * run:
Estado Inicial

Cuenta Cheques
Cuenta: 1234
Cliente: Maria Sofia
Balance: 1000.0
Permite sobregiro: true

Cuenta Ahorros
Cuenta: 5627
Cliente: Juan Velasco
Balance: 200.0
Tasa de interes: 0.1

Cuenta Platino
Cuenta: 8907
Cliente: Guillermo Guajala
Balance: 1600.0
Tasa de interes: 0.1

Operaciones

Prueba Cheques
Deposito de 200.0 realizado. Balance actual: 1200.0
Retiro de 800.0 realizado, ahora tiene 400.0

Prueba Ahorros
Deposito de 500.0 realizado. Balance actual: 700.0
Error: Cuenta de ahorros no puede tener balance negativo.
Interes calculado: 70.0 - Nuevo balance: 770.0

Prueba Platino
Deposito de 1000.0 realizado. Balance actual: 2600.0
Retiro de 4000.0 realizado. Balance actual: -1400.0
Interes Platino: 0.0 - Nuevo balance: -1400.0

Estado Final

Cuenta Cheques
Cuenta: 1234
Cliente: Maria Sofia
Balance: 400.0
Permite sobregiro: true

Cuenta Ahorros
Cuenta: 5627
Cliente: Juan Velasco
Balance: 770.0
Tasa de interes: 0.1

Cuenta Platino
Cuenta: 8907
Cliente: Guillermo Guajala
Balance: -1400.0
Tasa de interes: 0.1
BUILD SUCCESSFUL (total time: 0 seconds)
 */