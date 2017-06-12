package com.markable.classdemo.utils.rxUtils;

import com.orhanobut.logger.Logger;

import rx.Subscriber;

public abstract class SimpleSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Logger.e(e.toString());
    }
}