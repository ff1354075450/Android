package demo.com.webappdemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static demo.com.webappdemo.WebActivity.getActivitySize;

public class Jsoperation {
    private Context context;
    private Handler handler;
    private int opensize;
    private Dialog loadingDialog = null;
    private Toast toast = null;

    public Jsoperation(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    //将界面存入数组中去，方便删除
    public Jsoperation(Context context, Handler handler, int type) {
        this.context = context;
        this.handler = handler;
    }

    private void startActivity(Intent intent) {
        if (context instanceof Activity) {
            Activity a = (Activity) context;
            a.startActivity(intent);
            //设置动画效果
            a.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @JavascriptInterface
    public void progress(String type, String msg, String func) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.toast_1, null);
        toast = new Toast(context);
        ImageView img = (ImageView) view1.findViewById(R.id.toast_img);
        TextView textView = (TextView) view1.findViewById(R.id.toast_text);
        textView.setText(msg);
        if (type.equals("Success")) {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
            }
            img.setImageDrawable(context.getResources().getDrawable(R.drawable.yes,null));
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view1);
            toast.show();
        }
        if (type.equals("Error")) {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
            }
            img.setImageDrawable(context.getResources().getDrawable(R.drawable.no,null));
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view1);
            toast.show();
        }
        if (type.equals("Progress")) {
            loadingDialog = new Dialog(context);
            loadingDialog.setCancelable(true);
            loadingDialog.setContentView(view1);
            img.setImageDrawable(context.getResources().getDrawable(R.drawable.loading1,null));
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
            img.startAnimation(animation);
            loadingDialog.show();
        }
        if (type.equals("Dismiss")) {
            loadingDialog.dismiss();
        }
    }


    /**
     * 打开一个新的容器，里面用来存放一个新的网页
     *
     * @param url 打开新的网页
     * @param rtb 右边按钮文字 不存在则为null
     * @param fun 右边按钮对应的js方法 不存在则为null
     */
    @JavascriptInterface
    public void open(String url, String rtb, String fun) {
        opensize = getActivitySize();
        url = "file:///android_asset/" + url;
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        if (rtb != null) {
            intent.putExtra("rtbcontent", rtb);
        }
        if (fun != null) {
            intent.putExtra("rtbfun", fun);
        }
        intent.putExtra("rtbfun", fun);
        startActivity(intent);
    }

    @JavascriptInterface
    public void load(String url) {
        opensize = getActivitySize();
        url = "file:///android_asset/" + url;
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @JavascriptInterface
    public void finish(int i) {
        int size = WebActivity.getActivitySize();
        WebActivity.removeActivity(size - 1);
    }


    @JavascriptInterface
    public void finishactivity() {
        int size = getActivitySize();
        WebActivity.removeActivity(size - 1);
    }

    /**
     * 结束到那个页面
     *
     * @param i    历史中最后一个页面
     * @param type 类型
     */
    @JavascriptInterface
    public void finishto(int i, int type) {
        int size = getActivitySize();
        if (type == 1) {
            size = opensize;
        }
        for (int j = i; j < size; j++) {
            WebActivity.removeActivity(i);
        }
    }

    /**
     * 退出程序
     */
    @JavascriptInterface
    public void myexit() {

    }

    /**
     * print log
     * @param s
     */
    @JavascriptInterface
    public void jslog(String s) {
        Log.i("js", s);
    }


    @JavascriptInterface
    public void toast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开浏览器
     *
     * @param url
     */
    @JavascriptInterface
    public void brower(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    /**
     * 拨打电话
     *
     * @param number
     */
    @JavascriptInterface
    public void callservice(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        startActivity(intent);
    }

    /**
     * 阻止滑动
     */
    @JavascriptInterface
    public void preventParentTouchEvent() {
        MineWebView.preventParentTouchEvent();
    }

    /**
     * 允许滑动
     */
    @JavascriptInterface
    public void preventParentTouchEvent2() {
        MineWebView.preventParentTouchEvent2();
    }

}
