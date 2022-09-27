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
    private Set<String> skulls_index;;
    public Player(int id, int score){
        this.id = id;
        this.score = score;
        this.quit = false;
        this.dice = initializeDice();
        skulls_index = new HashSet<>();
    }

    public List<Die> initializeDice(){
        List<Die> d = new ArrayList<>();
        for(int i=0;i<8;i++){
            d.add(new Die(""));
        }
        return d;
    }

    public void addSkulls(List<Die> d){
        //https://stackoverflow.com/questions/23674624/how-do-i-convert-a-java-8-intstream-to-a-list
        List<Integer> ind = IntStream.range(0, d.size())
                .filter(i -> Objects.equals("skull", d.get(i).getFace().trim()))
                .boxed().collect(Collectors.toList()) ;
//        System.out.println("Skull index");
//        System.out.println("Skull size before add: "+this.skulls_index.size());
        List<String> inds = ind.stream().map(Object::toString)
                .collect(Collectors.toList());
//        inds.forEach(System.out::print);
        this.skulls_index.addAll(inds);
    }

    public void rerollSome(String[] index){
        for(int i=0;i<index.length;i++){
            this.dice.get(Integer.parseInt(index[i])).roll();
        }
        addSkulls(this.dice);
    }

    public void rerollAll(){
        for(int i=0;i<this.dice.size();i++){
            if(!this.skulls_index.contains(Integer.toString(i))) {
//                System.out.println(i);
                this.dice.get(i).roll();
            }
        }
        addSkulls(this.dice);
    }

    public Set<String> getSkulls(){
        return this.skulls_index;
    }

    public List<Die> getDice(){
        return this.dice;
    }

    public void setDice(List<Die> ld){
        this.dice.addAll(ld);
    }
}
