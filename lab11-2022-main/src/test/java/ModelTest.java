import it.unimi.di.sweng.slalom.Observer;
import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.presenters.FirstViewPresenter;
import it.unimi.di.sweng.slalom.views.RankView;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ModelTest {
    @Test
    public void ReadFromFileTest() throws FileNotFoundException {
        Model SUT = new Model();
        SUT.readFilePrimaManche(new Scanner(new File("./src/main/resources/first")));
        assertThat(SUT.getState().size()).isEqualTo(15);
    }

    @Test
    public void addIntTimesTest() {
        Model SUT = new Model();
        SUT.addInTimes("TIZIO CAIO", 56.4);
        SUT.addInTimes("TIZIO CAIO", 56.2);
        assertThat(SUT.getState().get("TIZIO CAIO").size()).isEqualTo(2);
    }

    @Test
    public void testNotifyObservers() {
        Observer<HashMap<String, LinkedList<Double>>> obs =  mock(Observer.class);
        Observer<HashMap<String, LinkedList<Double>>> obs1 = mock(Observer.class);

        Model SUT = new Model();

        SUT.addObserver(obs);
        SUT.addObserver(obs1);

        SUT.addInTimes("TIZIO CAIO", 56.4);
        verify(obs).update(SUT, SUT.getState());
        verify(obs1).update(SUT, SUT.getState());
    }

}
