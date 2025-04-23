public class Question_2 {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    static String[] input = {
            "10", "true", "20", "true", "40", "false", "false", "false", "true", "60", "false", "false"
    };
    static int index = 0;

    static int node1 = 40;
    static int node2 = 60;

    public static Node buildTree() {
        if (index >= input.length)
            return null;

        String val = input[index++];
        Node root = new Node(Integer.parseInt(val));

        if (index < input.length && input[index].equals("true")) {
            index++;
            root.left = buildTree();
        }
        if (index < input.length && input[index].equals("false")) {
            index++;
        }

        if (index < input.length && input[index].equals("true")) {
            index++;
            root.right = buildTree();
        }
        if (index < input.length && input[index].equals("false")) {
            index++;
        }

        return root;
    }

    public static Node findLCA(Node root, int n1, int n2) {
        if (root == null)
            return null;

        if (root.data == n1 || root.data == n2)
            return root;

        Node left = findLCA(root.left, n1, n2);
        Node right = findLCA(root.right, n1, n2);

        if (left != null && right != null)
            return root;

        return (left != null) ? left : right;
    }

    public static void main(String[] args) {
        Node root = buildTree();
        Node lca = findLCA(root, node1, node2);

        if (lca != null)
            System.out.println("LCA: " + lca.data);
        else
            System.out.println("LCA not found");
    }
}