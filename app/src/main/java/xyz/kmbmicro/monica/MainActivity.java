package xyz.kmbmicro.monica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout mainlayout = findViewById(R.id.mainlayout);

        ImageButton mainButton = findViewById(R.id.main_image_menu);
        mainButton.performClick();
        mainButton.performClick();
//        mainButton.setOnClickListener(new View.OnClickListener() {
//            //            @Override
////            public void onClick(View v) {
////            //    mainlayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
////            }
////        });
//
        ImageButton monicaButton = findViewById(R.id.monica_button);

        monicaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

    }
}
