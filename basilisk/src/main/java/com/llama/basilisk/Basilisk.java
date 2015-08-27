package com.llama.basilisk;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.llama.basilisk.rx.AndroidWatcher;
import com.llama.basilisk.binder.BinderSubscriber;
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

    @SafeVarargs
    public static void bindTextView(final TextView textView, final $$ModelBinder model, Mapper... textMappers) {

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

    @SafeVarargs
    public static void bind(final $$ModelBinder model, TextView textView, Mapper... textMappers) {
        if (textView instanceof EditText) Basilisk.bindModel(model, textView);
        Basilisk.bindTextView(textView, model, textMappers);
    }

    public static void bindHeight(final $$ModelBinder model, final View view) {
        Basilisk.bindViewHeight(view, model, PropertyMapper.density(view.getResources()));
    }

    private static void bindViewHeight(final View view, $$ModelBinder model, PropertyMapper... propertyMappers) {

        Observable<Object> subject = model.$$getTestSubject();
        for (Func1<Object, Object> propertyMapper : propertyMappers) {
            subject = subject.map(propertyMapper);
        }
        subject.subscribe(new BinderSubscriber() {
            @Override
            public void bind(Object o) {
                final Float floatValue = (Float) o;
                view.getLayoutParams().height = floatValue.intValue();
                view.requestLayout();
            }
        });

    }

}
