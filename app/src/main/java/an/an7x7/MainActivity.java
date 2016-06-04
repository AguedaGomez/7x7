package an.an7x7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import an.an7x7.GameCore.Game7x7;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTestGrid(View view){
        Intent intent = new Intent(this, Game7x7.class);
        startActivity(intent);
    }
}
