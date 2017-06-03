/**
 * Created by orrko_000 on 03/06/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

    public class MyTcpIpServer  {

        private ServerSocket server;
        private ExecutorService executor;
        public MyTcpIpServer(int port) {

            try {
                server = new ServerSocket(port);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot listen on port " + port);
            }
        }
        public void startServer(int maxClientsNum) {
            executor = Executors.newFixedThreadPool(maxClientsNum);
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    while (true) {
                        try {

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
    }


