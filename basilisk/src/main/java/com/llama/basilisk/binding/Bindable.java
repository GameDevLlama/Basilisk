package com.llama.basilisk.binding;

/**
 * Created by Christian Ringshofer on 31.08.15.
 */
public class Bindable<T> {

    private T type;

    public Bindable(T type) {
        this.type = type;
    }

    public T getType() {
        return this.type;
    }

}
