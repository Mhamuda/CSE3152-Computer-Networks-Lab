import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(4444);
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {

                String message = dataInputStream.readUTF();
                System.out.println("Message from client : " + message);
                Thread.sleep(100);
                dataOutputStream.writeUTF("Message successfully received.");

                if (message.compareTo("End") == 0) {
                    break;
                }
            }

            serverSocket.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}