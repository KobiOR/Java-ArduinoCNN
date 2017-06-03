import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by orrko_000 on 03/06/2017.
 */

public class Server {
    Server() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9000);
            InetAddress ip=InetAddress.getLocalHost();
            ip.getHostAddress();

            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        {
        }
    }
}

