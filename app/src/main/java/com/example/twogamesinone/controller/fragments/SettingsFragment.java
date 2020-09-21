package com.example.twogamesinone.controller.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.twogamesinone.R;
import com.example.twogamesinone.model.Settings;
import com.google.android.material.snackbar.Snackbar;

public class SettingsFragment extends Fragment {

    private EditText mPlayerOneName;
    private EditText mPlayerTwoName;
    private Switch mSwitchPoints;
    private Button mSaveButton;
    private Settings mSettings = Settings.getInstance();

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    private void findViews(View view) {
        mPlayerOneName = view.findViewById(R.id.edit_text_first_player);
        mPlayerTwoName = view.findViewById(R.id.edit_text_second_player);
        mSwitchPoints = view.findViewById(R.id.switch_point_view);
        mSaveButton = view.findViewById(R.id.button_save);
    }

    private void setListeners() {
        mSwitchPoints.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mSettings.setShowPoints(b);
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] names = new String[2];
                names[0] = mPlayerOneName.getText().toString();
                names[1] = mPlayerTwoName.getText().toString();
                for (int i = 0; i < 2; i++) {
                    if (names[i].length() == 0)
                        names[i] = String.format(getString(R.string.player), i + 1);
                }
                mSettings.setNames(names);
                Snackbar.make(getView(), R.string.changes_saved, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}