package fis.collection;

import java.util.Collection;
import java.util.Stack;

public interface ICollection<T> {
    int size();
    boolean isEmpty();
    void push(T item);
    T pop();
    T peek();
    void remove(T item);
}
