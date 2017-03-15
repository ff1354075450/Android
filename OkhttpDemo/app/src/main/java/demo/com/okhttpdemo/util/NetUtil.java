package demo.com.okhttpdemo.util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by ff on 2017/2/7.
 */

public class NetUtil {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final int CONNECTIMEOUT=30;

    private static final int READTIMEOUT=30;

    private static OkHttpClient okHttpClient ;
    static {
        okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(CONNECTIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READTIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public static String  get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static String post(String url,Map<String,String> param) throws IOException {
        FormBody.Builder body = new FormBody.Builder();
        for(String key : param.keySet()){
            body.add(key,param.get(key));
        }

        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }else {
            throw new IOException("unexpected code "+response);
        }
    }

    public static String postJson(String url,String json) throws IOException {
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }else{
            throw new IOException("Unexpected code " + response);
        }
    }

    public static void getFile(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if(!response.isSuccessful()){
            throw new IOException("unexception code "+response);
        }

        for (int i = 0; i < response.headers().size(); i++) {
            System.out.println(response.headers().name(i) + ": " + response.headers().value(i));
        }
        System.out.println(response.body().bytes());
    }

}
