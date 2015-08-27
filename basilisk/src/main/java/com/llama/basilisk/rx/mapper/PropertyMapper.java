package com.llama.basilisk.rx.mapper;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Christian Ringshofer on 27.08.15.
 */
public abstract class PropertyMapper extends Mapper {

    @Override
    public Object call(Object value) {
        try {
            final float parsedValue = Float.parseFloat((String) value);
            return this.map(parsedValue);
        } catch (NumberFormatException e) {
            return this.map(0f);
        }
    }

    public abstract float map(float value);

    public static PropertyMapper density(final Resources resources) {

        return new PropertyMapper() {
            @Override
            public float map(float value) {
                return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.getDisplayMetrics());
            }
        };

    }

    public static PropertyMapper densityMapper() {
        return null;
    }
}
