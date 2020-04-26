package SnakeGame;

import java.awt.*;
import java.util.Random;

class DrawSheet {
    static final int BS = 25;
    static final Dimension CanvasDim = new Dimension(BS * 20, BS * 20);
    private static Color rcb, rch, rcf;

    static Dimension getDim(Dimension d, int gap) {
        return new Dimension(d.width + gap, d.height + gap);
    }

    enum ColorRequestFor {
        HEAD, BODY, FRUIT
    }

    static Color requestNewColor(ColorRequestFor colorFor, boolean isDefault) {
        switch (colorFor) {
            case BODY: return isDefault ? Color.BLUE : rcb;
            case HEAD: return isDefault ? Color.MAGENTA : rch;
            case FRUIT: return isDefault ? Color.RED : rcf;
        }
        return Color.BLACK;
    }

    static void makeRandomColor() {
        rcb = randomizeSnakeColor();
        rcf = randomizeSnakeColor();
        rch = randomizeSnakeColor();
    }

    private static Color randomizeSnakeColor() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}
