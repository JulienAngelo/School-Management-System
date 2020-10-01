package com.example.studentinfo.SendNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:Key=AAAA__Dmjtg:APA91bElfjp8XhMk-g7jck_Mvtu1K2-muEB3mGK5EvMEv_sawhHn2jSwaTiLnEIx1cGrmvTlcQm_rDQIueY9bjuZgIdjNY0qhtZmuas7tJpM1hdkPiWhs6aRpUEo9wq3d3z-M4Ylyir9"
            }
    )
    @POST("fcm/send")
    Call<MyResponse>sendNotification(@Body NotificationSender body);
}
