package linkedList;

import java.util.*;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 
 * @ClassName AddTwoNumbers 
 * @Description TODO 
 * @author guotg
 * @date 2020-9-10 18:50:00 
 */
public class AddTwoNumbers {
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(0);
		int jw = 0;
		ListNode r2 = new ListNode(0);
		r2.next = result;
		while(l1 != null || l2 != null || jw != 0) {
			if(r2.next == null)r2.next = new ListNode(0);
			r2 = r2.next;
			int a = 0; int b = 0;
			if(l1 != null) {
				a = l1.val;
			}
			if(l2 != null) {
				b = l2.val;
			}
			int sum = a + b + jw;
			r2.val = sum%10;
			if(sum > 9) {
				jw = 1;
			}else {
				jw = 0;
			}
			
			
			l1 = l1.next;
			l2 = l2.next;
		}
		
		return result;
	}

	public static void reorderList(ListNode head) {
		ListNode result = head;
		Deque<ListNode> deque = new ArrayDeque<>();
		boolean first = false;
		while(head!=null){
			deque.offerLast(head);
			head = head.next;
		}
		head = result;
		while(!deque.isEmpty()){
			ListNode temp = head.next;
			head.next = deque.pollLast();
			deque.pollLast();
			head.next.next = temp;
			head = temp;
		}
		if(head!=null)head.next = null;
		head = result;
	}

	public ListNode middleNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	/**
	 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
	 **/
	public int[] reversePrint(ListNode head) {
		List<Integer> result = new ArrayList<>();
		reversePrint(head, result);
		return result.stream().mapToInt(Integer::valueOf).toArray();
	}

	private void reversePrint(ListNode head, List<Integer> result) {
		if(head == null) return;
		reversePrint(head.next, result);
		result.add(head.val);
	}

	public ListNode deleteDuplication(ListNode head) {
		if(head == null || head.next == null)return head;
		Set<Integer> set = new HashSet<>();
		ListNode node = head;
		set.add(node.val);
		while(node.next != null) {
			ListNode curr = node.next;
			if(set.contains(curr.val)) {
				node.next = node.next.next;
			}else {
				set.add(curr.val);
				node = node.next;
			}
		}
		return head;
	}

	/**
	 * 快慢指针找从尾数第k个节点
	 **/
	public ListNode findKthToTail(ListNode head, int k) {
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null) {
			fast = fast.next;
			if(k == 0) {
				slow = slow.next;
			} else {
				k--;
			}
		}
		return k==0? slow : null;
	}

	/**
	 * 快慢指针证明有环
	 **/
	public ListNode ifCycle(ListNode head) {
		if(head == null) return null;
		ListNode fast = head;
		ListNode slow = head;

		while(fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(fast.equals(slow)) return fast;
		}
		return null;
	}

	/**
	 * 找环的入口
	 *
	 **/
	public ListNode detectCycle(ListNode head) {
		if(head == null) return null;
		ListNode fast = head;
		ListNode slow = head;
		ListNode meet = null;
		while(fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(fast.equals(slow)) {
				meet = fast;
				break;
			}
		}
		if(meet != null) {
			fast = head;
			while(fast != slow) {
				fast = fast.next;
				slow = slow.next;
			}
			return fast;
		}
		return null;
	}



	ListNode root = null;

	public static ListNode reverseList(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode result = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return result;
	}
	ListNode successor = null; // 后驱节点
	public ListNode reverseN(ListNode head, int n) {
		if (n == 1) {
			// 记录第 n + 1 个节点
			successor = head.next;
			return head;
		}
		// 以 head.next 为起点，需要反转前 n - 1 个节点
		ListNode last = reverseN(head.next, n - 1);

		head.next.next = head;
		// 让反转之后的 head 节点和后面的节点连起来
		head.next = successor;
		return last;
	}


	public static ListNode mergeListNode(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;


		ListNode resultHead = new ListNode(0);
		ListNode result = resultHead;
		while(true) {
			if(l1 == null || l2 == null) {
				result.next = l1 == null? l2 : l1;
				break;
			}
			if(l1.val < l2.val) {
				result.next = l1;
				l1 = l1.next;
			} else {
				result.next = l2;
				l2 = l2.next;
			}
			result = result.next;
		}
		return resultHead.next;
	}

	public Node copyRandomList(Node head) {
		Map<Node, Node> map = new HashMap<>();
		Node node = head;

		while(node != null) {
			Node copy = new Node(node.val);
			map.put(node, copy);
			node = node.next;
		}

		node = head;
		while(node != null) {
			Node copy = map.get(node);
			copy.next = map.get(node.next);
			copy.random = map.get(node.random);
			node = node.next;
		}
		return map.get(head);
	}

	public ListNode deleteNode(ListNode head, int val) {
		ListNode node = new ListNode(0);
		ListNode result = node;
		node.next = head;
		while(node.next != null) {
			if(node.next.val == val) {
				node.next = node.next.next;
				break;
			}
			node = node.next;
		}
		return result.next;
	}

//	public ListNode sortList(ListNode head) {
//
//		ListNode temp = head;
//
//	}
//
//	public ListNode sortList(ListNode head, ListNode begin, ListNode end) {
//
//		ListNode temp = begin;
//		ListNode left = new ListNode(0);
//		ListNode rightHead = new ListNode(0);
//		while(begin.next != null && begin.next != end) {
//			if(begin.val)
//		}
//
//	}

	/**
	 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
	 * @param headA
	 * @param headB
	 * @return
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		boolean a = false;
		boolean b = false;
		ListNode aTemp = headA;
		ListNode bTemp = headB;
		while(aTemp != null && bTemp != null) {
			aTemp = aTemp.next;
			bTemp = bTemp.next;
			if(!a && aTemp==null) {
				aTemp = headB;
				a = true;
			}
			if(!b && bTemp == null) {
				bTemp = headA;
				b = true;
			}
			if(a && b && aTemp == bTemp) {
				return aTemp;
			}
		}
		return null;
	}

	/**
	 * 回文链表 1-2-2-1
	 * @param head
	 * @return
	 */
	public boolean isPalindrome(ListNode head) {
		return true;
	}

	public ListNode partition(ListNode head, int x) {
		ListNode left = new ListNode(-1), l = left;
		ListNode right = new ListNode(-1), r = right;
		while(head != null) {
			if(head.val < x) {
				l.next = head;
				l=l.next;
			}else {
				r.next = head;
				r = r.next;
			}
			head = head.next;
		}
		l.next = right.next;
		r.next = null;
		return left.next;
	}

	/**
	 * 合并 K 个升序链表
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
		for (ListNode list : lists) {
			if(list != null) {
				pq.offer(list);
			}
		}
		ListNode dummy = new ListNode(-1), t = dummy;
		while(!pq.isEmpty()) {
			t.next = pq.poll();
			t = t.next;
			if(t.next != null) {
				pq.offer(t.next);
			}
			t.next = null;
		}
		return dummy.next;
	}

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode result = new ListNode(0), p = result;
		while(list1 != null || list2 != null) {
			if(list2 == null || list1 != null && list1.val <= list2.val) {
				result.next = list1;
				result = result.next;
				list1 = list1.next;
			}else {
				result.next = list2;
				result = result.next;
				list2 = list2.next;
			}
		}
		return p.next;
	}

	/**
	 * 倒数第k个节点 1 2 3 4 5
	 * @param head
	 * @param k
	 * @return
	 */
	public int kthToLast(ListNode head, int k) {
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null) {
			fast = fast.next;
			if(k <= 0) {
				slow = slow.next;
			}
			k--;
		}
		return slow.val;
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		int k = n + 1;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode slow = dummy;
		ListNode fast = dummy;
		while(fast != null) {
			fast = fast.next;
			if(k <= 0) {
				slow = slow.next;
			}
			k--;
		}
		slow.next = slow.next.next;
		return dummy.next;
	}

	public ListNode deleteDuplicates(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null) {
			if(slow.val != fast.val) {
				slow.next = fast;
				slow = slow.next;
			}
			fast = fast.next;
			slow.next = null;
		}
		return head;
	}

	public ListNode reverseBetween(ListNode head, int left, int right) {
		return reverseBetween(head, left, right, head.val == left);
	}

	public ListNode reverseBetween(ListNode head, int left, int right, boolean isReverse) {
		if(head == null) {
			return null;
		}
		if(head.val == left) {
			isReverse = true;
		}

		ListNode listNode = reverseBetween(head.next, left, right, head.val != right && isReverse);
		if(isReverse) {
			listNode.next = head;
			head.next = null;
		}else {
			listNode = head;
		}
		return listNode;
	}

	public static void main(String[] args) {
		System.out.println(new AddTwoNumbers().reverseN(ListNode.convert(new int[]{1,2,3,4,5}), 3));
	}

	public static class ListNode {
		public int val;
		public ListNode next;
		public ListNode(int x) { val = x; }
		public ListNode(int val, ListNode next) { this.val = val; this.next = next; }


		public static ListNode convert(int[] arr){
			ListNode node = new ListNode(arr[0]);
			ListNode result = node;
			for (int i=1;i<arr.length;i++){
				node.next = new ListNode(arr[i]);
				node = node.next;
			}
			return result;
		}

		public String toString() {
			String result = String.valueOf(val);
			ListNode temp = next;
			while(temp != null) {
				result += String.valueOf(temp.val);
				temp = temp.next;
			}
		    return String.valueOf(result);
		}
	}

	public static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
}