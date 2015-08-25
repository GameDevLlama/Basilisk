package com.llama.basilisk;

import android.widget.TextView;

import com.llama.basilisk.rx.AndroidWatcher;

import rx.Subscriber;

/**
 * Created by Christian Ringshofer on 25.08.15.
 */
public class Basilisk {

    public static void bindModel(final Subscriber<Object> model, final TextView textView) {
        AndroidWatcher.watch(textView).subscribe(model);
    }

    public static void bindTextView(final TextView textView, final $$ModelBinder model) {

        model.$$getTestSubject().subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final Object string) {
                textView.setText((String) string);
            }
        });

    }
}
