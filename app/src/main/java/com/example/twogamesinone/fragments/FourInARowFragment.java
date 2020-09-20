package com.example.twogamesinone.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twogamesinone.R;
import com.example.twogamesinone.enums.FourInARowStates;
import com.example.twogamesinone.model.FourInARow;
import com.maktab.minigames.model.Settings;

public class FourInRowFragment extends Fragment {

    private FourInARow mFourInARow = new FourInARow();
    private Button[][] mPlaces = new Button[5][5];
    private TextView mFirstPlayerScore;
    private TextView mSecondPlayerScore;
    private TextView mReset;
    private int mTurnIndex = 0;
    private int[] mScores = new int[2];
    private Settings mSettings = Settings.getInstance();
    private String[] mNames = new String[2];
    private boolean mShowFinishedDetails = false;
    private static final String BUNDLE_FOUR_IN_ROW = "BUNDLE_FOUR_IN_ROW";
    private static final String BUNDLE_TURN_INDEX = "BUNDLE_TURN_INDEX";
    private static final String BUNDLE_SCORES = "BUNDLE_SCORES";
    private static final String BUNDLE_SHOW_FINISHED_DETAILS = "BUNDLE_SHOW_FINISHED_DETAILS";


    public FourInRowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_FOUR_IN_ROW, mFourInARow);
        outState.putInt(BUNDLE_TURN_INDEX, mTurnIndex);
        outState.putIntArray(BUNDLE_SCORES, mScores);
        outState.putBoolean(BUNDLE_SHOW_FINISHED_DETAILS, mShowFinishedDetails);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mFourInARow = (FourInARow) savedInstanceState.getSerializable(BUNDLE_FOUR_IN_ROW);
            mTurnIndex = savedInstanceState.getInt(BUNDLE_TURN_INDEX);
            mScores = savedInstanceState.getIntArray(BUNDLE_SCORES);
            mShowFinishedDetails = savedInstanceState.getBoolean(BUNDLE_SHOW_FINISHED_DETAILS);
        } else
            mFourInARow = new FourInARow();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four_in_row, container, false);
        findViews(view);
        setViews();
        setListeners();
        setSettings();
        setScores();
        turnColorSet();
        return view;
    }

    private void findViews(View view) {
        mFirstPlayerScore = view.findViewById(R.id.textView_first_score_f);
        mSecondPlayerScore = view.findViewById(R.id.textView_second_score_f);
        mReset = view.findViewById(R.id.textView_try_again_f);
        mPlaces[0][4] = view.findViewById(R.id.button_1_f);
        mPlaces[1][4] = view.findViewById(R.id.button_2_f);
        mPlaces[2][4] = view.findViewById(R.id.button_3_f);
        mPlaces[3][4] = view.findViewById(R.id.button_4_t);
        mPlaces[4][4] = view.findViewById(R.id.button_5_t);
        mPlaces[0][3] = view.findViewById(R.id.button_6_f);
        mPlaces[1][3] = view.findViewById(R.id.button_7_f);
        mPlaces[2][3] = view.findViewById(R.id.button_8_f);
        mPlaces[3][3] = view.findViewById(R.id.button_9_t);
        mPlaces[4][3] = view.findViewById(R.id.button_10_t);
        mPlaces[0][2] = view.findViewById(R.id.button_11_f);
        mPlaces[1][2] = view.findViewById(R.id.button_12_f);
        mPlaces[2][2] = view.findViewById(R.id.button_13_f);
        mPlaces[3][2] = view.findViewById(R.id.button_14_t);
        mPlaces[4][2] = view.findViewById(R.id.button_15_t);
        mPlaces[0][1] = view.findViewById(R.id.button_16_f);
        mPlaces[1][1] = view.findViewById(R.id.button_17_f);
        mPlaces[2][1] = view.findViewById(R.id.button_18_f);
        mPlaces[3][1] = view.findViewById(R.id.button_19_t);
        mPlaces[4][1] = view.findViewById(R.id.button_20_t);
        mPlaces[0][0] = view.findViewById(R.id.button_21_f);
        mPlaces[1][0] = view.findViewById(R.id.button_22_f);
        mPlaces[2][0] = view.findViewById(R.id.button_23_f);
        mPlaces[3][0] = view.findViewById(R.id.button_24_t);
        mPlaces[4][0] = view.findViewById(R.id.button_25_t);
    }

    private void setViews() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mFourInARow.getState(i, j) != FourInARowStates.EMPTY)
                    if (mFourInARow.getState(i, j) == FourInARowStates.RED)
                        mPlaces[i][j].setBackgroundColor(Color.RED);
                    else
                        mPlaces[i][j].setBackgroundColor(Color.BLUE);
            }
        }
        if (mShowFinishedDetails)
            showReset();
    }

    private void setListeners() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final int index = i;
                mPlaces[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setState(index);
                    }
                });
            }
        }
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFourInARow.reset();
                mReset.setText(null);
                mShowFinishedDetails = false;
                clearButtons();
            }
        });
    }

    private void setState(int i) {
        if (mTurnIndex % 2 == 0) {
            for (int j = 0; j < 5; j++) {
                if (mFourInARow.getState(i, j) == FourInARowStates.EMPTY) {
                    mFourInARow.setState(i, j, FourInARowStates.BLUE);
                    mPlaces[i][j].setBackgroundColor(Color.BLUE);
                    mTurnIndex++;
                    break;
                }
            }
        } else for (int j = 0; j < 5; j++) {
            if (mFourInARow.getState(i, j) == FourInARowStates.EMPTY) {
                mFourInARow.setState(i, j, FourInARowStates.RED);
                mPlaces[i][j].setBackgroundColor(Color.RED);
                mTurnIndex++;
                break;
            }
        }
        checkFinished();
        checkIsFull();
        turnColorSet();
    }

    private void checkFinished() {
        String winner = "";
        if (mFourInARow.isFinished() == FourInARowStates.BLUE)
            winner = mNames[0];
        else if (mFourInARow.isFinished() == FourInARowStates.RED)
            winner = mNames[1];
        if (mFourInARow.isFinished() != FourInARowStates.EMPTY) {
            Toast.makeText(getActivity(), (String.format("%s%s", winner
                    , getString(R.string.winner_call))), Toast.LENGTH_LONG).show();
            whoIsTheWinner(mFourInARow.isFinished());
            mTurnIndex = 0;
            buttonsEnabled(false);
            mShowFinishedDetails = true;
            showReset();
        }
    }

    private void checkIsFull() {
        if (mFourInARow.isFull() && mFourInARow.isFinished() == FourInARowStates.EMPTY) {
            Toast.makeText(getActivity(), getString(R.string.draw), Toast.LENGTH_LONG).show();
            buttonsEnabled(false);
            mShowFinishedDetails = true;
            mTurnIndex = 0;
            showReset();
        }
    }

    private void buttonsEnabled(boolean enable) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mPlaces[i][j].setEnabled(enable);
            }
        }
    }

    private void clearButtons() {
        buttonsEnabled(true);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mPlaces[i][j].setBackgroundColor(getResources().getColor(R.color.defaultColor));
            }
        }
    }

    private void showReset() {
        mReset.setText(R.string.play_again);
    }

    private void whoIsTheWinner(FourInARowStates states) {
        if (states == FourInARowStates.BLUE)
            mScores[0]++;
        else if (states == FourInARowStates.RED)
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
