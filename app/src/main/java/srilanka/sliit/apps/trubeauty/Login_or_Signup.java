package srilanka.sliit.apps.trubeauty;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class Login_or_Signup extends Activity {

    CardView cv1;
    TextView tv1,tv2;
    EditText ed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_or_signup);



try {
  if (Build.VERSION.SDK_INT >= 21) {

        getWindow().setStatusBarColor(getResources().getColor(R.color.statusBar1));//status bar
    }

    cv1 = (CardView) findViewById(R.id.cv_login);
    ed = (EditText) findViewById(R.id.ed_username);
    tv1 = (TextView) findViewById(R.id.tv_error);
    tv2 = (TextView) findViewById(R.id.tv_salon);




    cv1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String no=ed.getText().toString();

            int len=no.length();

            if(len==10 || len==12) {

                tv1.setText("valid number");


                ed.setText(" "+numbers(no,len));

                Intent intent1 = new Intent(Login_or_Signup.this, UserSignUp.class);
                intent1.putExtra("NO", no);
                startActivity(intent1);
                finish();
            }else{

                tv1.setText("Please enter a valid number");



            }
        }
    });


    tv2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent1 = new Intent(Login_or_Signup.this, Register_Salon.class);
            startActivity(intent1);
            finish();
        }
    });

    }catch (Exception e){
        AlertDialog.Builder adb = new AlertDialog.Builder(Login_or_Signup.this);

        adb.setTitle("Error!").setMessage("\nOops, Something went wrong :( \n "+e.getMessage())
                .setCancelable(false)
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

    }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder adb = new AlertDialog.Builder(Login_or_Signup.this);

        adb.setTitle("Exit").setMessage("\n Are you sure you want to Exit?")
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
//+9475 099 9660
    private String numbers(String s,int i){
    if(i==10)
           return s.substring(0,3)+" "+s.substring(3,6)+" "+s.substring(6);
    else
        return s.substring(0,5)+" "+s.substring(5,8)+" "+s.substring(8);

    }
}
