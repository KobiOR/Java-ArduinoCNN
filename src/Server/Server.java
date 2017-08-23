package Server;

import javafx.scene.image.ImageView;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by orrko_000 on 03/06/2017.
 */

public class Server {
    private ServerSocket server;
    private ExecutorService executor;
    private ObjectInputStream in;
    private  ObjectOutputStream out;
    Server() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            InetAddress ip=InetAddress.getLocalHost();
            ip.getHostAddress();

            Socket clientSocket = serverSocket.accept();
             in = new ObjectInputStream(clientSocket.getInputStream());
             out = new ObjectOutputStream(clientSocket.getOutputStream());

            while (clientSocket.isConnected())
                if (in.available()>=4)
                    readImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        {
        }
    }

    public void startServer(int maxClientsNum) {
        executor = Executors.newFixedThreadPool(maxClientsNum);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true){
                    try {
                        System.out.print(".");
                        Socket socket = server.accept();
                        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    check();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                        });
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }
    public synchronized void check() throws  Exception {
        Thread thread = new Thread(new Runnable() {
            public void run()
            {
                while(true)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        });thread.start();



    }
    public void readImage(){
        try {
            int height =this.in.readInt();
            int width =this.in.readInt();
            ImageView image=new ImageView();
            image.setLayoutX(width);
            image.setLayoutY(height);
            for(int i = 0; i <height ; i++) {
                for(int j = 0; j <width ; j++) {
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }


    }
}

