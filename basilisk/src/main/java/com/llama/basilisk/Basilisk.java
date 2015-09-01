package com.llama.basilisk;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.llama.basilisk.binding.BinderSubscriber;
import com.llama.basilisk.rx.AndroidWatcher;
import com.llama.basilisk.rx.mapper.Mapper;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Christian Ringshofer on 25.08.15.
 */
public class Basilisk {

    public static void bindModel(final Subscriber<Object> model, final TextView textView) {
        AndroidWatcher.watch(textView).subscribe(model);
    }

    private static void bindProperties(
            final View view,
            final $$ModelBinder model,
            final Mapper... propertyMappers
    ) {

        for (final Mapper mapper : propertyMappers) {
            Observable<Object> subject = model.$$getTestSubject();
            for (final Mapper m : mapper.getMappers()) {
                subject = subject.map(m);
            }
            subject.subscribe(new BinderSubscriber() {
                @Override
                public void bind(Object o) {
                    final Float floatValue = o instanceof String ? null : (Float) o;
                    final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    // TODO do a cleanup
                    try {
                        switch (mapper.getProperty()) {
                            case TEXT:
                                if (view instanceof TextView) {
                                    final String newString = (String) o;
                                    if (view.isFocused()) return;
                                    final TextView textView = (TextView) view;
                                    if (textView.getText().equals(newString)) return;
                                    textView.setText(newString);
                                }
                                break;
                            case HEIGHT:
                                layoutParams.height = floatValue.intValue();
                                view.requestLayout();
                                break;
                            case WIDTH:
                                layoutParams.width = floatValue.intValue();
                                view.requestLayout();
                                break;
                        }
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static void bind(
            @NonNull final $$ModelBinder model,
            @NonNull View view,
            Mapper... mappers
    ) {
        if (view instanceof EditText) Basilisk.bindModel(model, (TextView) view);
        Basilisk.bindProperties(view, model, mappers);
    }

}
