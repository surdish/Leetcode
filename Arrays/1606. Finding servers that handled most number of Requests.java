import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] count = new int[k];
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) free.add(i);

        // {endTime, serverId}
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        for (int i = 0; i < arrival.length; i++) {
            // free up servers that finished by arrival[i]
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                free.add((int) busy.poll()[1]);
            }
            if (free.isEmpty()) continue; // dropped

            Integer s = free.ceiling(i % k);
            if (s == null) s = free.first();

            free.remove(s);
            count[s]++;
            busy.offer(new long[]{(long) arrival[i] + load[i], s});
        }

        int max = 0;
        for (int c : count) max = Math.max(max, c);

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == max) ans.add(i);
        }
        return ans;
    }
}