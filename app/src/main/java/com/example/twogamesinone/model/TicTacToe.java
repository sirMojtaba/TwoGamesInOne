package com.example.twogamesinone.model;

import com.example.twogamesinone.enums.TicTacToeStates;

import java.io.Serializable;

public class TicTacToe implements Serializable {

    private TicTacToeStates[][] mTicTacToe;

    public TicTacToe() {
        mTicTacToe = new TicTacToeStates[3][3];
        emptyStates();
    }

    public void reset() {
        emptyStates();
    }

    public void setState(int i, int j, TicTacToeStates input) {
        mTicTacToe[i][j] = input;
    }

    public TicTacToeStates getState(int i, int j) {
        return mTicTacToe[i][j];
    }


    public TicTacToeStates isFinished() {
        for (int i = 0; i < 3; i++) {
            if (mTicTacToe[i][0] != TicTacToeStates.EMPTY
                    && mTicTacToe[i][0] == mTicTacToe[i][1] && mTicTacToe[i][0] == mTicTacToe[i][2])
                return mTicTacToe[i][0];
        }
        for (int j = 0; j < 3; j++) {
            if (mTicTacToe[0][j] != TicTacToeStates.EMPTY
                    && mTicTacToe[0][j] == mTicTacToe[1][j] && mTicTacToe[0][j] == mTicTacToe[2][j])
                return mTicTacToe[0][j];
        }
        if (mTicTacToe[0][0] == mTicTacToe[1][1] && mTicTacToe[0][0] == mTicTacToe[2][2]
                || mTicTacToe[0][2] == mTicTacToe[1][1] && mTicTacToe[0][2] == mTicTacToe[2][0])
            return mTicTacToe[1][1];
        return TicTacToeStates.EMPTY;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mTicTacToe[i][j] == TicTacToeStates.EMPTY)
                    return false;
            }
        }
        return true;
    }

    private void emptyStates() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mTicTacToe[i][j] = TicTacToeStates.EMPTY;
            }
        }
    }
}
