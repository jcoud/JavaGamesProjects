import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;


@Log4j
@Getter
@Setter
class Reference {
    class extendPoint extends Point{
        int width, height;
        extendPoint(int width, int height){this.width = width; this.height = height;}
    }
    final String VERSION = "#git_build_count";
    final int box = 25;
    final int gap = 10;
    int score = 0;
    int[][] current_rotated_shape, current_default_shape, field, next_random_shape;
    extendPoint canvasSize;
    String current_shape_name;
    boolean gameOver, pause;
    LinkedList<String> name_list = new LinkedList<>();
    LinkedList<int[][]> shape_list = new LinkedList<>();
    HashMap<String,int[][]> shape_array_list = new HashMap<>(7);
    int dx, dy;
    Color BACKGROUND_COLOR = Color.decode("#295f48");
    int
            level = 1, maxDelay = 350,
            delay = maxDelay, lines;

    /* COLOR STYLE
    #295f48	(41,95,72)
    #204c39	(32,76,57)
    #18392b	(24,57,43)
    #eeeeee	(238,238,238)
    #000000	(0,0,0)
     */

    String[] colorSheet = {
            "#dddd00", //YELLOW o
            "#dd00dd", //MAGENTA T
            "#dfa000", //ORANGE L
            "#3f3fdd", //BLUE J
            "#00dd00", //GREEN S
            "#dd3f3f", //RED Z
            "#00dddd", //CYAN I
            "#000000", //BLACK default
    };

    final int[][] o = {
            {1,1},
            {1,1}
    };
    final int[][] T = {
            {0,2,0},
            {2,2,0},
            {0,2,0}
    };
    final int[][] L = {
            {0,3,3},
            {0,3,0},
            {0,3,0}
    };
    final int[][] J = {
            {4,4,0},
            {0,4,0},
            {0,4,0}
    };
    final int[][] S = {
            {0,5,5},
            {5,5,0},
            {0,0,0}
    };
    final int[][] Z = {
            {6,6,0},
            {0,6,6},
            {0,0,0}
    };
    final int[][] I = {
            {0,0,0,0},
            {7,7,7,7},
            {0,0,0,0},
            {0,0,0,0}
    };
}
