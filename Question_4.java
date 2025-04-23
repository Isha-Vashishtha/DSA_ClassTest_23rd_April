import java.util.*;

public class Question_4 {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class Info {
        int size;
        int min;
        int max;
        int ans;
        boolean isBST;

        Info(int size, int min, int max, int ans, boolean isBST) {
            this.size = size;
            this.min = min;
            this.max = max;
            this.ans = ans;
            this.isBST = isBST;
        }
    }

    static Node buildTree(int[] preorder, int[] inorder, int start, int end) {
        if (start > end)
            return null;

        int curr = preorder[idx++];
        Node node = new Node(curr);

        if (start == end)
            return node;

        int pos = search(inorder, start, end, curr);

        node.left = buildTree(preorder, inorder, start, pos - 1);
        node.right = buildTree(preorder, inorder, pos + 1, end);

        return node;
    }

    static int search(int[] inorder, int start, int end, int val) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == val)
                return i;
        }
        return -1;
    }

    static int idx = 0;

    static Info largestBST(Node root) {
        if (root == null)
            return new Info(0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);

        if (root.left == null && root.right == null)
            return new Info(1, root.data, root.data, 1, true);

        Info left = largestBST(root.left);
        Info right = largestBST(root.right);

        Info curr = new Info(0, 0, 0, 0, false);
        curr.size = left.size + right.size + 1;

        if (left.isBST && right.isBST && root.data > left.max && root.data < right.min) {
            curr.min = Math.min(left.min, root.data);
            curr.max = Math.max(right.max, root.data);
            curr.ans = curr.size;
            curr.isBST = true;
            return curr;
        }

        curr.ans = Math.max(left.ans, right.ans);
        curr.isBST = false;
        return curr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] preorder = new int[n];
        for (int i = 0; i < n; i++)
            preorder[i] = sc.nextInt();

        int[] inorder = new int[n];
        for (int i = 0; i < n; i++)
            inorder[i] = sc.nextInt();

        idx = 0;
        Node root = buildTree(preorder, inorder, 0, n - 1);

        System.out.println(largestBST(root).ans);
    }
}
