package SJ;

import java.awt.Color;
import robocode.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html
/**
 * SJRobo - a robot by Samuel Jhonata
 */
public class SJRobo extends AdvancedRobot {

    /**
     * run: SJRobo's default behavior
     */
    public void run() {
        setBodyColor(Color.BLUE); //cor do corpo
        setGunColor(Color.white); //cor da arma
        setRadarColor(Color.white); //cor do radar
        setBulletColor(Color.blue); //cor da bala

        // Robot main loop
        while (true) {
            setAhead(40); //andar pra frente
            setTurnRight(180); //girar pra direita
            setBack(50); //andar pra trás
            execute(); //executar conjuntamente
            setBack(10); //andar pra trás
            for (int i = 0; i < 2; i++) {
                setAhead(0); //andar pra frente
                setTurnRight(45); //girar pra direita
                setTurnGunRight(80); //girar o canhão para a direita
            }
            for (int i = 0; i < 4; i++) {
                setBack(60); //andar pra tras
                setTurnRight(40); //girar pra direita
                setTurnGunLeft(30); //girar o canhão para a esquerda
                execute();//executar conjuntamente
            }

        }
    }

    public void onScannedRobot(ScannedRobotEvent e) { //detecta um adversário no radar

        if(e.getDistance()<15){
            fire(3); //atirar
        }else if(e.getDistance() < 30){
            fire(2.5); //atirar
        }else if(e.getDistance() < 50){
            fire(2); //atirar
        }else{
            fire(1.5); //atirar
        }
    }

    public void onHitByBullet(HitByBulletEvent e) { //atingido por um tiro
        setBack(40); //andar pra trás
    }

    public void onHitWall(HitWallEvent e) { //bate em uma parede
        setTurnLeft(180 - Math.abs(e.getBearing())); //girar para a esquerda
        setAhead(80); //andar pra frente
        execute();//executar conjuntamente
    }

    public void onHitRobot(){ //bate em um adversário
        setTurnLeft(90); //girar para a esquerda
        setAhead(150); //andar pra frente
        fire(3); //atirar
        execute();//executar conjuntamente
    }

    public void onHitByBullet(){ //atingido por uma bala
        setTurnRight(10); //girar pra direita
        setAhead(50); //andar pra frente
        execute();//executar conjuntamente
    }

   public void onBulletHit(){//bala disparada atingiu inimigo
       setAhead(30); //andar pra frente
       setTurnLeft(0); //girar para a esquerda
       execute();//executar conjuntamente
   }

}
