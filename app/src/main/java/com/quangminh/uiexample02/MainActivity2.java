package com.quangminh.uiexample02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity2 extends AppCompatActivity {

    ImageButton button;
    EditText date, hoten, phone;
    String account ;
    String passww;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky_2);

        date = findViewById(R.id.edsn);
        hoten = findViewById(R.id.hotendk);
        phone = findViewById(R.id.phonedk);

        button = findViewById(R.id.next2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            account = bundle.getString("account");
            passww = bundle.getString("pass");
        }


        DateFormat df = new SimpleDateFormat("dd-MMM-yy");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();

        final long today = MaterialDatePicker.todayInUtcMilliseconds();

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a Date");
        builder.setSelection(today);

        final MaterialDatePicker materialDatePicker = builder.build();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }


        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                date.setText(materialDatePicker.getHeaderText());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this,MainActivity3.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("acc", account );
                bundle1.putString("pass", passww );
                bundle1.putString("hoten", Hoten() );
                bundle1.putString("date", date() );
                bundle1.putString("phone1", phone());
                i.putExtras(bundle1);
                startActivity(i);
            }
        });
    }

    private String Hoten(){
        String name = hoten.getText().toString().trim();
        return name;

    }
    private String phone(){
        String phone1 = phone.getText().toString().trim();
        return phone1;
    }

    private  String date(){
        String date1 = date.getText().toString().trim();
        return date1;
    }
}