package com.llama.basilisk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.llama.basilisk.binder.BinderSubscriber;
import com.llama.basilisk.binder.Property;
import com.llama.basilisk.rx.AndroidWatcher;
import com.llama.basilisk.rx.mapper.Mapper;
import com.llama.basilisk.rx.mapper.PropertyMapper;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Christian Ringshofer on 25.08.15.
 */
public class Basilisk
{

    public static void bindModel(final Subscriber<Object> model, final TextView textView)
    {
        AndroidWatcher.watch(textView).subscribe(model);
    }

    private static void bindProperties(
            @NonNull final View view,
            @NonNull final $$ModelBinder model,
            final Mapper... mappers
    )
    {

        for (final Mapper mapper : mappers)
        {
            Observable<Object> subject = model.$$getTestSubject();
            for (final Mapper m : mapper.getMappers())
            {
                subject = subject.map(m);
            }
            subject.subscribe(new BinderSubscriber()
            {
                @Override
                public void bind(Object o)
                {
                    try
                    {
                        final Float floatValue = mapper instanceof PropertyMapper ? (Float) o : null;
                        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        // TODO do a cleanup
                        final Property property = mapper.getProperty();
                        switch (property)
                        {
                            case TEXT:
                                if (view instanceof TextView)
                                {
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
                    } catch (ClassCastException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static void bind(
            @NonNull final $$ModelBinder model,
            @NonNull View view,
            @Nullable Mapper... mappers
    )
    {
        if (view instanceof EditText) Basilisk.bindModel(model, (TextView) view);
        Basilisk.bindProperties(view, model, mappers);
    }

}
