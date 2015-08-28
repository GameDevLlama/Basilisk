package com.llama.basilisk.math;

/**
 * Created by Christian Ringshofer on 28.08.15.
 * <p/>
 * Will calculate the power by the input. The base is the value coming from the model.
 */
public class Pow extends Formula {

    private final float exponent;

    /**
     * will create a new Pow-Formula.
     *
     * @param exponent
     */
    public Pow(final float exponent) {
        this.exponent = exponent;
    }

    @Override
    public float calculate(final float... input) {
        return (float) Math.pow(input[0], this.exponent);
    }

}
