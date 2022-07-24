package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView playerOneScore,playerTwoScore,playerStatus,pp1,pp2;
    private Button[] buttons=new Button[9];
    private Button resetGame;
    private int playerOneScoreCount,playerTwoScoreCount,rountCount;
    boolean activePlayer;
    String s1="",s2="";
    int gameState[]={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore =(TextView) findViewById(R.id.playerOneScore);
        playerTwoScore =(TextView) findViewById(R.id.playerTwoScore);
        playerStatus= (TextView) findViewById(R.id.playerStatus);
        resetGame=(Button) findViewById(R.id.resetGame);
        pp1=findViewById(R.id.playerOne);
        pp2=findViewById(R.id.playerTwo);
        Bundle extras=getIntent().getExtras();
        if(extras!=null) {
            s1=extras.getString("p1");
            s2=extras.getString("p2");
            pp1.setText(s1);
            pp2.setText(s2);
        }
        for(int i=0;i<9;i++){
            String s1="btn_"+i;
            int s=getResources().getIdentifier(s1,"id",getPackageName());
            buttons[i]=(Button) findViewById(s);
            buttons[i].setOnClickListener(this);
        }
        rountCount=0;
        playerOneScoreCount=0;
        playerTwoScoreCount=0;
        activePlayer=true;
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerStatus.setText("");
                playerOneScoreCount=0;
                playerTwoScoreCount=0;
                updatePlayerScore();
                playAgain();
            }
        });


    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")) return;
        String buttonId=v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer=Integer.parseInt(buttonId.substring(buttonId.length()-1,buttonId.length()));
        if(activePlayer){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#5800FF"));
            gameState[gameStatePointer]=0;
        }
        else{
            ((Button)v).setText("o");
            ((Button)v).setTextColor(Color.parseColor("#EF5B0C"));
            gameState[gameStatePointer]=1;
        }
        rountCount++;
        if(checkWinner()){
            if(activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, s1 +" Won!", Toast.LENGTH_SHORT).show();
            }
            else{
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, s2 +" Won!", Toast.LENGTH_SHORT).show();
            }
            playAgain();
        }
        else if(rountCount==9){
            playAgain();
            Toast.makeText(this, "No Winner!", Toast.LENGTH_SHORT).show();
        }
        else activePlayer=!activePlayer;

        if(playerOneScoreCount>playerTwoScoreCount) playerStatus.setText(s1+" is Winning");
        else if(playerOneScoreCount<playerTwoScoreCount) playerStatus.setText(s2+" is Winning");
        else playerStatus.setText("");
    }
    public boolean checkWinner(){
        boolean winnerResult=false;
        for(int a[]:winningPositions){
            if(gameState[a[0]]==gameState[a[1]] && gameState[a[0]]==gameState[a[2]] && gameState[a[0]]!=2){
                winnerResult=true;
            }
        }
        return winnerResult;
    }

    public  void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain(){
        rountCount=0;
        activePlayer=true;
        for(int i=0;i<buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
    }
}