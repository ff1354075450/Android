package demo.com.rxjavahttpdemo;

/**
 * Created by ff on 2017/1/20.
 */



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 发送一个http请求
 * http://192.168.1.26:88/control/cgi/api!getProxyUid.action?deviceUId=D8:8B:8A:A1:E0:E0&devicePid=231231
 * retufit的使用，先定义 一个接口类
 */
public interface GetParam {
    @Headers("apikey:xxx")
    @GET("api!getProxyUid.action")//声明请求方式,传入地址
    Call<String> getcity(@Query("deviceUId")String uid,@Query("devicePid")String pid);

}
