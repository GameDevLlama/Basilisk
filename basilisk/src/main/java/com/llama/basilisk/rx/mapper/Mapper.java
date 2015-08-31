package com.llama.basilisk.rx.mapper;

import com.llama.basilisk.binder.Property;

import java.util.ArrayList;

import rx.functions.Func1;

/**
 * Created by Christian Ringshofer on 27.08.15.
 */
public abstract class Mapper implements Func1<Object, Object> {

    protected final ArrayList<Mapper> mappers = new ArrayList<>();
    private final Property property;

    protected Mapper() {
        this(null);
    }

    protected Mapper(final Property property) {
        this.property = property;
    }

    public static TextMapper forText() {
        return TextMapper.create();
    }

    public static PropertyMapper forProperty(final Property property) {
        return PropertyMapper.create(property);
    }

    @Override
    public abstract Object call(Object o);

    protected void addMapper(final Mapper mapper) {
        this.mappers.add(mapper);
    }

    public ArrayList<Mapper> getMappers() {
        return this.mappers;
    }

    public Property getProperty() {
        return this.property;
    }

}
