package demo.com.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ff on 2017/1/23.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<String> mdate;

    public MyAdapter(String[] mdate) {
        this.mdate= new ArrayList<>();
        for (int i = 0; i < mdate.length; i++) {
            this.mdate.add(mdate[i]);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
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
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
        Log.e("data",mdate.get(position)+"");
        holder.mTextView.setText(mdate.get(position));
        if(mOnItemClickListener!=null){
            //click
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(holder.itemView,pos);
                }
            });
//longclick
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.OnItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mdate.size();
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

    public void addItem(String content,int positon){
        Log.e("position",positon+"");
        mdate.add(positon,content);
        notifyItemInserted(positon);
    }

    public void removeItem(int positon){
        Log.e("position",positon+"");
        mdate.remove(positon);
        notifyItemRemoved(positon);
    }
}