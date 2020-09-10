package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends AppCompatActivity {
    //1 for x or first player
    //0 for o or second player
    static final String WIN="com.example.tictactoe.GameActivity.WIN";
    String firstUser,secondUser;
    String Draw="Match Draw !!!";
    public int activePlayer=1;
    public int win=0;
    public boolean reset=false;
    private int []allPosition={2,2,2,2,2,2,2,2,2};
    private int [][]winningPositions={{0,1,2},{3,4,5},{6,7,8},
                                     {0,3,6},{1,4,7},{2,5,8},
                                     {0,4,8},{2,4,6}};
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
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intentData =getIntent();
        if(intentData!=null)
        {
            firstUser=intentData.getStringExtra(MainActivity.FirstUser);
            secondUser=intentData.getStringExtra(MainActivity.SecondUser);
            if(firstUser.length()<1)
            {
                assert secondUser != null;
                if(secondUser.length()>0)
                {
                    firstUser="First Player";
                    Toast.makeText(GameActivity.this,"First Player are not Set",Toast.LENGTH_SHORT).show();
                }
                else
                {

                        firstUser="First Player";
                        secondUser="Second Player";
                        Toast.makeText(GameActivity.this,"Both Player are not Set",Toast.LENGTH_SHORT).show();

                }
            }
            assert secondUser != null;
            if(secondUser.length()<1)
            {
                if(firstUser.length()>0)
                {
                    secondUser="Second Player";
                    Toast.makeText(GameActivity.this,"Second Player are not Set",Toast.LENGTH_SHORT).show();
                }
                else
                {

                    firstUser="First Player";
                    secondUser="Second Player";
                    Toast.makeText(GameActivity.this,"Both Player are not Set",Toast.LENGTH_SHORT).show();

                }
            }
            TextView fighting=findViewById(R.id.fiter);
            fighting.setText(firstUser+" v/s "+secondUser);
            TextView status=findViewById(R.id.trun);
            status.setText(firstUser+"'s Turn");
        }
        else
        {
            TextView fighting=findViewById(R.id.fiter);
            fighting.setText(firstUser+" v/s "+secondUser);
            TextView status=findViewById(R.id.trun);
            status.setText(firstUser+"'s Turn");

        }
    }

    @SuppressLint("SetTextI18n")
    public void playToTap(View view)
    {
        ImageView reader=(ImageView) view;
        int result=Integer.parseInt(reader.getTag().toString());
        if(!reset)
        {
            //for fill the grid
            if(allPosition[result]==2)
            {
                allPosition[result]=activePlayer;
                reader.setTranslationY(-100f);
                if(activePlayer==1)
                {
                    reader.setImageResource(R.drawable.x);
                    TextView status=findViewById(R.id.trun);
                    status.setText(secondUser+"'s Turn");
                    activePlayer=0;
                }
                else
                {
                    reader.setImageResource(R.drawable.o);
                    TextView status=findViewById(R.id.trun);
                    status.setText(firstUser+"'s Turn");
                    activePlayer=1;
                }
                reader.animate().translationY(1f).setDuration(300);
            }
            // for check anyone has won
            for(int [] winPosition:winningPositions)
            {
                if(allPosition[winPosition[0]]==allPosition[winPosition[1]]&&
                        allPosition[winPosition[1]]==allPosition[winPosition[2]]&&
                        allPosition[winPosition[0]]!=2)
                {
                    if(allPosition[winPosition[0]]==1)
                    {
                        reset=true;
                        Intent w=new Intent(this,Main2Activity.class);
                        String winner=firstUser+" Has Won!!!";
                        w.putExtra(WIN,winner);
                        startActivity(w);
                        win=1;
                    }
                    else
                    {
                        reset=true;
                        Intent w=new Intent(this,Main2Activity.class);
                        String winner=secondUser+ " Has Won!!!";
                        w.putExtra(WIN,winner);
                        startActivity(w);
                        win=1;
                    }
                }
            }
            int flag=0;
            for(int i=0;i<=8;i++)
            {
                if(allPosition[i]==2)
                    {
                        flag=1;
                        break;
                    }

            }
            if(flag==0&& win!=1)
            {
                reset=true;
                Intent w=new Intent(this,Main2Activity.class);
                w.putExtra(WIN,Draw);
                startActivity(w);
            }

        }
        else
        {
            //checking purpose
            TextView status=findViewById(R.id.trun);
            status.setText("Something gone Wrong");
        }
    }
    public void resetGame()
    {
        reset=false;
        activePlayer=1;
        for(int i=0;i<9;i++)
            allPosition[i]=2;
        ((ImageView) findViewById(R.id.c1)).setImageResource(0);
        ((ImageView) findViewById(R.id.c2)).setImageResource(0);
        ((ImageView) findViewById(R.id.c3)).setImageResource(0);
        ((ImageView) findViewById(R.id.c4)).setImageResource(0);
        ((ImageView) findViewById(R.id.c5)).setImageResource(0);
        ((ImageView) findViewById(R.id.c6)).setImageResource(0);
        ((ImageView) findViewById(R.id.c7)).setImageResource(0);
        ((ImageView) findViewById(R.id.c8)).setImageResource(0);
        ((ImageView) findViewById(R.id.c9)).setImageResource(0);

    }
}
