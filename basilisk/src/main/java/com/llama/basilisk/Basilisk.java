package com.llama.basilisk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.llama.basilisk.binder.BinderSubscriber;
import com.llama.basilisk.binder.Properties;
import com.llama.basilisk.binder.Property;
import com.llama.basilisk.rx.AndroidWatcher;
import com.llama.basilisk.rx.mapper.Mapper;
import com.llama.basilisk.rx.mapper.PropertyMapper;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Christian Ringshofer on 25.08.15.
 */
public class Basilisk {

    public static void bindModel(final Subscriber<Object> model, final TextView textView) {
        AndroidWatcher.watch(textView).subscribe(model);
    }

    public static void bindTextView(
            final TextView textView,
            final $$ModelBinder model,
            final Mapper... textMappers
    ) {

        Observable<Object> subject = model.$$getTestSubject();
        for (Func1<Object, Object> textMapper : textMappers) {
            subject = subject.map(textMapper);
        }
        subject.subscribe(new BinderSubscriber() {
            @Override
            public void bind(Object o) {
                final String newString = (String) o;
                if (textView.isFocused()) return;
                if (textView.getText().equals(newString)) return;
                textView.setText(newString);
            }
        });

    }

    private static void bindProperties(
            final View view,
            final $$ModelBinder model,
            final Mapper... propertyMappers
    ) {

        Observable<Object> subject = model.$$getTestSubject();
        for (Func1<Object, Object> propertyMapper : propertyMappers) {
            if (propertyMapper instanceof PropertyMapper) {
                subject = subject.map(propertyMapper);
            }
        }
        subject.subscribe(new BinderSubscriber() {

            @Override
            public void bind(Object o) {
                final Float floatValue = (Float) o;
                if (this.p.length > 0) {
                    final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    for (Property property : p) {
                        switch (property) {
                            case HEIGHT:
                                layoutParams.height = floatValue.intValue();
                                break;
                            case WIDTH:
                                layoutParams.width = floatValue.intValue();
                                break;
                        }
                    }
                    view.requestLayout();
                }
            }
        });

    }

    public static void bind(
            @NonNull final $$ModelBinder model,
            @NonNull View view,
            Mapper... mappers
    ) {
        if (view instanceof EditText) Basilisk.bindModel(model, (TextView) view);
        if (view instanceof TextView) Basilisk.bindTextView((TextView) view, model, mappers);
        Basilisk.bindProperties(view, model, mappers);
    }

}
