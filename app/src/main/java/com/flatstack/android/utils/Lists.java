package com.flatstack.android.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rx.functions.Action1;
import rx.functions.Func1;

public class Lists {

    public static boolean isEmpty(@Nullable List list) {
        return list == null || list.isEmpty();
    }

    /**
     * @return list of T which contains values which filter.call(item) returns true
     */
    @NonNull
    public static <T> List<T> filterBy(@NonNull List<T> list,
                                       @NonNull Func1<T, Boolean> filter) {
        List<T> filteredList = new ArrayList<>();
        for (T item : list) {
            if (filter.call(item)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    @Nullable
    public static <T> List<T> merge(@Nullable List<T> list1, @Nullable List<T> list2) {
        if (list1 == null && list2 == null) return null;
        List<T> finalList = new ArrayList<T>();
        if (list1 != null) {
            finalList.addAll(list1);
        }
        if (list2 != null) {
            finalList.addAll(list2);
        }
        return finalList;
    }

    @Nullable
    public static <T> List<T> uniq(@Nullable List<T> list) {
        if (list != null) {
            Set<T> set = new HashSet<>(list);
            list.clear();
            list.addAll(set);
        }
        return list;
    }

    public static <T> void forEach(@NonNull List<T> list, @NonNull Action1<T> action) {
        for (T item : list) {
            action.call(item);
        }
    }

    /**
     * @return Pair of two lists. First list contains values which satisfy splitOnPair
     * i.e. condition.call(item) returns true. Second list contains rest of items from #list.
     */
    public static <T> Pair<List<T>, List<T>> splitOnPair(@NonNull List<T> list,
                                                         @NonNull Func1<T, Boolean> condition) {
        List<T> list1 = new ArrayList<>();
        List<T> list2 = new ArrayList<>();
        for (T item : list) {
            if (condition.call(item)) {
                list1.add(item);
            } else {
                list2.add(item);
            }
        }
        return new Pair<>(list1, list2);
    }

    public static <K, V> Map<K, List<V>> groupBy(List<V> list, Func1<V, K> condition) {
        Map<K, List<V>> map = new HashMap<>();
        for (V item : list) {
            K key = condition.call(item);
            if (!map.containsKey(key)) {
                List<V> _list = new ArrayList<>();
                _list.add(item);
                map.put(key, _list);
            } else {
                map.get(key).add(item);
            }
        }
        return map;
    }

    public static <I, O> List<O> map(@NonNull List<I> input, @NonNull Func1<I, O> mapper) {
        List<O> result = new ArrayList<>();
        for (I item : input) {
            result.add(mapper.call(item));
        }
        return result;
    }
}
