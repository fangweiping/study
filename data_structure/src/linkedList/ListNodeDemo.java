package linkedList;

public class ListNodeDemo {

    public static void main(String[] args) {
        //初始链表
        Node<Integer> listNode1 = new Node<Integer>(1);
        Node<Integer> listNode2 = new Node<Integer>(2);
        Node<Integer> listNode3 = new Node<Integer>(3);
        Node<Integer> listNode4 = new Node<Integer>(4);
        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);

        Node<Integer> node = reverseLinkedList(listNode1);
        Node<Integer> newNode = reverse(node);
        printElement(newNode);

    }

    //打印链表存储元素
    public static void printElement(Node<Integer> head) {
        Node listNode = head.getNext();
        System.out.println(head.getVal());
        while(listNode!=null){
            System.out.println(listNode.getVal());
            listNode=listNode.getNext();
        }
    }

    //递归链表反转
    public static Node<Integer> reverseLinkedList(Node<Integer> head) {
        if (head==null||head.getNext()==null) {
            return head;
        }

        Node temp = head.getNext();
        Node newNode = reverseLinkedList(head.getNext());
        temp.setNext(head);
        head.setNext(null);
        return newNode;
    }

    //遍历链表反转
    public static Node<Integer> reverse(Node<Integer> node) {
        Node pre = null;
        Node next = null;

        while (node!=null) {
            next = node.getNext();
            node.setNext(pre);
            pre=node;
            node=next;
        }
        return pre;
    }
}
