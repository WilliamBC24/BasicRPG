package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale=3;
    float volume;

    public Sound(){
        soundURL[0]=getClass().getResource("/Resource/sound/chest.wav");
        soundURL[1]=getClass().getResource("/Resource/sound/door.wav");
        soundURL[2]=getClass().getResource("/Resource/sound/key.wav");
        soundURL[3]=getClass().getResource("/Resource/sound/shoes.wav");
        soundURL[4]=getClass().getResource("/Resource/sound/walk.wav");
        soundURL[5]=getClass().getResource("/Resource/sound/music.wav");
        soundURL[6]=getClass().getResource("/Resource/sound/Intro.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);
            fc=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        }catch(Exception e){

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void checkVolume(){
        switch (volumeScale){
            case 0:
                volume=-80f;
                break;
            case 1:
                volume=-25f;
                break;
            case 2:
                volume=-12f;
                break;
            case 3:
                volume=-5f;
                break;
            case 4:
                volume=1f;
                break;
            case 5:
                volume=6f;
                break;
        }
        fc.setValue(volume);
    }
}
