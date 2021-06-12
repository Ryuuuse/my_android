package es.exsample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Sample3 extends AppCompatActivity {

    static int price = 0;
    static int count_isu,count_table,count_bed = 0;
    TextView textView1;
    LinearLayout ll,ll2,ll3,ll4;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    List<TextView> textViewlist1 = new ArrayList();
    List<TextView> textViewlist2 = new ArrayList();
    List<TextView> textViewlist3 = new ArrayList();

    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d_6);

        RadioGroup radioGroup = findViewById(R.id.RadioGroup);
        radioGroup.check(R.id.RadioButton1);

        ll = findViewById(R.id.LinearLayout);
        ll.setGravity(Gravity.CENTER);
        ll2 = new LinearLayout(this);
        ll3 = new LinearLayout(this);
        ll4 = new LinearLayout(this);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new button1ClickListener());
        String total = "¥" + price;
        textView1 = findViewById(R.id.textView2);
        textView1.setText(total);

        Drawable btn_color1 = ResourcesCompat.getDrawable(getResources(), R.drawable.custom_button6, null);
        Drawable btn_color2 = ResourcesCompat.getDrawable(getResources(), R.drawable.custom_button7, null);

        if (count_isu > 0) {
            ll2.setGravity(Gravity.CENTER);
            ll.addView(ll2, new LinearLayout.LayoutParams(WC, WC));
            TextView textView = new TextView(this);
            textView.setText("座り心地の良い椅子          ");
            ll2.addView(textView, new LinearLayout.LayoutParams(WC, WC));
            Button isu_counter1 = new Button(this);
            isu_counter1.setBackground(btn_color2);
            isu_counter1.setTextColor(0xffffffff);
            isu_counter1.setText("-1");
            isu_counter1.setOnClickListener(new isu_counter1ClickListener());
            ll2.addView(isu_counter1, new LinearLayout.LayoutParams(150, 150));
            TextView textView2_1 = new TextView(this);
            textView2_1.setText("       " + count_isu + "       ");
            textViewlist1.add(textView2_1);
            ll2.addView(textView2_1, new LinearLayout.LayoutParams(WC, WC));
            Button isu_counter2 = new Button(this);
            isu_counter2.setBackground(btn_color1);
            isu_counter2.setTextColor(0xffffffff);
            isu_counter2.setText("+1");
            isu_counter2.setOnClickListener(new isu_counter2ClickListener());
            ll2.addView(isu_counter2, new LinearLayout.LayoutParams(150, 150));
        }
        if (count_table > 0) {
            ll3.setGravity(Gravity.CENTER);
            ll.addView(ll3, new LinearLayout.LayoutParams(WC, WC));
            TextView textView = new TextView(this);
            textView.setText("広々と使いやすい机          ");
            ll3.addView(textView, new LinearLayout.LayoutParams(WC, WC));
            Button table_counter1 = new Button(this);
            table_counter1.setBackground(btn_color2);
            table_counter1.setTextColor(0xffffffff);
            table_counter1.setText("-1");
            table_counter1.setOnClickListener(new table_counter1ClickListener());
            ll3.addView(table_counter1, new LinearLayout.LayoutParams(150, 150));
            TextView textView2_2 = new TextView(this);
            textView2_2.setText("       " + count_table + "       ");
            textViewlist2.add(textView2_2);
            ll3.addView(textView2_2, new LinearLayout.LayoutParams(WC, WC));
            Button table_counter2 = new Button(this);
            table_counter2.setBackground(btn_color1);
            table_counter2.setTextColor(0xffffffff);
            table_counter2.setText("+1");
            table_counter2.setOnClickListener(new table_counter2ClickListener());
            ll3.addView(table_counter2, new LinearLayout.LayoutParams(150, 150));
        }
        if (count_bed > 0) {
            ll4.setGravity(Gravity.CENTER);
            ll.addView(ll4, new LinearLayout.LayoutParams(WC, WC));
            TextView textView = new TextView(this);
            textView.setText("ぐっすり睡眠ベッド          ");
            ll4.addView(textView, new LinearLayout.LayoutParams(WC, WC));
            Button bed_counter1 = new Button(this);
            bed_counter1.setBackground(btn_color2);
            bed_counter1.setTextColor(0xffffffff);
            bed_counter1.setText("-1");
            bed_counter1.setOnClickListener(new bed_counter1ClickListener());
            ll4.addView(bed_counter1, new LinearLayout.LayoutParams(150, 150));
            TextView textView2_3 = new TextView(this);
            textView2_3.setText("       " + count_bed + "       ");
            textViewlist3.add(textView2_3);
            ll4.addView(textView2_3, new LinearLayout.LayoutParams(WC, WC));
            Button bed_counter2 = new Button(this);
            bed_counter2.setBackground(btn_color1);
            bed_counter2.setTextColor(0xffffffff);
            bed_counter2.setText("+1");
            bed_counter2.setOnClickListener(new bed_counter2ClickListener());
            ll4.addView(bed_counter2, new LinearLayout.LayoutParams(150, 150));
        }
    };

    private class button1ClickListener implements Button.OnClickListener {

        public void onClick(View v){
            if(price>0){
                Intent intent = new Intent(Sample3.this, Sample4.class);
                startActivity(intent);
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "カートに商品がありません！";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
            }
        }
    }

    private class isu_counter1ClickListener implements Button.OnClickListener {

        public void onClick(View v){
            if (count_isu > 0) {
                count_isu -= 1;
                textViewlist1.get(0).setText("       " + count_isu + "       ");
                price -= 3980;
                String total = "¥" + price;
                textView1.setText(total);
            }


        }
    }

    private class isu_counter2ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            count_isu += 1;
            textViewlist1.get(0).setText("       " + count_isu + "       ");
            price += 3980;
            String total = "¥" + price;
            textView1.setText(total);

        }
    }

    private class table_counter1ClickListener implements Button.OnClickListener {

        public void onClick(View v){
            if (count_table > 0) {
                count_table -= 1;
                textViewlist2.get(0).setText("       " + count_table + "       ");
                price -= 7980;
                String total = "¥" + price;
                textView1.setText(total);
            }
        }
    }

    private class table_counter2ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            count_table += 1;
            textViewlist2.get(0).setText("       " + count_table + "       ");
            price += 7980;
            String total = "¥" + price;
            textView1.setText(total);
        }
    }

    private class bed_counter1ClickListener implements Button.OnClickListener {

        public void onClick(View v){
            if (count_bed > 0) {
                count_bed -= 1;
                textViewlist3.get(0).setText("       " + count_bed + "       ");
                price -= 29980;
                String total = "¥" + price;
                textView1.setText(total);
            }
        }
    }

    private class bed_counter2ClickListener implements Button.OnClickListener {

        public void onClick(View v) {
            count_bed += 1;
            textViewlist3.get(0).setText("       " + count_bed + "       ");
            price += 29980;
            String total = "¥" + price;
            textView1.setText(total);
        }
    }

    public void onBackButtonClick(View view){
        finish();
    }


}