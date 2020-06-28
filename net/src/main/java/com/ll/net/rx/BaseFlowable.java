package com.ll.net.rx;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseFlowable {
    public static <T> void doObservable(Flowable<T> flowable, BaseSubscriber<T> subscriber){
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}
