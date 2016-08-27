package ws.feras.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // just create a new Object for our game engine.
    // the constructor take one argument which is our current Context.
    public TicTacToe newGame;

    public void startGame(View view)
    {
        //here we go :))
        newGame.stateChange(view);

    }

    @Override
    public void recreate() {
        super.recreate();
    }

    public void newGame(View view){
        recreate();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newGame = new TicTacToe(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.newGameBtn:
                newGame(getCurrentFocus());
        }
    return true;
    }
}
