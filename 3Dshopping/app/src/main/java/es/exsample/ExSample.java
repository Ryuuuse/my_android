package es.exsample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class ExSample extends AppCompatActivity{

protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d_1);
        Button button1=findViewById(R.id.button1);
        button1.setOnClickListener(new button1ClickListener());

        }

private class button1ClickListener implements Button.OnClickListener {

    public void onClick(View v) {
        Intent intent = new Intent(ExSample.this, Sample1.class);
        startActivity(intent);
    }
}
}
