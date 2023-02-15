public class Node<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private Node<K, V> left, right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public boolean equals(Node<K, V> other) {
        if (other == null) return false;
        boolean left, right;
        if (this.left == null) {
            left = other.left == null;
        }
        else {
            left = this.left.equals(other.left);
        }
        if (this.right == null) {
            right = other.right == null;
        }
        else {
            right = this.right.equals(other.right);
        }
        return left && right && this.key.equals(other.key) && this.value.equals(other.value);
    }

    public V find(K key){
        if (key == null)
            return null;
        if (this.getKey() == key)
            return this.getValue();
        else if (this.getKey().compareTo(key) > 0){
            V result1 = null;
            if(this.getLeft() != null){
                result1 = this.getLeft().find(key);
            }
            return result1;
        } else {
            V result2 = null;
            if (this.getRight() != null) {
                result2 = this.getRight().find(key);
            }
            return result2;
        }
    }

    public void add(K key, V value) {
        if (this.getKey() == key){
            this.setValue(value);
        }
        else if (this.getKey().compareTo(key) > 0){
            if(this.getLeft() != null){
                this.left.add(key, value);
            } else{
                Node newNode = new Node(key,value);
                this.left = newNode;
            }
        } else if(this.getKey().compareTo(key) < 0) {
            if (this.getRight() != null) {
                this.right.add(key, value);
            } else {
                Node newNode = new Node(key,value);
                this.right = newNode;
            }
        }
    }

    public boolean containsSubtree(Node<K, V> other) {
        if(other == null){
            return true;
        }
        if(this.key.equals(other.key)) {
            return this.equals(other);
        }
        return containsSubtree(other.left) && containsSubtree(other.right);
    }

    public K getKey() {
        return key;
    }


    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    private K min() {
        if (this.left == null) {
            return this.key;
        }
        else {
            return this.left.min();
        }
    }

    public V[] flatten() {
        if(this == null){
            return null;
        }
        Object[] rAnswer = new Object[0];
        Object[] lAnswer = new Object[0];
        if(this.left != null){
            lAnswer = this.left.flatten();
        }
        if(this.right != null){
            rAnswer = this.right.flatten();
        }
        Object[] answer = new Object[lAnswer.length + rAnswer.length + 1];
        for(int i = 0; i < lAnswer.length; i++){
            answer[i] = lAnswer[i];
        }
        answer[lAnswer.length] = value;
        for(int i = lAnswer.length + 1; i < rAnswer.length + lAnswer.length + 1; i++){
            answer[i] = rAnswer[i - lAnswer.length - 1];
        }
        return (V[]) answer;
    }

    public void remove(K key, Node<K, V> parent) {
        // key == this.key
        if (key.equals(this.key)) {
            if (this.left != null && this.right != null) {
                K min = this.right.min();
                this.value = this.find(min);
                this.key = min;
                this.right.remove(min, this);
            }
            else if (parent.left == this) {
                parent.left = (left != null) ? left : right;
            }
            else if (parent.right == this) {
                parent.right = (left != null) ? left : right;
            }
        }
        // key < this.key
        else if (key.compareTo(this.key) < 0 && this.left != null) {
            this.left.remove(key, this);
        }
        // key > this.key
        else if (key.compareTo(this.key) > 0 && this.right != null) {
            this.right.remove(key, this);
        }
    }
}
