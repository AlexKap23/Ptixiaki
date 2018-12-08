import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationData {

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String mBody) {
        body = mBody;
    }

}