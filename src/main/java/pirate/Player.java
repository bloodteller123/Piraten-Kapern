package pirate;


import java.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private int score;
    private final int id;
    private static List<Die> dice;;
    private boolean quit;

    public Player(int id, int score){
        this.id = id;
        this.score = score;
        this.quit = false;
        this.dice = initializeDice();
    }

    public List<Die> initializeDice(){
        List<Die> d = new ArrayList<>();
        for(int i=0;i<8;i++){
            d.add(new Die(""));
        }
        return d;
    }

    public List<Die> getDice(){
        return this.dice;
    }

}
