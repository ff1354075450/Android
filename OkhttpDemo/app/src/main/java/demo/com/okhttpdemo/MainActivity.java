package demo.com.okhttpdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager linearLayoutManager;

    private ItemTouchHelper itemTouchHelper;

    private List<Meizi> meizis;

    private RecAdapter mAdapter;

    private Context context;
    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.line_swipe_refresh);
        recyclerView=(RecyclerView)findViewById(R.id.line_recycler);
        //创建默认的线性LayoutManager
        linearLayoutManager = new LinearLayoutManager(this);
        //设置为线性布局，类似于listview
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void setListener(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                setDate("http://gank.io/api/data/福利/10/1");
            }
        });
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0,swipeFlags=0;
                if(recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager){
                    dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                }else if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                    dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                    swipeFlags = ItemTouchHelper.START|ItemTouchHelper.END;
                }

                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(meizis,from,to);
                mAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mAdapter.removeItem(viewHolder.getAdapterPosition());
            }
        });
    }

    private void setDate(String url){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String result = response.body().string();
                    JSONObject jsonObject = new JSONObject(result);
                    Gson gson = new Gson();
                    String jsonData = jsonObject.getString("results");
                    if(meizis==null || meizis.size()==0){
                        meizis = gson.fromJson(jsonData,new TypeToken<List<Meizi>>(){}.getType());
                        Meizi pages = new Meizi();
                        pages.setPage(page);
                        meizis.add(pages);
                    }else{
                        List<Meizi> more = gson.fromJson(jsonData,new TypeToken<List<Meizi>>(){}.getType());
                        meizis.addAll(more);
                        Meizi pages = new Meizi();
                        pages.setPage(page);
                        meizis.add(pages);
                    }

                    if(mAdapter == null){
                        mAdapter = new RecAdapter(context,meizis,recyclerView);
                        recyclerView.setAdapter(mAdapter);
                        itemTouchHelper.attachToRecyclerView(recyclerView);
                    }else {
                        mAdapter.notifyDataSetChanged();
                    }
                    swipeRefreshLayout.setRefreshing(false);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
