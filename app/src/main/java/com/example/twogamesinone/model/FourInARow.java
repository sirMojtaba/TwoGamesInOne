package com.example.twogamesinone.model;

import com.example.twogamesinone.enums.FourInARowStates;

import java.io.Serializable;

public class FourInARow implements Serializable {
    private FourInARowStates[][] mFourInOne;

    public FourInARow() {
        mFourInOne = new FourInARowStates[5][5];
        emptyStates();
    }

    public void setState(int i, int j, FourInARowStates input) {
        mFourInOne[i][j] = input;
    }

    public FourInARowStates getState(int i, int j) {
        return mFourInOne[i][j];
    }

    public void reset() {
        emptyStates();
    }

    public FourInARowStates isFinished() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if (mFourInOne[i][j] != FourInARowStates.EMPTY
                        && mFourInOne[i][j] == mFourInOne[i][j + 1]
                        && mFourInOne[i][j] == mFourInOne[i][j + 2]
                        && mFourInOne[i][j] == mFourInOne[i][j + 3])
                    return mFourInOne[i][j];
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                if (mFourInOne[i][j] != FourInARowStates.EMPTY
                        && mFourInOne[i][j] == mFourInOne[i + 1][j]
                        && mFourInOne[i][j] == mFourInOne[i + 2][j]
                        && mFourInOne[i][j] == mFourInOne[i + 3][j])
                    return mFourInOne[i][j];
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (mFourInOne[i][j] != FourInARowStates.EMPTY
                        && mFourInOne[i][j] == mFourInOne[i + 1][j + 1]
                        && mFourInOne[i][j] == mFourInOne[i + 2][j + 2]
                        && mFourInOne[i][j] == mFourInOne[i + 3][j + 3])
                    return mFourInOne[i][j];
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 4; j > 2; j--) {
                if (mFourInOne[i][j] != FourInARowStates.EMPTY
                        && mFourInOne[i][j] == mFourInOne[i + 1][j - 1]
                        && mFourInOne[i][j] == mFourInOne[i + 2][j - 2]
                        && mFourInOne[i][j] == mFourInOne[i + 3][j - 3])
                    return mFourInOne[i][j];
            }
        }
        return FourInARowStates.EMPTY;
    }

    public boolean isFull() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mFourInOne[i][j] == FourInARowStates.EMPTY)
                    return false;
            }
        }
        return true;
    }

    private void emptyStates() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mFourInOne[i][j] = FourInARowStates.EMPTY;
            }
        }
    }
}
