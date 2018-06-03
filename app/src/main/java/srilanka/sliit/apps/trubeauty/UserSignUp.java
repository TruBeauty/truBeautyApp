package srilanka.sliit.apps.trubeauty;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserSignUp extends AppCompatActivity {

    TextView tv;
    Button btn;
    String number;
    EditText ed;
    TextView ed1;
    DatabaseReference  dr, dref;
    Boolean oldUser=false;

    String name=" ", email=" ", gender=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        try{




        number=getIntent().getStringExtra("NO");

            tv=(TextView)findViewById(R.id.tv_msg);
            ed=(EditText) findViewById(R.id.ed_pin);
            ed1=(TextView) findViewById(R.id.ed_view);
            btn=(Button)findViewById(R.id.btn_submit);

        if(number.charAt(0)=='0') {
            number="+94"+number.substring(1);
        }


        dr=FirebaseDatabase.getInstance().getReference().child("Profile");


        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(number).exists()) {


                    dr = FirebaseDatabase.getInstance().getReference().child("Profile").child(number);

                    dr.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            name = dataSnapshot.child("Name").getValue().toString();
                            email = dataSnapshot.child("Email").getValue().toString();
                            gender = dataSnapshot.child("Gender").getValue().toString();

                            Toast.makeText(getApplicationContext(), "Welcome Back! \n" + name, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                else{
                    Toast.makeText(getApplicationContext(),"New User", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });










        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
int len=ed.length();
ed1.setText("Enter 6 digit code : "+(len+1)+"/6");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });






        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("  Verify "+numbers(number));
        }



        tv.setText("Enter the 6-digit code, sent to \n"+numbers(number)+"\n (Testing Version : Enter any 6 numbers)");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len=ed.length();
                if(len==6) {
                    Intent intent1 = new Intent(UserSignUp.this, get_User_Name.class);
                    intent1.putExtra("Name", name);
                    intent1.putExtra("Number", number);
                    intent1.putExtra("Email", email);
                    intent1.putExtra("Gender", gender);
                    startActivity(intent1);
                    finish();
                }
                else{
                    ed1.setText("Please enter any 6 numbers");
                }
            }
        });

}catch (Exception e){
    tv.setText(e.getMessage());


            AlertDialog.Builder adb = new AlertDialog.Builder(UserSignUp.this);

            adb.setTitle("Exit").setMessage("\n "+e.getMessage())
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog alertDialog = adb.create();
            alertDialog.show();
}

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Login_or_Signup.class);
        startActivity(intent);
        finish();
    }

    private String numbers(String s){

            return s.substring(0,5)+" "+s.substring(5,8)+" "+s.substring(8);

    }


}
