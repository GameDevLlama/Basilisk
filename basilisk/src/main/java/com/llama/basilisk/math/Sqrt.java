package com.llama.basilisk.math;

/**
 * Created by Christian Ringshofer on 28.08.15.
 */
public class Sqrt extends Formula {

    @Override
    public float calculate(final float... input) {
        return (float) Math.sqrt(input[0]);
    }

}
