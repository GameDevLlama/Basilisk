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

    public abstract Object get(String key);

    public abstract void set(String key, Object value);

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    public void bind() {
        this.$$testSubject.onNext(this.get("test"));
    }

}
