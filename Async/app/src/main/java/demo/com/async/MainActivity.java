package demo.com.async;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
        dialog.setIcon(R.drawable.picture4);
        dialog.setMax(100);
        dialog.setCancelable(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        text = (TextView) findViewById(R.id.text);
    }

    public void async(View view) {
        new MyAsync().execute();
    }

    private class MyAsync extends AsyncTask<Integer, Integer, Integer> {
        private static final String TAG = "Async";

        public MyAsync() {
            super();
        }

        //准备执行
        @Override
        protected void onPreExecute() {
            dialog.show();
            Log.e(TAG, Thread.currentThread().getName() + " onPreExecute ");
        }

        //执行完成
        @Override
        protected void onPostExecute(Integer o) {
            dialog.dismiss();
            text.setText("LOAD DATA SUCCESS ");
            Log.e(TAG, Thread.currentThread().getName() + " onPostExecute ");
        }

        //更新
        @Override
        protected void onProgressUpdate(Integer[] values) {
            dialog.setProgress(values[0]);
            Log.e(TAG, Thread.currentThread().getName() + " onProgressUpdate ");
        }

        @Override
        protected void onCancelled(Integer o) {
            super.onCancelled(o);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        //后台运行
        @Override
        protected Integer doInBackground(Integer[] params) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }

            Log.e(TAG, Thread.currentThread().getName() + " doInBackground ");
            return 0;
        }
    }
}
