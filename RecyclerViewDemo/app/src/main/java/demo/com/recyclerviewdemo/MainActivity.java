package demo.com.recyclerviewdemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import static demo.com.recyclerviewdemo.R.id.recycler_view;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private MyAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private String[] mdate={"java","android","rube","python","go","javascript","php","c","c++","c#"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 创建recycleview对象
         * 设置显示规则
         */
        mRecyclerView = (RecyclerView) findViewById(recycler_view);

        //如果不修改布局recyclerView的大小可以使用下面的方法来改善性能
        mRecyclerView.setHasFixedSize(true);
        //使用一个线性布局管理器
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //使用一个适配器
        mAdapter = new MyAdapter(mdate);

        MultipleAdapter multipleAdapter = new MultipleAdapter(mdate,this);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        //设置动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                mAdapter.removeItem(position);
                Toast.makeText(MainActivity.this,position+"click",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                mAdapter.addItem("hello",position);
                Toast.makeText(MainActivity.this,position+"longClick",Toast.LENGTH_SHORT).show();
            }
        });
    }


    //发送短信
    public void sendMsg(String phone,String msg) {
        SmsManager smanager = SmsManager.getDefault();
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(), 0);
        smanager.sendTextMessage(phone, null, msg, pi, null);

    }

    public void add(){

    }


}
