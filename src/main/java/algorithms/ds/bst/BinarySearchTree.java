package algorithms.ds.bst;

import java.util.function.Consumer;

public class BinarySearchTree<T> {

    static final class Node<T> {
        public T value;
        public Node left;
        public Node right;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;

    public Node<T> search(T val) {
        return search(root, val);
    }

    public void insert(T val) {
        root = insert(root, val);
    }

    public void delete(T val) {
        root = delete(root, val);
    }

    public void traverseInOrder(Node<T> start, Consumer<Node<T>> visitor) {
        if (start != null) {
            traverseInOrder(start.left, visitor);
            visitor.accept(start);
            traverseInOrder(start.right, visitor);
        }
    }

    public void traversePreOrder(Node<T> start, Consumer<Node<T>> visitor) {
        if (start != null) {
            visitor.accept(start);
            traversePreOrder(start.left, visitor);
            traversePreOrder(start.right, visitor);
        }
    }

    public void traversePostOrder(Node<T> start, Consumer<Node<T>> visitor) {
        if (start != null) {
            traversePostOrder(start.left, visitor);
            traversePostOrder(start.right, visitor);
            visitor.accept(start);
        }
    }

    private Node<T> delete(Node<T> start, T val) {
        if (start == null) {
            return null;
        }
        if (cmp(val, start.value) < 0) {
            start.left = delete(start.left, val);
        } else if (cmp(val, start.value) > 0) {
            start.right = delete(start.right, val);
        } else {
            // now, key are equal

            // when 1 child node or no child nodes
            if (start.right == null) {
                return start.right;
            } else if (start.left == null) {
                return start.right;
            }

            // both child nodes exist
            Node<T> inOrderSuccessor = findMinNode(start.right);
            start.value = inOrderSuccessor.value;
            start.right = delete(start.right, inOrderSuccessor.value);
        }
        return root;
    }

    private Node<T> findMinNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node<T> insert(Node<T> start, T val) {
        if (start == null) {
            return new Node<>(val);
        }
        if (isLess(val, start.value)) {
            start.left = search(start.left, val);
        } else {
            start.right = search(start.right, val);
        }
        return start;
    }

    private Node<T> search(Node<T> start, T val) {
        if (start == null || start.value.equals(val)) {
            return start;
        }
        if (isLess(val, start.value)) {
            return search(start.left, val);
        } else {
            return search(start.right, val);
        }
    }

    private boolean isLess(T val1, T val2) {
        return cmp(val1, val2) < 0;
    }

    private int cmp(T val1, T val2) {
        Comparable<T> cmp = (Comparable<T>)val1;
        return cmp.compareTo(val2);
    }
}
