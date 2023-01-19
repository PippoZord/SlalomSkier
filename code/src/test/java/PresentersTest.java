import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.presenters.FirstViewPresenter;


import it.unimi.di.sweng.slalom.views.RankView;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PresentersTest  {

    @Test
    public void firstPresenterRankView() {
        RankView view = mock(RankView.class);
        Model model = new Model();
        FirstViewPresenter SUT = new FirstViewPresenter(model, view);
        model.addObserver(SUT);
        model.addInTimes("TIZIO", 56.8);
        verify(view).set(0, "TIZIO 56.8");
    }
}
