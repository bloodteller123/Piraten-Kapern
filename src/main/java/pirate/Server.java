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
    private boolean loop=true;
    private int turn_helper = 0;
    private int potential_winner_id = Integer.MIN_VALUE;
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
            writers.get(key).writeObject(msg);
        }
    }
    public void setTurn(){
        this.turn_helper += 1;
    }
    public int getTurn(){
        return this.turn_helper;
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

                    if(player_id == 1){
                        out.writeObject("true");
                        out.flush();
                        String ans = (String)in.readObject();;
                        while(ans == null || !ans.equals("s")){
                            ans = (String)in.readObject();;
                        }
                        this.server.toggleStart();
                        this.server.broadcast("Player 1 has started the game. ");
                    }else{
                        out.writeObject("false");
                        out.flush();
//                        System.out.println("sent false"+ " from thread" +Thread.currentThread().getId());
                        while(!this.server.gameStart()){
                            Thread.sleep(100);
                        }
                        System.out.println("Exit game start wait"+ " from thread" +Thread.currentThread().getId());
                    }
                }

                System.out.println("Enter game loop"+ " from thread" +Thread.currentThread().getId());

                while(!maxScoreReached || loop){
                    if(players.peek().getId() != this.player_id)  {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    };
                    System.out.println("Gameplay by "+this.player_id+ " from thread" +Thread.currentThread().getId());
                    if(this.player_id==1) this.server.setTurn();
                    out.writeObject("turn");
                    out.flush();
                    out.writeObject(fc.drawCard());
                    out.flush();
                    int[] info = (int[])in.readObject();
                    System.out.println(info[0]+ " from thread" +Thread.currentThread().getId());
                    scores.put(this.player_id, scores.getOrDefault(this.player_id, 0)+info[0]);
                    deduct(info[1], info[2]);
                    checkWin();

                    players.add(players.poll());
                    System.out.println("Next player Id: "+players.peek().getId() + " from thread" +Thread.currentThread().getId());
                    out.writeObject("next");
                    System.out.println("-----------------Turn "+this.server.getTurn()+ "------------------------");
                    for(Map.Entry<Integer, Integer> entry : scores.entrySet()){
                        System.out.println("Player"+entry.getKey() + " has scores: " + entry.getValue());
                    }
                    System.out.println("-----------------------------------------");
                }

//                for(Map.Entry<Integer, Integer> entry : scores.entrySet()){
//                    System.out.println("Player"+entry.getKey() + " has scores: " + entry.getValue());
//                }
                this.server.broadcast("Player"+potential_winner_id+" has won the game");
                this.server.broadcast("END");
            }catch(IOException exp){
                exp.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void deduct(int deducted, int isSeaBattles){
            System.out.println("Enter deduct: " +deducted);
            for (Player p : players){
                System.out.println(p.getId());
                if(p.getId() != this.player_id && isSeaBattles > 0) continue;
                if(p.getId() == this.player_id && isSeaBattles == 0) continue;

                if(scores.getOrDefault(p.getId(), 0)+deducted <0) scores.put(p.getId(),0);
                else scores.put(p.getId(), scores.getOrDefault(p.getId(), 0)+deducted);

                if(p.getId() == potential_winner_id && scores.getOrDefault(p.getId(),0) < 3000){
                    System.out.println("below 3000");
                    maxScoreReached = false;
                }
            }
        }
        public void checkWin(){
            if(scores.get(this.player_id) >= 3000){
                maxScoreReached = true;
//                System.out.println(this.player_id);
                if(this.player_id==potential_winner_id) loop=false;
                potential_winner_id = this.player_id;
                System.out.println("potential_winner_id "+potential_winner_id);
            }
            System.out.println("finish checkWin");
        }
    }
}
