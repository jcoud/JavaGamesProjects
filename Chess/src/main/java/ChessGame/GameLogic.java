package ChessGame;

import ChessGame.board.BoardSet;
import ChessGame.board.Square;
import ChessGame.figures.*;
import ChessGame.utils.DrawUtils;
import ChessGame.utils.GameUtils;

import javax.swing.*;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;


class GameLogic {
    private static GameLogic INSTANCE = new GameLogic();

    int prevIndex = 0;
    int currIndex = 0;
    boolean st_moving;
    private boolean st_checkmate;
    private boolean st_check;
    private boolean st_turn_canRun;
    private boolean st_turn_canBeCovered;
    private boolean st_turn_canAttackerBeRemoved;

    boolean sw_show_connectionLines = true;
    boolean sw_show_squareHLType = true;
    boolean sw_show_rulerMaster = true;
    boolean sw_show_rulerSimpleColor = true;
    boolean sw_show_rulerSquares = true;
    boolean sw_show_rulerDots = true;
    boolean sw_gl_ignorePlayerColor = false;
    boolean sw_gl_freeMove = false;

    boolean sw_sys_show_info = false;
    boolean sw_sys_full_info = false;

    private int turnIterator = 1;
    private boolean currTurnForPlayerIsBlack;
    ArrayList<Object[]> squarePare;
    DefaultListModel<String> moveDataInfo = new DefaultListModel<>();
    ArrayList<MoveData> moveDataList = new ArrayList<MoveData>() {
        @Override
        public boolean add(MoveData moveData) {
            moveDataInfo.addElement(moveData.getDataString());
            return super.add(moveData);
        }
    };

    BoardSet boardData;
    static GameLogic getInstance() {
        return INSTANCE;
    }

    private GameLogic() {
        INSTANCE = this;
    }

    void newGame() {
        DrawUtils.nextRandom();
        boardData = new BoardSet();
        st_checkmate = false;
        st_check = false;
        st_moving = false;
        currTurnForPlayerIsBlack = false;
        squarePare = new ArrayList<>();
        turnIterator = 1;
        moveDataList.clear();
        boardData.getRemovedFigures().clear();
    }

    private void nextPlayerTurn() {
        currTurnForPlayerIsBlack = !currTurnForPlayerIsBlack;
    }

    void move() {
        if (!(prevIndex > 0 && currIndex > 0)) return;
        Square sp = boardData.getSquare(prevIndex);
        Square sc = boardData.getSquare(currIndex);
        if (prevIndex == currIndex) {
            st_moving = false;
            sc.markSelectedAs(false);
            return;
        }
        Square squarePrev = (Square) sp.copy();
        Square squareCurr = (Square) sc.copy();
        IFigureHolder fp = squarePrev.getFigure();
        if (sw_gl_freeMove) {
            IFigureHolder tf = (IFigureHolder) fp.copy();
            st_moving = false;
            MoveData md = buildMoveData();
            if (squareCurr.isEmpty()) {
                boardData.updateFigure(fp, currIndex);
                md.Moving(tf, fp);
            } else {
                boardData.updateFigure(fp, currIndex, true);
                md.Capturing(tf, fp, squareCurr.getFigure());
            }
            moveDataList.add(md);
            if (fp instanceof IFirstMove) ((IFirstMove) fp).turn();
            if (isCheckmateForKing()) System.out.println("Check Mate!");
            return;
        }
        if (st_checkmate) return;

        if (!sw_gl_ignorePlayerColor && fp.isColorBlack() == !currTurnForPlayerIsBlack) return;

        for (ArrayList<Integer> dir : boardData.getAvailableSpots(fp)) {
            if (!(dir.contains(currIndex))) continue;
            IFigureHolder tf = (IFigureHolder) fp.copy();
            IFigureHolder[] fa = getKingAndAttackerAfterTurn(fp, currIndex);
            if (fa[1] != null && fp.isColorBlack() != fa[1].isColorBlack()) {
                System.out.println("can not turn, cause: " + fa[1] + " can target " + fa[0]);
                return;
            }
            MoveData md = buildMoveData();
            if (!squareCurr.isEmpty()) {
                IFigureHolder fc = squareCurr.getFigure();
                King k = fc instanceof King ? (King) fc : fp instanceof King ? (King) fp : null;
                Rook r = fc instanceof Rook ? (Rook) fc : fp instanceof Rook ? (Rook) fp : null;
                if (k != null && r != null && k.isColorBlack() == r.isColorBlack()) {
                    boolean f = canCastle(k, r);
                    if (f) {
                        IFigureHolder tk = k.copy();
                        IFigureHolder tr = r.copy();
                        castle(k, r);
                        md.Castling(tk, k, tr, r, tr.posPoint().x != 0 && tr.posPoint().x == 7);
                    }
                } else {
                    if (fp instanceof Pawn && GameUtils.indexToPoint(currIndex).y == 0) {
                        PawnInAge.getInstance().buildData((Pawn) fp, fc).request();

                    }
                    if (fp instanceof IFirstMove) ((IFirstMove) fp).turn();
                    boardData.updateFigure(fp, currIndex, true);
                    md.Capturing(tf, fp, fc);
                }
            } else {
                if (fp instanceof Pawn && GameUtils.indexToPoint(currIndex).y == 0) {
                    ((IFirstMove) fp).turn();
                    PawnInAge.getInstance().buildData((Pawn) tf, currIndex).request();

                } else {
                    if (fp instanceof IFirstMove) ((IFirstMove) fp).turn();
                    boardData.updateFigure(fp, currIndex);
                    md.Moving(tf, fp);
                }
            }
            turnIterator++;
            st_moving = false;
            nextPlayerTurn();
            st_checkmate = isCheckmateForKing();
            if (st_checkmate) {
                System.out.println("Check Mate!");
            }
            md.st_c = st_check;
            md.st_m = st_checkmate;
            if (md.etype != null) moveDataList.add(md);
            return;
        }

    }

    private boolean isCheckmateForKing() {
        squarePare.clear();
        boardData.setUnExcludedSpots(null);
        st_checkmate = false;
        st_check = false;
        boardData.fields().forEach(s -> s.getHlTypes().clear());
        for (Square s : boardData.fields()) {
            if (s.isEmpty()) continue;
            IFigureHolder f = s.getFigure();
            if (!(f instanceof King)) continue;
            IFigureHolder ff = getTargetOwnerOf(f);
            if (ff == null) continue;
            st_check = true;
            return isCheckmateForKing_by_(f, ff);
        }
        return false;
    }

    private boolean isCheckmateForKing_by_(IFigureHolder king, IFigureHolder attacker) {
        System.out.println();
        System.out.println("check");
        boolean b;
        ArrayList<Object[]> cc = targetOwnersOf_by_(king, attacker);
        st_turn_canRun = false;
        for (Object[] o : cc) {
            st_turn_canRun |= !(boolean) o[2];
        }
        b = !st_turn_canRun;
        System.out.println("can run : " + st_turn_canRun);
        if (!cc.isEmpty()) {
            cc.forEach(o -> {
                if (!(boolean) o[2] && o[0] == null) {
                    squarePare.add(new Object[]{king.posPoint(), GameUtils.indexToPoint((int) o[1]), new Color(100, 255, 100)});
                    boardData.getSquare((int) o[1]).addHlType(Square.SquareHighlightingType.RUN_TO);
                } else if (o[0] != null) {
                    squarePare.add(new Object[]{((IFigureHolder) o[0]).posPoint(), king.posPoint(), new Color(255, 100, 100)});
                    boardData.getSquare(((IFigureHolder) o[0]).posIndex()).addHlType(Square.SquareHighlightingType.ATTACKING_FROM);
                }
                System.out.println("\t" + ((boolean) o[2] ? "cause: " + o[0] : "free") + " in " + o[1]);
            });
        }
        cc = coverOwnersOf(king, attacker);
        st_turn_canBeCovered = cc.isEmpty();
        b &= st_turn_canBeCovered;
        System.out.println("can be covered : " + !st_turn_canBeCovered);
        if (!cc.isEmpty()) {
            cc.forEach(o -> {
                squarePare.add(new Object[]{((IFigureHolder) o[0]).posPoint(), GameUtils.indexToPoint((int) o[1]), new Color(255, 255, 100)});
                boardData.getSquare((int) o[1]).addHlType(Square.SquareHighlightingType.COVER_IN);
                boardData.getSquare(((IFigureHolder) o[0]).posIndex()).addHlType(Square.SquareHighlightingType.COVER_BY);
                System.out.println("\t" + o[0] + " in " + o[1]);
            });
        }
        cc = targetOwnersOfAttacker(attacker);
        st_turn_canAttackerBeRemoved = false;
        for (Object[] o : cc) {
            st_turn_canAttackerBeRemoved |= !(boolean) o[2];
        }
        b &= !st_turn_canAttackerBeRemoved;
        System.out.println("can attacker be removed : " + st_turn_canAttackerBeRemoved);
        if (!cc.isEmpty()) {
            cc.forEach(o -> {
                if (!(boolean) o[2]) {
                    squarePare.add(new Object[]{((IFigureHolder) o[0]).posPoint(), GameUtils.indexToPoint((int) o[1]), new Color(255, 100, 100)});
                    boardData.getSquare((int) o[1]).addHlType(Square.SquareHighlightingType.ATTACKER_TARGET);
                    boardData.getSquare(((IFigureHolder) o[0]).posIndex()).addHlType(Square.SquareHighlightingType.ATTACKING_FROM);
                }
                System.out.println("\t" + ((boolean) o[2] ? "cause: " : "") + o[0] + " in " + o[1]);
            });
        }
        ArrayList<Integer> q = new ArrayList<>();
        squarePare.forEach(o -> q.add(GameUtils.pointToIndex((Point) o[1])));
        boardData.setUnExcludedSpots(q);
        return b;
    }

    private void castle(IFigureHolder king, IFigureHolder rook) {
        if (rook.posPoint().x == 0) {
            boardData.updateFigure(rook, GameUtils.pointToIndex(3, rook.posPoint().y));
            boardData.updateFigure(king, GameUtils.pointToIndex(2, rook.posPoint().y));
        } else if (rook.posPoint().x == 7) {
            boardData.updateFigure(rook, GameUtils.pointToIndex(5, rook.posPoint().y));
            boardData.updateFigure(king, GameUtils.pointToIndex(6, rook.posPoint().y));
        }
        ((IFirstMove) king).turn();
        ((IFirstMove) rook).turn();
    }

    private boolean canCastle(King king, Rook rook) {
        if (isTargetByAnotherOf(king)) return false;
        IFigureHolder k = king.copy();
        IFigureHolder r = rook.copy();
        ArrayList<Square> s = boardData.makeSquareListCopy();

        castle(k, r);

        boolean b = !isTargetByAnotherOf(k);
        boardData.applyChanges(s);
        return b;
    }

    private IFigureHolder getTargetOwnerOf(IFigureHolder king) {
        for (Square square : boardData.fields()) {
            if (!square.isEmpty() && square.posIndex() != king.posIndex()) {
                IFigureHolder figureAt = square.getFigure();
                if (figureAt.isColorBlack() != king.isColorBlack()) {
                    IFigureHolder f = getCheckedKingBy(figureAt);
                    if (f == null) continue;
                    if (king.posIndex() == f.posIndex()) {
                        return figureAt;
                    }
                }
            }
        }
        return null;
    }

    private ArrayList<Object[]> targetOwnersOf_by_(IFigureHolder king, IFigureHolder attacker) {
        ArrayList<Object[]> ff = new ArrayList<>();

        /*
            сделать копию поля
            установить запаршиваемую фигуру в копированное поле
            проверить поле
            вернуть поле в оригинал
         */

        ArrayList<ArrayList<Integer>> q = boardData.getAvailableSpots(king);
        for (ArrayList<Integer> dir : q) {
            if (dir.isEmpty()) continue;
            int index = dir.get(0);
            if (attacker.posIndex() == index) continue;
            IFigureHolder f = getKingAndAttackerAfterTurn(king, index)[1];
            if (f == null) ff.add(new Object[]{null, index, false});
            else ff.add(new Object[]{f, index, true});
        }
        return ff;
    }

    private boolean isTargetByAnotherOf(IFigureHolder king) {
        return getTargetOwnerOf(king) != null;
    }

    private ArrayList<Object[]> coverOwnersOf(IFigureHolder king, IFigureHolder attacker) {
        ArrayList<Object[]> ff = new ArrayList<>();

        /*
            найти квадрат с фигурой того же цвета
            пройтись по доступным точкам этой фигуры
            каждую точку проверить на функцию, при которой король не найдёт атакующего (isTargetByAnotherOf)
            если атакующих несколько - проверять по такому же алгоритму

         */

        for (Square s : boardData.fields()) {
            if (s.isEmpty()) continue;
            IFigureHolder o = s.getFigure();
            if (o.isColorBlack() != king.isColorBlack()) continue;
            if (s.posIndex() == king.posIndex()) continue;
            IFigureHolder f = (IFigureHolder) o.copy();
            for (ArrayList<Integer> dir : boardData.getAvailableSpots(o)) {
                for (Integer index : dir) {
                    if (index == king.posIndex()) break;
                    if (index == attacker.posIndex()) break;
                    ArrayList<Square> bc = boardData.makeSquareListCopy();
                    boardData.updateFigure(f, index);
                    if (!isTargetByAnotherOf(king)) {
                        ff.add(new Object[]{o, index});
                    }
                    boardData.applyChanges(bc);
                }
            }
        }
        return ff;
    }

    private IFigureHolder[] getKingAndAttackerAfterTurn(IFigureHolder movingFigure, int movingTo_idx) {
        IFigureHolder[] ff = new IFigureHolder[2];
        IFigureHolder fc = (IFigureHolder) movingFigure.copy();
        ArrayList<Square> bc = boardData.makeSquareListCopy();
        boardData.updateFigure(fc, movingTo_idx);
        boardData.getSquare(movingFigure.posIndex()).clear();
        if (movingFigure instanceof King) {
            IFigureHolder f = getTargetOwnerOf(fc);
            ff[0] = fc;
            ff[1] = f;
        } else {
            ff = searchAndGetKingAndAttacker();
        }
        boardData.applyChanges(bc);
        return ff;
    }

    private IFigureHolder[] searchAndGetKingAndAttacker() {
        for (Square s : boardData.fields()) {
            if (s.isEmpty()) continue;
            IFigureHolder f = s.getFigure();
            if (f.isColorBlack() == currTurnForPlayerIsBlack) continue;
            for (ArrayList<Integer> dir : boardData.getAvailableSpots(f)) {
                if (dir.isEmpty()) continue;
                int idx = dir.get(dir.size() - 1);
                Square sk = boardData.getSquare(idx);
                if (sk.isEmpty()) continue;
                IFigureHolder fk = sk.getFigure();
                if (fk.isColorBlack() != currTurnForPlayerIsBlack) continue;
                return fk instanceof King ? new IFigureHolder[]{fk, f} : new IFigureHolder[2];
            }
        }
        return new IFigureHolder[2];
    }

    private ArrayList<Object[]> targetOwnersOfAttacker(IFigureHolder attacker) {
        ArrayList<Object[]> af = new ArrayList<>();
        for (Square s : boardData.fields()) {
            if (s.isEmpty()) continue;
            IFigureHolder f = s.getFigure();

            if (f.isColorBlack() == attacker.isColorBlack()) continue;
            for (ArrayList<Integer> dir : boardData.getAvailableSpots(f)) {
                if (dir.isEmpty()) continue;
                int idx = dir.get(dir.size() - 1);

                Square ss = boardData.getSquare(idx);
                if (ss.isEmpty()) continue;
                IFigureHolder ff = ss.getFigure();
                if (ff.posIndex() != attacker.posIndex()) continue;
                ArrayList<Square> bc = boardData.makeSquareListCopy();
                IFigureHolder fa = getKingAndAttackerAfterTurn(f, idx)[1];
                if (fa == null) af.add(new Object[]{f, idx, false});
                else af.add(new Object[]{fa, idx, true});
                boardData.applyChanges(bc);
            }
        }
        return af;
    }

    private IFigureHolder getCheckedKingBy(IFigureHolder figureBy) {
        ArrayList<ArrayList<Integer>> q = boardData.getAvailableSpots(figureBy);
        for (ArrayList<Integer> dir : q) {
            if (dir.isEmpty()) continue;
            int index = dir.get(dir.size() - 1);
            Square squareAt = boardData.getSquare(index);
            if (!squareAt.isEmpty() && squareAt.getFigure() instanceof King)
                return boardData.getSquare(index).getFigure();
        }
        return null;
    }

    void updateBoard() {
        isCheckmateForKing();
    }

    void reverseMove(MoveData data) {
        turnIterator = data.turnCount;
        currTurnForPlayerIsBlack = data.isCurrColorBlack;
        switch (data.etype) {
            case moving:
                boardData.updateFigure(data.figure_newState[0], data.figure_oldState[0].posIndex());
                break;
            case capturing:
                boardData.updateFigure(data.figure_newState[0], data.figure_oldState[0].posIndex());
                boardData.setNewFigure(data.figure_add[0]);
                boardData.unRemove(data.figure_add[0]);
                break;
            case castling_l:
            case castling_s:
                boardData.updateFigure(data.figure_newState[0], data.figure_oldState[0].posIndex());
                boardData.updateFigure(data.figure_newState[1], data.figure_oldState[1].posIndex());
                break;
            case exchange:
                boardData.updateFigure(data.figure_newState[0], data.figure_oldState[0].posIndex());
                if (data.figure_add[0] != null) {
                    boardData.setNewFigure(data.figure_add[0]);
                    boardData.unRemove(data.figure_add[0]);
                }
                break;
        }
        st_moving = false;
        GameLogic.getInstance().moveDataInfo.removeElementAt(GameLogic.getInstance().moveDataInfo.size()-1);
        GameLogic.getInstance().moveDataList.remove(GameLogic.getInstance().moveDataList.size()-1);
    }

    public enum EventType {moving, castling_l, castling_s, capturing, exchange}

    MoveData buildMoveData() {
        return new MoveData(currTurnForPlayerIsBlack, turnIterator, st_check, st_checkmate);
    }
}
