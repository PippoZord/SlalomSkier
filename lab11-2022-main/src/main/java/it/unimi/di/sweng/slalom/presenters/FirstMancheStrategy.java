package it.unimi.di.sweng.slalom.presenters;

import java.util.LinkedList;
import java.util.Map;

public class FirstMancheStrategy implements OrderStrategy{
    @Override
    public int compare(Map.Entry<String, LinkedList<Double>> t, Map.Entry<String, LinkedList<Double>> t1) {
        return t.getValue().get(0).compareTo(t1.getValue().get(0));
    }
}
