/*
Name : Calculator_Server.java
Anradha Bhave Himanshi Chetwan
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

interface OperationalFunctionalInterface {
    int operation(int a, int b);
}

interface CalculatorInterface {

    int performOperation(OperationalFunctionalInterface ofi, int num1, int num2);

    static void helpMessage(int exitCode) {
        System.out.println("Usage: <num><operation><num>");
        System.exit(exitCode);
    }
}

public class Calculator_Server  {
    static int port = 7676;


    public static void main(String[] args) throws IOException {
        DatagramSocket socket_d = new DatagramSocket(port);
        byte buffer[] = new byte[256];
        DatagramPacket packet_s = new DatagramPacket(buffer, buffer.length);
        socket_d.receive(packet_s);
        String incmingData = new String(packet_s.getData());
        int op1 = 0, op2 = 0;
        InetAddress inetAddress = packet_s.getAddress();
        incmingData = incmingData.trim();
        String[] result_array = incmingData.split("(?<=[-+*/])|(?=[-+*/])");
        System.out.println(Arrays.toString(result_array));
        if (result_array.length != 3) {
            String sendThis = "Usage: <num><operation><num>";
            buffer = sendThis.getBytes();
            packet_s = new DatagramPacket(buffer, buffer.length, inetAddress,
                    packet_s.getPort());
            socket_d.send(packet_s);
            CalculatorInterface.helpMessage(1);
        }
        try {
            op1 = Integer.parseInt(result_array[0]);
        } catch (NumberFormatException e) {
            String sendThis = "Usage: <num><operation><num>";
            buffer = sendThis.getBytes();
            packet_s = new DatagramPacket(buffer, buffer.length, inetAddress,
                    packet_s.getPort());
            socket_d.send(packet_s);
            CalculatorInterface.helpMessage(2);
        }

        if (!(result_array[1].equals("+") || result_array[1].equals("-") || result_array[1].equals(
                "*") || result_array[1].equals("/"))) {
            String sendThis = "Usage: <num><operation><num>";
            buffer = sendThis.getBytes();
            packet_s = new DatagramPacket(buffer, buffer.length, inetAddress,
                    packet_s.getPort());
            socket_d.send(packet_s);
            CalculatorInterface.helpMessage(3);
        }
        try {
            op2 = Integer.parseInt(result_array[2]);
        } catch (NumberFormatException e) {
            String sendThis ="Usage: <num><operation><num>";
            buffer = sendThis.getBytes();
            packet_s = new DatagramPacket(buffer, buffer.length, inetAddress,
                    packet_s.getPort());
            socket_d.send(packet_s);
            CalculatorInterface.helpMessage(2);
        }
        int resultUltimate;
        CalculatorInterface calObj =
                (OperationalFunctionalInterface ofi, int num1,
                 int num2) -> {
                    int finalResult = 0;
                    finalResult = ofi.operation(num1, num2);
                    return finalResult;
                };
        OperationalFunctionalInterface obj = (int number1, int number2) -> {
            int result = 0;
            if (result_array[1].equals("+")) {
                result = number1 + number2;
            } else if (result_array[1].equals("-")) {
                result = number1 - number2;
            } else if (result_array[1].equals("*")) {
                result = number1 * number2;
            } else if (result_array[1].equals("/")) {
                result = number1 / number2;
            }

            return result;
        };
        resultUltimate = calObj.performOperation(obj, op1, op2);
        String sendThis = "" + resultUltimate;
        buffer = sendThis.getBytes();
        packet_s = new DatagramPacket(buffer, buffer.length, inetAddress,
                packet_s.getPort());
        socket_d.send(packet_s);
        socket_d.close();
    }
}

