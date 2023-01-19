package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.Observer;
import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.views.RankView;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FirstViewPresenter implements Observer<HashMap<String, LinkedList<Double>>> {

    private Model model;
    private final RankView rankView;

    private final FirstMancheStrategy strategy = new FirstMancheStrategy();
    public FirstViewPresenter(Model m, RankView view){
        model = m;
        rankView = view;
    }
    @Override
    public void update(@NotNull Observable<HashMap<String, LinkedList<Double>>> subject, @NotNull HashMap<String, LinkedList<Double>> state) {
        model = (Model) subject;
        Set<Map.Entry<String, LinkedList<Double>>> entry = state.entrySet();
        LinkedList<Map.Entry<String, Double>> list = new LinkedList<>();
        for (Map.Entry<String, LinkedList<Double>> e : entry) {
            list.add(Map.entry(e.getKey(), e.getValue().get(0)));
        }
        list.sort(strategy);
        updateView(list);
    }

    private void updateView(LinkedList<Map.Entry<String, Double>> list) {
        for (int i=0; i<list.size(); i++){
            String s = createString(list.get(i));
            rankView.set(i, s);
        }
    }

    private String createString(Map.Entry<String, Double> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.getKey());
        sb.append(" ");
        sb.append(list.getValue());
        return sb.toString();
    }
}
