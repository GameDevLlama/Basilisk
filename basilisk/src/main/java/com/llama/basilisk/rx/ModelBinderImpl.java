package com.llama.basilisk.rx;

import com.llama.basilisk.$$ModelBinder;

/**
 * Created by Christian Ringshofer on 31.08.15.
 */
public class ModelBinderImpl extends $$ModelBinder {

    private String test = "Basilisk";

    @Override
    public Object get(String key) {

        return this.test;

    }

    @Override
    public void set(String key, Object value) {

        this.test = (String) value;
        // should be auto generated
        super.bind();

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Object s) {
        // should use generated / bound setter
        this.set("test", String.valueOf(s));
    }

}
