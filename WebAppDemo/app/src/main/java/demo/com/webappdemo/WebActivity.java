package demo.com.webappdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ff on 2017/2/28.
 */

public class WebActivity extends AppCompatActivity {

    private ImageView topback;
    private TextView toptitle;
    private TextView topRightButton;
    private WebView webView;
    private Context context;
    private String rightButtonFunction;
    private static List<Activity> weblist = new ArrayList<Activity>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        addActivity(this);
        setContentView(R.layout.activity_web);
        //		初始化
        topback = (ImageView) findViewById(R.id.topback);
        topRightButton = (TextView) findViewById(R.id.top_right_button);
        toptitle = (TextView) findViewById(R.id.toptitle);
        webView = (WebView) findViewById(R.id.webView1);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setWebChromeClient(new WebChromeClient() {
            //读取html的title并显示
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                toptitle.setText(title);
            }
        });
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        //提供js方法调用
        webView.addJavascriptInterface(new Jsoperation(context, handler), "client");
        //		获取传入参数，初始化页面
        Intent intent = getIntent();
        //得到url参数
        final String url = intent.getStringExtra("url");
        webView.loadUrl(url);
        //得到右侧按钮文字
        String rtbcontent = intent.getStringExtra("rtbcontent");
        if (rtbcontent != null) {
            topRightButton.setVisibility(View.VISIBLE);
            topRightButton.setText(rtbcontent);
        } else {
            topRightButton.setText("");
        }
        //得到右侧按钮对应的js方法
        String func = intent.getStringExtra("rtbfun");
        if (func != null) {
            rightButtonFunction = func;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.loadUrl("javascript:onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void TopBack(View view) {
        if (webView.canGoBack()) {
            webView.goBack(); // 后退
        } else {
            this.finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    public void TopRight(View view) {
        webView.loadUrl("javascript: " + rightButtonFunction);
    }

    /**
     * 点击返回，返回上一个网页
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); // 后退
            return true;
        } else {
            int i = getActivitySize();
            WebActivity.removeActivity(i - 1);
            return true;
        }
    }

    public static void addActivity(Activity a) {
        weblist.add(a);
    }

    public static void removeActivity(int i) {
        weblist.get(i).finish();
        weblist.remove(i);
    }

    public static int getActivitySize() {
        return weblist.size();
    }
}
