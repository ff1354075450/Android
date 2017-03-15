package demo.com.contentprovider1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ff on 2017/2/24.
 */

public class SqlHelper {

    private SQLiteDatabase db ;
    private static final String TABLE = "words";
    public SqlHelper(String dbname,Context context){
        db = context.openOrCreateDatabase(dbname, Context.MODE_PRIVATE,null);
//        db.execSQL("" +
//                "CREATE TABLE words(" +
//                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "word VARCHAR(20)," +
//                "detail VARCHAR(50)" +
//                ");");
    }

    public void insert(String word,String detail){
        ContentValues cv = new ContentValues();
        cv.put("word",word);
        cv.put("detail",detail);
        db.insert(TABLE,null,cv);
    }

    public void update(String key , String value){
        ContentValues cv = new ContentValues();
        cv.put(key,value);
        db.update(TABLE,cv,"word=?",new String[]{key});
    }

    public void delete(String word, String detail){
        db.delete(TABLE,word+"=?",new String[]{detail});
    }

    public Cursor query(String word){
        return db.query(TABLE,new String[]{"word","detail"},"word=?",new String[]{word},null,null,null);
    }

}
