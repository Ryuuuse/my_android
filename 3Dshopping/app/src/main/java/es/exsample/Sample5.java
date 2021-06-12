package es.exsample;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Sample5 extends AppCompatActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_3d_8);
		Button button1 = findViewById(R.id.button1);
		button1.setOnClickListener(new Sample5.button1ClickListener());
	}


	private class button1ClickListener implements Button.OnClickListener {

		public void onClick(View v){
			Sample3.price = 0;
			Sample3.count_isu = 0;
			Sample3.count_table = 0;
			Sample3.count_bed = 0;
			Intent intent = new Intent(Sample5.this, ExSample.class);
			startActivity(intent);

		}
	}

	public void onBackButtonClick(View view){
		finish();
	}


}