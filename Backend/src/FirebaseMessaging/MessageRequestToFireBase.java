package FirebaseMessaging;

import FirebaseNotification.NotificationData;
import FirebaseNotification.NotificationRequestModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;


public class MessageRequestToFireBase {

// call this method when you want to push a notification to mobileApp

    public static void sendRequest() throws IOException {

        System.out.println("Sending a request to firebase");


        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                "https://fcm.googleapis.com/fcm/send");

        MessageRequestModel messageRequestModel = new MessageRequestModel();
        MessagingData messagingData = new MessagingData();

        messagingData.setMessage("The temperature is to high");
        messageRequestModel.setData(messagingData);
        messageRequestModel.setTo("/topics/Motion");



        Gson gson = new Gson();
        Type type = new TypeToken<MessageRequestModel>() {
        }.getType();

        String json = gson.toJson(messageRequestModel, type);

        StringEntity input = new StringEntity(json);
        input.setContentType("application/json");

        // server key of your firebase project goes here in header field.
        // You can get it from firebase console.
        postRequest.setEntity(input);
        postRequest.addHeader("Authorization", "key=AAAAUjRANo0:APA91bEa3ZFxWvMQ8jsN1r4w_h3Pf61_czH7QjQhG7pq3U_tTiX4a6ivByRnFGk_hVIgFieCT3LvgDoQ_LvIW_UY2456JpNInLKa8ahUyiXQ4zgYvmPQIMKEx0G0JOT4VskGbbipx_ay");



        System.out.println("reques:" + json);

        HttpResponse response = httpClient.execute(postRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        } else if (response.getStatusLine().getStatusCode() == 200) {

            System.out.println("response:" + EntityUtils.toString(response.getEntity()) );

        }
    }




}