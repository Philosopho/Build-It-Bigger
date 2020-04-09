package com.udacity.gradle.builditbigger;

import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

public class JokeIdlingResource implements IdlingResource {
   @Nullable
   private volatile ResourceCallback callBack;

   private AtomicBoolean isIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callBack = callback;
    }

    public void setIdleState(boolean idleState) {
        isIdleNow.set(idleState);

        if (idleState && callBack != null) {
            callBack.onTransitionToIdle();
        }
    }
}
