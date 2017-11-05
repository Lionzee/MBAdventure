package id.lionze.mbadventure;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by Lionze on 27 Okt 2017.
 */

public class MainActivity extends Activity {

    public Button myClick;
    ProgressBar progressBar;
    int pValue = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        myClick = (Button) findViewById(R.id.btnClick);

        progressBar.setProgress(pValue);

        myClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pValue -= 10;
                progressBar.setProgress(pValue);
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
