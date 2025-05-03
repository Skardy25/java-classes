import java.util.*;
class DoublyLinkedList <T> implements Iterable<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    private class Node<T> {
        T data;
        Node<T> next, prev;
        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
        @Override
        public String toString() {
            return data.toString();
        }
    }
    public DoublyLinkedList() {}

    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T elem) {
        addLast(elem);
    }

    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.prev = new Node<T>(elem, head, null);
            head = head.prev;
        }
        size++;
    }

    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        }  
        else {
            tail.next = new Node<T>(elem, null, tail);
            tail = tail.next;
        }
        size++;
    }
    private checkEmpty() {
        if (isEmpty()) throw new RuntimeException("Empty List");
    }
    public T peekFirst() {
        this.checkEmpty();
        return head.data;
    }
    public T peekLast() {
        this.checkEmpty();
        return tail.data; 
    }
    public T removeFirst() {
        this.checkEmpty();
        T data = head.data;
        head = head.next;
        --size;
        if (isEmpty()) tail = null;
        else head.prev = null;
        return data; 
    }
    public T removeLast() {
        this.checkEmpty();
        T data = tail.data;
        tail = tail.prev;
        --size;
        if (isEmpty()) head = null;
        else tail.next = null;
        return data;
    }

    private T remove(Node<T> node) {
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();
        node.next.prev = node.prev;
        node.prev.next = node.next;
        T data = node.data;
        // limpiamos memoria
        node.data = null;
        node = node.prev = node.next = null;

        --size;
        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        int i;
        Node<T> trav;
        if (index < size/2) {
            for (i = 0, trav = head; i != index; i++)
                trav = trav.next; 
        } 
        else {
            for (i = size-1, trav = tail; i!= index; i--)
                trav = trav.prev;
        }
        return remove(trav);
    }

    public boolean remove(Object obj) {
        Node<T> trav = head;
        if (obj == null) {
            for (trav = head; trav!= null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }

        } 
        else {
            for (trav = head; trav!= null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                } 
            } 
        }
        return false;
    }
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;
        if (obj == null) {
            for (; trav!= null; trav = trav.next, index++) {
                if (trav.data == null) {
                    return index;
                }
            }
        } else { 
            for (; trav!= null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) {
                    return index;
                }
            } 
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj)!= -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;
            @Override
            public boolean hasNext() {
                return trav!= null;
            }
            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };  
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while (trav!= null) {
            sb.append(trav.data + ", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
