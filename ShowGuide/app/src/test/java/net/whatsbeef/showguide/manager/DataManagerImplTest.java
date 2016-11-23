package net.whatsbeef.showguide.manager;

import net.whatsbeef.showguide.model.ShowTimeModel;
import net.whatsbeef.showguide.service.DownloadService;
import net.whatsbeef.showguide.service.ParseService;
import net.whatsbeef.showguide.service.ServiceFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by k.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class DataManagerImplTest {

    @Mock
    private ServiceFactory mockedServiceFactory;

    @Mock
    private DownloadService mockedDownloadService;

    @Mock
    private ParseService mockedParserService;

    private DataManagerImpl dataManager;

    @Mock
    private Feedback<ShowTimeModel> feedback;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dataManager = DataManagerImpl.getInstance(RuntimeEnvironment.application.getApplicationContext(),
                mockedDownloadService, mockedParserService);

    }

    @Test
    public void testGetShowListData() {
        dataManager.getShowListData("abcd");
        verify(mockedDownloadService, times(1)).execute(any(String.class));

    }

    @Test
    public void testOnTaskCompletedSuccessWithString() {
        dataManager.onTaskCompletedSuccess("DummyStr");
        verify(mockedParserService, times(1)).execute(any(String.class));
    }

    @Test
    public void testOnTaskCompletedSuccessWithData() {
        ShowTimeModel mockedShowTimeModel = mock(ShowTimeModel.class);
        dataManager.onTaskCompletedSuccess(mockedShowTimeModel);
        assertEquals(dataManager.getShowTimeData(), mockedShowTimeModel);
    }

}
