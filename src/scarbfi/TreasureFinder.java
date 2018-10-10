package scarbfi;

import java.util.Scanner;

class TreasureFinder {

    private Integer northSouthSteps;
    private Integer eastWestSteps;

    private Integer getDirectionStep(Integer direction, Integer steps) {
        if(direction == 1 || direction == 3) return steps * -1;
        else return steps;
    }

    private Integer getCardinalDirection(Integer steps) {
        if(steps > 0) return 0;
        else return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        short count = scanner.nextShort();
        TreasureFinder treasureFinder = new TreasureFinder();


        Integer instructionsCount;
        for(int i = 0; i < count; i++) {
            instructionsCount = scanner.nextInt();

            treasureFinder.resetSteps();
            for(int j = 0; j < instructionsCount; j++) {
                treasureFinder.findTreasure(scanner.nextInt(), scanner.nextInt());
            }
            treasureFinder.showPath();
        }
    }

    public void findTreasure(Integer direction, Integer steps) {
        steps = this.getDirectionStep(direction, steps);
        if(direction == 0 || direction == 1) this.northSouthSteps += steps;
        else if (direction == 2 || direction == 3) this.eastWestSteps += steps;
    }

    public void showPath() {
        if(this.northSouthSteps == 0 && this.eastWestSteps == 0) System.out.println("studnia");
        else {
            if(this.northSouthSteps != 0)
                System.out.println(this.getCardinalDirection(this.northSouthSteps) + 0 + " " + Math.abs(this.northSouthSteps));
            if(this.eastWestSteps != 0) {
                System.out.println(this.getCardinalDirection(this.eastWestSteps) + 2 + " " + Math.abs(this.eastWestSteps));
            }
        }
    }

    public void resetSteps() {
        this.setEastWestSteps(0);
        this.setNorthSouthSteps(0);
    }

    public void setNorthSouthSteps(int northSouthSteps) {
        this.northSouthSteps = northSouthSteps;
    }

    public void setEastWestSteps(int eastWestSteps) {
        this.eastWestSteps = eastWestSteps;
    }
}
