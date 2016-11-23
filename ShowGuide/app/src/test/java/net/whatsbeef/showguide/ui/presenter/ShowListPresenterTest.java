package net.whatsbeef.showguide.ui.presenter;

import net.whatsbeef.showguide.BuildConfig;
import net.whatsbeef.showguide.interfaces.DataManager;
import net.whatsbeef.showguide.manager.Feedback;
import net.whatsbeef.showguide.model.ShowTimeModel;
import net.whatsbeef.showguide.ui.activity.ShowListActivity;
import net.whatsbeef.showguide.utils.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by k.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ShowListPresenterTest {

    ShowListActivity mainActivity;

    @Before
    public void setup() {
        mainActivity = Robolectric.buildActivity(ShowListActivity.class).create().get();
    }

    @Test
    public void testShowListPresenterConstructor() throws Exception {
        ShowListPresenter presenter = new ShowListPresenter(mainActivity);
        assertNotNull(presenter);
    }

    @Test
    public void testOnResumeFunctionality() throws Exception {
        ShowListActivity mockedActivity = mock(ShowListActivity.class);
        ShowListPresenter presenter = new ShowListPresenter(mockedActivity);
        assertNotNull(presenter);
        presenter.onResume();
        verify(mockedActivity, times(1)).showLoading();

    }

    @Test
    public void testDataLoaded() throws Exception {
        ShowListActivity mockedActivity = mock(ShowListActivity.class);
        when(mockedActivity.getContext()).thenReturn(mockedActivity);
        when(mockedActivity.getApplication()).thenReturn(RuntimeEnvironment.application);
        ShowListPresenter presenter = new ShowListPresenter(mockedActivity);
        assertNotNull(presenter);

        DataManager mockedDataManager = mock(DataManager.class);
        Feedback feedback = mock(Feedback.class);
        when(mockedDataManager.getShowTimeData()).thenReturn(mock(ShowTimeModel.class));
        presenter.dataManager = mockedDataManager;
        doNothing().when(presenter.dataManager).getShowListData(String.format(Constants.BASE_URL, 0), feedback);
        assertNotNull(presenter.dataManager.getShowTimeData());
    }


}