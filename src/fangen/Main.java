package fangen;

import java.util.Map;
import java.util.Scanner;

enum FanDirection {
    Clockwise,
    Anticlockwise
}

enum FanPrinterType {
    QuadrupleSymmetry
}

enum WingType {
    AsteriskTriangle
}

interface WingedFanPrinter {
    void printFan(Integer size, FanDirection direction);
}

interface WingPainter {
    CharMap GetWingMap();
}

class WingedFanPrinterQuadrupleSymmetry implements WingedFanPrinter {
    private CharMap wingMap;
    private boolean[][] fan;

    public WingedFanPrinterQuadrupleSymmetry(CharMap wingMap) {
        this.wingMap = wingMap;
    }

    @Override
    public void printFan(Integer size, FanDirection direction) {
        fan = new boolean[2 * size][2 * size];

        if(size == 1) {
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    System.out.print(wingMap.getKey());
                }
                System.out.println("");
            } return;
        }

        this.generateFan(size, direction);
        Character key = wingMap.getKey();
        Character value = wingMap.getValue();
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                if(!fan[i][j]) System.out.print(value);
                else System.out.print(key);
            }
            System.out.println("");
        }
    }

    private void generateFanWings(int posX, int posY,  int size) {
        fan[posX][posY] = true;
        fan[posY + size][posX + size] = true;
        fan[posX + size][size - 1 - posY] = true;
        fan[size - 1 - posX][posY + size] = true;
    }

    private void generateFan(Integer size, FanDirection direction) {
        for(int i = 0; i < size; i++) {
            for(int j = i; j < size; j++) {
                if(direction == FanDirection.Clockwise) {
                    this.generateFanWings(j, i, size);
                } else if(direction == FanDirection.Anticlockwise) {
                    this.generateFanWings(i, j, size);
                }
            }
        }
    }

}

class WingPainterAsteriskTriangle implements WingPainter {

    @Override
    public CharMap GetWingMap() {
        return new CharMap.CharMapBuilder()
                .setKey('*')
                .setValue('.')
                .build();
    }
}


class WingedFanPrinterFactory {
    public static WingedFanPrinter getWingedFanPrinter(FanPrinterType type) {
        WingPainter painter = WingPainterFactory.getWingPainter(WingType.AsteriskTriangle);
        CharMap wingMap = painter.GetWingMap();
        if (type == FanPrinterType.QuadrupleSymmetry) {
            return new WingedFanPrinterQuadrupleSymmetry(wingMap);
        }
        throw new IllegalArgumentException("Illegal FanPrinterType: " + type);
    }
}

class WingPainterFactory {

    public static WingPainter getWingPainter(WingType type) {
        if(type == WingType.AsteriskTriangle) {
            return new WingPainterAsteriskTriangle();
        }

        throw new IllegalArgumentException("Illegal WingPainterType: " + type);
    }
}

class Main {

    public static void main(String[] args) {
        WingedFanPrinter fanPrinter = WingedFanPrinterFactory.getWingedFanPrinter(FanPrinterType.QuadrupleSymmetry);

        Scanner scanner = new Scanner(System.in);
        Integer fan = scanner.nextInt();
        while (fan != 0) {
            fanPrinter.printFan(Math.abs(fan), fan > 0 ? FanDirection.Clockwise : FanDirection.Anticlockwise);
            fan = scanner.nextInt();
        }
    }
}

class CharMap implements Map.Entry<Character, Character> {
    private Character key;
    private Character value;

    public CharMap() { }

    public CharMap(Character key, Character value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Character getKey() {
        return this.key;
    }

    @Override
    public Character getValue() {
        return this.value;
    }

    public Character setKey(Character key) {
        this.key = key;
        return key;
    }

    @Override
    public Character setValue(Character value) {
        this.value = value;
        return value;
    }

    public static class CharMapBuilder {
        private Character key;
        private Character value;

        public CharMapBuilder setKey(Character key) {
            this.key = key;
            return this;
        }

        public CharMapBuilder setValue(Character value) {
            this.value = value;
            return this;
        }

        public CharMap build() {
            return new CharMap(key, value);
        }
    }
}