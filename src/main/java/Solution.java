import java.util.*;

public class Solution {

    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<Server> free = new PriorityQueue<>(Comparator
                .comparing((Server s) -> s.weight)
                .thenComparing((Server s) -> s.index)
        );
        PriorityQueue<Server> busy = new PriorityQueue<>(Comparator
                .comparing((Server s) -> s.availableAt)
                .thenComparing((Server s) -> s.weight)
                .thenComparing((Server s) -> s.index)
        );
        for (int i = 0; i < servers.length; i++) {
            free.offer(new Server(servers[i], 0, i));
        }
        int time = 0;
        int i = 0;
        int[] res = new int[tasks.length];
        Queue<Integer> awaiting = new LinkedList<>();
        while (i != tasks.length || !awaiting.isEmpty()) {
            if (free.isEmpty()) {
                time = busy.peek().availableAt;
                while (i < tasks.length && i != time) {
                    awaiting.offer(i++);
                }
            }
            while (!busy.isEmpty() && busy.peek().availableAt <= time) {
                free.offer(busy.poll());
            }
            if (i != tasks.length) {
                awaiting.offer(i++);
            }

            while (!awaiting.isEmpty() && !free.isEmpty()) {
                Server s = free.poll();
                int taskIndex = awaiting.poll();
                res[taskIndex] = s.index;
                busy.offer(new Server(s.weight, time + tasks[taskIndex], s.index));
            }
            time++;
        }
        return res;
    }
}

class Server {
    int weight;
    int availableAt;
    int index;

    Server(int weight, int availableAt, int index) {
        this.weight = weight;
        this.availableAt = availableAt;
        this.index = index;
    }
}