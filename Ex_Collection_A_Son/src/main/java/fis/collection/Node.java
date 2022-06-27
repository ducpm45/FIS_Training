package fis.collection;

public class Node<T> {
    T value;
    Node next;

    public Node(T value) {
        this.value = value;
        next = null;
    }

    public T getValue() {
        return value;
    }
}
