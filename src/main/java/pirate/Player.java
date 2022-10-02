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
    private String card = "";
    private int deductedPoints;
    public Player(int id, int score){
        this.id = id;
        this.score = score;
        this.quit = false;
        this.dice = initializeDice();
        skulls_index = new HashSet<>();
        this.deductedPoints = 0;
    }

    public List<Die> initializeDice(){
        List<Die> d = new ArrayList<>();
        for(int i=0;i<8;i++){
            d.add(new Die(""));
        }
        return d;
    }

    public boolean isEnd(){
        return this.quit;
    }

    public void endGame(){
        quit=true;

        System.out.println("Turn ends");
    }

    public void skullCheck(){
        System.out.println("here");
        if(this.skulls_index.size() == 3){
            endGame();
        }
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
    public void removeSkull(String index){
        this.skulls_index.remove(index);
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
        this.dice = new ArrayList<>();
        this.dice.addAll(ld);
    }
    public void calculateScore(List<Die> ld){
        int monkey=0;
        for(DiceType i : DiceType.values()){

            int occurrences = (int) ld.stream().filter(die -> die.getFace().equalsIgnoreCase(i.toString())).count();
            if((i.toString().trim().equals("coin") && this.card.equals("coin")) ||
                    (i.toString().trim().equals("diamond") && this.card.equals("diamond"))){
                occurrences ++;
            }
            if(i.toString().trim().equals("monkey")&& this.card.equals(("MP"))){
                monkey = occurrences;
                continue;
            }
            //
            if(i.toString().trim().equals("parrot") && this.card.equals(("MP"))) occurrences += monkey;
            System.out.println(occurrences);
            int XofAKind = 0;
            switch(occurrences){
                case 3:
                    XofAKind = 100;
                    break;
                case 4:
                    XofAKind = 200;
                    break;
                case 5:
                    XofAKind = 500;
                    break;
                case 6:
                    XofAKind = 1000;
                    break;
                case 7:
                    XofAKind = 2000;
                    break;
                case 8:
                case 9:
                    XofAKind = 4000;
                    break;
                    default: XofAKind=0;
            }
            switch(i.toString().trim()){
                case "skull":
                    setScore(0);
                    break;
                case "diamond":
                    setScore(100 * occurrences + XofAKind);
                    break;
                case "monkey":
                    setScore(XofAKind);
                    break;
                case "saber":
                    setScore(XofAKind);
                    break;
                case "coin":
                    setScore(100 * occurrences + XofAKind);
                    break;
                case "parrot":
                    setScore(XofAKind);
                    break;
            }
        }
        // full chest
        if(checkFullChest()){
            setScore(500);
        };
    }
    public boolean checkFullChest(){
        if(this.skulls_index.size() >1) return false;
        final Map<String, Integer> counts = new HashMap<>();
        for(Die i : this.dice){
            if(!i.toString().equals("coin   ") && !i.toString().equals("diamond"))
                counts.merge(i.toString(), 1, Integer::sum);
        }
        if(this.card.equals("MP")){
            counts.put("parrot ",counts.getOrDefault("parrot ", 0) +counts.get("monkey "));
            counts.remove("monkey ");
        }
        for(Map.Entry<String, Integer> entry : counts.entrySet()){
            if(entry.getValue() <3) return false;
        }
        return true;
    }

    public void setCard(String c){
        this.card = c;
    }

    public String getCard(){
        return this.card;
    }
    public void setScore(int val) {
        this.score += val;
        this.score = this.score<0? 0 : this.score;
    }
    public int getScore(){
        return this.score;
    }
    public int[] getInfo() {
        if(this.card.equals("captain")) return new int[]{this.score*2, this.deductedPoints*2};
        return new int[]{this.score, this.deductedPoints};
    }
    public void reset(){
        this.score = 0;
        this.dice = new ArrayList<>();
        this.quit = false;
        this.skulls_index = new HashSet<>();
        this.card = "";
    }
}
