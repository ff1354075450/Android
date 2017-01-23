package self.ff.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import self.ff.account.common.login;

public class LoginActivity extends AppCompatActivity implements login {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_login);
    }

    @Override
    public void Login(View view) {



        Observable.just(1)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        //http请求
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        //获取结果并且执行操作

                    }
                });
    }

    @Override
    public void Register(View view) {

    }

    @Override
    public void MissPassword(View view) {

    }
}
