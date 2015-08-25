package com.llama.basilisk.rx;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import rx.subjects.PublishSubject;

/**
 * Created by Christian Ringshofer on 25.08.15.
 */
public class AndroidWatcher {

    public static PublishSubject<String> watch(final TextView textView) {

        final PublishSubject<String> watcherSubject = PublishSubject.create();
        final TextWatcher textWatcher = new TextWatcher() {

            private PublishSubject<String> subject = watcherSubject;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subject.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

        };
        textView.addTextChangedListener(textWatcher);
        return watcherSubject;

    }

}
