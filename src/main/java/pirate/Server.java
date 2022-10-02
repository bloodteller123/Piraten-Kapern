package pirate;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.*;


//https://www.infoworld.com/article/2853780/socket-programming-for-scalable-systems.html
//https://gist.github.com/jordsonneveld/1177411
//https://www.geeksforgeeks.org/establishing-the-two-way-communication-between-server-and-client-in-java/
public class Server {

    private ServerSocket serverSocket;
    private boolean start = false;
    private Map<Integer, ObjectOutputStream> writers = new HashMap<>();
    private Queue<Player> players = new LinkedList<>();
    private Map<Integer, Integer> scores = new HashMap<>();
    private FortuneCard fc;
    private boolean maxScoreReached = false;
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Specify a port number");
            System.exit(-1);
        }

        int port = Integer.parseInt(args[0]);
        System.out.println("starting server");
        Server server = new Server(port);
        server.runServer();
    }
    public Server(int port){
        try{
            serverSocket = new ServerSocket(port);
            fc = new FortuneCard();
        }catch (IOException exp){
            exp.printStackTrace();
        }
    }

    public void runServer(){
        int id = 1;
        System.out.println("runServer");
        while(!start){
            try{
                Socket clientSocket = serverSocket.accept();

            }catch(IOException exp){
                System.out.println("Accepting error" + exp.toString());
            }
        }
    }
    public void broadcast(String msg) throws IOException {
        for (Integer key : writers.keySet()) {
            System.out.println(key);
            writers.get(key).writeObject(msg);
        }
    }
    public void toggleStart(){
        start = !start;
    }
    public boolean gameStart(){
        return start;
    }
}
