package com.alex.line;

import java.util.ArrayList;

/**
 * Created by Alex on 10/28/2017.
 */

public class BinaryTree {

    public static void main(String[] args) {
        TraverseTree(SimpleTestTree());
    }

    //Node depth is equal to value
    public static Node SimpleTestTree() {
        Node level4a = new Node(4, null, null);
        Node level3a = new Node(3, level4a, null);
        Node level2a = new Node(2, level3a, null);
        Node level1a = new Node(1, level2a, null);

        Node level4b = new Node(4, null, null);
        Node level4b2 = new Node(4, null, null);
        Node level3b = new Node(3, level4b, level4b2);
        Node level2b = new Node(2, null, level3b);
        Node level2b2 = new Node(2, null, null);
        Node level1b = new Node(1, level2b, level2b2);

        Node level0 = new Node(0, level1a, level1b);
        return level0;
    }

    static ArrayList<Node> queue = new ArrayList<>();

    static void TraverseTree(Node root) {
        queue.clear();
        queue.add(root);

        while (!queue.isEmpty()) {
            ArrayList<Node> tmpQueue = new ArrayList<>();
            tmpQueue.addAll(queue);
            queue.clear();

            System.out.println("");
            for (Node n : tmpQueue) {
                System.out.print(n.val + ",");
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
        }
    }

}

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
