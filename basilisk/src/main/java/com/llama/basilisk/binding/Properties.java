package com.llama.basilisk.binding;

/**
 * Created by Christian Ringshofer on 27.08.15.
 */
public class Properties {

    private final Property[] properties;

    public Properties(final Property[] properties) {
        this.properties = properties;
    }

    public static Properties with(Property... properties) {
        return new Properties(properties);
    }

    public Property[] getProperties() {
        return this.properties;
    }

}
