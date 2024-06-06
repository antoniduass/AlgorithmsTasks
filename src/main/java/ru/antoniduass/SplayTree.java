package ru.antoniduass;

import java.util.LinkedList;
import java.util.Queue;

public class SplayTree {
    static private class Node implements Comparable<Node> {
        private final int key;
        private String data;
        private Node parent;
        private Node leftChild;
        private Node rightChild;

        private Node(int key, String data) {
            this.key = key;
            this.data = data;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.key, o.key);
        }
    }
    static public class Entry {
        private final int key;
        private final String data;

        public Entry(int key, String data) {
            this.key = key;
            this.data = data;
        }
        public int getKey() {
            return key;
        }
        public String getData() {
            return data;
        }
    }
    private Node root;
    public void add(int key, String data) {
        if (root == null) {
            root = new Node(key, data);
        } else {
            Node newNode = new Node(key, data);
            Node current = root;
            Node parent;
            while (true) {
                if (key <= current.key) {
                    parent = current;
                    current = parent.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        newNode.parent = parent;
                        splay(newNode);
                        return;
                    }
                } else {
                    parent = current;
                    current = parent.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        newNode.parent = parent;
                        splay(newNode);
                        return;
                    }
                }
            }
        }
    }
    public void set(int key, String data) {
        Node current = search(key);
        if (current == null) {
            System.out.println("There's no such key in the tree");
            return;
        }
        current.data = data;
    }
    public Node search(int key) {
        if (root == null) return null;
        Node current = root;
        Node parent;
        while (current.key != key) {
            parent = current;
            if (key < current.key) current = current.leftChild;
            else current = current.rightChild;
            if (current == null) {
                splay(parent);
                return null;
            }
        }
        splay(current);
        return root;
    }
    public void delete(int delNode) {
        if (root == null) {
            System.out.println("The tree is empty");
            return;
        }
        Node delN = search(delNode);
        if (delN == null) {
            System.out.println("There's no such key in the tree");
            return;
        }
        Node successor;
        if (delN.leftChild != null) {
            successor = getLeftSuccessor(delN);
        } else {
            successor = getRightSuccessor(delN);
        }
        successor.leftChild = delN.leftChild;
        if (successor.leftChild != null) {
            successor.leftChild.parent = successor;
        }
        successor.rightChild = delN.rightChild;
        if (successor.rightChild != null) {
            successor.rightChild.parent = successor;
        }
        successor.parent = null;
        root = successor;
    }
    private Node getLeftSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.leftChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.rightChild;
        }
        if (successor != delNode.leftChild) {
            if (successor.leftChild != null) {
                successorParent.rightChild = successor.leftChild;
                successor.leftChild.parent = successorParent;
            } else {
                successorParent.rightChild = null;
            }
        } else {
            successorParent.leftChild = successor.leftChild;
            successor.leftChild.parent = successorParent;
        }
        return successor;
    }
    private Node getRightSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {
            if (successor.rightChild != null) {
                successorParent.leftChild = successor.rightChild;
                successor.rightChild.parent = successorParent;
            } else {
                successorParent.leftChild = null;
            }
        } else {
            successorParent.rightChild = successor.rightChild;
            successor.rightChild.parent = successorParent;
        }
        return successor;
    }
    public Entry min() {
        if (root == null) {
            System.out.println("The tree is empty");
            return null;
        }
        Node current = root;
        Node parent;
        while (true) {
            parent = current;
            current = parent.leftChild;
            if (current == null) {
                splay(parent);
                return new Entry(root.key, root.data);
            }
        }
    }
    public Entry max() {
        if (root == null) {
            System.out.println("The tree is empty");
            return null;
        }
        Node current = root;
        Node parent;
        while (true) {
            parent = current;
            current = parent.rightChild;
            if (current == null) {
                splay(parent);
                return new Entry(root.key, root.data);
            }
        }
    }
    public String print(){
        if (root == null) return "_";
        StringBuilder result = new StringBuilder();
        StringBuilder level = new StringBuilder();
        Queue<Node> q = new LinkedList<>();
        int numsOnLevel = 1;
        int current = 0;
        boolean flag = false; // check !equalNull elements on level
        q.add(root);
        while (!q.isEmpty()) {
            if (current == 0) flag = false;
            if (q.peek() != null) {
                flag = true;
                if (q.peek() == root) {
                    level.append("[")
                            .append(root.key)
                            .append(" ")
                            .append(root.data)
                            .append("]");
                } else {
                    level.append("[")
                            .append(q.peek().key)
                            .append(" ")
                            .append(q.peek().data)
                            .append(" ")
                            .append(q.peek().parent.key)
                            .append("]");
                }
                q.add(q.peek().leftChild);
                q.add(q.peek().rightChild);
                q.remove();
            } else {
                level.append("_");
                q.add(null);
                q.add(null);
                q.remove();
            }
            if (++current == numsOnLevel) {
                if (!flag) {
                    result.deleteCharAt(result.length() - 1);
                    return result.toString();
                }
                level.append("\n");
                result.append(level);
                level = new StringBuilder();
                numsOnLevel *= 2;
                current = 0;
            } else {
                level.append(" ");
            }
        }
        return result.toString();
    }
    private void splay(Node node) {
        while (root != node) {
            if (node.parent.parent != null && (node == node.parent.leftChild && node.parent == node.parent.parent.leftChild)) {
                zig(node.parent);
                zig(node);
            } else if (node.parent.parent != null && (node == node.parent.rightChild && node.parent == node.parent.parent.rightChild)) {
                zag(node.parent);
                zag(node);
            } else if (node.parent.parent != null && (node == node.parent.leftChild && node.parent == node.parent.parent.rightChild)) {
                zig(node);
                zag(node);
            } else if (node.parent.parent != null && (node == node.parent.rightChild && node.parent == node.parent.parent.leftChild)) {
                zag(node);
                zig(node);
            } else {
                if (node == node.parent.leftChild) zig(node);
                else zag(node);
            }
        }
    }
    private void zig(Node node) {
        Node swap = node.rightChild;
        node.rightChild = node.parent;
        node.rightChild.leftChild = swap;
        if (swap != null) swap.parent = node.rightChild;
        if (node.parent != root && node.parent == node.parent.parent.leftChild) {
            swap = node.parent.parent;
            node.parent = swap;
            node.parent.leftChild = node;
        } else if (node.parent != root && node.parent == node.parent.parent.rightChild) {
            swap = node.parent.parent;
            node.parent = swap;
            node.parent.rightChild = node;
        } else {
            node.parent = null;
            root = node;
        }
        node.rightChild.parent = node;
    }
    private void zag(Node node) {
        Node swap = node.leftChild;
        node.leftChild = node.parent;
        node.leftChild.rightChild = swap;
        if (swap != null) swap.parent = node.leftChild;
        if (node.parent != root && node.parent == node.parent.parent.leftChild) {
            swap = node.parent.parent;
            node.parent = swap;
            node.parent.leftChild = node;
        } else if (node.parent != root && node.parent == node.parent.parent.rightChild) {
            swap = node.parent.parent;
            node.parent = swap;
            node.parent.rightChild = node;
        } else {
            node.parent = null;
            root = node;
        }
        node.leftChild.parent = node;
    }
}
