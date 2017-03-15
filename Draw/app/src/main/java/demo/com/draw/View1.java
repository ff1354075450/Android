package demo.com.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ff on 2016/12/21.
 */

public class View1 extends View {

    public View1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        //设置画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);//设置是否抗锯齿
        paint.setColor(Color.BLUE);//设置颜色
        paint.setStyle(Paint.Style.STROKE);//设置画笔的填充风格
        paint.setStrokeWidth(4);//设置画笔的触笔宽度+

        int width = this.getWidth();
        //绘制圆形
        canvas.drawCircle(width/10+10,width/10+10,width/10,paint);
        //绘制弧线
        RectF oval = new RectF(10,100,2*width/10,2*width/10+100);
        canvas.drawRect(oval,paint);
        canvas.drawArc(oval,0,270,true,paint);
        /**
         * 在图片中截取一块，贴到界面
         * src:将要截取的位置
         * des：将要粘贴的位置
         */
        BitmapDrawable bd = (BitmapDrawable)getResources().getDrawable(R.drawable.t1);
        Bitmap bitmap=bd.getBitmap();
        Rect src = new Rect(0,0,300,300);
        Rect des = new Rect(0,0,100,100);
        canvas.drawBitmap(bitmap,src,des,paint);

    }
}
