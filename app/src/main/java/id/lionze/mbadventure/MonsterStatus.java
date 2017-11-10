package id.lionze.mbadventure;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Lionze on 27 Okt 2017.
 */

public class MonsterStatus {

    MainActivity main = new MainActivity();

    private static Context c;
    private String monsterName;
    double   monsterHP = 50;
    double currentHP = monsterHP;
    public boolean isAlive;
    private int currentStage = 1;
    private int headID,bodyID,armsID,legsID;

    public MonsterStatus(Context _c){
        c = _c;
    }

    public int getStage(){
        return currentStage;
    }

    public void setMonsterHP(){

        if(currentStage < 11){
            monsterHP = 50;
            currentHP = monsterHP;
        } else if(currentStage > 10 && currentStage < 21){
            monsterHP = 100;
            currentHP = monsterHP;
        }

    }

    public double getMonsterHP(){
        return monsterHP;
    }
    public double getCurrentHP(){return currentHP;}

    public void onClick(Double _clickPower){
        currentHP -= _clickPower;
        Log.i("Click Power ",Double.toString(_clickPower));
        Log.i("Current Hp",Double.toString(currentHP));
        if(currentHP <= 0){
            currentStage += 1;
            Log.i("Stage",Integer.toString(currentStage));
            setMonsterHP();
            Toast.makeText(c, "Current Stage : " + Integer.toString(currentStage), Toast.LENGTH_SHORT).show();
        }
    }



}
