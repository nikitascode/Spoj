package pa05_pot;

import java.util.Scanner;

class Main {

    private long power(int base, int power) {
        long result = 1;
        while (power > 0) {
            if((power & 1) == 1) {
                result *= base;
            }

            power >>= 1;
            base *= base;
        }

        return result;
    }

    public short solution(int base, int power) {
        return (short) (power(base % 10, (power % 4) + 4) % 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        short count = scanner.nextShort();
        Main main = new Main();

        for(int i = 0; i < count; i++) {
            System.out.println(main.solution(scanner.nextInt(), scanner.nextInt()));
        }
    }
}
