package it.unimi.di.sweng.slalom.presenters;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public interface OrderStrategy extends Comparator<Map.Entry<String, LinkedList<Double>>> {
}
