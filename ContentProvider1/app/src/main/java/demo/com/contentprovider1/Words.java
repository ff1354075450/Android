package demo.com.contentprovider1;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ff on 2017/2/23.
 */

public final class Words {
    public static final String AUTHORITY = "demo.provider";
    public static final class Word implements BaseColumns
    {
        public final static String _ID = "_id";
        public final static String WORD = "word";
        public final static String DETAIL = "detail";

        public final static Uri DICT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/words");
        public final static Uri WORD_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/word");
    }
}
