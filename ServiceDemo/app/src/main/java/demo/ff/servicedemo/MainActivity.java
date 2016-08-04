package demo.ff.servicedemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private ListView mlistview;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList=new ArrayList<String>();
    private Map<String,Objects> map=new HashMap<String, Objects>();
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> listdata;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mlistview);

        arrayList.add("item0");
        arrayList.add("item1");
        arrayList.add("item2");
        arrayList.add("item3");
        arrayList.add("item4");
        arrayList.add("item5");
        arrayList.add("item6");
        arrayList.add("item7");
        arrayList.add("item8");
        arrayList.add("item9");
        arrayList.add("item10");
        arrayList.add("item11");
        arrayList.add("item21");
        arrayList.add("item14");

//        mlistview = (ListView) findViewById(R.id.listView);
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        mlistview.setAdapter(arrayAdapter);
//        mlistview.setOnItemClickListener(this);
//        mlistview.setOnScrollListener(this);

        listdata=new ArrayList<Map<String, Object>>();
        simpleAdapter=new SimpleAdapter(this,getdata(),R.layout.item,new String[]{"button","textview"},new int[]{R.id.button2,R.id.text2});
        mlistview.setAdapter(simpleAdapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("第几个i：" + i + "第几行l:" + l);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            //用力划过
            //停止滑动
            //滑动
            case SCROLL_STATE_FLING:
                Toast.makeText(this,"用力划过",Toast.LENGTH_SHORT);
                //滑动到底部增加数据
                arrayList.add("新增数据");
                arrayAdapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                Toast.makeText(this,"停止滑动",Toast.LENGTH_SHORT);
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Toast.makeText(this,"滑动",Toast.LENGTH_SHORT);
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://demo.ff.servicedemo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://demo.ff.servicedemo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

private List<Map<String,Object>> getdata(){
    for(int i=0;i<20;i++){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("button","hhhh");
        map.put("textview","xxx"+i);
        listdata.add(map);
    }


    return listdata;
}
    //启动一个服务
//    public void open(View view){
//        Intent intent=new Intent();
//        intent.setClass(MainActivity.this,MSD.class);
//        startService(intent);
//
//    }
}
