package com.dudeofawesome.flyerboard;

import android.app.DownloadManager;
import android.os.AsyncTask;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by dudeofawesome on 3/29/15.
 */
public class InterfaceServer {
    private static final String URL = "http://10.0.0.103:35937/";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    public static String getFlyers (String school) {
        String json = "school:" + school;
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(URL + "flyers")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("Failure");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
        return "Failed to connect";
    }
}
