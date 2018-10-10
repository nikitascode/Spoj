package fzi_stef;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Instrumentalist {
    private List<Long> maxConcertsCostList;
    private Long currentCost;

    public Instrumentalist() {
        maxConcertsCostList = new ArrayList<>();
        currentCost = 0L;
    }

    public void addConcertCost(Long concertCost) {
        currentCost += concertCost;

        if(currentCost < 0) {
            currentCost = 0L;
        }

        if(concertCost > 0) {
            maxConcertsCostList.add(currentCost);
        }
    }

    public Long getMaxCost() {
        if(maxConcertsCostList.isEmpty()) return 0L;
        return maxConcertsCostList
                 .stream()
                 .mapToLong(value -> value)
                 .max().orElseThrow(NoSuchElementException::new);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Instrumentalist stefan = new Instrumentalist();
        Integer count = scanner.nextInt();

        for(int i = 0; i < count; i++) {
            stefan.addConcertCost(scanner.nextLong());
        }

        System.out.println(stefan.getMaxCost());
    }
}
