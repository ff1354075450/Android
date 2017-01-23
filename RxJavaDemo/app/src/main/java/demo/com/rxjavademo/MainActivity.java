package demo.com.rxjavademo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *本demo主要参照：https://gank.io/post/560e15be2dca930e00da1083
 * 文字来进行的如有疑惑可自行解决
 *

 *
 */
public class MainActivity extends AppCompatActivity {

    private Context context;
    private TextView tv;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        tv = (TextView) findViewById(R.id.tv);
        imageView = (ImageView) findViewById(R.id.imageview);


        doDemo();



        Log.e("Main","have finish");
    }

    public void demo1() {
        //新建被观察者
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("this is bokedemo");
                        subscriber.onCompleted();

                    }
                }
        );

        //创建观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                tv.setText(s);
            }
        };
        //可观察元素选择订阅到观察者
        myObservable.subscribe(subscriber);
    }


    public void demo2(){
        Observable<String> myobservable = Observable.just("this is demo2");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("demo2",s);
            }
        };
        myobservable.subscribe(onNextAction);
    }

    /**
     * java8以上开始支持lambda表达式
     * 需要更改配置: build.gradle
     * defaultConfig {
     * ...
     * jackOptions {
     * enabled true
     * }
     * }
     * compileOptions {
     * sourceCompatibility JavaVersion.VERSION_1_8
     * targetCompatibility JavaVersion.VERSION_1_8
     * }
     *
     */
    public void demo3(){
        Observable.just("this is demo3")
                .subscribe(s -> tv.setText(s));
    }

    /**
     * 操作符的使用
     * observable 和最终subscriber之间修改observable发出的事件，rx提供了很多操作符
     */
    public void demo4(){
        Observable.just("this is demo4")
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String s) {
                        return s+"-have finish";
                    }
                })
                .subscribe(s->Log.e("demo4",s));
    }

    /**
     * map操作，可以对类型进行转换，
     * map可以有多个
     */

    public void demo5(){
        List<String> list ;
        Observable.just("this is demo5")
                .map(s->s+"-have finish")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return "map2"+s;
                    }
                })
                .filter(new Func1<String, Boolean>() {//过滤，如果包含a则返回
                    @Override
                    public Boolean call(String s) {
                        return s.contains("a");
                    }
                })
                .subscribe(s->Log.e("demo5",s));
        //from 操作符
        list = Arrays.asList("java","android","iso","python");
        Observable.from(list)
                .take(3)//表示只取得前三
                .doOnNext(new Action1<String>() {//在做donext之前的一些操作
                    @Override
                    public void call(String s) {
                        Log.e("doOnnext",s+"\n do on next");
                    }
                })
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("dem5form",s);
            }
        });

    }


    /**
     * 入门总结：
     * observable可以用来查询数据库，subscriber可以用来显示结果
     * observable可以是屏幕上的点击事件，subscriber可以用力相应事件
     * observable 可以是一个网络请求，subsectiber可以用来显示请求结果
     *
     * observable 和subscriber是独立于中间变化的过程，
     * 在observable和subscriber中间可以增加任何数量的Map.整个系统是高度可组合的
     *
     * FuncX和Actionx的区别在于FuncX包装的是有返回值的方法，Actionx无返回值
     * map用来对象的直接变换
     *
     */


    /**
     * Scheduler 调度器。用来指定每一段代码运行在什么线程，rxjava内置了几个secheduler
     * subscribeOn():指定subscribe()所发生的线程,即obserable.onsubscribe所激活时所处的线程
     * observeOn() 指定subscriber所运行的线程，或者叫做事件消费线程
     * 下面的方法多用于从后台取得数据
     */


    public void demo6(){
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io()) //指定subscribe 发生在Io线程
                .observeOn(AndroidSchedulers.mainThread()) //指定subscriber的回调发生主线程
        .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.e("demo6","name:"+integer);
            }
        });
    }


    /**
     * 由图片id得到图片并显示，有点轮播图片的样子
     */
    public void demo7(){
        int drawableRes = R.drawable.picture1;

        Observable.create(new Observable.OnSubscribe<Drawable>(){

            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(drawableRes);

                //通过订阅给观察者发送消息
                Log.e("demo7","picture1");
                subscriber.onNext(drawable);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //第二张图片
                Log.e("demo7","picture2");
                drawable = getTheme().getDrawable(R.drawable.picture2);
                subscriber.onNext(drawable);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //第三张图片
                Log.e("demo7","picture3");
                drawable = getTheme().getDrawable(R.drawable.picture3);
                subscriber.onNext(drawable);


                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }
                });
    }

    /**
     * 问题，subscribe()方法在线程中被调用，Scheduler的原理
     *
     * 4变换
     * rxjava 提供了对于事件序列进行变换的支持。
     * 就是将序列中的对象或者整个序列进行加工处理，转换成不同的事件或者事件序列
     *
     *map的使用，demo234有进行实例
     *
     * 下面几个demo将用于说明：flatMap变换
     *
     *
     */

//打印出一组学生的名字
    public void flatdemo1(){
        String[] students={"jone","lili","mading","kate"};
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("flatdemo1",s);
            }
        };

        Observable.from(students)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return "name:"+s;
                    }
                })
                .subscribe(subscriber);
    }

    /**
     * 使用flatmap打印所有课程
     * flatmap和map有一个相同点：把传入的参数转化之后返回给另一个对象
     * 不同点：flatMap中返回的是observable对象，
     * 第一个observable对象不是直接被发送到subscriber的回调中。
     *
     * flatmap的原理如下：
     * 1.使用传入的事件对象创建一个Observable对象
     * 2.并不发送这个Observable,而是将它激活，于是他开始发送事件
     * 3.每一个创建出来的Observable发送的事件，都回被汇入一个observable,而这个observable负责想这些事件统一
     * 交给subscrible的回调。
     * 这三步，把事件拆分成了两级，通过一组新建的observable将初始的对象“铺平”，之后通过统一路径分发下去
     * 这个“铺平”就是flatmap的flat
     *
     * 通过flatMap可以把嵌套请求卸载一条链中，从而保持逻辑的清晰-=================疑惑，这如何实现
     * @return
     */
    public void flatdemo2(){
        List students = initStudents();
        Observable.from(students)
                .flatMap(new Func1<Student,Observable<String>>() {
                    @Override
                    public Observable<String> call(Student stu) {
                        return Observable.from(stu.getCourse());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        Log.e("flatdemo2",o.toString());
                    }
                });
    }

    /**
     * 变换原理lift()
     * 变换实质上都是对事件序列的处理和在发送
     * 在Rxjava内部，它们是基于同一个基础的变换方法lift(Operator)
     *
     * lift不使用在实际工程中
     * @return
     */

    public void liftdemo(){
        Observable.just(1)
                .lift(new Observable.Operator<String,Integer>(){
                    @Override
                    public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber){
                        return new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.e("liftdeom","收到源obser提交的参数"+integer);
                                subscriber.onNext("result" + integer);
                            }
                        };
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("liftdemo",s);
                    }
                });
    }

    /**
     * compose：对observable整体的变换
     * @return
     */
    public void composeDemo(){
        MapAll mapall = new MapAll();
        Observer observe = new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.e("composeDemo",o.toString());
            }
        };
        Observable.just(1).compose(mapall).subscribe(observe);
        Observable.just(2).compose(mapall).subscribe(observe);
        Observable.just(3).compose(mapall).subscribe(observe);
        Observable.just(4).compose(mapall).subscribe(observe);
    }



    public class MapAll implements Observable.Transformer<Integer,String>{

        @Override
        public Observable<String> call(Observable<Integer> integerObservable) {
            return integerObservable
                    .flatMap(new Func1<Integer, Observable<String>>() {
                        @Override
                        public Observable<String> call(Integer integer) {
                            return Observable.just(integer+"");
                        }
                    })
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            return "hello"+s;
                        }
                    });
        }
    }

    /**
     * 线程变换
     * observeOn()指定的是他之后的操作所在的线程，因此如果有多次切换线程的需求，
     * 只要在每个想要切换线程的位置调用一次observeOn()即可
     * @return
     */

    public void SchedulerDemo1(){
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())//切换到新的线程，
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        Log.e("map1","第一次转换 "+integer);
                        return integer+"";
                    }
                })
                .observeOn(Schedulers.io())//切换到io线程
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Log.e("map2","io线程变换 "+s);
                        return "io"+s;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("schedulerDemo",s);
                    }
                });
    }

    /**
     * doOnSubscribe()使的代码在主线程中执行
     * @return
     */
    public void doDemo(){
        Observable.just(1)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() { //需要在主线程中执行的工作
                        Toast.makeText(context,"mainThread",Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread()) //指定主线程
                .observeOn(AndroidSchedulers.mainThread()) //切换到主线程
                .subscribe();
    }




    private List<Student> initStudents(){
        List<Student> list = new ArrayList<Student>();

        String[] course1 = {"English","Math","Chinese Language"};
        String[] course2 = {"biology","geography","history"," Politics"};
        String[] course3 = {"physics","chemistry"};

        Student st1 = new Student("jone",15,"man",course1);
        Student st2 = new Student("kate",18,"woman",course2);
        Student st3 = new Student("mading",17,"man",course3);

        list.add(st1);
        list.add(st2);
        list.add(st3);

        return list;
    }


}
