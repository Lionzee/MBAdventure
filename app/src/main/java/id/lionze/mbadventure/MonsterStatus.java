package id.lionze.mbadventure;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lionze on 27 Okt 2017.
 */

public class MonsterStatus {

    MainActivity main = new MainActivity();

    private static Context c;
    private String monsterName;
    double baseHp = 50;
    double   monsterHP = baseHp;
    double currentHP = monsterHP;
    public boolean isAlive;
    private int currentStage = 1;
    private int currentLevel = 1;
    private int headID,bodyID,armsID,legsID;

    public MonsterStatus(Context _c){
        c = _c;
        monsterHP = baseHp;
    }

    public int getStage(){
        return currentStage;
    }

    public void setMonsterHP(){
        monsterHP = baseHp * currentLevel;
        currentHP = monsterHP;
        Toast.makeText(c, "Stage " + Integer.toString(currentStage), Toast.LENGTH_SHORT).show();
        currentStage += 1;
    }

    public void setBossHP(){
        monsterHP = (baseHp * currentLevel) * 10;
        currentHP = monsterHP;
        Toast.makeText(c,"You are fighting the level " + Integer.toString(currentLevel) + " Boss",Toast.LENGTH_LONG).show();
        currentStage = 1;
        currentLevel += 1;
    }

    public String hpUpdate(){
        String displayHP;
        displayHP = Double.toString(currentHP) + "/" + Double.toString(monsterHP);

        return displayHP;
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
            if(currentStage <= 10){
                setMonsterHP();
            }else {
                setBossHP();
            }

        }
    }




}
