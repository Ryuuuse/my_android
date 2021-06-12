package es.exsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Sample1 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d_2);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        button1.setOnClickListener(new Sample1.button1ClickListener());
        button2.setOnClickListener(new Sample1.button2ClickListener());
        button3.setOnClickListener(new Sample1.button3ClickListener());
        button4.setOnClickListener(new Sample1.button4ClickListener());
        button5.setOnClickListener(new Sample1.button5ClickListener());
        button6.setOnClickListener(new Sample1.button6ClickListener());
        button7.setOnClickListener(new Sample1.button7ClickListener());
        button8.setOnClickListener(new Sample1.button8ClickListener());

    }

    ;


    private class button1ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            Intent intent = new Intent(Sample1.this, Sample2.class);
            intent.putExtra("image", "isu");
            intent.putExtra("name", "座り心地の良い椅子");
            intent.putExtra("price", "¥3,980");
            startActivity(intent);
        }
    }

    private class button2ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            Sample3.price += 3980;
            Sample3.count_isu += 1;

            Context context = getApplicationContext();
            CharSequence text = "商品をカートに追加しました";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show();
        }
    }

    private class button3ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            Intent intent = new Intent(Sample1.this, Sample2.class);
            intent.putExtra("image", "table");
            intent.putExtra("name", "広々と使いやすい机");
            intent.putExtra("price", "¥7,980");
            intent.putExtra("num", "2");
            startActivity(intent);
        }
    }


    private class button4ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            Sample3.price += 7980;
            Sample3.count_table += 1;

            Context context = getApplicationContext();
            CharSequence text = "商品をカートに追加しました";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show();
        }
    }

    private class button5ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            Intent intent = new Intent(Sample1.this, Sample2.class);
            intent.putExtra("image", "bed");
            intent.putExtra("name", "ぐっすり睡眠ベッド");
            intent.putExtra("price", "¥29,980");
            intent.putExtra("num", "3");
            startActivity(intent);
        }
    }

    private class button6ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            Sample3.price += 29980;
            Sample3.count_bed += 1;

            Context context = getApplicationContext();
            CharSequence text = "商品をカートに追加しました";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show();
        }
    }

    private class button7ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            Intent intent2 = new Intent(Sample1.this, Sample3.class);
            startActivity(intent2);


        }
    }

    private class button8ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            Sample3.price = 0;
            Sample3.count_isu= 0;
            Sample3.count_table=0;
            Sample3.count_bed=0;
            Intent intent2 = new Intent(Sample1.this, ExSample.class);
            startActivity(intent2);



        }
    }
}
