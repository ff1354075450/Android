package edu.basic.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ff on 2016/8/24.
 */
public class DrawView extends View {
private float currentx=40;
    public float currenty=40;
    Paint p=new Paint();
    public DrawView(Context context) {
        super(context);
    }


    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        p.setColor(Color.RED);
        canvas.drawCircle(currentx,currenty,15,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        currentx=event.getX();
        currenty=event.getY();
        //通知当前组件，重绘自己
        invalidate();
        return true;
    }

}
