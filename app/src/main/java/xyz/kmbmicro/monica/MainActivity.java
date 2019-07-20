package xyz.kmbmicro.monica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton mainButton = findViewById(R.id.main_image_menu);
        mainButton.performClick();
        mainButton.performClick();

    }
}
