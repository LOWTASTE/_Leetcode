package videocode;


public class Code00_MorrisTravel {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node = new Node(2);
        Node node1 = new Node(3);
        head.left = node;
        head.right = node1;
        morris(head);
        morrisPre(head);
        morrisIn(head);
        morrisPos(head);
    }

    public static void morris(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            // 有没有左子树
            mostRight = cur.left;
            // 没有左子树右移

            // 有左子树分两类情况判断
            if (mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    public static void morrisPre(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            // cur表示当前节点，mostRight表示cur的左孩子的最右节点
            mostRight = cur.left;
            if(mostRight != null){
                // cur有左孩子，找到cur左子树最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight的右孩子指向空，让其指向cur，cur向左移动
                if(mostRight.right == null){
                    mostRight.right = cur;
                    System.out.print(cur.val+" ");
                    cur = cur.left;
                    continue;
                } else {
                    // mostRight的右孩子指向cur，让其指向空，cur向右移动
                    mostRight.right = null;
                }
            }
            // 如果没有左子树打印当前节点
            else {
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisIn(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            // cur表示当前节点，mostRight表示cur的左孩子的最右节点
            mostRight = cur.left;
            if(mostRight != null){
                // cur有左孩子，找到cur左子树最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight的右孩子指向空，让其指向cur，cur向左移动
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // mostRight的右孩子指向cur，让其指向空，cur向右移动
                    mostRight.right = null;
                }
            }
            System.out.print(cur.val+ " ");
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisPos(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while (mostRight.right !=null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }
    public static void printEdge(Node node){
        Node tail =reverseEdge(node);
        Node cur = tail;
        while (cur != null ){
            System.out.print(cur.val+" ");
            cur =cur.right;
        }
        reverseEdge(tail);
    }
    public static Node reverseEdge(Node node){
        Node pre = null;
        Node next = null;
        while (node != null){
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

}


class Node {
    public Node left;
    public Node right;
    public Integer val;
    Node(Integer val){
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "left=" + left +
                ", right=" + right +
                ", val=" + val +
                '}';
    }
}
