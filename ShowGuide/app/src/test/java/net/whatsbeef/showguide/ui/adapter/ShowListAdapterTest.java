package net.whatsbeef.showguide.ui.adapter;

import android.databinding.ViewDataBinding;
import android.widget.LinearLayout;

import net.whatsbeef.showguide.SLTestRunner;
import net.whatsbeef.showguide.model.Result;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by k.
 */

@RunWith(SLTestRunner.class)
public class ShowListAdapterTest {

    @Test
    public void testAdapterConstructor() throws Exception {
        List<Result> mockedList = mock(ArrayList.class);
        ShowListAdapter pfa = new ShowListAdapter(mockedList);
        pfa.getItemCount();
        verify(mockedList, times(1)).size();
    }

    @Ignore("Because Robolectric doesn't support Data Binding yet")
    @Test
    public void testOnCreateViewHolderFunctionality() throws Exception {
        List<Result> mockedList = mock(ArrayList.class);
        ShowListAdapter pfa = new ShowListAdapter(mockedList);

        LinearLayout mockedParent = mock(LinearLayout.class);
        when(mockedParent.getContext()).thenReturn(RuntimeEnvironment.application.getApplicationContext());
        assertNotNull(pfa.onCreateViewHolder(mockedParent, 0));
    }

    @Test
    public void testOnBindViewHolderFunctionality() throws Exception {
        List<Result> dataList = new ArrayList<>();
        dataList.add(mock(Result.class));
        dataList.add(mock(Result.class));
        ShowListAdapter pfa = new ShowListAdapter(dataList);

        ShowListAdapter.ShowListViewHolder mockedHolder = mock(ShowListAdapter.ShowListViewHolder.class);
        ViewDataBinding mockedVDB = mock(ViewDataBinding.class);
        when(mockedHolder.getBinding()).thenReturn(mockedVDB);
        pfa.onBindViewHolder(mockedHolder, 1);
        verify(mockedVDB, times(1)).setVariable(any(Integer.class), any());
        verify(mockedVDB, times(1)).executePendingBindings();
    }

    @Test
    public void testGetItemCountForCodeCoverage() throws Exception {
        List<Result> dataList = new ArrayList<>();
        dataList.add(mock(Result.class));
        dataList.add(mock(Result.class));
        ShowListAdapter pfa = new ShowListAdapter(dataList);

        assertEquals(2, pfa.getItemCount());

    }

}