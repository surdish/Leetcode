
import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int MOD = 1_000_000_007;

        long[] tree = new long[4 * n];
        long[] lazy = new long[4 * n];
        long[] sumSq = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;
        long cur = 0;

        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            int l = last.getOrDefault(x, -1) + 1;

            update(1, 0, n - 1, l, i, 1, tree, lazy, sumSq, MOD);

            cur = sumSq[1] % MOD;
            ans = (ans + cur) % MOD;
            last.put(x, i);
        }

        return (int) ans;
    }

    private void update(int node, int start, int end,
                        int l, int r, long val,
                        long[] tree, long[] lazy,
                        long[] sumSq, int MOD) {
        if (l > end || r < start) return;

        if (l <= start && end <= r) {
            long len = end - start + 1L;
            sumSq[node] = (sumSq[node]
                    + 2 * val * tree[node]
                    + len * val * val) % MOD;
            tree[node] = (tree[node] + len * val) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            return;
        }

        push(node, start, end, tree, lazy, sumSq, MOD);

        int mid = (start + end) / 2;
        update(node * 2, start, mid, l, r, val,
               tree, lazy, sumSq, MOD);
        update(node * 2 + 1, mid + 1, end, l, r, val,
               tree, lazy, sumSq, MOD);

        tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }

    private void push(int node, int start, int end,
                      long[] tree, long[] lazy,
                      long[] sumSq, int MOD) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;
        apply(node * 2, start, mid, lazy[node],
              tree, lazy, sumSq, MOD);
        apply(node * 2 + 1, mid + 1, end, lazy[node],
              tree, lazy, sumSq, MOD);

        lazy[node] = 0;
    }

    private void apply(int node, int start, int end, long val,
                       long[] tree, long[] lazy,
                       long[] sumSq, int MOD) {
        long len = end - start + 1L;

        sumSq[node] = (sumSq[node]
                + 2 * val * tree[node]
                + len * val * val) % MOD;

        tree[node] = (tree[node] + len * val) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }
}
