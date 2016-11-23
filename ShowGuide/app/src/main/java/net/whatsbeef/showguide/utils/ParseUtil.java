package net.whatsbeef.showguide.utils;

import net.whatsbeef.showguide.model.Result;
import net.whatsbeef.showguide.model.ShowTimeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k.
 */
public class ParseUtil {
    public static ShowTimeModel parseShowData(String data) throws JSONException
    {
        ShowTimeModel showTimeModel=new ShowTimeModel();

        List<Result> resultData= new ArrayList<>();
        JSONObject result=new JSONObject(data);
        if(result.has(Constants.TAG_RESULTS))
        {
            JSONArray resultList=result.optJSONArray(Constants.TAG_RESULTS);
            if(resultList!=null)
            {
                for(int i=0;i<resultList.length();i++)
                {

                    Result childItem=new Result();
                    childItem.setChannel(resultList.getJSONObject(i).optString(Constants.TAG_CHANNEL));
                    childItem.setEndTime(resultList.getJSONObject(i).optString(Constants.TAG_END_TIME));
                    childItem.setStartTime(resultList.getJSONObject(i).optString(Constants.TAG_START_TIME));
                    childItem.setName(resultList.getJSONObject(i).optString(Constants.TAG_NAME));
                    childItem.setRating(resultList.getJSONObject(i).optString(Constants.TAG_RATING));
                    resultData.add(childItem);
                }
            }

            showTimeModel.setCount(result.optInt(Constants.TAG_COUNT,0));
            showTimeModel.setResults(resultData);
            return showTimeModel;
        }

        return null;
    }
}
