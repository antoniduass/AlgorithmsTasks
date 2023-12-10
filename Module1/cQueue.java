package FirstCode;

import java.util.Scanner;



public class cQueue {
    static class LightQueue {
        LightQueue (int size) {
            this.size = size;
            queue = new String[size];
        }
        private final int size;
        private final String[] queue;
        private int count = 0;
        private int head = 0;
        private int tail = 0;
        public void push(String x) {
            if (count >= size) {
                System.out.println("overflow");
                return;
            }
            if (count == 0) {
                head = 0;
                tail = 0;
                queue[count++] = x;
            }
            else {
                if (tail == size - 1) {
                    tail = 0;
                    queue[tail] = x;
                    count++;
                    return;
                }
                tail = tail + 1;
                queue[tail] = x;
                count++;
            }
        }
        public String pop() {
            if (count == 0) return "underflow";
            String result = queue[head];
            count--;
            if (head == size - 1) {
                head = 0;
                return result;
            }
            head = head + 1;
            return result;
        }
        public String print() {
            StringBuilder result = new StringBuilder();
            if (count == 0) return "empty";
            for (int i = head; i != tail; i = (i + 1) % size) result.append(queue[i]).append(" ");
            result.append(queue[tail]);
            return result.toString();
        }
    }

    public static void main(String[] args) {
        String setSize = "^set_size [0-9]+$";
        String queueMethods = "^push \\S*$|^pop$|^print$";
        Scanner scanner = new Scanner(System.in);
        String buffer;
        LightQueue test = null;
        while (scanner.hasNext()) {
            buffer = scanner.nextLine();
            if (buffer.length() == 0) continue;
            else if (buffer.matches(setSize)) {
                String[] Size = buffer.split(" ");
                int queueSize = Integer.parseInt(Size[1]);
                test = new LightQueue(queueSize);
                break;
            } else {
                System.out.println("error");
            }
        }
        while (scanner.hasNext()) {
            buffer = scanner.nextLine();
            if (buffer.length() == 0) continue;
            else if (buffer.matches(queueMethods)) {
                String [] Size = buffer.split(" ");
                switch (Size[0]) {
                    case "push" -> test.push(Size[1]);
                    case "pop" -> System.out.println(test.pop());
                    case "print" -> System.out.println(test.print());
                    default -> System.out.println("error");
                }
            }
        }
        scanner.close();
    }
}
