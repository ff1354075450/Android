package demo.com.contentprovider1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ff on 2017/2/23.
 */

public class Provider1 extends ContentProvider {
    private String TAG="provider1";
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int INSERT = 1;
    private static final int DELETE = 2;
    private static final int UPDATE = 3;
    private static final int QUERY = 4;
    private SqlHelper sqlHelper;
    private MatrixCursor matrixCursor;

    static {
        matcher.addURI(Words.AUTHORITY,"insert",INSERT);
        matcher.addURI(Words.AUTHORITY,"delete",DELETE);
        matcher.addURI(Words.AUTHORITY,"update",UPDATE);
        matcher.addURI(Words.AUTHORITY,"query",QUERY);
    }

    private void log(String s){
        Log.e(TAG,s);
    }
    @Override
    public boolean onCreate() {
        log("create");
        sqlHelper = new SqlHelper("provider1.db",this.getContext());
        matrixCursor = new MatrixCursor(new String[]{"id","name","detail"},1);
        return true;
    }



    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        log("query:" + uri +"  projection:"+ projection[0].toString()+"  selection:" + selection + "  selelctionargs "+selectionArgs[0].toString()  );
        matrixCursor.addRow(new Object[]{"1","zhouyuec","laji"});

        return matrixCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.e(TAG,"getType:"+uri);
       switch(matcher.match(uri))
       {
           case INSERT:
               //返回多条数据
               return "vnd.android.cursor.dir/org.demo.dict";
           case DELETE:
               //返回单条数据
               return "vnd.android.cursor.item/org.demo.dict";
           case UPDATE:
               return "vnd.android.cursor.item/org.demo.dict";
           case QUERY:
               return "vnd.android.cursor.item/org.demo.dict";
           default:
               throw new IllegalArgumentException("为知uri"+uri);
       }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        log("insert:" + uri + "ContentValues:"+values);
        return uri ;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        log("delete:"+ uri+"selection:"+selection);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        log("update");
        return 0;
    }
}
