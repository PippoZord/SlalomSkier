package it.unimi.di.sweng.slalom.presenters;

import java.util.Map;

public class FirstMancheStrategy implements OrderStrategy{
    @Override
    public int compare(Map.Entry<String, Double> t, Map.Entry<String, Double> t1) {
        return t.getValue().compareTo(t1.getValue());
    }
}
