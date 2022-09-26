package pirate;

public enum DiceType {
    SKULL("skull  "),
    DIAMOND("diamond"),
    MONKEY("monkey "),
    SABRE("saber  "),
    COIN("coin   "),
    PARROT("parrot ");

    private String value;

    DiceType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}

