package main.java;

import java.awt.*;

class Shape {
    private final int[][] shapeArray;
    private final Color shapeColor;
    private final int box = Reference.box;

    Shape(int[][] shapeArray, Logic.ShapeNames shapeName){
        this.shapeArray = shapeArray;
        String[] c = Reference.st_show_default_shape_color ? Reference.defaultColorSheet : Reference.randomColor;
        switch (shapeName){
            case O: {shapeColor = Color.decode(c[0]); break;}
            case T: {shapeColor = Color.decode(c[1]); break;}
            case L: {shapeColor = Color.decode(c[2]); break;}
            case J: {shapeColor = Color.decode(c[3]); break;}
            case S: {shapeColor = Color.decode(c[4]); break;}
            case Z: {shapeColor = Color.decode(c[5]); break;}
            case I: {shapeColor = Color.decode(c[6]); break;}
            default:  {shapeColor = Color.decode(c[7]); break;}
        }
    }
    void paintShape(Graphics2D g2, int _x, int _y, boolean drawShapeProjection){
        //        if (darker) this_color = new Color(this_color.getRed(), this_color.getGreen(), this_color.getBlue(), 60);
//        else shapeOutline(g2, _x, _y);
        if (drawShapeProjection) shapeProjection(g2);

        for (int i = 0; i < this.shapeArray.length; i++) {
            for (int j = 0; j < this.shapeArray[0].length; j++) {
                if (this.shapeArray[i][j] != 0) {
                    g2.setColor(this.shapeColor);
                    g2.fillRect(i * box + _x - box*2, j * box + _y, box, box);
//                    g2.setColor(new Color(0,0,0,40));
//                    g2.setStroke(new BasicStroke(5));
//                    g2.drawLine(
//                            (i * box + box /2) + _x - box*2,(j * box) + box/4 + _y,
//                            (i * box + box /2) + _x - box*2,(j * box + box) - box/4 + _y);
//                    g2.drawLine(
//                            (i * box) + box/4 + _x - box*2,(j * box + box / 2) + _y,
//                            (i * box + box) - box/4 + _x - box*2,(j * box + box / 2) + _y);
//                    g2.setStroke(new BasicStroke(1));
                    g2.setColor(new Color(0,0,0,40));
                    g2.fillRect((i - 2) * box + _x + box/4 - 2, j * box + _y + box/4 - 2, box - box/4 - 3, box - box/4 - 3 );
                }
            }
        }
        shapeOutline(g2, _x, _y);
    }
    private void shapeOutline(Graphics2D g2, int _x, int _y){
        for (int i = 0; i < this.shapeArray.length; i++) {
            for (int j = 0; j < this.shapeArray[0].length; j++) {
                if (this.shapeArray[i][j] != 0) {
                    g2.setColor(Color.BLACK);
                    g2.drawRect((i - 2) * box + _x - 1, j * box + _y - 1, box + 1, box + 1);
                }
            }
        }
    }
    private void shapeProjection(Graphics2D g2){
        Point p = Logic.setShadow(this.shapeArray);
        g2.setStroke(new BasicStroke(2));
        for (int i = 0; i < this.shapeArray.length; i++) {
            for (int j = 0; j < this.shapeArray.length; j++) {
                if (this.shapeArray[i][j] != 0){
                    g2.setColor(shapeColor);
                    g2.drawRect( (i - 2 + p.x) * box+1, (j + p.y) * box+1, box-1, box-1);
                }
            }
        }
        g2.setStroke(new BasicStroke(1));
    }
}
