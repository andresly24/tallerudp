import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Receiver {

    public static final int PORT = 2020;
    public List<Sender> usuarios = new ArrayList<>();

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

                
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String mensaje = (new String(buffer)).trim() ;
                buffer = mensaje.getBytes();

                List<InetAddress> direccionesUsuarios = new ArrayList<>();
                for(Sender usuario : usuarios){
                    direccionesUsuarios.add(usuario.direccion);
                }

                if (!direccionesUsuarios.contains(packet.getAddress())){
                    BufferedReader reader = new BufferedReader(new FileReader("src/user.txt"));
                    String linea;
                    while((linea = reader.readLine()) != null){
                        if (linea.trim().equals(mensaje)){
                            Sender sender = new Sender(address, port);
                            usuarios.add(sender);
                            System.out.println("Usuario Registrado");
                            break;
                        }
                        else{
                            System.out.println("Usuario no esta registrado");
                        }
                    }
                }
   

                for(Sender usuario : usuarios){
                     packet = new DatagramPacket(buffer, buffer.length, usuario.direccion, usuario.port);
                     socket.send(packet);
                }

                System.out.println(mensaje);
                System.out.println(direccionesUsuarios);
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
