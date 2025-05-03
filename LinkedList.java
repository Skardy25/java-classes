import java.util.*;

class Node<T> {
    T data;
    Node<T> next;

    public Node() {
        this.data = null;
        this.next = null; 
    }
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next; 
    }
    public T getData() {
        return this.data; 
    }
    public void setData(T data) {
        this.data = data; 
    }

}
class LinkedList<T>  implements Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }
    public void remove(int index) {
        this.checkIndex(index);
        // remove the first element
        if (index == 0) {
            this.head = this.head.next; 
        }  
        else {
            Node<T> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (index == this.size - 1) {
                this.tail = current; 
            } 
        }
        this.size--;
        
    }
    public void add(int index, T data) {
        this.checkIndex(index);
        Node<T> newNode = new Node<T>(data);
        if (index == 0) {
            newNode.next = this.head;
            this.head = newNode; 
        } 
        else {
            Node<T> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (index == this.size) {
                this.tail = newNode;
            }

        }
        this.size++;
    }
    
    public T get(int index) {
        this.checkIndex(index);
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data; 
    }
    public int size() {
        return this.size; 
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            public boolean hasNext() {
                return current != null;
            }
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        }; 
    }
}

class Main {
    public static void main(String[] args) {
       LinkedList<Integer> array = new LinkedList<Integer>();
       array.add(20);
       array.add(2);
       array.add(6);
       array.add(6);
       for(Integer item : array){
           System.out.println(item);
       }
       
    }
}
