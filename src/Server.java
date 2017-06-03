/**
 * Created by orrko_000 on 03/06/2017.
 */

public class Server {
     Server(){
        MyTcpIpServer server = new MyTcpIpServer(9000);
        server.startServer(50);

    }
}
