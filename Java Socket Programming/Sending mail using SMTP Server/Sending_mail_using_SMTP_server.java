import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Sending_mail_using_SMTP_server {
    
    private static DataOutputStream dataOutputStream;
    public static void main(String[] args) throws Exception{
        
        int delay = 1000;
        String userMail = "your email address";
        String userPassword = "your password";

        String userName = new String(Base64.getEncoder().encode(userMail.getBytes()));
        String password = new String(Base64.getEncoder().encode(userPassword.getBytes()));

        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("smtp.gmail.com",456);

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());

        send("EHLO smtp.gmail.com\r\n");
        Thread.sleep(delay);

        System.out.println("SERVER: " + bufferedReader.readLine());
        System.out.println("SERVER: " + bufferedReader.readLine());
        System.out.println("SERVER: " + bufferedReader.readLine());
        System.out.println("SERVER: " + bufferedReader.readLine());
        System.out.println("SERVER: " + bufferedReader.readLine());
        System.out.println("SERVER: " + bufferedReader.readLine());
        System.out.println("SERVER: " + bufferedReader.readLine());
        System.out.println("SERVER: " + bufferedReader.readLine());
        System.out.println("SERVER: " + bufferedReader.readLine());

        send("AUTH LOGIN\r\n");
        Thread.sleep(delay);
        System.out.println("SERVER: " + bufferedReader.readLine());

        send(userName + "\r\n");
        Thread.sleep(delay);
        System.out.println("SERVER: " + bufferedReader.readLine());

        sent(password + "\r\n");
        Thread.sleep(delay);
        System.out.println("SERVER: " + bufferedReader.readLine());

        send("MAIL FROM: <s1912076104@ru.ac.bd>\r\n");
        Thread.sleep(delay);
        System.out.println("SERVER: " + bufferedReader.readLine());

        send("RCPT TO: <asifzaman3180@gmail.com>\r\n");
        Thread.sleep(delay);
        System.out.println("SERVER: " + bufferedReader.readLine());

        send("DATA\r\n");
        Thread.sleep(delay);
        System.out.println("SERVER: " + bufferedReader.readLine());

        Thread.sleep(delay);
        send("Subject: 3Y1S\r\n");

        Thread.sleep(delay);
        send("Name => Mst Mhamuda Khatun\nID=>1912076104\r\n");

        Thread.sleep(delay);
        send(".\r\n");
        System.out.println("SERVER: " + bufferedReader.readLine());

        send("QUIT\r\n");

    }

    private static void send(String message) throws Exception{
        dataOutputStream.writeByte(message);
        System.out.println("CLIENT: " + message);
    }
}
