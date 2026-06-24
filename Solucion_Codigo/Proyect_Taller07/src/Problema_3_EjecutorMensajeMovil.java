/**
 * Problema03: Implemente un sistema de envío de mensajes a móviles. 
 * Existen dos tipos de mensajes que se pueden enviar entre móviles,
 * mensajes de texto (SMS) y mensajes que contienen imágenes (MMS).
 * Por un lado, los mensajes de texto contienen un mensaje en caracteres 
 * que se desea enviar de un móvil a otro. Por otro lado, los mensajes 
 * que contienen imágenes almacenan información sobre la imagen a enviar,
 * la cual se representará por el nombre del fichero que la contiene. 
 * Independientemente del tipo de mensaje, cada mensaje tendrá asociado
 * un remitente de dicho mensaje y un destinatario. Ambos estarán definidos
 * obligatoriamente por un número de móvil, y opcionalmente se podrá guardar 
 * información sobre su nombre. Además, los métodos enviarMensaje y 
 * visualizarMensaje deben estar definidos.
 * @author Joel Cabrera
 * @version 1.0
 */
class Mensaje {
    public String remitente;
    public String destinatario;
    public String numRemitente;
    public String numDestinatario;

    public Mensaje(String remitente, String destinatario, String numRemitente, String numDestinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.numRemitente = numRemitente;
        this.numDestinatario = numDestinatario;
    }

    public void guardarInformacion() {
        System.out.println("Remitente: " + remitente + " [" + numRemitente + "]");
        System.out.println("Destinatario: " + destinatario + " [" + numDestinatario + "]");
    }

    public void enviarMensaje() {
        System.out.println("Se envía el mensaje desde: " + remitente + " a " + destinatario);
    }

    public void visualizarMensaje() {
        System.out.println("De: " + remitente + " - Para: " + destinatario);
    }

    @Override
    public String toString() {
        return "Remitente: " + remitente + " (" + numRemitente + ")"
                + "\nDestinatario: " + destinatario + " (" + numDestinatario + ")";
    }
}

class Sms extends Mensaje {
    public String texto;

    public Sms(String remitente, String destinatario, String numRemitente, String numDestinatario, String texto) {
        super(remitente, destinatario, numRemitente, numDestinatario);
        this.texto = texto;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("SMS enviado de " + remitente + " a " + destinatario);
        System.out.println("Mensaje: " + texto);
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("Contenido del SMS");
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return super.toString() + "\nTexto: " + texto;
    }
}

class Mms extends Mensaje {
    public String imagen;

    public Mms(String remitente, String destinatario, String numRemitente, String numDestinatario, String imagen) {
        super(remitente, destinatario, numRemitente, numDestinatario);
        this.imagen = imagen;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("MMS enviado de " + remitente + " a " + destinatario);
        System.out.println("Imagen: " + imagen);
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("Contenido del MMS");
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return super.toString() + "\nImagen: " + imagen;
    }
}

public class Problema_3_EjecutorMensajeMovil {

    public static void main(String[] args) {

        System.out.println("Prueba SMS");
        Sms sms = new Sms("Joel", "Sofia", "0980715982", "099111902", "Cfm vamos a jugar");
        sms.enviarMensaje();
        System.out.println();
        sms.visualizarMensaje();
        System.out.println();

        System.out.println("Prueba MMS");
        Mms mms = new Mms("Kiara S", "Bryan ", "0980862495", "09678239", "imagen.png");
        mms.enviarMensaje();
        System.out.println();
        mms.visualizarMensaje();
        System.out.println();

        System.out.println("Guardar Informacion");
        sms.guardarInformacion();
        System.out.println();
        mms.guardarInformacion();
    }
}
/**
 * run:
Prueba SMS
SMS enviado de Joel a Sofia
Mensaje: Cfm vamos a jugar

Contenido del SMS
Remitente: Joel (0980715982)
Destinatario: Sofia (099111902)
Texto: Cfm vamos a jugar

Prueba MMS
MMS enviado de Kiara S a Bryan 
Imagen: imagen.png

Contenido del MMS
Remitente: Kiara S (0980862495)
Destinatario: Bryan  (09678239)
Imagen: imagen.png

Guardar Informacion
Remitente: Joel [0980715982]
Destinatario: Sofia [099111902]

Remitente: Kiara S [0980862495]
Destinatario: Bryan  [09678239]
BUILD SUCCESSFUL (total time: 0 seconds)
 */