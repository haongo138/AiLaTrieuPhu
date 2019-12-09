package com.example.ai_la_trieu_phu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button button_bat_dau,button_xep_hang;
    MediaPlayer mediaPlayer;
    DATABASE database;
    TextView top1,top2,top3,top4,top5;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playSound(R.raw.bg_music);
        anhxa();
        Bat_dau();
        Xep_hang();
        copydata();
    }
    public void Bat_dau(){
        button_bat_dau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                playSound(R.raw.luatchoi);
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

    }
    public void Xep_hang(){
        button_xep_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog =new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.diem_so);
                top1=(TextView) dialog.findViewById(R.id.top1);
                top2=(TextView) dialog.findViewById(R.id.top2);
                top3=(TextView) dialog.findViewById(R.id.top3);
                top4=(TextView) dialog.findViewById(R.id.top4);
                top5=(TextView) dialog.findViewById(R.id.top5);
                button=(Button) dialog.findViewById(R.id.btnOK);
                int i=0;
                String[] text=new String[5];
                database=new DATABASE(MainActivity.this, "diem.sqlite",null,1);
                Cursor datacauhoi1=database.getDATA("select * from diem  order by diem desc");
                while (datacauhoi1.moveToNext()) {
                    text[i]=datacauhoi1.getInt(0)+"";
                    i++;
                    if (i==5){
                        break;
                    }
                }
                top1.setText(text[0]);
                top2.setText(text[1]);
                top3.setText(text[2]);
                top4.setText(text[3]);
                top5.setText(text[4]);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }
    public void playSound(int i){
        mediaPlayer= MediaPlayer.create(this,i);
        mediaPlayer.start();
    }
    public void anhxa(){
        button_bat_dau=(Button) findViewById(R.id.button_bat_dau);
        button_xep_hang=(Button) findViewById(R.id.button_xep_hang);
    }
    protected void copydata(){
        File file=new File(Environment.getDataDirectory()+"/data/com.example.ai_la_trieu_phu/databases/cauhoi.sqlite");
        if (file.exists()){
            return;
        }
        File file1=file.getParentFile();
        file1.mkdir();
        database=new DATABASE(MainActivity.this, "diem.sqlite",null,1);
        database.sqlDATA("create table if not exists diem(diem int)");
        for(int i=0;i<5;i++) {
            database.sqlDATA("insert into diem values('0')");
        }
        String text="";
        String[] chuoi;
        int i=0;
        try{
            InputStream inputStream= getAssets().open("cauhoi.txt");
            int size= inputStream.available();
            byte[] bytes=new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            text=new String(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        chuoi= text.split("\\@");
        database=new DATABASE(this, "cauhoi.sqlite",null,1);
        database.sqlDATA("create table if not exists cauhoi(socau integer primary key autoincrement,cauhoi varchar(200),dapanA varchar(200),dapanB varchar(200),dapanC varchar(200),dapanD varchar(200),ketqua varchar(1))");

       while (true){
           if(chuoi[i].equals("OK")) {
               return;
           }
           database.sqlDATA("insert into cauhoi values(null,'"+chuoi[i]+"','"+chuoi[i+1]+"','"+chuoi[i+2]+"','"+chuoi[i+3]+"','"+chuoi[i+4]+"','"+chuoi[i+5]+"')");
           i+=6;
       }

    }
}
