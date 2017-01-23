package demo.com.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ff on 2016/12/22.
 */

public class DrawView extends View {

    private final static String TAG="DrawView";

    float prex;
    float prey;
    private Path path;
    public Paint paint;
    Bitmap cacheBitmap = null;
    Canvas cacheCanvas = null;

    public DrawView(Context context){
        super(context);
        cacheBitmap=Bitmap.createBitmap(720,1280,Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        path = new Path();
        cacheCanvas.setBitmap(cacheBitmap);

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setDither(true);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.e(TAG,"位置"+x+":"+y);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
//               从前一个点绘制到当前点之后，把当前点定义成下次绘制的前一个点
                path.moveTo(x,y);
                prex = x;
                prey = y;
                break;
            case MotionEvent.ACTION_MOVE:
//                从前一个点绘制到前点之后，把当前点定义成下次绘制的前一个点
                path.quadTo(prex,prey,x,y);
                prex = x;
                prey = y;
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path,paint);
                path.reset();
                break;
            default:
                break;
        }
        invalidate();
//        返回true表示处理方法已经处理该事件了
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"xxx");
        Paint bmpPaint = new Paint();
        canvas.drawBitmap(cacheBitmap,0,0,bmpPaint);
        canvas.drawPath(path,paint);
        canvas.drawRect(0,0,100,100,paint);
    }
}
