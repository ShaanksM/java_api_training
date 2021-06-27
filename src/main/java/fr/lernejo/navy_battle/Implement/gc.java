package fr.lernejo.navy_battle.Implement;

public enum gc {
    CASEVIDE("."),
    SUCCESSFUL_FIRE("X"), BATEAU("B"), MISSED_FIRE("o");
    private final String ltr;

    public String getltr() {
        return ltr;
    }

    gc(String ltr) { this.ltr = ltr; }

}
