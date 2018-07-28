package algorithms.ds.stack;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StackTest {

    @Test
    public void testEmptyArrayStack() {
        testEmptyStack(new ArrayStack<>());
    }

    @Test
    public void testEmptyLinkedListStack() {
        testEmptyStack(new LinkedListStack<>());
    }

    @Test
    public void testArrayStack() {
        testStack(new ArrayStack<>());
    }

    @Test
    public void testLinkedListStack() {
        testStack(new LinkedListStack<>());
    }

    public void testEmptyStack(IStack<String> stack) {
        assertEquals(0, stack.size());
        try {
            stack.pop();
            fail("Should throw NoSuchElementException here");
        } catch (NoSuchElementException ignore) {
            // ignore
        }
    }

    public void testStack(IStack<Integer> stack) {
        assertEquals(0, stack.size());
        for (int i=1; i<=1000; i++) {
            stack.push(i);
            assertEquals(i, stack.size());
        }

        for (int i=1000; i>500; i--) {
            assertEquals(i, stack.peek().intValue());
            assertEquals(i, stack.pop().intValue());
            assertEquals(i-1, stack.size());
        }

        for (int i=1; i<=10000; i++) {
            stack.push(i+500);
            assertEquals(i+500, stack.size());
        }

        assertEquals(10500, stack.size());

        for (int i=10500; i>0; i--) {
            assertEquals(i, stack.peek().intValue());
            assertEquals(i, stack.pop().intValue());
            assertEquals(i-1, stack.size());
        }

        assertEquals(0, stack.size());
        try {
            stack.pop();
            fail("Should throw NoSuchElementException here");
        } catch (NoSuchElementException ignore) {
            // ignore
        }
    }
}
