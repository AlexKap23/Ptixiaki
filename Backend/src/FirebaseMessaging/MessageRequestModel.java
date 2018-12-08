package FirebaseMessaging;

import FirebaseNotification.NotificationData;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MessageRequestModel {

    @SerializedName("to")
    private String mTo;

    @SerializedName("data")
    private MessagingData mData;

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        mTo = to;
    }

    public MessagingData getData() {
        return mData;
    }

    public void setData(MessagingData data) {
        mData = data;
    }



}


