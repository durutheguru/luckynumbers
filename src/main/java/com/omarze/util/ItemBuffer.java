package com.omarze.util;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * created by julian
 */
public class ItemBuffer<T> {


    private int maxSize;


    private List<T> items;


    private Consumer<List<T>> cleanBufferConsumer;



    public ItemBuffer(int maxSize, Consumer<List<T>> cleanBufferConsumer) {
        this.maxSize = maxSize;
        this.cleanBufferConsumer = cleanBufferConsumer;

        items = new ArrayList<>();
    }


    public void add(T item){
        items.add(item);

        if (items.size() > maxSize) {
            flush();
        }
    }


    public void flush() {
        cleanBufferConsumer.accept(items);
        items.clear();
    }


    public int size() {
        return items.size();
    }


}
