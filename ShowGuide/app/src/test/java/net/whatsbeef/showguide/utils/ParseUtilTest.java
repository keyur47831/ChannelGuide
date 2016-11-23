package net.whatsbeef.showguide.utils;

import net.whatsbeef.showguide.SLTestRunner;
import net.whatsbeef.showguide.model.ShowTimeModel;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by k.
 */
@RunWith(SLTestRunner.class)
public class ParseUtilTest {

    private static InputStream getInputStreamFromPath(Object obj, String fileName) throws Exception {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    private static String readFileInStr(InputStream in) {
        byte[] buffer = new byte[Constants.MAX_BUFFER_READ_SIZE];
        ByteArrayOutputStream baos;
        baos = new ByteArrayOutputStream();
        int read;

        try {
            do {
                read = in.read(buffer);
                if (read == -1) {
                    break;
                }
                baos.write(buffer, 0, read);
            } while (read >= 0);
            baos.close();
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testIfParsingSuccessfully() throws Exception {
        String jsonStr = readFileInStr(getInputStreamFromPath(this, "test_data.json"));
        assertNotNull(jsonStr);
        assertFalse(jsonStr.isEmpty());

        JSONObject jsonObject = new JSONObject(jsonStr);
        ShowTimeModel model = ParseUtil.parseShowData(jsonObject.toString());
        assertNotNull(model);
        assertNotNull(model.getResults());
        assertNotNull(model.getCount());
    }
    @Test
    public void testIfParsingErrorModel() throws Exception {
        String jsonStr = readFileInStr(getInputStreamFromPath(this, "test_error1.json"));
        assertNotNull(jsonStr);
        assertFalse(jsonStr.isEmpty());

        JSONObject jsonObject = new JSONObject(jsonStr);
        ShowTimeModel model = ParseUtil.parseShowData(jsonObject.toString());
        assertNull(model);

    }
    @Test
    public void testIfParsingErrorResults() throws Exception {
        String jsonStr = readFileInStr(getInputStreamFromPath(this, "test_error2.json"));
        assertNotNull(jsonStr);
        assertFalse(jsonStr.isEmpty());

        JSONObject jsonObject = new JSONObject(jsonStr);
        ShowTimeModel model = ParseUtil.parseShowData(jsonObject.toString());
        assertNotNull(model);
        assertEquals(model.getResults().size(),0);

    }
    @Test
    public void testIfParsingSuccessfullyResults() throws Exception {
        String jsonStr = readFileInStr(getInputStreamFromPath(this, "test_data.json"));
        assertNotNull(jsonStr);
        assertFalse(jsonStr.isEmpty());
        JSONObject jsonObject = new JSONObject(jsonStr);
        ShowTimeModel model = ParseUtil.parseShowData(jsonObject.toString());
        assertNotNull(model);
        assertNotNull(model.getResults());
        assertNotNull(model.getCount());
        assertEquals(model.getResults().size(),10);
    }

}