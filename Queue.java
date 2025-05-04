class Queue<T> implements Iterable<T> {
    private java.util.LinkedList<T> list = new java.util.LinkedList<T>();
    public Queue() {}
    public Queue(T firstElem) {
        offer(firstElem);
    }
    public void enqueue(T item) {
        list.addLast(item);
    }
    public T dequeue() {
        return list.poll();
    }
    public void offer(T item) {
        list.addFirst(item);
    }
    public T poll() {
        return list.pollLast();
    }
    public T peek() {
        if(isEmpty())
            throw new RuntimeException("Queue is empty");
        return list.peekLast();
    }
    public int size() {
        return list.size();
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public java.util.Iterator<T> iterator() {
        return list.iterator(); 
    }

    @override
    public String toString() {
        return list.toString();
    }

}
