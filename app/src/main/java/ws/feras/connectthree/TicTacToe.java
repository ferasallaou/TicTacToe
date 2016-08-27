package ws.feras.connectthree;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;


/**
 * Created by Feras on 8/26/16.
 */
public class TicTacToe {
    /* counter to know how many clicks has been done,
     max should be 9 clicks to fill the whole board.
    */
    private byte counter;
    /* WhoTurn is refering the Player.
    Player1 is false, and he is using X.
    Player2 is true, and he is using O.
    */
    private boolean whosTurn;

    // just passing the Context;
    private Context mainContext;

    // is the steps over || someone has won?
    private boolean isTerminated;

    // the media player :)
    MediaPlayer mPlayer;

    // what are the possible cases to win a tic tac toe?
    public int[][] winningScenarios = {
            {0,1,2}, {3,4,5},
            {6,7,8}, {0,3,6},
            {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };

    //gamestate is a simple array to hold each player and his clicks.
    //-1 means no click yet in that position.
    public int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    public TicTacToe(Context mC)
    {
        this.counter = 1;
        this.whosTurn = false;
        this.mainContext = mC;
        this.isTerminated = false;
        this.mPlayer  = MediaPlayer.create(mC, R.raw.x);

    }

    public int[][] getWinningScenarios()
    {
        return this.winningScenarios;
    }

    public byte getCounter()
    {
        return this.counter;
    }

    public boolean getWhosTurn()
    {
        return this.whosTurn;
    }

    public void stateChange(View view)
    {
        if (counter < 10 && isTerminated != true) {
             if (view.getAlpha() == 0.0f) {
                if (this.whosTurn == false) {
                    playSound(R.raw.x);
                    view.setBackgroundResource(R.drawable.x);
                    view.animate().alpha(1f).setDuration(2000);
                    this.whosTurn = true;
                    counter++;
                    addClick(view, false);
                } else {
                    playSound(R.raw.o);
                    view.setBackgroundResource(R.drawable.o);
                    view.animate().alpha(1f).setDuration(2000);
                    this.whosTurn = false;
                    counter++;
                    addClick(view, true);
                }
            } else {
                 Toast.makeText(this.mainContext, R.string.already_pressed, Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this.mainContext, R.string.game_finished, Toast.LENGTH_SHORT).show();

        }
    }

    public void addClick(View view, boolean turn)
    {
        int currentBtn = -1;
        switch ( view.getId())
        {
            case R.id.btn1:
                currentBtn = 0;
                break;
            case R.id.btn2:
                currentBtn = 1;
                break;
            case R.id.btn3:
                currentBtn = 2;
                break;
            case R.id.btn4:
                currentBtn = 3;
                break;
            case R.id.btn5:
                currentBtn= 4;
                break;
            case R.id.btn6:
                currentBtn = 5;
                break;
            case R.id.btn7:
                currentBtn = 6;
                break;
            case R.id.btn8:
                currentBtn = 7;
                break;
            case R.id.btn9:
                currentBtn = 8;
                break;

        }
        if (turn == false)
        {
            this.gameState[currentBtn] = 0;

        }else{
            this.gameState[currentBtn] = 1;
        }
        checkWinningState(this.gameState, turn);


    }

    public void checkWinningState(int[] gameStateArr, boolean turn)
        {
            for (int[] singleScenario : winningScenarios)
            {
                if (gameStateArr[singleScenario[0]] == gameStateArr[singleScenario[1]]
                        && gameStateArr[singleScenario[1]] == gameStateArr[singleScenario[2]]
                        && gameStateArr[singleScenario[0]] != -1)
                {
                    this.isTerminated = true;
                    playSound(R.raw.win);
                    if (turn == false)
                    {

                      Toast.makeText(mainContext, R.string.first_player_won, Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(mainContext, R.string.second_player_won, Toast.LENGTH_SHORT).show();
                    }

                }
            }
         }

    public void playSound(int src)
    {
        mPlayer.reset();
        mPlayer.release();
        mPlayer = MediaPlayer.create(this.mainContext, src);
        mPlayer.start();

    }
}
