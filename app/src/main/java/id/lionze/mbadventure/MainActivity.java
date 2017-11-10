package id.lionze.mbadventure;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Lionze on 27 Okt 2017.
 */

public class MainActivity extends Activity {

    PlayerStatus playerStatus;
    MonsterStatus monsterStatus;
    public Button myClick;
    public Context context;
    ProgressBar progressBar;
    Double hpBarScale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        context = MainActivity.this;
        playerStatus = new PlayerStatus();
        monsterStatus = new MonsterStatus(MainActivity.this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        myClick = (Button) findViewById(R.id.btnClick);

        hpBarScale = (monsterStatus.getCurrentHP() / monsterStatus.getMonsterHP())*100;

        progressBar.setProgress(hpBarScale.intValue());

        myClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monsterStatus.onClick(playerStatus.getClickDamage());
                hpBarScale = (monsterStatus.getCurrentHP() / monsterStatus.getMonsterHP())*100;
                progressBar.setProgress(hpBarScale.intValue());
                Log.i("HPScale",Integer.toString(hpBarScale.intValue()));

            }
        });

    }
}

class GameView extends SurfaceView implements Runnable{

    MainActivity mainActivity;


    public GameView(Context context) {
        super(context);


    }

    @Override
    public void run() {

    }
}
