import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node<K, V> getRoot() {
        return this.root;
    }

    public void add(K key, V value) {
        if(root == null){
            Node newNode = new Node(key,value);
            this.root = newNode;
        } else {
            this.root.add(key, value);
        }
    }

    public V find(K key) {
        if(root == null){
            return null;
        } else {
         return root.find(key);
        }
    }

    @SuppressWarnings("unchecked")
    public V[] flatten() {
        if(root == null){
            return (V[]) new Object[0];
        }
        Object[] answer = this.root.flatten();
        return (V[]) answer;
    }

    public boolean containsSubtree(BinaryTree<K, V> other) {
        if(other == null) {
            return false;
        }
        return this.root.containsSubtree(other.root);
    }


    public void remove(K key) {
        if (this.root != null) {
            if (this.root.getKey().equals(key)) {
                if (this.root.getLeft() == null && this.root.getRight() == null)  {
                    this.root = null;
                } else if (this.root.getLeft() != null && this.root.getRight() == null) {
                    this.root = this.root.getLeft();
                } else if (this.root.getLeft() == null && this.root.getRight() != null) {
                    this.root = this.root.getRight();
                } else {
                    this.root.remove(key, null);
                }
            } else {
                this.root.remove(key, null);
            }
        }
    }
}
