import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        UnionSet unionSet = new UnionSet();

        int[][] input = {{1,2},{1,3},{2,3}};

        System.out.println(Arrays.toString(unionSet.findRedundantConnection(input)));
    }
}
