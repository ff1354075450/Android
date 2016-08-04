package demo.ff.mylivelist;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = new ListView(this);

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        //每一个HashMap 对应ListView 中 每一个 item 的数据
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("Image", R.drawable.start);
        map.put("Text1", "周杰伦");
        map.put("Text2", "菊花台");
        map.put("Button", "更多");
        list.add(map);

        map = null;
        map = new HashMap<String, Object>();
        map.put("Image", R.drawable.ic_menu_send);
        map.put("Text1", "邓丽君");
        map.put("Text2", "在水一方");
        map.put("Button", "更多");
        list.add(map);

        map = null;
        map = new HashMap<String, Object>();
        map.put("Image", R.drawable.start);
        map.put("Text1", "蔡琴");
        map.put("Text2", "恰似你的温柔");
        map.put("Button", "更多");
        list.add(map);

        //适配器，绑定数据
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.item,
                new String[]{"Text1", "Text2", "Button", "Image"},
                new int[]{R.id.text1, R.id.text2, R.id.button, R.id.image}
        ) {
            //在这重写的函数里，设置每一个item中按钮的响应事件
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final int p=position;
                final View view = super.getView(position, convertView, parent);
                Button button =(Button)view.findViewById(R.id.button);
                TextView text1=(TextView)view.findViewById(R.id.text1);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //警告框的写法
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("More")
                                .setMessage("你选中了"+String.valueOf(p))
                                .setPositiveButton("确定",new DialogInterface.OnClickListener(){

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        setTitle("点击了输入框上的确定按钮");
                                    }
                                })
                                .setNeutralButton("中立",new DialogInterface.OnClickListener(){

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        setTitle("点击了对话框的中立");
                                    }
                                })
                                .setNeutralButton("取消",new DialogInterface.OnClickListener(){

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        setTitle("点击了对话框的取消按钮");
                                    }
                                })
                                .create()
                                .show();
                    }
                });
                return view;

            }
        };
        //绑定适配器
        listView.setAdapter(adapter);

        //设置背景颜色选择器
        listView.setSelector(R.color.black);

        //设置焦点响应问题    同时要将 item 中的焦点 focusable 设置为 false
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        //设置 item 的监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //获得 item 里面的文本控件
                TextView text1=(TextView)view.findViewById(R.id.text1);
                Toast.makeText(getApplicationContext(), text1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //设置显示的内容
        setContentView(listView);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
                Uri.parse("android-app://demo.ff.mylivelist/http/host/path")
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
                Uri.parse("android-app://demo.ff.mylivelist/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
