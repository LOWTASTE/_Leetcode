import java.util.ArrayList;
import java.util.List;

public class SolutionTree {
    public static void main(String[] args) {
        // new a peach tree and give the tree a name
        PeachTree peachTree = new PeachTree();
        peachTree.setName("peachtree");
        // leaves too many so use list to store
        List<Leaf> leaves = new ArrayList<>();
        // make some leaves
        Leaf leaf1 = new Leaf(1);
        Leaf leaf2 = new Leaf(2);
        Leaf leaf3 = new Leaf(3);
        // add them to leaves list
        leaves.add(leaf1);
        leaves.add(leaf2);
        leaves.add(leaf3);
        // set leaves
        peachTree.setLeaves(leaves);
        // get leaves

        // if you want to output it you need to override the toString() method of Leaf class;
        //
        System.out.println(peachTree.getLeaves());
        // and so on.......
    }
}

abstract class Tree{
    // 名字
    protected String name;

    // 构造函数
    public Tree(){}

    public Tree(String name) {
        this.name = name;
    }

    // 设置名称方法
    protected void setName(String name){
        this.name = name;
    }

    // 获取名称方法
    protected String getName(){
        return name;
    }
}


class PeachTree extends Tree{
    private List<Peach> peaches;
    private List<Leaf> leaves;
    public PeachTree(List<Peach> peaches, List<Leaf> leaves) {
        this.peaches = peaches;
        this.leaves = leaves;
    }

    public PeachTree(String name) {
        super(name);
    }

    public PeachTree() {
        super();
    }

    public List<Peach> getPeaches() {
        return peaches;
    }

    public void setPeaches(List<Peach> peaches) {
        this.peaches = peaches;
    }

    public List<Leaf> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leaf> leaves) {
        this.leaves = leaves;
    }

    @Override
    protected void setName(String name) {
        super.setName(name);
    }

    @Override
    protected String getName() {
        return super.getName();
    }
}


class AppleTree extends Tree{
    private List<Apple> apples;
    private List<Leaf> leaves;

    // 获取
    public List<Apple> getApples() {
        return apples;
    }

    public List<Leaf> getLeaves() {
        return leaves;
    }

    // 设置
    public void setApples(List<Apple> apples) {
        this.apples = apples;
    }

    public void setLeaves(List<Leaf> leaves) {
        this.leaves = leaves;
    }

    @Override
    protected void setName(String name) {
        super.setName(name);
    }

    public AppleTree() {
        super();
    }

    public AppleTree(String name) {
        super(name);
    }

    @Override
    protected String getName() {
        return super.getName();
    }
}


class Poplar extends Tree{
    private List<Leaf> leaves;

    // 获取
    public List<Leaf> getLeaves() {
        return leaves;
    }

    // 设置
    public void setLeaves(List<Leaf> leaves) {
        this.leaves = leaves;
    }

    @Override
    protected void setName(String name) {
        super.setName(name);
    }

    public Poplar() {
        super();
    }

    public Poplar(String name) {
        super(name);
    }

    @Override
    protected String getName() {
        return super.getName();
    }
}

class Leaf {
    public Leaf(Integer id) {
        this.id = id;
    }

    // 标识叶子
    Integer id;

    @Override
    public String toString() {
        return "Leaf{" +
                "id=" + id +
                '}';
    }
}

class Peach {
    public Peach(Integer id) {
        this.id = id;
    }

    // 标识桃子
    Integer id;
}

class Apple {
    public Apple(Integer id) {
        this.id = id;
    }

    // 标识苹果
    Integer id;
}