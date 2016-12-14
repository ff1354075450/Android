package demo.com.transitions;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
    }
    private void setupWindowAnimations(){
        Transition slide= TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        getWindow().setExitTransition(slide);

        //返回到Acitivity的动画效果
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.RIGHT);
        slideTransition.setDuration(1000);
        getWindow().setReenterTransition(slideTransition);
    }

    public void jump1(View view){
        Intent intent = new Intent(this,TransitionActivity1.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this). toBundle());
    }
    public void jump2(View view){
        Intent intent = new Intent(this,TransitonActivity2.class);
        //设定两个xml需要共享的空间的transitonName为shareName
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,view,"shareName").toBundle());
    }
    public void jump3(View view){


    }
    public void jump4(View view){

    }

}
