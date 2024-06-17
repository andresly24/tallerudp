import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Sender {


    public InetAddress direccion;
    public int port;

    public InetAddress getDireccion() {
        return this.direccion;
    }

    public void setDireccion(InetAddress direccion) {
        this.direccion = direccion;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Sender(InetAddress direccion, int port) {
        this.direccion = direccion;
        this.port = port;
    }

    public Sender() {
    }

    public void conectar() {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner consola = new Scanner(System.in);
 
            System.out.println("Ejemplo: andres,1234");
            System.out.print("Nombre y Clave:");
            String userName = consola.nextLine();
            InetAddress nodo_address = InetAddress.getByName("localhost");
            int nodo_port = 2020;

            String mensajeName = userName;
            byte[] bufferName = mensajeName.getBytes();
            DatagramPacket packetName = new DatagramPacket(bufferName, bufferName.length, nodo_address, nodo_port);
            socket.send(packetName);    

            while (true) {
                
                System.out.print("Mensaje: ");
                String mensaje = userName + ": " + consola.nextLine();

                byte[] buffer = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, nodo_address, nodo_port);
                socket.send(packet);

                buffer = new byte[1500];
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                mensaje = (new String(buffer)).trim();
                System.out.println("Mensaje recibido: " + mensaje);
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
