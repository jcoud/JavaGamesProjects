import java.awt.*;

class FieldInit {
    private static final int _BS_ = Reference.BS;
    FieldInit(String  playerBoardColor){
        if (playerBoardColor.equals(Reference.BLACK_SQUARE)){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int index = i + 1 + j*8;
                    if ((i+1) % 2 == 0 && j % 2 == 0) Reference.placeHolderArrayList.add(
                        Reference.squareInit(
                            index,
                            new Point(i, j),
                            Reference.BLACK_SQUARE,
                            "empty",
                            "none"
                        )
                    );
                    else if (i % 2 == 0 && (j+1) % 2 == 0) Reference.placeHolderArrayList.add(
                        Reference.squareInit(
                            index,
                            new Point(i, j),
                            Reference.BLACK_SQUARE,
                            "empty",
                            "none"
                        )
                    );
                }
            }
        }
        if (playerBoardColor.equals(Reference.WHITE_SQUARE)){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int index = i + 1 + j*8;
                    if ((i) % 2 == 0 && j % 2 == 0) {
                        Reference.placeHolderArrayList.add(Reference.squareInit(index, new Point(i, j), Reference.WHITE_SQUARE,"empty","none"));
                    }else if ((i+1) % 2 == 0 && (j+1) % 2 == 0) {
                        Reference.placeHolderArrayList.add(Reference.squareInit(index, new Point(i, j), Reference.WHITE_SQUARE,"empty","none"));
                    }
                }
            }
        }
    }
    void paint(Graphics2D g){
        Reference.placeHolderArrayList.forEach( (p) -> {
            if (p.getSquareColorName().equals(Reference.BLACK_SQUARE)) g.setColor(Color.BLACK);
            if (p.getSquareColorName().equals(Reference.WHITE_SQUARE)) g.setColor(Color.LIGHT_GRAY);
            g.fillRect(p.getPositionPoint().x * _BS_, p.getPositionPoint().y * _BS_, _BS_, _BS_);
        });
    }
}
