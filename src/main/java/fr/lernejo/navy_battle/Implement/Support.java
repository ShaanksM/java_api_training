package fr.lernejo.navy_battle.Implement;
import java.util.ArrayList;
import java.util.List;

public class Support<S> {
    private final List<S> list = new ArrayList<>();

    public void set(S obj) {
        list.clear();
        list.add(obj);
    }

    public boolean isCASEVIDE() {
        return  list.isEmpty();
    }

    public boolean isNotCASEVIDE() {
        return !isCASEVIDE();
    }

    public S get() {
        if(list.isEmpty())
            throw new RuntimeException(" Vide !");

        return list.get(0);
    }
}
