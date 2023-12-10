package FirstCode;

import java.util.Scanner;

public class aSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        while (scanner.hasNextInt()) {
            result += scanner.nextInt();
        }
        scanner.close();
        System.out.println(result);
    }
}

