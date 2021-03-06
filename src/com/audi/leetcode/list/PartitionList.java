package com.audi.leetcode.list;

/**
 * https://leetcode.com/problems/partition-list/
 * <p>
 * 给定一个基准值，比基准值大的都在右边，比基准值小的都在左边
 * <p>
 * 可以使用虚拟头结点来简化编码
 *
 * @author WangQuanzhou
 * @date 2020-08-08
 */
public class PartitionList {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode leftHead = new ListNode(-1);
        ListNode rightHead = new ListNode(-1);
        ListNode leftDummyHead = leftHead;
        ListNode rightDummyHead = rightHead;

        while (null != head) {
            if (head.val < x) {
                // 注意下面两行代码的顺序不能错
                leftHead.next = head;
                leftHead = leftHead.next;
            } else {
                // 注意下面两行代码的顺序不能错
                rightHead.next = head;
                rightHead = rightHead.next;
            }
            head = head.next;
        }

        rightHead.next = null;
        // 合并左右链表
        if (null == leftHead) {
            return rightHead;
        }

        leftHead.next = rightDummyHead.next;

        return leftDummyHead.next;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        PartitionList partitionList = new PartitionList();
        ListNode head = partitionList.partition(node1, 3);
//        ListNode head = partitionList.partition2(node1, 3);
        while (null != head) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
