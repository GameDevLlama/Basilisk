package com.llama.basilisk.rx.mapper;

import rx.Observable;

/**
 * Created by Christian Ringshofer on 27.08.15.
 */
public abstract class Mapper implements Observable.Transformer<Object, Object> {

    @Override
    public abstract Observable<Object> call(Observable<Object> objectObservable);

}
