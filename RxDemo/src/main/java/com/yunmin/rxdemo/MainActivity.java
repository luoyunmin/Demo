package com.yunmin.rxdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    //;:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                Log.e("lym", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("lym", "onError");
            }

            @Override
            public void onNext(String s) {
                Log.e("lym", s);
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Rx");
                subscriber.onNext("Java");
                subscriber.onCompleted();
            }
        });
        Log.e("lym", "hehehe");
        observable.subscribe(observer);
        Observable observable1 = Observable.just("just1", "just2");
        String[] str1 = {"str1", "str2", "str3"};
        Observable observable2 = Observable.from(str1);
        observable1.subscribe(observer);
        observable2.subscribe(observer);

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {

            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        };

        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {

            }
        };

        String[] strs = {"strs1", "strs2", "strs3"};
        Observable observable3 = Observable.from(strs);
        observable3.subscribe(onNextAction);
        observable3.subscribe(onNextAction, onErrorAction);
        observable3.subscribe(onNextAction, onErrorAction, onCompletedAction);

        String[] names = {"name1", "name2", "name3"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("lym", s);
            }
        });

        Log.e("lym", "mainThread:" + Thread.currentThread().getName());

        final int resId = R.mipmap.ic_launcher;
        final ImageView imageView = (ImageView) findViewById(R.id.img);
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Log.e("lym", "callThread:" + Thread.currentThread().getName());
                Drawable drawable = getResources().getDrawable(resId);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        Log.e("lym", "onNextThread:" + Thread.currentThread().getName());
                        imageView.setImageDrawable(drawable);
                    }
                });


        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e("lym", "");
                    }
                });

        Observable.just("images/logo.png").map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String s) {
                return null;
            }
        }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                //imageView.setImageBitmap(bitmap);
            }
        });

        Student student1 = new Student();
        student1.setName("张三");
        List<Course> courseList1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Course course = new Course();
            course.setCourseName("课程" + i);
            courseList1.add(course);
        }
        student1.setCourseList(courseList1);

        Student student2 = new Student();
        student2.setName("李四");
        List<Course> courseList2 = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            Course course = new Course();
            course.setCourseName("课程" + i);
            courseList2.add(course);
        }
        student2.setCourseList(courseList2);

        Student[] students = {student1, student2};
        Log.e("lym", student1.getName() + "---" + student2.getName());

        Subscriber<String> subscriber2 = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                StackTraceElement[] stackElements = e.getStackTrace();
                if (stackElements != null) {
                    for (int i = 0; i < stackElements.length; i++) {
                        Log.e("lym", stackElements[i].getClassName());
                        Log.e("lym", stackElements[i].getFileName());
                        Log.e("lym", stackElements[i].getLineNumber() + "");
                        Log.e("lym", stackElements[i].getMethodName());
                        Log.e("lym", "-----------------------------------");
                    }
                }
            }

            @Override
            public void onNext(String s) {
                Log.e("lym", "subscriber2 onNext:" + s);
            }
        };
        Observable.from(students).map(new Func1<Student, String>() {
            @Override
            public String call(Student student) {
                return student.getName();
            }
        }).subscribe(subscriber2);


        Subscriber<Course> subscriber1 = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.e("lym", "course onNext:" + course.getCourseName());
            }
        };

        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourseList());
            }
        }).subscribe(subscriber1);

        final Subscriber<String> subscriberLift = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("lym", "lift onError");
            }

            @Override
            public void onNext(String s) {
                Log.e("lym", "lift onNext:" + s);
            }
        };


        Observable.just(1, 2, 3, 4, 5, 6).lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("lym", "Observable onError");
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("lym", "Observable onNext");
                        subscriber.onNext(integer + "");
                    }
                };
            }
        }).subscribe(subscriberLift);

        Observable.Transformer lift = new LiftAllTransformer();
        Observable.just(1, 2, 2, 3, 5).compose(lift);

    }


    public class LiftAllTransformer implements Observable.Transformer<Integer, String> {
        @Override
        public Observable<String> call(Observable<Integer> integerObservable) {
            return integerObservable.lift(new Observable.Operator<String, Integer>() {
                @Override
                public Subscriber<? super Integer> call(Subscriber<? super String> subscriber) {
                    return new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Integer integer) {

                        }
                    };
                }
            });
        }
    }
}
