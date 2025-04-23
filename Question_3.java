import java.util.*;

public class question_3 {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    static Scanner sc = new Scanner(System.in);

    public static Node buildTree() {
        int data = sc.nextInt();
        boolean hasLeft = sc.nextBoolean();
        Node node = new Node(data);
        if (hasLeft) {
            node.left = buildTree();
        }
        boolean hasRight = sc.nextBoolean();
        if (hasRight) {
            node.right = buildTree();
        }
        return node;
    }

    public static Node findLCA(Node root, int n1, int n2) {
        if (root == null)
            return null;

        if (root.data == n1 || root.data == n2)
            return root;

        Node leftLCA = findLCA(root.left, n1, n2);
        Node rightLCA = findLCA(root.right, n1, n2);

        if (leftLCA != null && rightLCA != null)
            return root;

        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    public static void main(String[] args) {
        Node root = buildTree();

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        Node lca = findLCA(root, n1, n2);

        if (lca != null) {
            System.out.println(lca.data);
        } else {
            System.out.println("LCA not found");
        }
    }
}