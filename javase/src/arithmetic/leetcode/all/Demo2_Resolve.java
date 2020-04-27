package arithmetic.leetcode.all;


import arithmetic.po.ListNode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Demo2_Resolve {

    public static void main(String[] args) {
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node3= new ListNode(3);
        node2.next=node4;
        node4.next=node3;

        ListNode node5= new ListNode(5);
        ListNode node6= new ListNode(6);
        node4= new ListNode(4);
        node5.next=node6;
        node6.next=node4;

        ListNode listNode = addTwoNumbers(node2, node5);

        System.out.println(listNode.val);
        while (listNode.next != null) {
            listNode=listNode.next;
            System.out.println(listNode.val);
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       ListNode dummyNode=new ListNode(0);
        ListNode p =l1,q=l2 ,curr=dummyNode;
        int z =0;
        while(p!=null||q!=null){
            int x  = p!=null?p.val:0;
            int y = q!=null?q.val:0;
            int sum = x+y+z;
            z = sum/10;
            curr.next=new ListNode(sum%10);
            curr=curr.next;
            if(p!=null)p=p.next;
            if(q!=null)q=q.next;
        }
        if (z > 0) {
            curr.next=new ListNode(z);
        }
        return dummyNode.next;
    }
}
