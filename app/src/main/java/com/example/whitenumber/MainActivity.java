package com.example.whitenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    static boolean isPlay = true;
    MediaPlayer  mediaPlayer;/*播放器对象*/
    Button music_btn;/*静音按钮*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music_btn=(Button)findViewById(R.id.btn_music);/*获取静音按钮*/
        PlayMusic();
    }

    private void PlayMusic() {
        mediaPlayer = MediaPlayer.create(this,R.raw.main_music);
        mediaPlayer.setLooping(true);/*循环播放音乐*/
        mediaPlayer.start();
    }

    public void OnPlay(View v){
        startActivity(new Intent(MainActivity.this,SelectActivity.class));
    }
    public void OnAbout(View v){
        startActivity(new Intent(MainActivity.this,  AboutActivity.class));
    }
    public void OnMusic(View v){
        if(isPlay=true){
            if(mediaPlayer!=null){
                mediaPlayer.stop();
                music_btn.setBackgroundResource(R.drawable.btn_music2);
                isPlay=false;
            }
        }else{
            PlayMusic();
            music_btn.setBackgroundResource(R.drawable.btn_music1);
            isPlay=true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
       if( mediaPlayer!=null){
           mediaPlayer.stop();
       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
/*开启音乐*/
    @Override
    protected void onRestart() {
        super.onRestart();
        if(isPlay=true){
            PlayMusic();
        }
    }
}
