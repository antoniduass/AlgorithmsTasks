package FirstCode;

import java.util.Scanner;

public class bStack {
    static class Stack {
        public Stack(int size) {
            this.size = size;
            stack = new String[size];
        }
        private final int size;
        private final String[] stack;
        private int count = 0;
        public void push(String X) {
            if (count >= size) System.out.println("overflow");
            else {
                stack[count] = X;
                count++;
            }
        }
        public String pop() {
            if (count == 0) return "underflow";
            else {
                String temp = stack[--count];
                stack[count] = null;
                return temp;
            }
        }
        public String print() {
            if (count == 0) return "empty";
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < stack.length; i++) {
                if (i == stack.length - 1) {
                    result.append(stack[i]);
                    break;
                }
                result.append(stack[i]).append(" ");
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        String setSize = "^set_size [0-9]+$";
        String stackMethods = "^push \\S*$|^pop$|^print$";
        Scanner scanner = new Scanner(System.in);
        String buffer;
        Stack stack = null;
        while (scanner.hasNext()) {
            buffer = scanner.nextLine();
            if (buffer.length() == 0) {
                continue;
            } else if (buffer.matches(setSize)) {
                String[] Size = buffer.split(" ");
                int stackSize = Integer.parseInt(Size[1]);
                stack = new Stack(stackSize);
                break;
            } else {
                System.out.println("error");
            }
        }
        while (scanner.hasNext()) {
            buffer = scanner.nextLine();
            if (buffer.length() == 0) {
              continue;
            } else if (buffer.matches(stackMethods)) {
                String[] Size = buffer.split(" ");
                switch (Size[0]) {
                    case "push" -> stack.push(Size[1]);
                    case "pop" -> System.out.println(stack.pop());
                    case "print" -> System.out.println(stack.print());
                    default -> System.out.println("error");
                }
            }
        }
        scanner.close();
    }
}