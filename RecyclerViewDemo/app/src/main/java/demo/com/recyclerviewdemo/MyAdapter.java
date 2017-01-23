package demo.com.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ff on 2017/1/23.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private String[] mdate;

    public MyAdapter(String[] mdate) {
        this.mdate = mdate;
    }

    /**
     * 创建一个新的视图，被布局管理器所调用
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建一个新的视图
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item,parent,false);
        //设置视图的大小，边界，填充和布局参数
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    /**
     * 替换视图中的内容（被视图管理器调用）
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Log.e("data",mdate[position]);
        holder.mTextView.setText(mdate[position]);
    }

    @Override
    public int getItemCount() {
        return mdate.length;
    }

    /**
     * 为每一条数据提供一个视图引用
     * 复杂数据的每一个条目可能需要超过一个视图，和
     * 在一个视图持有者中，你能够提供一条通道，数据和每个item视图之间的通道
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}