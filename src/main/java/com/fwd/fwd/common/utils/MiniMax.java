package com.fwd.fwd.common.utils;

import com.fwd.fwd.entity.Game;

public class MiniMax {

    public static int[] getBestMove(Game game) {
        int[] bestMove = {-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < game.getRowSize(); row++) {
            for (int col = 0; col < game.getColSize(); col++) {

                if (!game.isBoardOccupy(row, col)) {
                    game.setMarkOnBoard(row, col, true);
                    int moveValue = miniMax(game, 0, false);
                    game.removeMarkOnBoard(row, col);

                    if (moveValue > bestValue) {
                        bestValue = moveValue;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int miniMax(Game game, int depth, boolean isMax) {

        if (game.evaluateBoard(true))  return 10 - depth;
        if (game.evaluateBoard(false)) return depth - 10;
        if (game.getAvailableMoves().isEmpty()) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int[] move : game.getAvailableMoves()) {
                game.setMarkOnBoard(move[0], move[1], true);
                best = Math.max(best, miniMax(game, depth + 1, false));
                game.removeMarkOnBoard(move[0], move[1]);
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int[] move : game.getAvailableMoves()) {
                game.setMarkOnBoard(move[0], move[1], false);
                best = Math.min(best, miniMax(game, depth + 1, true));
                game.removeMarkOnBoard(move[0], move[1]);
            }
            return best;
        }
    }
}
