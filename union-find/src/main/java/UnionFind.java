import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;


public class UnionFind {

    List<List<Integer>> components;

    private UnionFind() {

    }

    public static void main(String[] args) throws IOException {
        out.println("Put N: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        UnionFind unionFind = UnionFind.init(n);
        while (true) {
            out.println("Current state: " + unionFind.toString());
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
        UnionFind unionFind = new UnionFind();
        unionFind.components = new ArrayList<>();
        out.println("Before  " + unionFind.toString());
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> iArr = new ArrayList<>();
            iArr.add(i);
            unionFind.components.add(iArr);
            out.println("i=" + i + ":    " + unionFind.toString());
        }
        out.println("After   " + unionFind.toString());

        return unionFind;
    }

    //add connection between p and q
    public void union(int p, int q) {
        List<Integer> c2 = components.stream()
                .filter(b -> b.contains(q))
                .filter(b -> !b.contains(p))
                .findFirst().orElse(Collections.emptyList());

        components.stream()
                .filter(c1 -> c1.contains(p))
                .findFirst()
                .ifPresent(c -> c.addAll(c2));

        components.remove(c2);

    }

    //are p and q in the same component?
    public boolean connected(int p, int q) {
        return components.stream()
                .anyMatch(b -> (b.contains(p) && b.contains(q)));
    }

    //component identifier for p (0 to N – 1)
    public int find(int p) {
        for (int i = 0; i < count(); i++) {
            if (components.get(i).contains(p)) {
                return i;
            }
        }
        ;
        return -1;
    }

    //number of components
    public int count() {
        return components.size();
    }

    @Override
    public String toString() {
        return "Size = " + this.count() + "\n" + components.toString();
    }
}