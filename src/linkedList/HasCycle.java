package linkedList;

/**
 * 环形链表相关
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        ListNode node2 = head;
        while (head!=null&&head.next!=null&&node2.next!=null&&node2.next.next!=null){
            head = head.next;
            node2 = node2.next.next;
            if(head.val == node2.val){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取环起始节点位置
     *https://leetcode.cn/problems/linked-list-cycle-ii/?envType=study-plan-v2&envId=top-100-liked
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        //双指针 追及
        return null;
    }

    public ListNode swapPairs(ListNode head) {

        ListNode root = new ListNode(0);
        ListNode result = root;
        if(head!=null&&head.next!=null){
            ListNode a1 = head;
            ListNode a2 = head.next;
            while(a2!=null){
                ListNode temp = a2.next;
                root.next = a2;
                a2.next = a1;
                a1.next = temp;

                root = root.next.next;
                a1 = temp;
                if(temp == null){
                    a2 = null;
                }else{
                    a2 = temp.next;
                }
            }
            return result.next;
        }
        return head;
    }

    public static void main(String[] args) {
        new HasCycle().detectCycle(ListNode.convert(new int[]{1,2,3},0));
    }
}
