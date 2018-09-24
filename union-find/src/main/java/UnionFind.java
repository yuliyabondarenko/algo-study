import java.io.IOException;
import java.util.*;

import static java.lang.System.out;


public class UnionFind {

    private int n;
    private int[] ind;

    private UnionFind() {

    }

    private UnionFind(int n) {
        this.n = n;
    }

    public static void main(String[] args) throws IOException {
        out.println("Put N: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        UnionFind unionFind = UnionFind.init(n);
        out.println("Current state: " + unionFind.toString());
        while (true) {
            out.println("Put A: ");
            int a = in.nextInt();
            out.println("Put B: ");
            int b = in.nextInt();

            if (!unionFind.connected(a, b)) {
                unionFind.union(a, b);
                out.println("Current state: " + unionFind.toString());
            }
        }


    }

    //initialize union-find data structure with N objects (0 to N – 1)
    public static UnionFind init(int n) {
        UnionFind unionFind = new UnionFind(n);

        unionFind.ind = new int[n];
        for (int i = 0; i < n; i++) {
            unionFind.ind[i] = i;
        }
        return unionFind;
    }

    //add connection between p and q
    public void union(int p, int q) {
        ind[q] = p;
        for (int i = 0; i < n; i++) {
            if (ind[i] == q) {
                ind[i] = p;
            }
        }
    }

    //are p and q in the same component?
    public boolean connected(int p, int q) {
        return ind[p] == ind[q];
    }

    //component identifier for p (0 to N – 1)
    public int find(int p) {
        return ind[p];
    }

    //number of components
    public int count() {
        return getInverseIndexes().size();
    }

    public Map<Integer, List<Integer>> getInverseIndexes() {
        Map<Integer, List<Integer>> inverseIndexes = new HashMap<>();
        for (int i = 0; i < ind.length; i++) {
            int index = ind[i];
            if(inverseIndexes.containsKey(index)) {
                inverseIndexes.get(index).add(i);
            } else {
                List<Integer> values = new ArrayList<>();
                values.add(i);
                inverseIndexes.put(index, values);
            }
        }
        return inverseIndexes;
    }

    @Override
    public String toString() {
        return "Size =" + count() + "\n" +
                "Array: " + Arrays.toString(ind) + "\n" +
                "Map: " + getInverseIndexes();
    }
}