package fis.collection;

import junit.framework.TestCase;

public class MyQueueTest extends TestCase {

    public void testSize() {
    }

    public void testIsEmpty() {
    }

    public void testPush() {
        MyQueue<Student> myQueue = new MyQueue<>();
        assertTrue(myQueue.isEmpty());
        Student s1 = new Student(1, "Duc");
        Student s2 = new Student(2, "Thuy");
        Student s3 = new Student(3, "Thuy");
        Student s4 = new Student(4, "Thuy");
        Student s5 = new Student(5, "Thuy");
        Student s6 = new Student(6, "Thuy");
        myQueue.push(s1);myQueue.push(s2);myQueue.push(s3);myQueue.push(s4);
        myQueue.push(s5);myQueue.push(s6);
        assertTrue(!myQueue.isEmpty());
        assertTrue(myQueue.size() == 6);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.getTail().getValue());
        Student s0 = new Student(0, "Minh Duc");
        myQueue.pushToHead(s0);
        assertTrue(myQueue.size() == 7);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.getTail().getValue());
    }

    public void testPop() {
        MyQueue<Student> myQueue = new MyQueue<>();
        assertTrue(myQueue.isEmpty());
        Student s1 = new Student(1, "Duc");
        Student s2 = new Student(2, "Thuy");
        Student s3 = new Student(3, "Thuy");
        Student s4 = new Student(4, "Thuy");
        Student s5 = new Student(5, "Thuy");
        Student s6 = new Student(6, "Thuy");
        myQueue.push(s1);myQueue.push(s2);myQueue.push(s3);myQueue.push(s4);
        myQueue.push(s5);myQueue.push(s6);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.peek());
    }

    public void testRemove() {
    }
}