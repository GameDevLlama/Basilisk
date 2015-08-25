package com.llama.basilisk;

import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by Christian Ringshofer on 25.08.15.
 */
public abstract class $$ModelBinder extends Subscriber<Object> {

    private PublishSubject<Object> $$testSubject = PublishSubject.create();

    public PublishSubject<Object> $$getTestSubject() {
        return this.$$testSubject;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    public void bind(Object o) {
        this.$$testSubject.onNext(o);
    }

}
