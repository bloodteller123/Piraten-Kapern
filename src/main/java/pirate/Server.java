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
    private Map<Integer, ClientHandler> clients = new HashMap<>();;
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
                ClientHandler clientSock = new ClientHandler(clientSocket, id, this);

                System.out.println(this);
                clients.put(id, clientSock);
                writers.put(id, clientSock.out);
                id++;
                new Thread(clientSock).start();

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
    private class ClientHandler implements Runnable {
        Socket clientSocket;
        int player_id;
        Server server;
        ObjectInputStream in;
        ObjectOutputStream out;
        Player player;
        public ClientHandler(Socket socket, int id, Server server) {

            clientSocket = socket;
            this.player_id = id;
            this.server = server;
            try{
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
            }catch(IOException exp){
                exp.printStackTrace();
            }
        }

        @Override
        public void run(){
            try{
                while(!this.server.gameStart()){
                    this.player = new Player(player_id, 0);
                    out.writeObject(this.player);
                    out.flush();
                    out.reset();
                    players.add(this.player);
                    System.out.println(this.player.getDice());
                    System.out.println(player_id);
                }
                System.out.println("Enter game loop"+ " from thread" +Thread.currentThread().getId());
            }catch(IOException exp){
                exp.printStackTrace();
            }
        }
    }
}
