package es.exsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Sample4 extends AppCompatActivity {
    String st1, st2, st3, st4, st5, st6, st7, st8;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d_7);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new Sample4.button1ClickListener());
    }

    private class button1ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            EditText editText1 = findViewById(R.id.editText1);
            EditText editText2 = findViewById(R.id.editText2);
            EditText editText3 = findViewById(R.id.editText3);
            EditText editText4 = findViewById(R.id.editText4);
            EditText editText5 = findViewById(R.id.editText5);
            EditText editText6 = findViewById(R.id.editText6);
            EditText editText7 = findViewById(R.id.editText7);
            EditText editText8 = findViewById(R.id.editText8);
            st1 = editText1.getText().toString();
            st2 = editText2.getText().toString();
            st3 = editText3.getText().toString();
            st4 = editText4.getText().toString();
            st5 = editText5.getText().toString();
            st6 = editText6.getText().toString();
            st7 = editText7.getText().toString();
            st8 = editText8.getText().toString();
            if (st1.equals("")|| st2.equals("")|| st3.equals("") || st4.equals("")|| st5.equals("")|| st6.equals("")|| st7.equals("")|| st8.equals("")) {
                Context context = getApplicationContext();
                CharSequence text = "入力していない項目があります！";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            } else {
                Intent intent = new Intent(Sample4.this, Sample5.class);
                startActivity(intent);
            }

        }
    }

    public void onBackButtonClick(View view){
        finish();
    }


}