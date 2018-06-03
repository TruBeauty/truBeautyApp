package srilanka.sliit.apps.trubeauty;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Splash_Screen extends Activity {

    sqLite_DB db;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash__screen);

        db=new sqLite_DB(getApplicationContext());

        Cursor data=db.getUserTable();

        number="+947";

        if (data != null){
            data.moveToFirst();

            number=data.getString(1);

        }

        Thread thread1=new Thread(){
            @Override
            public void run() {
                try{


                    sleep(2000);

                    if(number.equals("+947")) {

                        Intent intent = new Intent(Splash_Screen.this, Welcome_Screen_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{

                        Intent intent1 = new Intent(Splash_Screen.this, MainActivity.class);
                        startActivity(intent1);
                        finish();

                    }


                } catch (InterruptedException e) {

                    AlertDialog.Builder adb = new AlertDialog.Builder(Splash_Screen.this);

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
        };

        thread1.start();

    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
