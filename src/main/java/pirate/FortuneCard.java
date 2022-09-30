package pirate;

import java.util.ArrayList;
import java.util.*;

public class FortuneCard {
    private List<String> cards = null;
    private int index;
    public FortuneCard(){
        cards = new ArrayList<>();
        index = 0;
        buildCards(cards);
        Collections.shuffle(cards);
    }

    private void buildCards(List<String> c){
        String[] cards = new String[]{"chest","chest","chest","chest","sorceress",
        "sorceress","sorceress","sorceress","captain","captain","captain","captain",
        "MP","MP","MP","MP", "diamond","diamond","diamond","diamond","coin","coin","coin","coin",
        "2-skull","2-skull", "1-skull","1-skull","1-skull","2-sword","2-sword","3-sword","3-sword","4-sword","4-sword"};
        c.addAll(Arrays.asList(cards));
    }

    public List<String> getFC(){
        return this.cards;
    }
}
