package es.exsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Sample2 extends AppCompatActivity {

        static String unit_price;

        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_3d_5);
                Button button2 = findViewById(R.id.button2);
                Button button3 = findViewById(R.id.B_button);
                button2.setOnClickListener(new Sample2.button2ClickListener());
                button3.setOnClickListener(new Sample2.button4ClickListener());
                Intent intent = this.getIntent();
                String image = intent.getStringExtra("image");
                String name = intent.getStringExtra("name");
                unit_price = intent.getStringExtra("price");

                ImageView imageView = findViewById(R.id.imageView);
                int res_id = getResources().getIdentifier(image, "drawable", getPackageName());
                imageView.setImageResource(res_id);

                TextView textView1 = findViewById(R.id.textView1);
                textView1.setText(name);

                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText(unit_price);

                if (name.equals("座り心地の良い椅子")){
                        Button button1_1 = findViewById(R.id.button1);
                        button1_1.setOnClickListener(new Sample2.button1_1ClickListener());
                }else if  (name.equals("広々と使いやすい机")){
                        Button button1_2 = findViewById(R.id.button1);
                        button1_2.setOnClickListener(new Sample2.button1_2ClickListener());
                }else if  (name.equals("ぐっすり睡眠ベッド")){
                        Button button1_3 = findViewById(R.id.button1);
                        button1_3.setOnClickListener(new Sample2.button1_3ClickListener());
                }

        };

        private class button1_1ClickListener implements Button.OnClickListener {

                public void onClick(View v){
                        Intent intent = new Intent(Sample2.this, Sample6.class);
                        intent.putExtra("num","1");
                        startActivity(intent);
                }
        }

        private class button1_2ClickListener implements Button.OnClickListener {

                public void onClick(View v){
                        Intent intent = new Intent(Sample2.this, Sample6.class);
                        intent.putExtra("num","2");
                        startActivity(intent);
                }
        }

        private class button1_3ClickListener implements Button.OnClickListener {

                public void onClick(View v){
                        Intent intent = new Intent(Sample2.this, Sample6.class);
                        intent.putExtra("num","3");
                        startActivity(intent);
                }
        }

        private class button2ClickListener implements Button.OnClickListener {

                public void onClick(View v){
                        if (unit_price.equals("¥3,980")){
                                Sample3.price += 3980;
                                Sample3.count_isu += 1;
                        }else if  (unit_price.equals("¥7,980")){
                                Sample3.price += 7980;
                                Sample3.count_table += 1;
                        }else if  (unit_price.equals("¥29,980")){
                                Sample3.price += 29980;
                                Sample3.count_bed += 1;
                        }
                        Context context = getApplicationContext();
                        CharSequence text = "商品をカートに追加しました";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.TOP,0,0);
                        toast.show();
                }
        }

        private class button4ClickListener implements Button.OnClickListener {

                public void onClick(View v){
                        Intent intent = new Intent(Sample2.this, Sample1.class);
                        startActivity(intent);
                }
        }

}