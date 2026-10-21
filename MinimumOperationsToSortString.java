
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        if (n == 3) {
            if (s.charAt(0) == minChar(s) ||
                s.charAt(2) == maxChar(s)) {
                return 1;
            }
            return -1;
        }

        if (s.charAt(0) == minChar(s) ||
            s.charAt(n - 1) == maxChar(s)) {
            return 1;
        }

        if (s.charAt(0) == maxChar(s) &&
            s.charAt(n - 1) == minChar(s)) {
            return 3;
        }

        return 2;
    }

    private char minChar(String s) {
        char min = 'z';
        for (int i = 0; i < s.length(); i++) {
            min = (char) Math.min(min, s.charAt(i));
        }
        return min;
    }

    private char maxChar(String s) {
        char max = 'a';
        for (int i = 0; i < s.length(); i++) {
            max = (char) Math.max(max, s.charAt(i));
        }
        return max;
    }
}
