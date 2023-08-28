import java.util.Scanner;

public class _2104373_2a {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int X = in.nextInt();

        int maxShekelsFlicked = calculateMaxShekelsFlicked(X);
        System.out.println("Maximum shekels flicked: " + maxShekelsFlicked);
    }

    public static int calculateMaxShekelsFlicked(int X) {
        int maxShekels = 0;
        for (int i = 1; i <= X; i++) {
            int length = computeSequenceLength(i);
            maxShekels += length;
        }
        return maxShekels;
    }

    public static int computeSequenceLength(int num) {
        int length = 0;

        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num = num + num * 2 + 1;
            }

            length++;
        }

        return length;
    }
}
