package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    static final String FirstUser="com.example.tictactoe.MainActivity.firstUser";
    static final String SecondUser="com.example.tictactoe.MainActivity.SecondUser";
    public void startGame(View view)
    {
        Intent i=new Intent(this,GameActivity.class);
        EditText first=findViewById(R.id.first);
        EditText second=findViewById(R.id.second);
        String  firstUser=first.getText().toString();
        String secondUser=second.getText().toString();
        i.putExtra(FirstUser,firstUser);
        i.putExtra(SecondUser,secondUser);
        startActivity(i);
    }
}
