package it.unimi.di.sweng.slalom.model;

import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Model implements Observable<HashMap<String, LinkedList<Double>>> {

  private final HashMap<String, LinkedList<Double>> times = new HashMap<>();
  private final List<Observer<HashMap<String, LinkedList<Double>>>> observers = new ArrayList<>();

  public void readFilePrimaManche(@NotNull Scanner s) {

    while (s.hasNextLine()) {
      String linea = s.nextLine();
      String[] el = linea.split(";");
      String name = el[0];
      double time = Double.parseDouble(el[1]);

      System.out.printf("time: [%g] name: [%s]\n", time, name);
      addInTimes(name, time);
    }
  }

  @Override
  public void notifyObservers() {
    for (Observer<HashMap<String, LinkedList<Double>>> obs: observers) {
      obs.update(this, this.getState());
    }
  }

  @Override
  public void addObserver(@NotNull Observer<HashMap<String, LinkedList<Double>>> obs) {
    observers.add(obs);
  }

  @Override
  public @NotNull HashMap<String, LinkedList<Double>> getState() {
    return times;
  }

  public void addInTimes(String name, double time){
    if (times.containsKey(name)) {
      times.get(name).add(time);
    } else {
      LinkedList<Double> tmp = new LinkedList<>();
      tmp.add(time);
      times.put(name, tmp);
    }
    notifyObservers();
  }
}
