package net.whatsbeef.showguide.model;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import net.whatsbeef.showguide.R;

import java.io.Serializable;

/**
 * Created by k.
 */
public class Result extends BaseObservable implements Serializable {

    private String name;
    private String startTime;
    private String endTime;
    private String channel;
    private String rating;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime The start_time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return The endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime The end_time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return The channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel The channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    @BindingAdapter({"ratingImage"})
    public static void setRatingImage(TextView txt, String rating) {

        switch (rating) {
            case "AV":
                txt.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(txt.getContext(), R.drawable.av), null, null, null);
                break;
            case "PG":
                txt.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(txt.getContext(), R.drawable.pg), null, null, null);
                break;
            case "R":
                txt.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(txt.getContext(), R.drawable.r), null, null, null);
                break;
            case "M":
                txt.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(txt.getContext(), R.drawable.m), null, null, null);
                break;
            case "X":
                txt.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(txt.getContext(), R.drawable.x), null, null, null);
                break;
            case "MA":
                txt.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(txt.getContext(), R.drawable.ma), null, null, null);
                break;
            case "E":
                txt.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(txt.getContext(), R.drawable.e), null, null, null);
                break;
            case "G":
                txt.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(txt.getContext(), R.drawable.g), null, null, null);
                break;
            default:
                txt.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(txt.getContext(), R.drawable.nr), null, null, null);
                break;

        }

    }

}
