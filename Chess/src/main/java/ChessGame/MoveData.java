package ChessGame;

import ChessGame.figures.IFigureHolder;
import ChessGame.figures.Pawn;
import ChessGame.utils.GameUtils;

import java.util.Arrays;

class MoveData {
    boolean isCurrColorBlack;
    IFigureHolder[] figure_oldState = new IFigureHolder[6];
    IFigureHolder[] figure_newState = new IFigureHolder[6];
    IFigureHolder[] figure_add = new IFigureHolder[6];
    GameLogic.EventType etype;
    int turnCount;
    boolean st_c, st_m;
    @Override
    public String toString() {
        return "isCurrColorBlack: " + isCurrColorBlack + "\n" +
                "figure_oldState: " + Arrays.toString(figure_oldState) + "\n" +
                "figure_newState: " + Arrays.toString(figure_newState) + "\n" +
                "figure_add: " + Arrays.toString(figure_add) + "\n" +
                "eType: " + etype.name() + "\n" +
                "turnCount: " + turnCount + "\n" +
                "st_c: " + st_c + "\n" +
                "st_m: " + st_m + "\n";
    }

    MoveData(boolean isCurrColorBlack, int turnCount, boolean st_check, boolean st_mate) {
        this.isCurrColorBlack = isCurrColorBlack;
        this.turnCount = turnCount;
        this.st_c = st_check;
        this.st_m = st_mate;
    }

    String getDataString() {
        String s;
        switch (etype) {
            case moving:
                s = GameUtils.getFigureSymbol(figure_oldState[0]) + GameUtils.formatFigurePos(figure_oldState[0].posIndex()) + "-" + GameUtils.formatFigurePos(figure_newState[0].posIndex());
                break;
            case castling_l:
                return "0-0-0";
            case castling_s:
                return "0-0";
            case exchange:
                s = GameUtils.getFigureSymbol(figure_oldState[0]) + GameUtils.formatFigurePos(figure_oldState[0].posIndex()) + "=" + GameUtils.getFigureSymbol(figure_add[0]);
                break;
            case capturing:
                s = GameUtils.getFigureSymbol(figure_oldState[0]) + GameUtils.formatFigurePos(figure_oldState[0].posIndex()) + "x" + GameUtils.getFigureSymbol(figure_add[0]) + GameUtils.formatFigurePos(figure_add[0].posIndex());
                break;
            default:
                return "";
        }
        return "[" + turnCount + "]" + (isCurrColorBlack ? "..." : "   ") + s + (st_m ? "#" : st_c ? "+" : " ");
    }

    MoveData Exchange(Pawn pawn_old, Pawn pawn_new, IFigureHolder exchangeTo) {
        this.etype = GameLogic.EventType.exchange;
        this.figure_oldState[0] = pawn_old;
        this.figure_newState[0] = pawn_new;
        this.figure_add[0] = exchangeTo;
        return this;
    }

    MoveData Castling(IFigureHolder king_old, IFigureHolder king_new, IFigureHolder rook_old, IFigureHolder rook_new, boolean shortCastling) {
        this.etype = shortCastling ? GameLogic.EventType.castling_s : GameLogic.EventType.castling_l;
        this.figure_oldState[0] = king_old;
        this.figure_newState[0] = king_new;
        this.figure_oldState[1] = rook_old;
        this.figure_newState[1] = rook_new;
        return this;
    }

    MoveData Moving(IFigureHolder f_old, IFigureHolder f_new) {
        this.etype = GameLogic.EventType.moving;
        this.figure_oldState[0] = f_old;
        this.figure_newState[0] = f_new;
        return this;
    }

    MoveData Capturing(IFigureHolder f_old, IFigureHolder f_new, IFigureHolder f_target) {
        this.etype = GameLogic.EventType.capturing;
        this.figure_oldState[0] = f_old;
        this.figure_newState[0] = f_new;
        this.figure_add[0] = f_target;
        return this;
    }
}