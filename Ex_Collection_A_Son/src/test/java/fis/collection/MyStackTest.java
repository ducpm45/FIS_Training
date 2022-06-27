package fis.collection;

import org.junit.Assert;
import org.junit.Test;

public class MyStackTest {
    @Test
    public void testSize() {
        MyStack<Student> myStack = new MyStack<>();
        System.out.println(myStack.size());
    }

    @Test
    public void testIsEmpty() {
        MyStack<Student> myStack = new MyStack<>();
        Assert.assertTrue(myStack.isEmpty());
    }
    @Test
    public void testPush() {
        MyStack<Student> myStack = new MyStack<>();
        Student s1 = new Student(1, "Duc");
        Student s2 = new Student(2, "Thuy");
        Student s3 = new Student(3, "Thuy");
        Student s4 = new Student(4, "Thuy");
        Student s5 = new Student(5, "Thuy");
        Student s6 = new Student(6, "Thuy");
        myStack.push(s1);myStack.push(s2);myStack.push(s3);myStack.push(s4);
        myStack.push(s5);myStack.push(s6);
        System.out.println(myStack.size());
//        assertTrue(myStack.isEmpty());
        Assert.assertFalse(myStack.isEmpty());
    }

    @Test
    public void testPop() {
        MyStack<Student> myStack = new MyStack<>();
        Student s1 = new Student(1, "Duc");
        Student s2 = new Student(2, "Thuy");
        Student s3 = new Student(3, "Thuy");
        Student s4 = new Student(4, "Thuy");
        Student s5 = new Student(5, "Thuy");
        Student s6 = new Student(6, "Thuy");
        myStack.push(s1);myStack.push(s2);myStack.push(s3);myStack.push(s4);
        myStack.push(s5);myStack.push(s6);
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
    }
    @Test
    public void testRemove() {
        MyStack<Student> myStack = new MyStack<>();
        Student s1 = new Student(1, "Duc");
        Student s2 = new Student(2, "Thuy");
        Student s3 = new Student(3, "Thuy");
        Student s4 = new Student(4, "Thuy");
        Student s5 = new Student(5, "Thuy");
        Student s6 = new Student(6, "Thuy");
        myStack.push(s1);myStack.push(s2);myStack.push(s3);myStack.push(s4);
        myStack.push(s5);myStack.push(s6);
        Assert.assertEquals(6, myStack.size());
        myStack.remove(s3);
        Assert.assertEquals(5, myStack.size());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }
}