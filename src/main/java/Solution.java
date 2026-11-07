import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> free = new PriorityQueue<>(Comparator.comparingInt((int[] s) -> s[0]).thenComparing((int[] s) -> s[1]));
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt((int[] s) -> s[0]));

        int m = tasks.length;
        int[] res = new int[m];
        for (int i = 0; i < servers.length; i++) {
            free.offer(new int[]{servers[i], i});
        }
        int time = 0;
        for (int i = 0; i < m; i++) {
            if (time < i) {
                time = i;
            }

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] s = busy.poll();
                free.offer(new int[]{servers[s[1]], s[1]});
            }
            if (free.isEmpty()) {
                time = busy.peek()[0];
                while (!busy.isEmpty() && busy.peek()[0] == time) {
                    int[] s = busy.poll();
                    free.offer(new int[]{servers[s[1]], s[1]});
                }

            }

            int s = free.poll()[1];
            res[i] = s;
            busy.offer(new int[]{time + tasks[i], s});


        }

        return res;
    }
}