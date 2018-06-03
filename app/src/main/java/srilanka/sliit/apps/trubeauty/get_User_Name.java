package srilanka.sliit.apps.trubeauty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class get_User_Name extends AppCompatActivity {

    EditText et1, et2;
    RadioButton rbn1, rbn2;
    Button btn;
    String number;
    String gen="Female";
    DatabaseReference dr;
    sqLite_DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_name);
        try {
            et1 = (EditText) findViewById(R.id.ed_name);
            et2 = (EditText) findViewById(R.id.ed_email);
            rbn1 = (RadioButton) findViewById(R.id.rbn_female);
            rbn2 = (RadioButton) findViewById(R.id.rbn_male);
            btn = (Button) findViewById(R.id.btn_getUser);


            db=new sqLite_DB(getApplicationContext());

            dr = FirebaseDatabase.getInstance().getReference();

String temp=getIntent().getStringExtra("Name");
    if(!(temp.equals(" "))) {
        et1.setText(temp);
    }

            temp=getIntent().getStringExtra("Email");
            if(!(temp.equals(" "))) {
                et2.setText(temp);
            }



            number = getIntent().getStringExtra("Number");

            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {

                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setTitle("  Your User ID  "+number);
            }


            gen=getIntent().getStringExtra("Gender");

            if (gen.equals("Female")) {
                rbn1.setChecked(true);
                rbn2.setChecked(false);
            } else {
                rbn1.setChecked(false);
                rbn2.setChecked(true);
            }


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String gen=(rbn1.isChecked())?"Female":"Male";

                    dr.child("Profile").child(number).child("Number").setValue(number);
                    dr.child("Profile").child(number).child("Name").setValue(et1.getText().toString());
                    dr.child("Profile").child(number).child("Email").setValue(et2.getText().toString());
                    dr.child("Profile").child(number).child("Gender").setValue(gen);

                    db.UpdateUserTable(et1.getText().toString(),number,et2.getText().toString(),gen);



                    Intent intent1 = new Intent(get_User_Name.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
            });

        } catch (Exception e) {
            et1.setText(e.getMessage());

        }
    }
}
