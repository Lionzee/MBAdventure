package id.lionze.mbadventure;

/**
 * Created by Lionze on 27 Okt 2017.
 */

public class MonsterStatus {

    private String monsterName;
    private int monsterHP;
    public boolean isAlive;
    private int currentStage = 1;
    private int headID,bodyID,armsID,legsID;

    void spawnMonster(){
        isAlive = true;
        monsterName = "Prototype Monster";
        if (currentStage > 10 && currentStage < 21){
            monsterHP = 100;
        }else if(currentStage > 20 && currentStage < 31){
            monsterHP = 300;
        }else if(currentStage > 30 && currentStage < 41) {
            monsterHP = 750;
        }
    }

}
