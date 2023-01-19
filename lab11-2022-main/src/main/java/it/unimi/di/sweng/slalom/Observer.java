package it.unimi.di.sweng.slalom;

import org.jetbrains.annotations.NotNull;

public interface Observer<T> {
    public void update(@NotNull Observable<T> subject, @NotNull T state);
}
