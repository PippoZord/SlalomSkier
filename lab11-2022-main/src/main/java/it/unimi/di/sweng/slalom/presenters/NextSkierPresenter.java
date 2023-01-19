package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.views.NextSkierView;
import it.unimi.di.sweng.slalom.views.RankView;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;


public class NextSkierPresenter implements Presenter{


    private Model model;
    private final RankView rankView;
    private final NextSkierView nextView;

    private static int NEXT_SKIER = 14;

    public NextSkierPresenter(Model m, NextSkierView nV, RankView view){
        model = m;
        rankView = view;
        nextView = nV;
        m.addObserver(this);
        nextView.addHandlers(this);
    }
    @Override
    public void action(String text1, String text2) {
        if (!text1.equals("END OF SLALOM")) {
            model.addInTimes(text1, Double.parseDouble(text2));
        }
    }

    @Override
    public void update(@NotNull Observable<HashMap<String, LinkedList<Double>>> subject, @NotNull HashMap<String, LinkedList<Double>> state) {
        if (NEXT_SKIER >= 0) {
            model = (Model) subject;
            nextView.setName(rankView.get(NEXT_SKIER).substring(0, rankView.get(NEXT_SKIER).length() - 6));
            NEXT_SKIER--;
        } else {
            nextView.setName("END OF SLALOM");
        }
    }
}
