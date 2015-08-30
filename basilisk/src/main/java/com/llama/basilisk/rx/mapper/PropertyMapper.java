package com.llama.basilisk.rx.mapper;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import com.llama.basilisk.binder.Property;
import com.llama.basilisk.math.Formula;

/**
 * Created by Christian Ringshofer on 27.08.15.
 */
public class PropertyMapper extends Mapper {

    private final Property property;

    private PropertyMapper(final Property property) {
        this.property = property;
    }

    @Override
    public Object call(Object value) {
        if (value instanceof Float) return this.map((Float) value);
        try {
            final float parsedValue = Float.parseFloat((String) value);
            return this.map(parsedValue);
        } catch (NumberFormatException e) {
            return this.map(0f);
        }
    }

    public float map(float value) {
        return value;
    }

    public PropertyMapper density(final Resources resources) {

        return new PropertyMapper() {
            @Override
            public float map(float value) {
                return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.getDisplayMetrics());
            }
        };

    }

    public PropertyMapper math(@NonNull final Formula... formulas) {
        return new PropertyMapper() {
            @Override
            public float map(float value) {
                float result = value;
                for (final Formula formula : formulas) {
                    result = formula.calculate(result);
                }
                return result;
            }
        };
    }

    public static PropertyMapper forProperty(final Property property) {
        return new PropertyMapper(property);
    }

}
