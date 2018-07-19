package com.mahavira.partnersms.base.di;

import android.arch.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * A custom identifies annotation types that are used to associate ViewModel class as keys with
 * ViewModel Provider as values returned by {@link com.norman.base.core.ViewModelFactory}
 *
 * @see <a href="https://google.github.io/dagger/multibindings#map-multibindings">Map
 * multibinding</a>
 *
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {

    Class<? extends ViewModel> value();
}
