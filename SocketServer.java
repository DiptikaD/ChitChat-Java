import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//run server first, then run the client for additional chatters

public class SocketServer {
    ServerSocket server;
    Socket sk;
    InetAddress addr;

    ArrayList<ServerThread> list = new ArrayList<ServerThread>();
    Logger logger = Logger.getLogger(SocketServer.class.getName());
    FileHandler fileHandler = new FileHandler("/Users/devi/Dev/Projects/ChitChat-Java/log.txt", true);


    public SocketServer() throws IOException {
//        Logger logger = Logger.getLogger(SocketServer.class.getName());
//        FileHandler fileHandler = new FileHandler("/Users/devi/Dev/Projects/ChitChat-Java/log.txt", true);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        try {
            //loopback address which is used to refer to itself
        	addr = InetAddress.getByName("127.0.0.1");
        	//addr = InetAddress.getByName("192.168.43.1");
            
        	server = new ServerSocket(1234,50,addr);
            logger.info("\n Waiting for Client connection");
            SocketClient.main(null);
            while(true) {
                sk = server.accept();
                logger.info(sk.getInetAddress() + " connect");
                //Thread connected clients to ArrayList
                ServerThread st = new ServerThread(this);
                addThread(st);
                st.start();
            }
        } catch(IOException e) {
            logger.severe(e + "-> ServerSocket failed");
        }
    }

    public void addThread(ServerThread st) {
        list.add(st);
    }

    public void removeThread(ServerThread st){
        list.remove(st); //remove
    }

    public void broadCast(String message){
        for(ServerThread st : list){
            st.pw.println(message);
        }
    }

    public static void main(String[] args) throws IOException {
        new SocketServer();
    }
}

class ServerThread extends Thread {
    SocketServer server;
    PrintWriter pw;
    String name;

    public ServerThread(SocketServer server) throws IOException {
        this.server = server;
    }

    Logger logger = Logger.getLogger(SocketServer.class.getName());
    FileHandler fileHandler = new FileHandler("/Users/devi/Dev/Projects/ChitChat-Java/log.txt", true);

//    public String getUserName() {
//        return name;
//    }

    @Override
    public void run() {
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        try {
            // read
            BufferedReader br = new BufferedReader(new InputStreamReader(server.sk.getInputStream()));

            // writing
            pw = new PrintWriter(server.sk.getOutputStream(), true);
            name = br.readLine();
            server.broadCast("**["+name+"] Entered**");

            String data;
            while((data = br.readLine()) != null ){
                if(data == "/list"){
                    pw.println("a");
                }
                server.broadCast("["+name+"] "+ data);
            }
        } catch (Exception e) {
            //Remove the current thread from the ArrayList.
            server.removeThread(this);
            server.broadCast("**["+name+"] Left**");
            logger.info(server.sk.getInetAddress()+" - ["+name+"] Exit");
            logger.severe(e + "---->");
        }
    }
}