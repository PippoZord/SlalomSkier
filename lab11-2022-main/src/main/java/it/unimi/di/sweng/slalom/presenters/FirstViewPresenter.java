package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.Observer;
import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.views.RankView;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FirstViewPresenter implements Observer<HashMap<String, LinkedList<Double>>> {

    private Model model;
    private RankView rankView;

    private final FirstMancheStrategy strategy = new FirstMancheStrategy();
    public FirstViewPresenter(Model m, RankView view){
        model = m;
        rankView = view;
    }
    @Override
    public void update(@NotNull Observable<HashMap<String, LinkedList<Double>>> subject, @NotNull HashMap<String, LinkedList<Double>> state) {
        model = (Model) subject;
        Set<Map.Entry<String, LinkedList<Double>>> entry = state.entrySet();
        LinkedList<Map.Entry<String, LinkedList<Double>>> list = new LinkedList<>(entry);
        list.sort(strategy);
        updateView(list);
    }

    private void updateView(LinkedList<Map.Entry<String, LinkedList<Double>>> list) {
        for (int i=0; i<list.size(); i++){
            String s = createString(list.get(i));
            rankView.set(i, s);
        }
    }

    private String createString(Map.Entry<String, LinkedList<Double>> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.getKey());
        sb.append(" ");
        sb.append(list.getValue().get(0));
        return sb.toString();
    }
}
