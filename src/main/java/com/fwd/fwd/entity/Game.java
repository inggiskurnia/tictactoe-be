package com.fwd.fwd.entity;

import com.fwd.fwd.common.enums.Mark;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID gameId;

    @Column(nullable = false)
    private int rowSize;

    @Column(nullable = false)
    private int colSize;

    @Column(nullable = false)
    private int winLength;

    @Column(nullable = false)
    private String humanMark;

    @Column(nullable = false)
    private String aiMark;

    @Column(nullable = false)
    private String status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private String[][] board;

    public void setMarkOnBoard(int row, int col, boolean isAi){
        this.board[row][col] = isAi ? this.aiMark : this.humanMark;
    }

    public void removeMarkOnBoard(int row, int col){
        this.board[row][col] = Mark.EMPTY.getValue();
    }

    public boolean isBoardOccupy(int row, int col){
        return !this.board[row][col].equals(Mark.EMPTY.getValue());
    }

    public boolean evaluateBoard(boolean isAi) {

        String player = isAi ? this.aiMark : this.humanMark;

        // row
        for (int i = 0; i < this.rowSize; i++) {
            int countRow = 0;

            for (int j = 0; j < this.colSize; j++) {
                if (this.board[i][j].equals(player)) {
                    countRow++;
                    if (countRow == this.winLength) return true;
                } else {
                    countRow = 0;
                }
            }
        }

        for (int i = 0; i < this.colSize; i++) {
            int countCol = 0;

            for (int j = 0; j < this.rowSize; j++) {
                if (this.board[j][i].equals(player)) {
                    countCol++;
                    if (countCol == this.winLength) return true;
                } else {
                    countCol = 0;
                }
            }
        }

        // diagonal
        int countDiag = 0;
        for (int i = 0; i < Math.min(this.rowSize, this.colSize); i++) {
            if (this.board[i][i].equals(player)) {
                countDiag++;
                if (countDiag == this.winLength) return true;
            } else {
                countDiag = 0;
            }
        }

        return false;
    }

    public List<int[]> getAvailableMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (this.board[i][j].equals(Mark.EMPTY.getValue())) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }


}
