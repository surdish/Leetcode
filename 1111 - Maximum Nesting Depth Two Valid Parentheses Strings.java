class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);

            if (c == '(') {
                depth++;

                // Assign to the group with smaller depth
                answer[i] = depth % 2;
            } else {
                // Assign closing bracket to the same group
                answer[i] = depth % 2;
                depth--;
            }
        }

        return answer;
    }
}