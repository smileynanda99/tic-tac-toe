package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    String winner;
    GameActivity obj=new GameActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent winnerData=getIntent();
        winner=winnerData.getStringExtra(GameActivity.WIN);
        TextView res=findViewById(R.id.result);
        res.setText(winner);
        res.animate().translationY(500f).setDuration(900);



    }
    public void restart(View view)
    {
        obj.resetGame();
        Intent b=new Intent(this,GameActivity.class);
        startActivity(b);
    }
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.developer)
            Toast.makeText(this,"Thanks For Visits",Toast.LENGTH_LONG).show();
        return  super.onOptionsItemSelected(item);
    }
}
