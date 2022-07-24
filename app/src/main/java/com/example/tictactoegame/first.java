package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class first extends AppCompatActivity {
    EditText player1,player2;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        player1=findViewById(R.id.pl1);
        player2=findViewById(R.id.pl2);
        b=findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player1.getText().toString().isEmpty()){
                    player1.setError("Required!");
                    return;
                }
                if(player2.getText().toString().isEmpty()){
                    player2.setError("Required!");
                    return;
                }
                Intent abs=new Intent(first.this,MainActivity.class);
                abs.putExtra("p1",player1.getText().toString());
                abs.putExtra("p2",player2.getText().toString());
                startActivity(abs);
            }
        });

    }
}