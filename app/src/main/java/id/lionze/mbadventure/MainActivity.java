package id.lionze.mbadventure;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * Created by Lionze on 27 Okt 2017.
 */

public class MainActivity extends Activity{

    PlayerStatus playerStatus;
    MonsterStatus monsterStatus;
    public Button myClick,myDD;
    public Context context;
    ProgressBar progressBar;
    Boolean isRunning = true;
    Double hpBarScale;
    private RelativeLayout onClick;
    public ImageView ivMonster;
    public TextView hpDisplay,levelDisplay,timeDisplay,stageDisplay,scoreDisplay;
    public double playerDamage;
    CountDownTimer bossTimer;
    boolean isDD = false;
    boolean isTimerActive = false;
    ImageView ivSlash;

    public int[] imageArray = new int[7];
    int[] slashArray = new int[7];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        context = MainActivity.this;
        playerStatus = new PlayerStatus();
        monsterStatus = new MonsterStatus(MainActivity.this);
        hpDisplay = (TextView) findViewById(R.id.hpDisplay);
        levelDisplay = (TextView) findViewById(R.id.txtlevel);
        stageDisplay = (TextView) findViewById(R.id.txtstage);
        timeDisplay = (TextView) findViewById(R.id.timedisplay);
        scoreDisplay = (TextView) findViewById(R.id.scoreDisplay);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        onClick = (RelativeLayout) findViewById(R.id.click_layout) ;
        myDD = (Button) findViewById(R.id.DDbtn);
        ivMonster = (ImageView) findViewById(R.id.ivMonster);
        ivSlash = (ImageView) findViewById(R.id.ivSlash);

        imageArray[0] = R.drawable.monster1;
        imageArray[1] = R.drawable.monster2;
        imageArray[2] = R.drawable.monster3;
        imageArray[3] = R.drawable.monster4;
        imageArray[4] = R.drawable.monster5;
        imageArray[5] = R.drawable.monster6;
        imageArray[6] = R.drawable.monster7;
        slashArray[0] = R.drawable.slash1;
        slashArray[1] = R.drawable.slash2;
        slashArray[2] = R.drawable.slash3;
        slashArray[3] = R.drawable.slash4;
        slashArray[4] = R.drawable.slash5;
        slashArray[5] = R.drawable.slash6;
        slashArray[6] = R.drawable.slash7;


        // --------------------------------------------------

        startTimerThread();
        hpBarScale = (monsterStatus.getCurrentHP() / monsterStatus.getMonsterHP())*100;
        playerDamage = playerStatus.getClickDamage();
        progressBar.setProgress(hpBarScale.intValue());
        ivMonster.setImageDrawable(getResources().getDrawable(R.drawable.monster1));

        // --------------------------------------------------

        myDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isDD){
                    isDD = true;
                    myDD.setVisibility(View.INVISIBLE);
                    new CountDownTimer(15000,1000){
                        @Override
                        public void onTick(long millisUntilFinished) {
                            playerDamage = playerStatus.getClickDamage() * 2;
                            Log.i("Status : ","isOn Double Damage !");
                        }

                        @Override
                        public void onFinish() {
                            playerDamage = playerStatus.getClickDamage();
                            isDD = false;
                            Log.i("Status : ","Double Damage End !");
                            myDD.setVisibility(View.VISIBLE);
                        }
                    }.start();
                }
            }
        });

        onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monsterStatus.onClick(playerDamage);
                hpBarScale = (monsterStatus.getCurrentHP() / monsterStatus.getMonsterHP())*100;
                progressBar.setProgress(hpBarScale.intValue());
                Log.i("HPScale",Integer.toString(hpBarScale.intValue()));
                Random rand = new Random();
                int slash = rand.nextInt(7);
                ivSlash.setImageDrawable(getResources().getDrawable(slashArray[slash]));
            }
        });

    }

    //  MAIN GAME LOOP
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
                            if (monsterStatus.getStage() == 0){
                                stageDisplay.setText("Stage : BOSS");
                            }else{
                                stageDisplay.setText("Stage : " + Integer.toString(monsterStatus.getStage()));
                            }
                            scoreDisplay.setText("Score : " + Integer.toString(monsterStatus.getTotalScore()));
                            levelDisplay.setText("Level : " + Integer.toString(monsterStatus.getCurrentLevel()));
                            hpDisplay.setText(monsterStatus.hpUpdate());
                            int currentImage = 0;
                            if (currentImage != monsterStatus.getRandomMonster()){
                                currentImage = monsterStatus.getRandomMonster();
                                ivMonster.setImageDrawable(getResources().getDrawable(imageArray[currentImage]));
                            }
                        if(monsterStatus.getBossStatus() == true){
                                if(!isTimerActive){
                                    isTimerActive = true;
                                    timeDisplay.setVisibility(View.VISIBLE);
                                    bossTimer = new CountDownTimer(10000,1000) {
                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                            timeDisplay.setText("Timer : " + millisUntilFinished / 1000);
                                            if (monsterStatus.getBossStatus() == false){
                                                Log.i("Status ","Masuk sukses");
                                                Log.i("Mon Stat",String.valueOf(monsterStatus.getStage()));
                                                isTimerActive = false;
                                                timeDisplay.setVisibility(View.INVISIBLE);
                                                bossTimer.cancel();
                                            }
                                        }

                                        @Override
                                        public void onFinish() {
                                            Log.i("Status :","OnFinish");
                                            timeDisplay.setVisibility(View.INVISIBLE);
                                            if (monsterStatus.getStage() == 0){
                                                timeDisplay.setVisibility(View.INVISIBLE);
                                                isTimerActive = false;
                                                monsterStatus.setBossStage(false);
                                                monsterStatus.setCurrentStage(1);
                                                monsterStatus.setMonsterHP();
                                                hpBarScale = (monsterStatus.getCurrentHP() / monsterStatus.getMonsterHP())*100;
                                                progressBar.setProgress(hpBarScale.intValue());
                                            }
                                        }
                                    }.start();
                                }
                        }

                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }
}

