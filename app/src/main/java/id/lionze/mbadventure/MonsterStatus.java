package id.lionze.mbadventure;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

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
    private int randomMonster = 0;
    private boolean isBossStage = false;
    private int totalScore = 0;

    public MonsterStatus(Context _c){
        c = _c;
        monsterHP = baseHp;
    }

    public boolean getBossStatus(){
        return isBossStage;
    }

    public void setBossStage(boolean stat){
        isBossStage = stat;
    }

    public void setCurrentStage(int stage){
        currentStage = stage;
    }

    public int getTotalScore(){
        return totalScore;
    }

    public int getStage(){
        return currentStage;
    }
    public void setCurrentLevel(int level){
        currentLevel = level;
    }
    public  int getCurrentLevel(){return currentLevel;}

    public void setMonsterHP(){
        monsterHP = baseHp * currentLevel;
        currentHP = monsterHP;

    }

    public void setBossHP(){
        monsterHP = (baseHp * currentLevel) * 10;
        currentHP = monsterHP;

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

    public int getRandomMonster(){ return randomMonster;}

    public void onClick(Double _clickPower){
        currentHP -= _clickPower;
        Log.i("Click Power ",Double.toString(_clickPower));
        Log.i("Current Hp",Double.toString(currentHP));
        if(currentHP <= 0){
            totalScore += monsterHP / 10;
            Random rand = new Random();
            randomMonster = rand.nextInt(7);



            if(currentStage >= 1 && currentStage <= 9){
                currentStage += 1;
                setMonsterHP();
            }else if (currentStage == 10){
                currentStage = 0;
                setBossHP();
                isBossStage = true;
            }else if(currentStage == 0 && isBossStage == true){
                currentStage += 1;
                currentLevel += 1;
                setMonsterHP();
                isBossStage = false;
            }

        }
    }




}
