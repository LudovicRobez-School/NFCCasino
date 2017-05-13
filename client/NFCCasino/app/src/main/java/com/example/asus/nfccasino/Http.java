package com.example.asus.nfccasino;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author Gregory Vesic
 * @version 20/04/2017
 */
class Http extends AsyncTask<String, Integer, String> {

    private static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded");

    /**
     * Méthode doInBackground
     * @param arg0
     * @return
     */
    @Override
    protected String doInBackground(String... arg0) {
        String result;
        String type = arg0[0];
        switch (type) {
            case "GET":
                try {
                    Log.i("AvGET",arg0[1]);
                    Log.i("AvGET",arg0[2]);
                    String response = get(arg0[1],arg0[2]);
                    Log.i("ApGET",response);
                    result = response;
                    return result;
                }
                catch(IOException e) {
                    return e.toString();
                }
            case "POST":
                try {
                    Log.i("AvPOST",arg0[1]);
                    Log.i("AvPOST",arg0[2]);
                    String response = post(arg0[1],arg0[2]);
                    Log.i("AvPOST",response);
                    result = response;
                    return result;
                }
                catch(IOException e) {
                    return e.toString();
                }
            default:
                result = "Le premier argument doit être GET ou POST";
                return result;
        }
    }

    /**
     * Méthode post
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    private String post(String url,String params) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON,params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        if (response.code()>=200&&response.code()<=205)
            return response.body().string();
        else {
            Log.i("error code", String.valueOf(response.code()));
            return "-1";
        }
    }

    /**
     * Méthode get
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    private String get(String url,String params) throws IOException {
        if(params.equals("")) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            if (response.code()>=200&&response.code()<=205) {
                return response.body().string();

            } else {
                Log.i("error code", String.valueOf(response.code()));
                return "-1";
            }
        }
        else {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON,params);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            if (response.code()>=200&&response.code()<=205) {
                return response.body().string();
            } else {
                Log.i("error code", String.valueOf(response.code()));
                return "-1";
            }
        }
    }
}
