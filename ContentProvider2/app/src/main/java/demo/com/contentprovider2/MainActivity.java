package demo.com.contentprovider2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ContentResolver contentResolver;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentResolver = getContentResolver();
        uri = Uri.parse("content://demo.provider/");

    }

    public void insert(View view){
        ContentValues cv = new ContentValues();
        cv.put("mykey","myvalue");
        Uri newUri = contentResolver.insert(uri,cv);
        Toast("insert");
    }

    public void query(View view){
        Cursor cursor = contentResolver.query(uri,new String[]{"work"},"query=?",new String[]{"xx"},null);
        String s="";
        while(cursor.moveToNext()){
            s = cursor.getColumnName(1);
            s += cursor.getColumnName(2);
        }
        Log.e(TAG,s);
    }

    public void update(View view){
        ContentValues cv = new ContentValues();
        cv.put("name","tom");
        contentResolver.update(uri,cv,"update=?",new String[]{"xx"});
    }

    public void delete(View view){
        contentResolver.delete(uri,"word=?",new String[]{"cards"});
    }

    private void Toast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
