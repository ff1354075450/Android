package self.ff.account.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import self.ff.account.Code;
import self.ff.account.R;
import self.ff.account.adapter.RecAdapter;
import self.ff.account.common.FHttp;

/**
 * Created by ff on 2017/1/21.
 */

public class MainF1 extends Fragment {

    private RecyclerView mrecycleView;
    private SwipeRefreshLayout mswipe;
    private RecAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main1,container,false);


        init(rootView);


        return rootView;
    }

    private void init(View rootView) {
        mrecycleView = (RecyclerView) rootView.findViewById(R.id.main1_recycler);
        mswipe = (SwipeRefreshLayout) rootView.findViewById(R.id.main1_swipe);

        Observable.just(Code.SERVER+"/account/worker_queryall.do")
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        String str = FHttp.mypost(s,"");
                        JSONObject json = null;
                        try {
                            Log.d("mainf1",str);
                            json = new JSONObject(s);
                            JSONArray jsonArray = (JSONArray) json.get("worker");
                            myAdapter=new RecAdapter(jsonArray);
                            mrecycleView.setAdapter(myAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        return str;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                    }
                });



       initSwipe(rootView);

    }

    private void initSwipe(View rootView) {

        //设置卷内的颜色
        mswipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //设置下拉圆圈的背景
//		mswipe.setProgressBackgroundColor(android.R.color.holo_red_light);
        mswipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        //停止刷新动画
                        mswipe.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
}
