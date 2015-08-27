package com.llama.basilisk.binder;

import rx.Subscriber;

/**
 * Created by Christian Ringshofer on 27.08.15.
 */
public abstract class BinderSubscriber extends Subscriber<Object> {

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(final Object o) {
        this.bind(o);
    }

    public abstract void bind(Object o);

}
