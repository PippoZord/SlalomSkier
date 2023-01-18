import it.unimi.di.sweng.slalom.model.Model;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

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

}
