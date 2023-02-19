package com.example.catfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewFact;
    private Button buttonNext;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getCatFact();

        mainViewModel.getCatMutableLiveData().observe(this, cat -> textViewFact.setText(cat.getFact()));

        buttonNext.setOnClickListener(v -> mainViewModel.getCatFact());

        textViewFact.setOnClickListener(v -> showTranslate());
    }

    private void showTranslate(){
        try {
            String text = textViewFact.getText().toString();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.setComponent(new ComponentName(
                    "com.google.android.apps.translate",
                    //Change is here
                    //"com.google.android.apps.translate.HomeActivity"));
                    "com.google.android.apps.translate.TranslateActivity"));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // TODO Auto-generated catch block
            Toast.makeText(getApplication(), "Sorry, No Google Translation Installed",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void initViews() {
        textViewFact = findViewById(R.id.textViewFact);
        buttonNext = findViewById(R.id.buttonNextFact);
    }
}