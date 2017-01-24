package self.ff.account.common;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ff on 2017/1/24.
 */

public class FHttp {

    public static String mypost(String url,String param){
        StringBuffer result=new StringBuffer();
        try {
            URL murl=new URL(url);
            HttpURLConnection conn=(HttpURLConnection) murl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();//getoutputStream会隐含进行connect
            DataOutputStream out=new DataOutputStream(conn.getOutputStream());
            out.writeBytes(param);
            out.flush();
            //获取返回信息
            if(conn.getResponseCode()==200){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String temp=new String();
                while((temp = br.readLine()) != null){
                    result.append(temp);
                }
                br.close();
            }
        } catch (IOException e) {
            Log.e("postError", "http获取数据异常"+"httppost"+url+"?"+param,e);
            e.printStackTrace();
        }
        return result.toString();
    }
}
