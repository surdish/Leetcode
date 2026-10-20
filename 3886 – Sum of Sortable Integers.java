class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k == 0 && check(nums, k)) ans += k;
        }
        return ans;
    }

    private boolean check(int[] a, int k) {
        int n = a.length;
        int prevMax = Integer.MIN_VALUE;
        for (int s = 0; s < n; s += k) {
            int descents = 0, pos = -1;
            for (int i = s; i < s + k - 1; i++) {
                if (a[i] > a[i + 1]) {
                    descents++;
                    pos = i;
                    if (descents > 1) return false;
                }
            }
            int mn = a[s];
            int mx = a[s + k - 1];
            if (descents == 1) {
                if (a[s + k - 1] > a[s]) return false;
                mn = a[pos + 1];
                mx = a[pos];
            }
            if (mn < prevMax) return false;
            prevMax = mx;
        }
        return true;
    }
}