package demo.com.recyclerviewdemo;

import android.view.View;

/**
 * Created by ff on 2017/2/17.
 */

public interface OnItemClickListener {

    void OnItemClick(View view,int position);
    void OnItemLongClick(View view,int position);
}
