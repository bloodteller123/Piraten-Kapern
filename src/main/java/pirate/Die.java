package pirate;

import java.io.Serializable;
import java.util.*;
public class Die implements Serializable {
    private static final long serialVersionUID = 1L;
    private String current_face;
    Die(String value) {
        this.current_face = value;
    }

    public void roll() {
        setFace(DiceType.values()[new Random().nextInt(DiceType.values().length)].toString());
    }

    public void setFace(String value) {
        this.current_face = value;
    }

    public String getFace() {
        return this.current_face;
    }

    @Override
    public String toString(){
        return getFace();
    }


}
