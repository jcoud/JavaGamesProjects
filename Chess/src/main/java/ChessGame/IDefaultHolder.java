package ChessGame;

import java.awt.*;

public interface IDefaultHolder extends Cloneable {
    int posIndex();
    Point posPoint();
    boolean isColorBlack();

    Object copy();
}
