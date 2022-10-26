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
    private List<Die> dice;
    private boolean quit;
    private Set<String> skulls_index;
    private int skull_card;
    private String card = "";
    private int deductedPoints;
    private Set<String> treasures;
    private int seabattles = 0;
    private final Map<Integer, Integer> seabattles_score = new HashMap(){{
        put(2, 300);
        put(3, 500);
        put(4, 1000);
    }};
    private boolean isIOS=false;
    private ArrayList<String> values = new ArrayList<>(Arrays.asList("1","2","3"));
    private boolean sorceress=false;
    public Player(int id, int score){
        this.id = id;
        this.score = score;
        this.quit = false;
        this.dice = initializeDice();
        skulls_index = new HashSet<>();
        this.skull_card = 0;
        this.deductedPoints = 0;
        this.treasures = new HashSet<>();
        this.seabattles=0;
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
    public void setEnd(){this.quit=true;}
    public void endGame(){
        setScore(0);
        quit=true;
        if(this.treasures!=null && this.treasures.size()>0){
            System.out.println("here");
            List<Die> tres = buildTreasureList();
            calculateScore(tres);
        }
        if(this.seabattles !=0){
            System.out.println("xxx");
            this.deductedPoints = seabattles_score.get(this.seabattles) * (-1);
        }
        System.out.println("Turn ends");
    }
    public void landIslandOfSkull(){
        System.out.println("Enter Island of skulls");
        int numskulls = this.skulls_index.size() + this.skull_card;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            int nk = rollSome(br);
            if(nk - numskulls >=1){
                numskulls = nk;
            }else{
                break;
            }
            if(nk >6) break;
        };
        deductPoints(numskulls);
        System.out.println("Exit Island of Skulls");
    }
    public void skullCheck(boolean isIOS){
        if(this.getSkullSize()== 3 || (this.skulls_index.size() >=3 && this.seabattles >0)){
            endGame();
        } else if (this.getSkullSize() >3 && this.seabattles==0) {
//            isIOS = true;
            if (isIOS) landIslandOfSkull();
            endGame();
        }
    }
    public boolean getIsIOS(){
        return this.isIOS;
    }

    public void addSkulls(List<Die> d){
        //https://stackoverflow.com/questions/23674624/how-do-i-convert-a-java-8-intstream-to-a-list
        List<Integer> ind = IntStream.range(0, d.size())
                .filter(i -> Objects.equals("skull", d.get(i).getFace().trim()))
                .boxed().collect(Collectors.toList()) ;

        List<String> inds = ind.stream().map(Object::toString)
                .collect(Collectors.toList());
        this.skulls_index.addAll(inds);
    }
    public void setSkulls(String[] cs){
        System.out.println(cs[0]);
        if(cs[0].equals("1")) this.skull_card = 1;
        else this.skull_card = 2;
    }
    public void removeSkull(String index){
        this.skulls_index.remove(index);
    }
    public void rollSkulls(BufferedReader br) throws IOException{
        String sk=null;
        while(sk==null || !(sk.equals("no") || sk.equals("yes"))){
            System.out.println("Do you want to re-roll a skull (yes, no)? ");
            sk = br.readLine();
        }
        if(sk.equals("yes")) {
            this.sorceress = false;
            System.out.println("YOu have used your sorceress power");
            while(!sk.equals("end")){
                while(sk.trim().split("\\s+").length>1 || (!sk.matches("-?\\d+(\\.\\d+)?") || Integer.parseInt(sk)>7)) {
                    System.out.println("Select and remove/re-roll one skull (0 etc, press enter to exit): ");
                    sk = br.readLine();
                    if(sk.equals("")) break;
                }
                if(!sk.equals("")){
                    String ind = sk.trim();
                    removeSkull(ind);
                    System.out.println("Skulls: ");
                    printFortuneDice(this.skulls_index);
                    rerollSome(new String[]{ind});
                    addSkulls(this.dice);
                }
                sk = "end";
            }
        }
    }
    public boolean checkRollSelection(String[] selections){
        return selections.length >= 2 && Collections.disjoint(new ArrayList<>(skulls_index), Arrays.asList(selections))
                && (this.treasures==null?true:Collections.disjoint(this.treasures, Arrays.asList(selections)));
    }
    public void rerollSome(String[] index){
        for(int i=0;i<index.length;i++){
            this.dice.get(Integer.parseInt(index[i])).roll();
        }
    }
    public int rollSome(BufferedReader br){
        System.out.println("Enter dice # to re-roll (0 1 2 etc, the rest will be set aside): ");
        String lines = null;
        try {
            lines = br.readLine();
            String[] rolls_indices = lines.trim().split("\\s+");
            boolean openTreasure = false;
            while(!checkRollSelection(rolls_indices)){
                System.out.println("Must have at least 2 dice AND not SKULLS AND not ON chest card: ");
                if(this.treasures!=null && !openTreasure) System.out.println("type treasure to view the dice on treasure chest card");
                lines = br.readLine();
                if(this.treasures!=null) {
                    if (lines.equals("treasure")) {
                        printFortuneDice(this.treasures);
                        openTreasure=true;
                    }
                }
                rolls_indices = lines.trim().split("\\s+");
            }
            rerollSome(rolls_indices);
            addSkulls(this.dice);
            printDice();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.skulls_index.size();
    }
    public void rerollAll(){
        for(int i=0;i<this.dice.size();i++){
            if(!this.skulls_index.contains(Integer.toString(i))) {
                if(this.treasures!=null && this.treasures.contains(Integer.toString(i))){
                    continue;
                }
                this.dice.get(i).roll();
            }
        }
        addSkulls(this.dice);
    }

    public Set<String> getSkulls(){
        return this.skulls_index;
    }
    public int getSkullSize(){
        return this.getSkulls().size()+ this.skull_card;
    }
    public List<Die> getDice(){
        return this.dice;
    }

    public void setDice(List<Die> ld){
        this.dice = new ArrayList<>();
        this.dice.addAll(ld);
    }
    public List<Die> buildTreasureList(){
        List<Die> tres = new ArrayList<>();
        for(String i : this.treasures){
            tres.add(this.dice.get(Integer.parseInt(i)));
        }
        return tres;
    }
    public void activateSeaBattles(int num){
        this.seabattles=num;
    }
    public int getSeabattleNum(){
        return this.seabattles;
    }
    public void calculateScore(List<Die> ld){
        System.out.println("inside calculateScore: "+ld);
        if(this.seabattles > (int) ld.stream().filter(die -> die.getFace().trim().equalsIgnoreCase("saber")).count()
        || (this.seabattles >0 && this.skulls_index.size() >=3 )){
            this.deductedPoints = seabattles_score.get(this.seabattles) * (-1);
        }else {
            int monkey=0;
            for(DiceType i : DiceType.values()){
                int occurrences = (int) ld.stream().filter(die -> die.getFace().trim().equalsIgnoreCase(i.toString().trim())).count();
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
//                System.out.println(i.toString()+": "+occurrences);
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
            if(this.seabattles!=0){
                setScore(this.seabattles_score.get(this.seabattles));
            }
        }
    }
    public boolean checkFullChest(){
        if(this.skulls_index.size() >1) return false;
        final Map<String, Integer> counts = new HashMap<>();
        for(Die i : this.dice){
            if(!i.toString().trim().equals("coin") && !i.toString().trim().equals("diamond"))
                counts.merge(i.toString().trim(), 1, Integer::sum);
        }
        if(this.card.equals("MP")){
            counts.put("parrot",counts.getOrDefault("parrot", 0) +counts.get("monkey"));
            counts.remove("monkey");
        }
        for(Map.Entry<String, Integer> entry : counts.entrySet()){
            if(entry.getValue() <3) {
                if(this.seabattles >0 && !entry.getKey().equals("saber")) return false;
                else if(this.seabattles==0) return false;
            }
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
    public void deductPoints(int num){
        System.out.println("deduct points for other players");
        this.deductedPoints += num*100*(-1);
    }
    public int[] getInfo() {
        if(this.card.equals("captain")) return new int[]{this.score*2, this.deductedPoints*2,0};
        return new int[]{this.score, this.deductedPoints, this.seabattles};
    }
    public void addToTreasures(String[] indices){
        try{
            for(int i=0;i<indices.length;i++){
                this.treasures.add(indices[i]);
            }
        }catch(RuntimeException exp){
            System.out.println("Ops.. Try it in the next roll");
        }
    }
    public boolean removeFromTreasures(String[] indices){
        try{
            for(int i=0;i<indices.length;i++){
                if(!this.treasures.contains((indices[i]))) return false;
                this.treasures.remove(indices[i]);
            }
        }catch(RuntimeException exp){
            System.out.println("Ops.. Try it in the next roll");
        }
        return true;
    }
    public Set<String> getTreasures(){
        return this.treasures;
    }
    public void reset(){
        this.score = 0;
        this.dice = new ArrayList<>();
        this.quit = false;
        this.skulls_index = new HashSet<>();
        this.card = "";
        this.seabattles =0;
        this.deductedPoints = 0;
        this.treasures = null;
        this.values = new ArrayList<>(Arrays.asList("1","2","3"));
        this.sorceress = false;
        this.skull_card = 0;
    }
    public void printDice() {
        System.out.println(" _Dice0_    _Dice1_    _Dice2_    _Dice3_    _Dice4_    _Dice5_    _Dice6_    _Dice7_");
        System.out.println("_________  _________  _________  _________  _________  _________  _________  _________  ");
        System.out.println("|" + this.dice.get(0).toString()
                + "|  |" + this.dice.get(1).toString()
                + "|  |" +this.dice.get(2).toString()
                + "|  |" + this.dice.get(3).toString()
                + "|  |" + this.dice.get(4).toString()
                + "|  |" + this.dice.get(5).toString()
                + "|  |" + this.dice.get(6).toString()
                + "|  |" + this.dice.get(7).toString() + "|");
        System.out.println("_________  _________  _________  _________  _________  _________  _________  _________  ");
    }
    public void printFortuneDice(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        for(String i: set){
            sb.append(" _Dice"+i+"_") ;
            sb.append("   ");
        }
        sb.append("\n");
        for(String i: set){
            sb.append("_________  ");
        }
        sb.append("\n");

        for(String i: set){
            sb.append("|" + this.dice.get(Integer.parseInt(i)).toString()+ "|  ");
        }
        sb.append("\n");
        for(String i: set){
            sb.append("_________  ");
        }
        System.out.println(sb.toString());
    }
    public int getId(){
        return this.id;
    }
    public void modifyTreasures(BufferedReader br) throws IOException {
        String treasure=null;
        while(treasure==null || !(treasure.equals("no") || treasure.equals("yes"))){
            System.out.println("Do you want to modify treasure chest (yes, no)? ");
            treasure = br.readLine();
        }
        if(treasure.equals("yes")) {
            System.out.println("press 'end' to exit");
            while(!treasure.equals("end")){
                System.out.println("Select dice to be put on treasure chest (0 1 2 etc, press enter to exit add treasure): ");
                treasure = br.readLine();
                if(!treasure.equals("")){
                    String[] ind = treasure.trim().split("\\s+");
                    addToTreasures(ind);
                    System.out.println("Treasure chests: ");
                    printFortuneDice(this.treasures);
                }

                System.out.println("Select dice to be taken back from treasure chest (0 1 2 etc, press enter to exit remove treasure): ");
                treasure = br.readLine();
                if(!treasure.equals("")){
                    String[] ind = treasure.trim().split("\\s+");
                    if(!removeFromTreasures(ind)){
                        System.out.println("Please Only remove dice on treasure chest");
                    };
                    System.out.println("Treasure chests: ");
                    printFortuneDice(this.treasures);
                }
                System.out.println("press 'end' to exit");
            }
        }
    }
    public void play(String c){
        try{
            this.setCard(c);
            this.treasures = null;
            System.out.println("Your FC is: " +this.card);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            this.dice = initializeDice();
            if(this.card.split("-").length > 1){
                String[] cs = this.card.split("-");
                if(cs[1].equals("skull")){
//                    rerollSome(setSkulls(cs));
//                    addSkulls(this.dice);
                    setSkulls(cs);
                    this.dice.forEach(d -> d.roll());
                }else if(cs[1].equals("sword")){
                    activateSeaBattles(Integer.parseInt(cs[0]));
                    this.dice.forEach(d -> d.roll());
                }
            }else{
                if(this.card.equals("chest")) {
                    this.values.add("4");
                    this.treasures = new HashSet<>();
                }else if(this.card.equals("sorceress")){
                    this.sorceress = true;
                    this.values.add("4");
                }
                this.dice.forEach(d -> d.roll());
            }
            printDice();
            addSkulls(this.dice);
            skullCheck(true);
            while(!quit){
                System.out.println("Select an action: ");
                System.out.println("(1) Choose dice number to roll again");
                System.out.println("(2) Roll all again (except skulls)");
                System.out.println("(3) Score this round");
                if(this.treasures!=null){
                    System.out.println("(4) Modify treasures");
                }
                if(this.sorceress){
                    System.out.println("(4) re-roll one skull");
                }
                if(this.seabattles >0){
                    System.out.println("If you choose to score in the sea battle, " +
                            "you might lose or win points from your saber results "+ "(You need at least "+this.seabattles+
                            " sabers to win the score");
                }
                String ans = br.readLine();
                while(!values.stream().anyMatch(ans::equals)){
                    System.out.println("Select an action: ");
                    System.out.println("(1) Choose dice number to roll again");
                    System.out.println("(2) Roll all again (except skulls/chests)");
                    System.out.println("(3) Score this round");
                    if(this.treasures!=null){
                        System.out.println("(4) Modify treasures");
                    }
                    if(this.sorceress){
                        System.out.println("(4) re-roll one skull");
                    }
                    if(this.seabattles >0){
                        System.out.println("If you choose to score in the sea battle, " +
                                "you might lose or win points from your saber results");
                    }
                    ans = br.readLine();
                }
                if(ans.equals("1")){
                    System.out.println("Enter 1");
                    rollSome(br);
                    printDice();
                    skullCheck(true);
                }else if(ans.equals("2")){
                    System.out.println("Enter 2");
                    rerollAll();
                    printDice();
                    skullCheck(true);
                }else if(ans.equals("3")){
                    System.out.println("Enter 3");
                    quit = true;
                    calculateScore(this.dice);
                } else if(ans.equals("4")){
                    System.out.println("Enter 4");
                    if(this.treasures!=null) modifyTreasures(br);
                    if(this.sorceress) rollSkulls(br);
                    printDice();
                }
            }
        }catch(IOException exp){
            System.out.println("player error");
        }
    }
}
