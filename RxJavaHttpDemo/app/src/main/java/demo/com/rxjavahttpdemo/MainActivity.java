package demo.com.rxjavahttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.26:88/control/cgi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetParam gp = retrofit.create(GetParam.class);

        Call<String> gpCall = gp.getcity("D8:8B:8A:A1:E0:E0","231231");

        gpCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                Log.e("result",s);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

   class CityAdapter extends BaseAdapter{

       @Override
       public int getCount() {
           return list.size();
       }

       @Override
       public Object getItem(int position) {
           return list.get(position);
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {

           View view = getLayoutInflater().inflate(R.layout.item,null);
           TextView textView = (TextView) view.findViewById(R.id.tv);
           textView.setText(list.get(position));

           return view;
       }
   }
}
