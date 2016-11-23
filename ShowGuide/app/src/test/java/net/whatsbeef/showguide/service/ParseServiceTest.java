package net.whatsbeef.showguide.service;

import net.whatsbeef.showguide.interfaces.AsyncTaskCallback;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by k.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ParseServiceTest {
    @Test
    public void testParseServiceAsyncTaskFake() {
        TestAsync mockedAsyncTask = mock(TestAsync.class);
        ParseService ps = ParseService.getInstance(mockedAsyncTask);
        ps.execute("FakeStr");
        Robolectric.flushBackgroundThreadScheduler();
        verify(mockedAsyncTask, times(1)).onTaskCompletedFailure(any(String.class));
    }

    private class TestAsync implements AsyncTaskCallback<Object> {
        @Override
        public void onTaskCompletedSuccess(Object result) {

        }

        @Override
        public void onTaskCompletedFailure(Object result) {

        }
    }

}