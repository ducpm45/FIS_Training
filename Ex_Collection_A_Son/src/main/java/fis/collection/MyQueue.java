package fis.collection;

public class MyQueue<T> implements ICollection<T> {
    private Node<T> head;
    private Node<T> tail;
    MyQueue() {
        head = tail = null;
    }
    @Override
    public int size() {
        if(isEmpty()) {
            return 0;
        }
        int size = 0;
        Node<T> currentNode = head;
        while(currentNode != null) {
            size++;
            currentNode = currentNode.next;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null && tail ==null;
    }

    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        if(isEmpty()) {
            head = tail = newNode;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void pushToHead(T item) {
        Node<T> newNode = new Node<>(item);
        if(isEmpty()) {
            head = tail = newNode;
        }
        newNode.next = head;
        head = newNode;
    }

    @Override
    public T pop() {
        if(isEmpty()) {
            System.err.println("MyQueue is empty!");
        }
        T temp = head.getValue();
        head = head.next;
        return temp;
    }

    @Override
    public T peek() {
        return head.getValue();
    }

    @Override
    public void remove(T item) {
        if(isEmpty()) {
            System.err.println("MyQueue is empty!");
        }
        if(head.getValue().equals(item)) {
            pop();
        }
        Node<T> currentNode = head;
        while(currentNode.next != null) {
            if(currentNode.next.getValue().equals(item)) {
                Node<T> tempNode = currentNode.next;
                currentNode.next = tempNode.next;
            }
        }
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }
}
