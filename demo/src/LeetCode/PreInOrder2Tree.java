package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: PreInOrder2Tree</p>
 * <p>Description: leetcode.105:前序遍历与中序遍历构造二叉树</p>
 * <p>Company: www.xdu.com</p> 
 * @author	mxxlalala
 * @date	2020年9月1日下午4:18:43
 * @version 1.0
 */

public class PreInOrder2Tree {
	
	private static Map<Integer, Integer> indexMap;

	public static void main(String[] args) {
		int[] preorder = {3,9,20,15,7};
		int[] inorder = {9,3,15,20,7};
		TreeNode root = buildTree(preorder, inorder);
		preorder_traverse(root);
	}

	private static void preorder_traverse(TreeNode root) {
		if(root == null) return;
		System.out.println(root.val);
		preorder_traverse(root.left);
		preorder_traverse(root.right);
	}

	// Definition for a binary tree node.
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}

	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		int len = preorder.length;
		indexMap = new HashMap<Integer, Integer>();
		for(int i = 0; i < len; i++) {
			indexMap.put(inorder[i], i);
		}
		return myBuildTree(preorder, inorder, 0, len-1, 0, len-1);
	}

	private static TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, 
			int inorder_left, int inorder_right) {
		if(preorder_left > preorder_right) return null;
		
		//前序遍历中的第一个节点就是根节点
		int preorder_root = preorder_left;
		//确定中序遍历中的根节点
		int inorder_root = indexMap.get(preorder[preorder_root]);
		TreeNode root = new TreeNode(preorder[preorder_root]);
		int sub_left = inorder_root - inorder_left;
		root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + sub_left,
				inorder_left, inorder_root -1);
		root.right = myBuildTree(preorder, inorder, preorder_left + sub_left + 1, preorder_right,
				inorder_root + 1, inorder_right);
		return root;
	}

}
