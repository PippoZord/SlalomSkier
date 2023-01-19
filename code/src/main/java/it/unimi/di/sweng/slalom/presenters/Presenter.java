package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.Observer;

import java.util.HashMap;
import java.util.LinkedList;

public interface Presenter extends Observer<HashMap<String, LinkedList<Double>>>{
  void action(String text1, String text2);
}
