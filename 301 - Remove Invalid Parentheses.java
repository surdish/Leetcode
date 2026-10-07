import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        Set<String> set = new HashSet<>();

        int leftRemove = 0;
        int rightRemove = 0;

        // Find number of invalid '(' and ')'
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        backtrack(s, 0, leftRemove, rightRemove,
                  0, new StringBuilder(), set);

        return new ArrayList<>(set);
    }

    private void backtrack(String s, int index,
                            int leftRemove, int rightRemove,
                            int balance,
                            StringBuilder path,
                            Set<String> set) {

        if (index == s.length()) {

            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0) {

                set.add(path.toString());
            }

            return;
        }

        char c = s.charAt(index);

        // Remove current '('
        if (c == '(' && leftRemove > 0) {

            backtrack(
                s,
                index + 1,
                leftRemove - 1,
                rightRemove,
                balance,
                path,
                set
            );
        }

        // Remove current ')'
        if (c == ')' && rightRemove > 0) {

            backtrack(
                s,
                index + 1,
                leftRemove,
                rightRemove - 1,
                balance,
                path,
                set
            );
        }

        // Keep current character
        path.append(c);

        if (c == '(') {

            backtrack(
                s,
                index + 1,
                leftRemove,
                rightRemove,
                balance + 1,
                path,
                set
            );

        } else if (c == ')') {

            if (balance > 0) {
                backtrack(
                    s,
                    index + 1,
                    leftRemove,
                    rightRemove,
                    balance - 1,
                    path,
                    set
                );
            }

        } else {

            // Letter
            backtrack(
                s,
                index + 1,
                leftRemove,
                rightRemove,
                balance,
                path,
                set
            );
        }

        path.deleteCharAt(path.length() - 1);
    }
}