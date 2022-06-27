package com.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class IntegerStack {

    private Deque<Integer> data = new ArrayDeque<Integer>();

	public void push(Integer number) {
		data.addFirst(number);
	}

	public Integer pop() {
		if (data.isEmpty()) {
			System.err.println("Stack is empty!!!");
		}
		return data.removeFirst();
	}

	public Integer peek() {
		return data.peekFirst();
	}

	public String toString() {
		return data.toString();
	}

	public static void main(String[] args) {
		IntegerStack stack = new IntegerStack();
		for (int i = 0; i < 8; i++) {
			stack.push(i);
		}
		System.out.println("Stack after pushed: " + stack);

		int firtsOutNumber = stack.pop();
		System.out.println("First out number: " + firtsOutNumber);

		int topNumber = stack.peek();
		System.out.println("After poped, top number =  " + topNumber);

	}
    
}
