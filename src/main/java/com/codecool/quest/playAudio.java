package com.codecool.quest;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class playAudio {
    public static void playMusic(String musicPath){
        try {
            AudioInputStream audioStr = AudioSystem.getAudioInputStream(new File(musicPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStr);
            clip.start();
        } catch (Exception e){
            System.err.println("ERROR");
        }
    }
}
