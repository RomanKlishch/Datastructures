package com.rk.list.ArryaList;

public class ArrayList<T> {
    private Object[] array;
    private int position = 0;
    private int capacity = 2;

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
        position--;
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

//TODO: заполняю массив null для того что бы garbage collector мог почистить память.
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
        return position == 0;
    }

    public boolean contains(Object value) {
        for (int i = 0; i <position ; i++) {
            if (value.equals(array[i])){
                return true;
            }
        }
        return false;
    }

//Todo: что должны возращать lastIndexOf и indexOf если value нет в списке?
    public int indexOf(Object value) {
        for (int i = 0; i <position ; i++) {
            if (value.equals(array[i])){
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        for (int i = position-1; i == 0 ; i--) {
            if (value.equals(array[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (position>0) {
            StringBuilder builder = new StringBuilder();
            builder.append('[');
            for (int i = 0; i < position; i++) {
                if (i == position - 1) {
                    builder.append(array[i]);
                    return builder + "]";
                }
                builder.append(array[i]);
                builder.append(", ");
            }
        }
       return "[]";
    }

//TODO: может как увеличить так и обрезать массив
//TODO: наверное нужно настроить на какое-то определеное урезания массива,
//      а не до прказателя position
    private void resize() {
        Object[] newArray = new Object[(position+1)*capacity];
        System.arraycopy(this.array,0,newArray,0,position);
        this.array = newArray;
    }

    private void checkSize(){
        if (position==array.length-1){
            resize();
        }
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
