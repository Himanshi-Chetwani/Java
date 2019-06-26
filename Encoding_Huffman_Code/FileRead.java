import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileRead {
    public static void main(String[] args) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(
                "Output.txt"));
        int a;
        int counter = 0;
        while ((a = dis.read()) != -1 && counter < 50) {
            System.out.println(Integer.toBinaryString(a));
            counter++;
        }
    }
}
