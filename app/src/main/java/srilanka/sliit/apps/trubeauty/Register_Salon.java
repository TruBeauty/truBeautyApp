package srilanka.sliit.apps.trubeauty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Register_Salon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__salon);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Login_or_Signup.class);
        startActivity(intent);
        finish();
    }
}
