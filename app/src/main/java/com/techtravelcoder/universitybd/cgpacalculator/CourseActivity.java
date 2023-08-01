package com.techtravelcoder.universitybd.cgpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.techtravelcoder.universitybd.R;

public class CourseActivity extends AppCompatActivity {

    private LinearLayout editTextContainer;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        editTextContainer = findViewById(R.id.edittext_container);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditTexts();
            }
        });
    }

    private void addEditTexts() {


        for (int i = 0; i < 3; i++) {
            EditText editText = new EditText(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1
            );
            editText.setLayoutParams(layoutParams);
            editText.setHint("EditText " + (i + 1));
        }


    }
}
