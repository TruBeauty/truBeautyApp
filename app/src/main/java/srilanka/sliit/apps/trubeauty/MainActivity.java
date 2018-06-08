package srilanka.sliit.apps.trubeauty;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    sqLite_DB db;
    String name,number,email,gender;
    Button btn;

    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new sqLite_DB(getApplicationContext());

        Cursor data=db.getUserTable();

        tv1=(TextView)findViewById(R.id.tv_welcome);
        tv2=(TextView)findViewById(R.id.tv_details);
        btn=(Button)findViewById(R.id.btn_loginback);


        if (data != null){
            data.moveToFirst();

            number=data.getString(1);
            name=data.getString(2);
            email=data.getString(3);
            gender=data.getString(4);

        }

        tv1.setText("Welcome "+name+"!");
        tv2.setText("UserID : "+number
        +"\nEmail : "+email
        +"\nGender : "+gender);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Welcome_Screen_Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);

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

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
            setupViewPager(viewPager);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            tabLayout.setupWithViewPager(viewPager);//setting tab over viewpager
        }

        //Setting View Pager
        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFrag(new Register_Salon(), "services");
            viewPager.setAdapter(adapter);
        }


    }
