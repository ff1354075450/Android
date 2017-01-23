package demo.com.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private String[] mdate={"item1","item2","item3","item4","item5","item5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //如果不修改布局recyclerView的大小可以使用下面的方法来改善性能
        mRecyclerView.setHasFixedSize(true);
        //使用一个线性布局管理器
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //使用一个适配器
        mAdapter = new MyAdapter(mdate);
        mRecyclerView.setAdapter(mAdapter);

    }


}
