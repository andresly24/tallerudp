import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver {

    public static final int PORT = 2020;

    public Receiver() {
    }

    public void conectar() {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("Receiver is working...");

            while (true) {
                byte[] buffer = new byte[1500];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String mensaje = (new String(buffer)).trim();
                System.out.println(mensaje);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Receiver objeto = new Receiver();
        objeto.conectar();
    }
}
