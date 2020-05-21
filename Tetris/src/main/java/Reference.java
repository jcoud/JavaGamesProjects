package main.java;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;


@Log4j
@Getter
@Setter
class Reference {
    static class ExtendPoint extends Point {
        int width, height;

        ExtendPoint(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    static final String VERSION = "#git_build_count";
    static final int box = 25;
    static final int gap = 10;
    static int score = 0;
    static int[][] current_rotated_shape, current_default_shape, field, next_random_shape;
    static ExtendPoint canvasMetrix;
    static Logic.ShapeNames current_shape_name;
    static boolean
            gameOver, //in Logic init
            pause, //in Logic init
            st_show_shape_projection = true,
            st_show_default_shape_color = true;
    static LinkedList<Logic.ShapeNames> name_list = new LinkedList<>();
    static LinkedList<int[][]> shape_list = new LinkedList<>();
    static HashMap<Logic.ShapeNames, int[][]> shape_array_list = new HashMap<>(7);
    static int dx, dy;
    static Color BACKGROUND_COLOR = Color.decode("#295f48");
    static final int
            maxSpeed = 4;
    static int
            level = 1, lines;

    /* COLOR STYLE
    #295f48	(41,95,72)
    #204c39	(32,76,57)
    #18392b	(24,57,43)
    #eeeeee	(238,238,238)
    #000000	(0,0,0)
     */


    static String[] randomColor;
    static String[] defaultColorSheet = {
            "#dddd00", //YELLOW o
            "#dd00dd", //MAGENTA T
            "#dfa000", //ORANGE L
            "#3f3fdd", //BLUE J
            "#00dd00", //GREEN S
            "#dd3f3f", //RED Z
            "#00dddd", //CYAN I
            "#000000", //BLACK none
    };

    public static void randomizeShapeColor() {
        randomColor = new String[8];
        Random r = new Random();
        for (int i = 0; i < randomColor.length; i++) {
            randomColor[i] = String.format("#%02x%02x%02x", r.nextInt(255), r.nextInt(255), r.nextInt(255));
        }
    }

    static final int[][] o = {
            {1, 1},
            {1, 1}
    };
    static final int[][] T = {
            {0, 2, 0},
            {2, 2, 0},
            {0, 2, 0}
    };
    static final int[][] L = {
            {0, 3, 3},
            {0, 3, 0},
            {0, 3, 0}
    };
    static final int[][] J = {
            {4, 4, 0},
            {0, 4, 0},
            {0, 4, 0}
    };
    static final int[][] S = {
            {0, 5, 5},
            {5, 5, 0},
            {0, 0, 0}
    };
    static final int[][] Z = {
            {6, 6, 0},
            {0, 6, 6},
            {0, 0, 0}
    };
    static final int[][] I = {
            {0, 0, 0, 0},
            {7, 7, 7, 7},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
}
