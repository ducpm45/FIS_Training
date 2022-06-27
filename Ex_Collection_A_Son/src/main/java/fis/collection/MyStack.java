package fis.collection;

public class MyStack<T> implements ICollection<T> {
    private Node<T> topNode;

    public MyStack() {
        topNode = null;
    }

    @Override
    public int size() {
        int size = 0;
        if (isEmpty()) {
            return 0;
        }
        Node<T> currentNode = topNode;
        while (currentNode != null) {
            size++;
            currentNode = currentNode.next;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = topNode;
        topNode = newNode;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            System.err.println("MyStack is empty!");
        }
        T temp = topNode.getValue();
        topNode = topNode.next;
        return temp;
    }

    @Override
    public T peek() {
        return topNode.getValue();
    }

    @Override
    public void remove(T item) {
        if (isEmpty()) {
            System.err.println("MyStack is empty!");
        }
        if (topNode.getValue().equals(item)) {
            pop();
        }
        MyStack<T> tempStack = new MyStack<>();
        Node<T> currentNode = topNode;
        while (topNode.next != null) {
            if(currentNode.next.getValue().equals(item)) {
                tempStack.push(pop());
                currentNode = currentNode.next;
                topNode = currentNode;
            }
        }
    }
}
