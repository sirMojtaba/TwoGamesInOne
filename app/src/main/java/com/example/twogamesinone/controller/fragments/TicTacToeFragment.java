package com.example.twogamesinone.controller.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.twogamesinone.R;
import com.example.twogamesinone.enums.TicTacToeStates;
import com.example.twogamesinone.model.Settings;
import com.example.twogamesinone.model.TicTacToe;
import com.google.android.material.snackbar.Snackbar;

public class TicTacToeFragment extends Fragment {

    private Button[][] mPlaces = new Button[3][3];
    private TextView mFirstPlayerScore;
    private TextView mSecondPlayerScore;
    private TextView mReset;
    private int mTurnIndex = 0;
    private int[] mScores = new int[2];
    private TicTacToe mTicTacToe;
    private Settings mSettings = Settings.getInstance();
    private String[] mNames = new String[2];
    private boolean mShowFinishedDetails = false;
    private static final String BUNDLE_TIC_TAC_TOE = "BUNDLE_TIC_TAC_TOE";
    private static final String BUNDLE_TURN_INDEX = "BUNDLE_TURN_INDEX";
    private static final String BUNDLE_SCORES = "BUNDLE_SCORES";
    private static final String BUNDLE_SHOW_FINISHED_DETAILS = "BUNDLE_SHOW_FINISHED_DETAILS";

    public TicTacToeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_TIC_TAC_TOE, mTicTacToe);
        outState.putInt(BUNDLE_TURN_INDEX, mTurnIndex);
        outState.putIntArray(BUNDLE_SCORES, mScores);
        outState.putBoolean(BUNDLE_SHOW_FINISHED_DETAILS, mShowFinishedDetails);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mTicTacToe = (TicTacToe) savedInstanceState.getSerializable(BUNDLE_TIC_TAC_TOE);
            mTurnIndex = savedInstanceState.getInt(BUNDLE_TURN_INDEX);
            mScores = savedInstanceState.getIntArray(BUNDLE_SCORES);
            mShowFinishedDetails = savedInstanceState.getBoolean(BUNDLE_SHOW_FINISHED_DETAILS);
        } else
            mTicTacToe = new TicTacToe();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        findViews(view);
        setViews();
        setListeners();
        setSettings();
        setScores();
        turnColorSet();
        return view;
    }

    private void findViews(View view) {
        mFirstPlayerScore = view.findViewById(R.id.textView_first_score);
        mSecondPlayerScore = view.findViewById(R.id.textView_second_score);
        mReset = view.findViewById(R.id.textView_try_again);
        mPlaces[0][0] = view.findViewById(R.id.button_one);
        mPlaces[0][1] = view.findViewById(R.id.button_two);
        mPlaces[0][2] = view.findViewById(R.id.button_three);
        mPlaces[1][0] = view.findViewById(R.id.button_four);
        mPlaces[1][1] = view.findViewById(R.id.button_five);
        mPlaces[1][2] = view.findViewById(R.id.button_six);
        mPlaces[2][0] = view.findViewById(R.id.button_seven);
        mPlaces[2][1] = view.findViewById(R.id.button_eight);
        mPlaces[2][2] = view.findViewById(R.id.button_nine);
    }

    private void setViews() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mTicTacToe.getState(i, j) != TicTacToeStates.EMPTY)
                    mPlaces[i][j].setText(mTicTacToe.getState(i, j).toString());
            }
        }
        if (mShowFinishedDetails)
            showReset();
    }

    private void setListeners() {
        mPlaces[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setState(0, 0);
            }
        });
        mPlaces[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setState(0, 1);
            }
        });
        mPlaces[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setState(0, 2);
            }
        });
        mPlaces[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setState(1, 0);
            }
        });
        mPlaces[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setState(1, 1);
            }
        });
        mPlaces[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setState(1, 2);
            }
        });
        mPlaces[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setState(2, 0);
            }
        });
        mPlaces[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setState(2, 1);
            }
        });
        mPlaces[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setState(2, 2);
            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTicTacToe.reset();
                mReset.setText(null);
                mShowFinishedDetails = false;
                clearButtons();
            }
        });
    }

    private void setState(int i, int j) {
        if (mTurnIndex % 2 == 0 && mPlaces[i][j].getText().length() == 0) {
            mTicTacToe.setState(i, j, TicTacToeStates.X);
            mPlaces[i][j].setText(TicTacToeStates.X.toString());
            mTurnIndex++;
        } else if (mPlaces[i][j].getText().length() == 0) {
            mTicTacToe.setState(i, j, TicTacToeStates.O);
            mPlaces[i][j].setText(TicTacToeStates.O.toString());
            mTurnIndex++;
        }
        checkFinished();
        checkIsFull();
        turnColorSet();
    }

    private void checkFinished() {
        String winner = "";
        if (mTicTacToe.isFinished() == TicTacToeStates.X)
            winner = mNames[0];
        else if (mTicTacToe.isFinished() == TicTacToeStates.O)
            winner = mNames[1];
        if (mTicTacToe.isFinished() != TicTacToeStates.EMPTY) {
            Snackbar.make(getView(), (String.format("%s%s", winner
                    , getString(R.string.winner_call))), Snackbar.LENGTH_LONG).show();
            whoIsTheWinner(mTicTacToe.isFinished());
            mTurnIndex = 0;
            mShowFinishedDetails = true;
            buttonsEnabled(false);
            showReset();
        }
    }

    private void checkIsFull() {
        if (mTicTacToe.isFull() && mTicTacToe.isFinished() == TicTacToeStates.EMPTY) {
            Snackbar.make(getView(),  getString(R.string.draw), Snackbar.LENGTH_LONG).show();
            buttonsEnabled(false);
            mTurnIndex = 0;
            mShowFinishedDetails = true;
            showReset();
        }
    }

    private void buttonsEnabled(boolean enable) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mPlaces[i][j].setEnabled(enable);
            }
        }
    }

    private void clearButtons() {
        buttonsEnabled(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mPlaces[i][j].setText(null);
            }
        }
    }

    private void showReset() {
        mReset.setText(R.string.play_again);
    }

    private void whoIsTheWinner(TicTacToeStates states) {
        if (states == TicTacToeStates.X)
            mScores[0]++;
        else if (states == TicTacToeStates.O)
            mScores[1]++;
        setScores();
    }

    private void setScores() {
        if (mSettings.getShowPoints()) {
            mFirstPlayerScore.setText(String.format(getString(R.string.what_the_hell)
                    , mNames[0], getString(R.string.ss), mScores[0]));
            mSecondPlayerScore.setText(String.format(getString(R.string.thats_so_weired)
                    , mScores[1], getString(R.string.dd), mNames[1]));
        } else {
            mFirstPlayerScore.setText(String.format(getString(R.string.what_the_hell_2)
                    , mNames[0], ""));
            mSecondPlayerScore.setText(String.format(getString(R.string.thats_so_weired_2)
                    , "", mNames[1]));
        }
    }

    private void setSettings() {
        if (mSettings != null) {
            mNames = mSettings.getNames();
        }
    }

    private void turnColorSet() {
        if (mTurnIndex % 2 == 0) {
            mSecondPlayerScore.setTextColor(Color.parseColor("#00CDFF"));
            mFirstPlayerScore.setTextColor(Color.GREEN);
        } else {
            mSecondPlayerScore.setTextColor(Color.GREEN);
            mFirstPlayerScore.setTextColor(Color.parseColor("#00CDFF"));
        }
    }
}