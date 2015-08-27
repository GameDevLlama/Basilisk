package com.llama.basilisk.rx.mapper;

import rx.functions.Func1;

/**
 * Created by Christian Ringshofer on 27.08.15.
 */
public abstract class Mapper implements Func1<Object, Object> {

    @Override
    public abstract Object call(Object o);

}
