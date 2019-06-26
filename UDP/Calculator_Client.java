/*
Name : Calculator_Client.java
Anradha Bhave Himanshi Chetwan
 */
import java.net.*;

public class Calculator_Client {
    int port = 7676;

    public void doTheJob(String input) throws Exception {
        byte buf[] = input.getBytes();
        InetAddress aInetAddress = InetAddress.getByName("glados.cs.rit.edu");
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(buf,
                buf.length, aInetAddress, port);
        socket.send(packet);
        buf = new byte[256];
        DatagramPacket packet_Recv = new DatagramPacket(buf, buf.length);
        socket.receive(packet_Recv);
        System.out.println("Output : " + new String(packet_Recv.getData()));
        socket.close();

    }

    public String parseArgs(String[] args) {
        return args[0] + args[1] + args[2];
    }

    public static void main(String[] args) throws Exception {
        Calculator_Client calculator_client = new Calculator_Client();
        String input = calculator_client.parseArgs(args);
        calculator_client.doTheJob(input);

    }
}
