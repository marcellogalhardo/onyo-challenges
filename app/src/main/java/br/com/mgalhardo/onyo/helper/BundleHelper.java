package br.com.mgalhardo.onyo.helper;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class BundleHelper {

    private static final String NO_INSTANCES = "No instances.";

    private BundleHelper() {
        throw new AssertionError(NO_INSTANCES);
    }

    @SuppressWarnings({ "unchecked", "UnusedDeclaration" })
    public static <T extends Serializable> T getSerializable(@NonNull Bundle bundle, String key) {
        return (T) bundle.getSerializable(key);
    }

    @SuppressWarnings({ "unchecked", "UnusedDeclaration" })
    public static <T extends Parcelable> T getParcelable(@NonNull Bundle bundle, String key) {
        return (T) bundle.getSerializable(key);
    }

}