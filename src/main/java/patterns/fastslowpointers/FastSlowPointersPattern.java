package patterns.fastslowpointers;

public class FastSlowPointersPattern {
    /*
     * This pattern is used in problems like:
     * 1. Linked List Cycle
     * 2. Find Cycle Start
     * 3. Middle of Linked List
     * 4. Palindrome Linked List
     */
    
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Example 1: Detect Cycle in Linked List (LeetCode 141)
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
    
    // Example 2: Find Cycle Start (LeetCode 142)
    public static ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        
        // Find the meeting point
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        if (!hasCycle) return null;
        
        // Find cycle start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
    
    // Example 3: Middle of Linked List (LeetCode 876)
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    public static void main(String[] args) {
        // Create a linked list with cycle: 1->2->3->4->5->2
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2; // Create cycle
        
        // Test cycle detection
        System.out.println("Has cycle: " + hasCycle(head));
        
        // Test cycle start detection
        ListNode cycleStart = detectCycle(head);
        System.out.println("Cycle starts at node with value: " + 
                         (cycleStart != null ? cycleStart.val : "null"));
        
        // Create a new list without cycle for middle node test
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        
        // Test middle node
        ListNode middle = middleNode(head2);
        System.out.println("Middle node value: " + middle.val);
    }
}