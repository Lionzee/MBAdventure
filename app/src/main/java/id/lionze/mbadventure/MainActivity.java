package id.lionze.mbadventure;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Lionze on 27 Okt 2017.
 */

public class MainActivity extends Activity{

    PlayerStatus playerStatus;
    MonsterStatus monsterStatus;
    public Button myClick;
    public Context context;
    ProgressBar progressBar;
    Boolean isRunning = true;
    Double hpBarScale;
    private RelativeLayout onClick;
    public ImageView head,body,lefthand,righthand,foot;
    public TextView hpDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        context = MainActivity.this;
        playerStatus = new PlayerStatus();
        monsterStatus = new MonsterStatus(MainActivity.this);
        hpDisplay = (TextView) findViewById(R.id.hpDisplay);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        onClick = (RelativeLayout) findViewById(R.id.click_layout) ;
        head = (ImageView) findViewById(R.id.ivHead);
        body = (ImageView) findViewById(R.id.ivBody);
        lefthand = (ImageView) findViewById(R.id.ivLeftHand);
        righthand = (ImageView) findViewById(R.id.ivRightHand);
        foot = (ImageView) findViewById(R.id.ivFoot);

        int id = getResources().getIdentifier("id.lionze.mbadventure:drawable/dummy", null, null);
        head.setImageResource(id);

        // --------------------------------------------------

        startTimerThread();
        hpBarScale = (monsterStatus.getCurrentHP() / monsterStatus.getMonsterHP())*100;

        progressBar.setProgress(hpBarScale.intValue());

        /*myClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monsterStatus.onClick(playerStatus.getClickDamage());
                hpBarScale = (monsterStatus.getCurrentHP() / monsterStatus.getMonsterHP())*100;
                progressBar.setProgress(hpBarScale.intValue());
                Log.i("HPScale",Integer.toString(hpBarScale.intValue()));

            }
        });*/

        onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monsterStatus.onClick(playerStatus.getClickDamage());
                hpBarScale = (monsterStatus.getCurrentHP() / monsterStatus.getMonsterHP())*100;
                progressBar.setProgress(hpBarScale.intValue());
                Log.i("HPScale",Integer.toString(hpBarScale.intValue()));
            }
        });

    }

    private void startTimerThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            private long startTime = System.currentTimeMillis();
            public void run() {
                while (isRunning) {
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        public void run() {
                            hpDisplay.setText(monsterStatus.hpUpdate());
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }
}

