package it.unimi.di.sweng.slalom;


import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.presenters.FinalRankingPresenter;
import it.unimi.di.sweng.slalom.presenters.FirstViewPresenter;
import it.unimi.di.sweng.slalom.presenters.NextSkierPresenter;
import it.unimi.di.sweng.slalom.presenters.SecondManchePresenter;
import it.unimi.di.sweng.slalom.views.NextSkierView;
import it.unimi.di.sweng.slalom.views.RankView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Scanner;

public class Main extends Application {
  final public static int SKIER_NUM = 15;
  final public static int SKIER_NUM_TOTAL_RANK = 5;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("2022 - Women's Giant Slalom");

    NextSkierView nextSkier = new NextSkierView();

    RankView firstRun = new RankView("First manche", SKIER_NUM);
    RankView secondRun = new RankView("Second manche", SKIER_NUM);
    RankView totalRun = new RankView("Final ranking (first " + SKIER_NUM_TOTAL_RANK + " skiers)", SKIER_NUM_TOTAL_RANK);


    GridPane gridPane = new GridPane();
    gridPane.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    gridPane.add(nextSkier, 0, 0);
    GridPane.setColumnSpan(nextSkier, GridPane.REMAINING);
    gridPane.add(firstRun, 0, 1);
    gridPane.add(secondRun, 1, 1);

    gridPane.add(totalRun, 0, 2);
    GridPane.setColumnSpan(totalRun, GridPane.REMAINING);

    Model model = new Model();
    InputStream is = Main.class.getResourceAsStream("/first");
    assert is != null;
    Scanner s = new Scanner(is);
    model.readFilePrimaManche(s);

    FirstViewPresenter firstPresenter = new FirstViewPresenter(model, firstRun);
    SecondManchePresenter secondManchePresenter = new SecondManchePresenter(model, secondRun);
    NextSkierPresenter nextSkierPresenter = new NextSkierPresenter(model,nextSkier, firstRun);
    FinalRankingPresenter finalPresenter = new FinalRankingPresenter(model, totalRun);
    model.addObserver(firstPresenter);
    model.addObserver(secondManchePresenter);
    model.addObserver(nextSkierPresenter);
    model.addObserver(finalPresenter);


    Scene scene = new Scene(gridPane);
    primaryStage.setScene(scene);
    primaryStage.show();

    model.notifyObservers();
  }
}
