package com.rk.list.ArryaList;

import java.util.List;

public class ArrayList<T> {
    private Object[] array;
    private int position = 0;
   List<String> strings = new java.util.ArrayList<>();

    public ArrayList(int startSize) {
        array = new Object[startSize];

    }

    public ArrayList() {
        array = new Object[10];
    }

    public void add(T value) {
        checkSize();
        array[position++] = value;
    }
//TODO: можно добавить еще два условия if(position== array.length-1) и if(position< array.length-1)
// тогда на один System.arraycopy в случае переполнения массива будет меньше;
    public void add(T value, int index) {
        if (index>position){
            throw new IndexOutOfBoundsException();
        }
        checkSize();
        System.arraycopy(array,index,array,index+1,position-index);
        array[index]=value;
        position++;
    }

    public Object remove(int index) {
        if (index>position){
            throw new IndexOutOfBoundsException();
        }
        Object obj = array[index];
        System.arraycopy(array,index+1,array,index,position-index);
        return obj;
    }

    public Object get(int index) {
        if (index>position){
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public Object set(Object value, int index){
        if (index>position){
            throw new IndexOutOfBoundsException();
        }
        return array[index]=value;
    }

    public void clear() {
        position = 0;
        resize();
        for (Object value:array) {
            value = null;
        }
    }

    public int size() {
        return position;
    }

    public boolean isEmpty() {
        return position==0;
    }

    public boolean contains(Object value) {
        return false;
    }

    public int indexOf(Object value) {
        return 0;
    }

    public int lastIndexOf(Object value) {
        return 0;
    }

    public String toString() {
        return null;
    }
//TODO: может как увеличить так и обрезать массив
    private void resize() {
        Object[] newArray = new Object[(position+1)*2];
        System.arraycopy(this.array,0,newArray,0,position);
        this.array = newArray;
    }
//TODO: наверное нужно настроить на какое-то определеное урезания массива,
//      а не до прказателя position
    private void checkSize(){
        if (position==array.length-1){
            resize();
        }
    }
}
