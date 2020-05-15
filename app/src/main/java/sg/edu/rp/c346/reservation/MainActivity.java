package sg.edu.rp.c346.reservation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etNum, etSize;
    Button btnSubmit;
    RadioGroup rg;
    String SN;
    TimePicker tp;
    DatePicker dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etNum = findViewById(R.id.etNum);
        etSize = findViewById(R.id.etSize);
        rg = findViewById(R.id.rg);
        btnSubmit = findViewById(R.id.btnSubmit);
        tp = findViewById(R.id.timePicker);
        dp = findViewById(R.id.datePicker);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, final int hourOfDay, int i1) {
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (hourOfDay < 8 || hourOfDay > 21) {
                            Toast.makeText(MainActivity.this, "Please pick a time between 8am to 8:59pm", Toast.LENGTH_LONG).show();
                            Log.i("TIme", "Over");
                        } else {
                            String Name = etName.getText().toString();
                            String Num = etNum.getText().toString();
                            String Size = etSize.getText().toString();
                            int selectedButtonId = rg.getCheckedRadioButtonId();
                            // Get the radio button object from the Id we had gotten above
                            if (rg.getCheckedRadioButtonId() == -1) {
                                SN = "";
                            } else {
                                RadioButton rb = (RadioButton) findViewById(selectedButtonId);
                                SN = rb.getText().toString();
                            }

                            if (Name.contentEquals("")) {
                                Toast.makeText(MainActivity.this, "Please input your Name", Toast.LENGTH_LONG).show();
                                if (Num.contentEquals("")) {
                                    if (Size.contentEquals("")) {
                                        if (SN.contentEquals("")) {
                                            Toast.makeText(MainActivity.this, "Please input all fields", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            } else if (Num.contentEquals("")) {
                                Toast.makeText(MainActivity.this, "Please input your Number", Toast.LENGTH_LONG).show();
                            } else if (Size.contentEquals("")) {
                                Toast.makeText(MainActivity.this, "Please input group size", Toast.LENGTH_LONG).show();
                            } else if (Num.contentEquals("")) {
                                Toast.makeText(MainActivity.this, "Please input your Number", Toast.LENGTH_LONG).show();
                            } else if (SN.contentEquals("")) {
                                Toast.makeText(MainActivity.this, "Please Select Smoking or no", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "You have made a reservation of " + SN + " table for " + etSize.getText().toString().trim() + " pax for " + dp.getDayOfMonth() + "/" + dp.getMonth() + "/" + dp.getYear() + ", " + tp.getCurrentHour() + ":" + tp.getCurrentMinute(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });
    }
}