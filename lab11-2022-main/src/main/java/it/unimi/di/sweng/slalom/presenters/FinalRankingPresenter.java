package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.Observer;
import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.views.RankView;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FinalRankingPresenter implements Observer<HashMap<String, LinkedList<Double>>>, Comparator<Map.Entry<String, LinkedList<Double>>> {

    private Model model;
    private final RankView rankView;

    public FinalRankingPresenter(Model m, RankView rW) {
        rankView = rW;
        model = m;
        m.addObserver(this);
    }

    @Override
    public void update(@NotNull Observable<HashMap<String, LinkedList<Double>>> subject, @NotNull HashMap<String, LinkedList<Double>> state) {
        model = (Model) subject;
        Set<Map.Entry<String, LinkedList<Double>>> entry = state.entrySet();
        List<Map.Entry<String, LinkedList<Double>>> list = new ArrayList<>();
        for (Map.Entry<String, LinkedList<Double>> e : entry) {
            if (e.getValue().size() > 1) {
                e.getValue().add(e.getValue().get(0) + e.getValue().get(1));
                list.add(e);
                list.sort(this);
            }
        }

        int i = 0;
        for(Map.Entry<String, LinkedList<Double>> skier : list) {
            if (i < rankView.size()){
                StringBuilder sb = new StringBuilder();
                sb.append(skier.getKey());
                sb.append(" ");
                sb.append(skier.getValue().get(0));
                sb.append(" ");
                sb.append(skier.getValue().get(1));
                sb.append(" ");
                sb.append(skier.getValue().get(2));
                rankView.set(i, sb.toString());
                i++;
            } else {
                break;
            }
        }

    }

    @Override
    public int compare(Map.Entry<String, LinkedList<Double>> t, Map.Entry<String, LinkedList<Double>> t1) {
        return t.getValue().get(2).compareTo(t1.getValue().get(2));
    }

}
