import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 4444);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String message = scanner.nextLine();
                dataOutputStream.writeUTF(message);

                Thread.sleep(100);
                String response = dataInputStream.readUTF();
                System.out.println("Response from server : " + response);

                if (message.compareTo("End") == 0) {
                    break;
                }
            }
            socket.close();
            scanner.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}