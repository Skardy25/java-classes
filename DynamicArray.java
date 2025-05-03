// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class Array<T> implements Iterable<T> {
    private T[] arr;
    private int len = 0;
    private int capacity = 0;

    public Array(){
        this(16);
    }
    public Array(int capacity) {
       if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
       this.capacity = capacity;
       arr = (T[]) new Object[capacity];  
    }

    public int size() { return len; }
    public boolean isEmpty() { return size() == 0; }
    private void checkIndex(int index) {
        if(index < 0 || index >= len) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }
    public T get(int index) {
        this.checkIndex(index);
        return arr[index];
    }
    public void set(int index, T element) {
        this.checkIndex(index);
        arr[index] = element;
    }
    public void clear() {
        for(int i = 0; i < len; i++) {
            arr[i] = null;
        }
        len = 0;
    }

    public void add(T element) {
       if(len + 1 >= capacity) {
            if(capacity == 0) capacity = 1;
            else capacity *= 2;
            T[] new_arr = (T[]) new Object[capacity];
            for(int i = 0; i < len; i++) {
                new_arr[i] = arr[i];
            }
            arr = new_arr;   
       } 
       arr[len++] = element;
    }
    public T removeAt(int rm_index) {
       this.checkIndex(rm_index);
       T data = arr[rm_index];
       T[] new_arr = (T[]) new Object[len - 1]; 
       for(int i= 0, j = 0; i < len; i++, j++){
           if(i == rm_index) {
               j--; 
           }
           else {
               new_arr[j] = arr[i]; 
           }
       }
       arr = new_arr;
       this.capacity = --len;
       return data;
    }
    public boolean remove(Object obj) {
        for(int i = 0; i < len; i++) {
            if(arr[i].equals(obj)) {
                this.removeAt(i);
                return true;
            }
        } 
        return false;
    }

    public int indexOf(Object obj){
        for(int i = 0; i < len; i++) {
            if(arr[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return this.indexOf(obj) != -1; 
    }

    @Override
    public  Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < len;
            }
            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString() {
        if(len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for(int i = 0; i < len - 1; i++) {
                sb.append(arr[i] + ", ");
            }
            return sb.append(arr[len - 1] + "]").toString();  
        } 
    }
}
class Main {
    public static void main(String[] args) {
        Array<String> animals = new Array<String>(); 
        animals.add("capibara");
        animals.add("perro");
        animals.add("carpincho");
        for (String item : animals){
            System.out.println(item);
        }
    }
}
