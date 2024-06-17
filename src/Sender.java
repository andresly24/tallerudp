import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Sender {

    public Sender() {
    }

    public void conectar() {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner consola = new Scanner(System.in);

            System.out.print("Nombre: ");
            String userName = consola.nextLine();

            InetAddress nodo_address = InetAddress.getByName("localhost");
            int nodo_port = 2020;

            while (true) {
                
                System.out.print("Mensaje: ");
                String mensaje = userName + ": " + consola.nextLine();

                byte[] buffer = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, nodo_address, nodo_port);
                socket.send(packet);
            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sender objeto = new Sender();
        objeto.conectar();
    }
}
