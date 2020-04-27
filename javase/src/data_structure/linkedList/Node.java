package data_structure.linkedList;

public class Node<E> {

    private Node next;

    private E val;

    public Node(E val) {
        this.val = val;
    }


    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }
}
