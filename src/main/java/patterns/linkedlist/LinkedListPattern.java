package patterns.linkedlist;

public class LinkedListPattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Reverse Linked List
     * 2. Merge Two Sorted Lists
     * 3. Remove Nth Node From End
     * 4. Reorder List
     */
    
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Example 1: Reverse Linked List (LeetCode 206)
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
    
    // Example 2: Merge Two Sorted Lists (LeetCode 21)
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        curr.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
    
    // Example 3: Remove Nth Node From End (LeetCode 19)
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        
        // Advance first pointer by n+1 steps
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        
        // Move both pointers until first reaches end
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        
        // Remove the nth node
        second.next = second.next.next;
        return dummy.next;
    }
    
    // Example 4: Reorder List (LeetCode 143)
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        
        // Find middle
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode second = reverseList(slow.next);
        slow.next = null;
        
        // Merge two halves
        ListNode first = head;
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            
            first.next = second;
            second.next = temp1;
            
            first = temp1;
            second = temp2;
        }
    }
    
    public static void main(String[] args) {
        // Test Reverse Linked List
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        ListNode reversed = reverseList(head1);
        printList("Reversed list: ", reversed);
        
        // Test Merge Two Sorted Lists
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        ListNode merged = mergeTwoLists(l1, l2);
        printList("Merged list: ", merged);
        
        // Test Remove Nth Node From End
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        ListNode afterRemoval = removeNthFromEnd(head2, 2);
        printList("After removing 2nd node from end: ", afterRemoval);
        
        // Test Reorder List
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        head3.next.next.next = new ListNode(4);
        reorderList(head3);
        printList("Reordered list: ", head3);
    }
    
    private static void printList(String message, ListNode head) {
        System.out.print(message);
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}