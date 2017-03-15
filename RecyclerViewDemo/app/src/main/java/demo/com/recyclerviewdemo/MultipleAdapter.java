package demo.com.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ff on 2017/2/11.
 */

public class MultipleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;
    private List<String> mdata;
    private List<Integer> mHeights ;

    public MultipleAdapter(String[] data,Context context) {
        mTitles = data;
        mContext = context;
        mdata = Arrays.asList(mTitles);
        mLayoutInflater = LayoutInflater.from(context);
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mdata.size(); i++) {
            mHeights.add((int)(100+Math.random()*300));

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.img_item, parent, false));
        } else {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.rv_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //设置view高度为一个随机值
        ViewGroup.LayoutParams lp =holder.itemView.getLayoutParams();
        lp.height=mHeights.get(position);


        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).mTextView.setText(mdata.get(position));
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mTextView.setText(mdata.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public int getItemCount() {
        return mdata == null ? 0 : mdata.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        TextViewHolder(View view) {
            super(view);
           mTextView = (TextView) view.findViewById(R.id.item_tv);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //添加数据
                    Log.d("ImageViewHolder", "onClick--> position = " + getOldPosition());

                }
            });
        }

    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageViewHolder(View view) {
            super(view);
           mTextView = (TextView) view.findViewById(R.id.image);
        }
    }







}
