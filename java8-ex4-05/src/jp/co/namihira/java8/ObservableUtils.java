/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObservableUtils{

    /**
     * 指定されたObservableValueについて処理を実行した結果のObservableValueを返却します。
     *
     * @param f　観測対象のObservableValue
     * @param t 処理内容
     * @return　処理を実行した結果のObservableValue
     *
     * @throws IllegalArgumentException 引数のいずれかがnullの場合。
     */
    public static <T, R> ObservableValue<R> observe(
            Function<T, R> f, ObservableValue<T> t) {
        if (f == null || t == null) {
            throw new IllegalArgumentException("args must not be null");
        }

        return new ObservableValue<R>() {
            @Override
            public void addListener(InvalidationListener listener) {
                t.addListener(listener);
            }

            @Override
            public void removeListener(InvalidationListener listener) {
                t.removeListener(listener);
            }

            @Override
            @SuppressWarnings("unchecked")
            public void addListener(ChangeListener<? super R> paramChangeListener) {
                t.addListener((observable, oldValue, newValue) -> {
                    paramChangeListener.changed(
                            observe(f, (ObservableValue<T>) observable),
                            f.apply(oldValue), f.apply(newValue));
                });
            }

            @Override
            @SuppressWarnings("unchecked")
            public void removeListener(ChangeListener<? super R> paramChangeListener) {
                t.removeListener((observable, oldValue, newValue) -> {
                    paramChangeListener.changed(
                            observe(f, (ObservableValue<T>) observable),
                            f.apply(oldValue), f.apply(newValue));
                });
            }

            @Override
            public R getValue() {
                return f.apply(t.getValue());
            }
        };
    }

    /**
     * 指定されたObservableValueについて処理を実行した結果のObservableValueを返却します。
     *
     * @param f　処理内容
     * @param t　観測対象のObservableValue
     * @param u　観測対象のObservableValue
     * @return　処理を実行した結果のObservableValue
     *
     * @throws IllegalArgumentException 引数のいずれかがnullの場合。
     */
    public static <T, U, R> ObservableValue<R> observe(
            BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
        if (f == null || t == null || u == null) {
            throw new IllegalArgumentException("args must not be null");
        }

        return new ObservableValue<R>() {
            @Override
            public void addListener(InvalidationListener listener) {
                t.addListener(listener);
                u.addListener(listener);
            }

            @Override
            public void removeListener(InvalidationListener listener) {
                t.removeListener(listener);
                u.removeListener(listener);
            }

            @Override
            public void addListener(ChangeListener<? super R> paramChangeListener) {
                t.addListener((observable, oldValue, newValue) -> {
                    paramChangeListener.changed(
                            observe((v) -> f.apply(v, null), observable),
                            f.apply(oldValue, u.getValue()), f.apply(newValue, u.getValue()));
                });
                u.addListener((observable, oldValue, newValue) -> {
                    paramChangeListener.changed(
                            observe((v) -> f.apply(null, v), observable),
                            f.apply(t.getValue(), oldValue), f.apply(t.getValue(), newValue));
                });
            }

            @Override
            public void removeListener(ChangeListener<? super R> paramChangeListener) {
                t.removeListener((observable, oldValue, newValue) -> {
                    paramChangeListener.changed(
                            observe((v) -> f.apply(v, null), observable),
                            f.apply(oldValue, u.getValue()), f.apply(newValue, u.getValue()));
                });
                u.removeListener((observable, oldValue, newValue) -> {
                    paramChangeListener.changed(
                            observe((v) -> f.apply(null, v), observable),
                            f.apply(t.getValue(), oldValue), f.apply(t.getValue(), newValue));
                });
            }

            @Override
            public R getValue() {
                return f.apply(t.getValue(), u.getValue());
            }
        };
    }

}