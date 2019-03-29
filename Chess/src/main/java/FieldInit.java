import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

class FieldInit {
    private static final int _BS_ = Reference.BS;
    FieldInit(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int index = i + 1 + j*8;
                if ((i+1) % 2 == 0 && j % 2 == 0) Reference.placeHolderArrayList.add(
                    Reference.squareInit(
                        index,
                        new Point(i, j),
                        Reference.BLACK_SQUARE,
                        null,
                        null,
                        null
                    )
                );
                else if (i % 2 == 0 && (j+1) % 2 == 0) Reference.placeHolderArrayList.add(
                    Reference.squareInit(
                        index,
                        new Point(i, j),
                        Reference.BLACK_SQUARE,
                        null,
                        null,
                        null
                    )
                );
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int index = i + 1 + j*8;
                if ((i) % 2 == 0 && j % 2 == 0) Reference.placeHolderArrayList.add(
                    Reference.squareInit(
                        index,
                        new Point(i, j),
                        Reference.WHITE_SQUARE,
                        null,
                        null,
                        null
                    )
                );
                else if ((i+1) % 2 == 0 && (j+1) % 2 == 0) Reference.placeHolderArrayList.add(
                    Reference.squareInit(
                        index,
                        new Point(i, j),
                        Reference.WHITE_SQUARE,
                        null,
                        null,
                        null
                    )
                );
            }
        }
    }
    void drawing(Graphics2D g){
        Reference.placeHolderArrayList.forEach( (p) -> {
            if (p.getSquareColorName().equals(Reference.BLACK_SQUARE)) g.setColor(Color.decode("#b58869"));         //b58869
            if (p.getSquareColorName().equals(Reference.WHITE_SQUARE)) g.setColor(Color.decode("#fadcc2"));         //fadcc2
            g.fillRect(p.getSquarePositionPoint().x * _BS_, p.getSquarePositionPoint().y * _BS_, _BS_, _BS_);
        });
    }
}
