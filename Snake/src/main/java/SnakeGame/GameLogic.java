package SnakeGame;


import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

class GameLogic {

    boolean show_newGame;
    boolean step;
    // ===
    Point head, fruit;
    int level, snakeLength, score;
    LinkedList<Point> snake;
    byte
            /*DIRECTION*/
            DIR_CURR,
            DIR_UP = 0,
            DIR_DOWN = 1,
            DIR_LEFT = 2,
            DIR_RIGHT = 3;
    boolean
            /*STATUS*/      /*STATES*/
            st_running, show_gameStats = false,
            st_gameOver, show_controlInfo = true,
            show_grid = true;

    private static GameLogic INSTANCE = new GameLogic();
    boolean colorNormal;

    static GameLogic getInstance() {
        return INSTANCE;
    }

    private GameLogic() {
        INSTANCE = this;
    }

    void startNewGame() {
        /*GAME STATS INIT*/
        step = false;
        snake = new LinkedList<>();
        colorNormal = true;
        score = 0;
        level = 1;
        st_gameOver = false;
        show_newGame = true;
        DIR_CURR = DIR_LEFT;

        st_running = false;

        /*SNAKE INIT*/
        head = new Point(5, 5);
        snake.add(new Point(head.x + 1, head.y));
        snake.add(head);
        snakeLength = snake.size();
        setNewFruitPos();
    }

    void snakeMove() {
        if (!st_running) return;
//        snake.add(head);
        Point newHead;
        if (DIR_CURR == DIR_UP) newHead = new Point(head.x, head.y - 1);
        else if (DIR_CURR == DIR_DOWN) newHead = new Point(head.x, head.y + 1);
        else if (DIR_CURR == DIR_LEFT) newHead = new Point(head.x - 1, head.y);
        else if (DIR_CURR == DIR_RIGHT) newHead = new Point(head.x + 1, head.y);
        else return;

        if ((newHead.y >= 0) &&
            (newHead.y < DrawSheet.CanvasDim.height / DrawSheet.BS) &&
            (newHead.x >= 0) &&
            (newHead.x < DrawSheet.CanvasDim.width / DrawSheet.BS) &&
            !isPointInTail(newHead)) {
            head = newHead;
        } else {
            st_gameOver = true;
            st_running = false;
            return;
        }
        snake.add(head);
        if (head.equals(fruit)) {
            setNewFruitPos();
            snakeLength++;
            score+=10;
            if (score % 50 == 0) level+=1;
        }
        if (snake.size() > snakeLength) {
            snake.remove(0);
        }
    }

    private void setNewFruitPos() {
        Random random = new Random();
        ArrayList<Point> lp = new ArrayList<>(DrawSheet.CanvasDim.height / DrawSheet.BS * DrawSheet.CanvasDim.height / DrawSheet.BS);
        for (int i = 0; i < DrawSheet.CanvasDim.width / DrawSheet.BS; i++) {
            for (int j = 0; j < DrawSheet.CanvasDim.height / DrawSheet.BS; j++) {
                Point pp = new Point(i, j);
                if (snake.contains(pp)) continue;
                lp.add(pp);
            }
        }
        fruit = lp.get(random.nextInt(lp.size() - 1));
    }

    private boolean isPointInTail(Point point) {
        for (Point p : snake)
            if (p.equals(point)) return true;
        return false;
    }
}
