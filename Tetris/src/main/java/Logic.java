import java.awt.*;
import java.util.Random;

class Logic {
    private Reference ref = new Reference();
    static void init(){
        name_list.add("o"); shape_list.add(o);
        name_list.add("T"); shape_list.add(T);
        name_list.add("L"); shape_list.add(L);
        name_list.add("J"); shape_list.add(J);
        name_list.add("S"); shape_list.add(S);
        name_list.add("Z"); shape_list.add(Z);
        name_list.add("I"); shape_list.add(I);

        for (int i = 0; i < 7; i++){
            shape_array_list.put(name_list.get(i), shape_list.get(i));
        }
        newGame();
    }

    static void newGame(){
        field = new int[canvasSize.height/box+2][canvasSize.width/box+4];
        setBorder();
        gameOver = false;
        pause = false;
        score = 0;
        randomShape();
        newShape();
        System.out.println("new game");
    }
    private static void setBorder(){
        for (int i = 0; i < field[0].length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (!(i >= 2 && i<= field[0].length-3 && j <= field.length-3))
                    field[j][i] = 8;
            }
        }
    }
    private static void randomShape(){
        next_random_shape = shape_list.get(new Random().nextInt(7));
    }
    static void newShape(){
        int[][] array = next_random_shape;
        dx = field[0].length/2 - array.length/2;
        dy = 0;
        if (checkBy(array,dx,dy)) {
            gameOver = true;
        }
        else {
            current_default_shape = array;
        }
        current_rotated_shape = current_default_shape;
        current_shape_name = name_list.get(shape_list.indexOf(current_default_shape));
        randomShape();
    }
    static void nextShape(){
        int i = shape_list.indexOf(shape_array_list.get(current_shape_name));
        i++; if (i>shape_list.size()-1) i = 0;
        current_shape_name = name_list.get(i);
        current_rotated_shape = shape_array_list.get(current_shape_name);
        System.out.println(current_shape_name);
    }
    static void addToField(){
        int [][] array = current_rotated_shape.clone();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] != 0)
                    field[dy+j][dx+i] = array[i][j];
            }
        }
    }
    static void removeLine(){
        int[] line = new int[field.length-2];
        int[][] field_edit = field.clone();
        int count=0;

        for (int i = field.length-3; i >= 2; i--) {
            int line0 = 1;
            for (int j = 2; j <= field[0].length-3; j++) {line0 *=field[i][j];}
            if (line0 > 1) {line[i] = 1;}
        }
        for (int i = 0; i < line.length; i++) {
            if (line[i] == 1){
                count ++;
                for (int j = i; j > 0; j--) {
                    if (field_edit[0].length - 2 - 2 >= 0)
                        System.arraycopy(field[j - 1], 2, field_edit[j], 2, field_edit[0].length - 2 - 2);
                    /*
                    for (int k = 2; k < field_edit[0].length-2; k++) {
                        field_edit[j][k] = field[j-1][k];
                    }
                    */
                }
            }
        }
        if (count == 1) score+=100;
        else if (count == 2) score +=200;
        else if (count == 3) score +=400;
        else if (count == 4) score +=1200;

        lines += count;
        if (lines != 0) {
            if (level == 1) {
                if (lines >= 20) level++;
            }else if (level == 2) {
                if (lines >= 50) level++;
            }else if (level == 3) {
                if (lines >= 80) level++;
            }else if (level >= 4) {
                if (lines % 100 == 0) level++;
            }
        }
        if (delay > 60) {
            delay = maxDelay - (level-1)*24;
        }

        field = field_edit;
    }

    private static boolean checkBy(int[][] array, int x, int y){
        boolean b = false;
        exit:
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] * field[y+j][x+i] != 0) {
                    b = true;
                    break exit;
                }
            }
        }
        return b;
    }
    static boolean notCollideWithDir(String checkDir){
        int[][] array = current_rotated_shape.clone();
        int x = dx, y = dy;
        if (checkDir.equals("down"))    y++;
        if (checkDir.equals("left"))    x--;
        if (checkDir.equals("right"))   x++;

        return !checkBy(array,x,y);
    }
    static boolean canRotate(){
        int[][] array = rotateShape(current_rotated_shape);
        return !checkBy(array,dx,dy);
    }

    static int[][] rotateShape(int[][] shape){
        int len = shape.length;
        int[][] array = new int[len][len];
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                if (shape[row][col] != 0) {
                    array[len - 1 - col][row] = shape[row][col];
                } else array[len - 1 - col][row] = 0;
            }
        }
        return array;
    }

    static Point setShadow(int[][] shapeArray){
        Point p = new Point(dx,0);
        while (!checkBy(shapeArray, p.x, p.y + 1)){
            p.y++;
        }
        return p;
    }
}
