package com.example.twogamesinone.controller.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.twogamesinone.R;
import com.example.twogamesinone.controller.fragments.FourInRowFragment;
import com.example.twogamesinone.controller.fragments.SettingsFragment;
import com.example.twogamesinone.controller.fragments.TicTacToeFragment;

public class GameActivity extends AppCompatActivity {


    private Button mTicTocToeButton;
    private Button mFourInOneButton;
    private ImageButton mSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
        findViews();
        setListeners();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_holder);
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_holder, new TicTacToeFragment())
                    .commit();
        }
    }

    private void findViews() {
        mTicTocToeButton = findViewById(R.id.button_tic_tac_toe);
        mFourInOneButton = findViewById(R.id.button_four_in_row);
        mSettingsButton = findViewById(R.id.button_edit);
    }

    private void setListeners() {
        mTicTocToeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_holder);
                if (!(fragment instanceof TicTacToeFragment)) {
                    assert fragment != null;
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    fragmentManager.beginTransaction().add(R.id.fragment_holder, new TicTacToeFragment()).commit();
                }
            }
        });
        mFourInOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_holder);
                if (!(fragment instanceof FourInRowFragment)) {
                    assert fragment != null;
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    fragmentManager.beginTransaction().add(R.id.fragment_holder, new FourInRowFragment()).commit();
                }
            }

        });
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_holder);

                if (!(fragment instanceof SettingsFragment)) {
                    assert fragment != null;
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    fragmentManager.beginTransaction().add(R.id.fragment_holder, new SettingsFragment()).commit();
                }
            }

        });
    }

}
