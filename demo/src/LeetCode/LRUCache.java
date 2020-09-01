package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: LRUCache
 * </p>
 * <p>
 * Description: LeetCode.146
 * </p>
 * <p>
 * Company: www.xdu.com
 * </p>
 * 
 * @author mxxlalala
 * @date 2020年8月31日下午4:22:01
 * @version 1.0
 */
public class LRUCache {
	
	private class ListNode {
		private int key;
		private int value;
		private ListNode prev;
		private ListNode next;
		public ListNode() {}
		public ListNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
	
	int capacity;
	int size;
	private Map<Integer, ListNode> cache = new HashMap<Integer, ListNode>();;
	private ListNode head;
	private ListNode tail;

	public LRUCache(int capacitiy) {
		this.size = 0;
		this.capacity = capacitiy;
		head = new ListNode();
		tail = new ListNode();
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		ListNode node = cache.get(key);
		if (node == null) {
			return -1;
		}
		//如果 key存在，先通过哈希表定位，再移到头部
		move2Head(node);
		return node.value;
	}

	public void put(int key, int value) {
		ListNode node = cache.get(key);
		if (node == null) {
			//如果 key不存在，创建一个新的节点
			ListNode newNode = new ListNode(key, value);
			cache.put(key, newNode);
			add2Head(newNode);
			++size;
			if (capacity < size) {
				//双链表里移除尾部节点
				ListNode tail = removeTail();
				//哈希表里也要移除
				cache.remove(tail.key);
				--size;
			}
		}else{
			//如果 key存在，先通过哈希表定位，再修改 value，并移到头部
			node.value = value;
			move2Head(node);
		}
	}

	private void add2Head(ListNode node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	private void removeNode(ListNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	private void move2Head(ListNode node) {
		removeNode(node);
		add2Head(node);
	}

	private ListNode removeTail() {
		ListNode res = tail.prev;
		removeNode(res);
		return res;
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.cache.keySet());

		int res1 = cache.get(1);
		System.out.println(res1);

		cache.put(3, 3);

		int res2 = cache.get(2);
		System.out.println(res2);

		int res3 = cache.get(3);
		System.out.println(res3);

		cache.put(4, 4);
		System.out.println(cache.cache.keySet());

		int res4 = cache.get(1);
		System.out.println(res4);

		int res5 = cache.get(3);
		System.out.println(res5);

		int res6 = cache.get(4);
		System.out.println(res6);
	}
}
