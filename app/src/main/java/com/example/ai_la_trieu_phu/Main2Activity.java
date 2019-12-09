package com.example.ai_la_trieu_phu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    ImageButton imageButton5050, imageButtonkhangia, imageButtoncall, imageButtonstop;
    TextView textViewtime, textViewdiem, textViewcauhoi, textViewdiemso;
    TextView textView5, textView10, textView15;
    Button buttonA, buttonB, buttonC, buttonD, buttonsansang;
    ImageView imageViewclock;
    int dem = 30, diemso = 0, causo = 0, dapan, dapanso;
    int i = 0;
    Dialog dialog;
    int[] SOUND_QUEST_DAP_AN_DUNG = {R.raw.true_a, R.raw.true_b, R.raw.true_c, R.raw.true_d};
    int[] SOUND_QUEST_DAP_AN_SAI = {R.raw.lose_a, R.raw.lose_b, R.raw.lose_c, R.raw.lose_d};
    int[] SOUND_QUEST_DAP_AN = {R.raw.ans_a, R.raw.ans_b, R.raw.ans_c, R.raw.ans_d};
    int[] SOUND_QUEST = {R.raw.ques01, R.raw.ques02, R.raw.ques03, R.raw.ques04, R.raw.ques05, R.raw.ques06, R.raw.ques07, R.raw.ques08, R.raw.ques09, R.raw.ques10, R.raw.ques11, R.raw.ques12, R.raw.ques13, R.raw.ques14, R.raw.ques15};
    String ketqua, kiemtra;
    DATABASE database;
    MediaPlayer mediaPlayer;
    TimeOut timeOut=new TimeOut();
    Cauhoi cauhoi=new Cauhoi();
    KiemTraDapAn kiemTraDapAn;
    ArrayList<Integer> ktracauhoi=new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        anhxa();
        dialog();
        database();
        trogiup();
        traloi();
    }
    public void dialog() {
        dialog = new Dialog(Main2Activity.this);
        dialog.setContentView(R.layout.dialog_bang_diem);
        dialog.show();
        buttonsansang = (Button) dialog.findViewById(R.id.btnSanSang);
        buttonsansang.setEnabled(false);
        textView5 = (TextView) dialog.findViewById(R.id.text5);
        textView10 = (TextView) dialog.findViewById(R.id.text10);
        textView15 = (TextView) dialog.findViewById(R.id.text15);
        asyncTaskgioithieu.execute();
        buttonsansang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.batdauchoi);
                dialog.cancel();
                cauhoi.execute();
                timeOut.execute();
            }
        });
    }
    private class Cauhoi extends AsyncTask<Void,Integer,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            playSound(SOUND_QUEST[causo]);
            Cauhoi();
            buttonA.setEnabled(true);
            buttonB.setEnabled(true);
            buttonC.setEnabled(true);
            buttonD.setEnabled(true);
        }
    }
    private AsyncTask<Void,Integer,Void> asyncTask5050=new AsyncTask<Void, Integer, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random ran=new Random();
            int number=1+ran.nextInt(2);
            publishProgress(number);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(ketqua.equals("A")){
                switch (values[0]){
                    case 1:
                        buttonC.setText("");
                        buttonC.setEnabled(false);
                        buttonD.setText("");
                        buttonD.setEnabled(false);
                        break;
                    case 2:
                        buttonB.setText("");
                        buttonB.setEnabled(false);
                        buttonD.setText("");
                        buttonD.setEnabled(false);
                        break;
                    case 3:
                        buttonB.setText("");
                        buttonB.setEnabled(false);
                        buttonC.setText("");
                        buttonC.setEnabled(false);
                        break;
                }
            }else if(ketqua.equals("B")){
                switch (values[0]){
                    case 1:
                        buttonC.setText("");
                        buttonC.setEnabled(false);
                        buttonD.setText("");
                        buttonD.setEnabled(false);
                        break;
                    case 2:
                        buttonA.setText("");
                        buttonA.setEnabled(false);
                        buttonD.setText("");
                        buttonD.setEnabled(false);
                        break;
                    case 3:
                        buttonA.setText("");
                        buttonA.setEnabled(false);
                        buttonC.setText("");
                        buttonC.setEnabled(false);
                        break;
                }}else if(ketqua.equals("C")){
                switch (values[0]){
                    case 1:
                        buttonB.setText("");
                        buttonB.setEnabled(false);
                        buttonD.setText("");
                        buttonD.setEnabled(false);
                        break;
                    case 2:
                        buttonA.setText("");
                        buttonA.setEnabled(false);
                        buttonD.setText("");
                        buttonD.setEnabled(false);
                        break;
                    case 3:
                        buttonA.setText("");
                        buttonA.setEnabled(false);
                        buttonB.setText("");
                        buttonB.setEnabled(false);
                        break;
                }
            }else if(ketqua.equals("D")){
                switch (values[0]){
                    case 1:
                        buttonB.setText("");
                        buttonB.setEnabled(false);
                        buttonC.setText("");
                        buttonC.setEnabled(false);
                        break;
                    case 2:
                        buttonA.setText("");
                        buttonA.setEnabled(false);
                        buttonC.setText("");
                        buttonC.setEnabled(false);
                        break;
                    case 3:
                        buttonA.setText("");
                        buttonA.setEnabled(false);
                        buttonB.setText("");
                        buttonB.setEnabled(false);
                        break;
                }
            }
        }

    };
    private AsyncTask<Void,Integer,Void> asyncTaskgioithieu=new AsyncTask<Void, Integer, Void>() {
        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.aminstart);
            textView5.startAnimation(animation);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textView10.startAnimation(animation);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textView15.startAnimation(animation);
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playSound(R.raw.ready);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            buttonsansang.setEnabled(true);
        }
    };
    public void traloi(){
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.amindapan);
                buttonA.startAnimation(animation);
                kiemtra="A";
                dapanso=0;
                timeOut.cancel(true);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);
                playSound(SOUND_QUEST_DAP_AN[0]);
                kiemTraDapAn=new KiemTraDapAn();
                kiemTraDapAn.execute();
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.amindapan);
                buttonB.startAnimation(animation);
                kiemtra="B";
                dapanso=1;
                timeOut.cancel(true);
                buttonA.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);
                playSound(SOUND_QUEST_DAP_AN[1]);
                kiemTraDapAn=new KiemTraDapAn();
                kiemTraDapAn.execute();
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.amindapan);
                buttonC.startAnimation(animation);
                kiemtra="C";
                dapanso=2;
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                timeOut.cancel(true);
                buttonD.setEnabled(false);
                playSound(SOUND_QUEST_DAP_AN[2]);
                kiemTraDapAn=new KiemTraDapAn();
                kiemTraDapAn.execute();
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.amindapan);
                buttonD.startAnimation(animation);
                kiemtra="D";
                dapanso=3;
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                timeOut.cancel(true);
                playSound(SOUND_QUEST_DAP_AN[3]);
                kiemTraDapAn=new KiemTraDapAn();
                kiemTraDapAn.execute();
            }
        });
    }
    private AsyncTask <Void,Integer,Void> asyncTaskKhanGia =new AsyncTask<Void, Integer, Void>() {
        @Override
        protected void onPreExecute() {
            dialog = new Dialog(Main2Activity.this);
            dialog.setContentView(R.layout.layout_khangia);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView textViewA = (TextView) dialog.findViewById(R.id.khangia_A);
            TextView textViewB = (TextView) dialog.findViewById(R.id.khangia_B);
            TextView textViewC = (TextView) dialog.findViewById(R.id.khangia_C);
            TextView textViewD = (TextView) dialog.findViewById(R.id.khangia_D);
            final Button buttonok = (Button) dialog.findViewById(R.id.buttonOK);
            Random rd = new Random();
            int A = 0, B = 0, C = 0, D = 0;
            if(ketqua.equals("A")) {
                while (A <= D || A <= B || A <= C) {
                    B=rd.nextInt(100);
                    C=rd.nextInt(100-B);
                    D=rd.nextInt(100-B-C);
                    A = 100 - D - B - C;
                }
            }
            if(ketqua.equals("B")) {
                while (B <= A || B <= D || B <= C) {
                    A = rd.nextInt(100);
                    C = rd.nextInt(100 - A);
                    D = rd.nextInt(100 - A-C);
                    B = 100 - A - D - C;
                }
            }
            if(ketqua.equals("C")) {
                while (C <= A || C <= B || C <= D) {
                    A = rd.nextInt(100);
                    B = rd.nextInt(100 - A);
                    D = rd.nextInt(100 - A - B);
                    C = 100 - A - B - D;
                }
            }
            if(ketqua.equals("D")) {
                while (D <= A || D <= B || D <= C) {
                    A = rd.nextInt(100);
                    B = rd.nextInt(100 - A);
                    C = rd.nextInt(100 - A - B);
                    D = 100 - A - B - C;
                }
            }
            textViewA.setText(A + "%");
            textViewB.setText(B + "%");
            textViewC.setText(C + "%");
            textViewD.setText(D + "%");
            buttonok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timeOut=new TimeOut();
                    timeOut.execute();
                    dialog.cancel();
                }
            });

        }
    };
    public void trogiup() {
        imageButton5050.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeOut.cancel(true);
                asyncTask5050.execute();
                timeOut=new TimeOut();
                timeOut.execute();
                imageButton5050.setImageResource(R.drawable.atp__activity_player_button_image_help_5050_x);
                playSound(R.raw.sound5050);
                imageButton5050.setEnabled(false);
            }
        });
        imageButtonkhangia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeOut.cancel(true);
                asyncTaskKhanGia.execute();
                imageButtonkhangia.setImageResource(R.drawable.atp__activity_player_button_image_help_audience_x);
                playSound(R.raw.bg_audience);
                imageButtonkhangia.setEnabled(false);
            }
        });
        imageButtoncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Main2Activity.this);
                dialog.setContentView(R.layout.layout_call);
                dialog.show();
                imageButtoncall.setImageResource(R.drawable.atp__activity_player_button_image_help_call_x);
                playSound(R.raw.call);
                imageButtoncall.setEnabled(false);
                timeOut.cancel(true);
                ImageView obama = (ImageView) dialog.findViewById(R.id.help_obama);
                ImageView stever = (ImageView) dialog.findViewById(R.id.help_stever);
                ImageView billgate = (ImageView) dialog.findViewById(R.id.help_billgate);
                ImageView troll = (ImageView) dialog.findViewById(R.id.help_troll);
                obama.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        dialog = new Dialog(Main2Activity.this);
                        dialog.setContentView(R.layout.layout_dap_an_call);
                        TextView textView = (TextView) dialog.findViewById(R.id.tv_dap_an_call);
                        Button button = (Button) dialog.findViewById(R.id.btn_ok_call);
                        textView.setText("Bạn nên chọn đáp án " + ketqua);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timeOut=new TimeOut();
                                timeOut.execute();
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                    }
                });
                stever.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        dialog = new Dialog(Main2Activity.this);
                        dialog.setContentView(R.layout.layout_dap_an_call);
                        TextView textView = (TextView) dialog.findViewById(R.id.tv_dap_an_call);
                        Button button = (Button) dialog.findViewById(R.id.btn_ok_call);
                        textView.setText("Bạn nên chọn đáp án " + ketqua);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timeOut=new TimeOut();
                                timeOut.execute();
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                    }
                });
                billgate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        dialog = new Dialog(Main2Activity.this);
                        dialog.setContentView(R.layout.layout_dap_an_call);
                        TextView textView = (TextView) dialog.findViewById(R.id.tv_dap_an_call);
                        Button button = (Button) dialog.findViewById(R.id.btn_ok_call);
                        textView.setText("Bạn nên chọn đáp án " + ketqua);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timeOut=new TimeOut();
                                timeOut.execute();
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                    }
                });
                troll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        dialog = new Dialog(Main2Activity.this);
                        dialog.setContentView(R.layout.layout_dap_an_call);
                        TextView textView = (TextView) dialog.findViewById(R.id.tv_dap_an_call);
                        Button button = (Button) dialog.findViewById(R.id.btn_ok_call);
                        textView.setText("Bạn nên chọn đáp án " + ketqua);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timeOut=new TimeOut();
                                timeOut.execute();
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                    }
                });
            }
        });
        imageButtonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(Main2Activity.this);
                dialog.setContentView(R.layout.layout_end_game);
                textViewdiem=(TextView)  dialog.findViewById(R.id.tvFinish);
                textViewdiem.setText("Bạn đã dành được "+ diemso +" điểm. Cảm ơn bạn đã tham gia chương trình. Chúc bạn thành công trong cuộc sống !!!");
                Button button=(Button) dialog.findViewById(R.id.btnFinish);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                        database=new DATABASE(Main2Activity.this, "diem.sqlite",null,1);
                        database.sqlDATA("insert into diem values('"+diemso+"')");
                    }
                });
                dialog.show();
            }
        });
    }
    public void Cauhoi(){
        Cursor datacauhoi=database.getDATA("select *from cauhoi");
        Random random=new Random();
        int number;
        if(causo<5) {
            number = random.nextInt(25)+1;
            while (ktracauhoi.contains(number)){
                number=random.nextInt(25)+1;
            }
            ktracauhoi.add(number);
            while (datacauhoi.moveToNext()) {
                if (number == datacauhoi.getInt(0)) {
                    textViewcauhoi.setText(datacauhoi.getString(1));
                    buttonA.setText("A." + datacauhoi.getString(2));
                    buttonB.setText("B." + datacauhoi.getString(3));
                    buttonC.setText("C." + datacauhoi.getString(4));
                    buttonD.setText("D." + datacauhoi.getString(5));
                    ketqua = datacauhoi.getString(6);
                }
            }
        }else if(causo>=5||causo<10){
            number = random.nextInt(35)+1;
            number+=26;
            while (ktracauhoi.contains(number)){
                number=random.nextInt(35)+1;
                number+=26;
            }
            ktracauhoi.add(number);
            while (datacauhoi.moveToNext()) {
                if (number == datacauhoi.getInt(0)) {
                    textViewcauhoi.setText(datacauhoi.getString(1));
                    buttonA.setText("A." + datacauhoi.getString(2));
                    buttonB.setText("B." + datacauhoi.getString(3));
                    buttonC.setText("C." + datacauhoi.getString(4));
                    buttonD.setText("D." + datacauhoi.getString(5));
                    ketqua = datacauhoi.getString(6);
                }
            }
        }else if(causo>=10){
            number = random.nextInt(23);
            number+=62;
            while (ktracauhoi.contains(number)){
                number=random.nextInt(23)+1;
                number+=62;
            }
            ktracauhoi.add(number);
            while (datacauhoi.moveToNext()) {
                if (number == datacauhoi.getInt(0)) {
                    textViewcauhoi.setText(datacauhoi.getString(1));
                    buttonA.setText("A." + datacauhoi.getString(2));
                    buttonB.setText("B." + datacauhoi.getString(3));
                    buttonC.setText("C." + datacauhoi.getString(4));
                    buttonD.setText("D." + datacauhoi.getString(5));
                    ketqua = datacauhoi.getString(6);
                }
            }
        }
        if(ketqua.equals("A")){
            dapan=0;
        }else if(ketqua.equals("B")){
            dapan=1;
        }else if(ketqua.equals("C")){
            dapan=2;
        }else if(ketqua.equals("D")){
            dapan=3;
        }

    }
    private class KiemTraDapAn extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(1);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_check);
            switch (dapan) {
                case 0:
                    buttonA.startAnimation(animation);
                    break;
                case 1:
                    buttonB.startAnimation(animation);
                    break;
                case 2:
                    buttonC.startAnimation(animation);
                    break;
                case 3:
                    buttonD.startAnimation(animation);
                    break;
            }
            if(ketqua.equals(kiemtra)){
                playSound(SOUND_QUEST_DAP_AN_DUNG[dapanso]);
                cauhoi=new Cauhoi();
                timeOut=new TimeOut();
                dem = 30;
                causo++;
                switch (causo){
                    case 1:diemso=200;break;
                    case 2:diemso=400;break;
                    case 3:diemso=600;break;
                    case 4:diemso=1000;break;
                    case 5:diemso=2000;break;
                    case 6:diemso=3000;break;
                    case 7:diemso=6000;break;
                    case 8:diemso=10000;break;
                    case 9:diemso=14000;break;
                    case 10:diemso=22000;break;
                    case 11:diemso=30000;break;
                    case 12:diemso=40000;break;
                    case 13:diemso=60000;break;
                    case 14:diemso=85000;break;
                    case 15:diemso=150000;break;
                }
                textViewdiemso.setText(diemso + "");
                if(causo==15){
                    playSound(R.raw.bestplayer);
                    dialog = new Dialog(Main2Activity.this);
                    dialog.setContentView(R.layout.layout_end_game);
                    textViewdiem = (TextView) dialog.findViewById(R.id.tvFinish);
                    textViewdiem.setText("Bạn đã dành được " + diemso + " điểm. Cảm ơn bạn đã tham gia chương trình. Chúc bạn thành công trong cuộc sống !!!");
                    Button button = (Button) dialog.findViewById(R.id.btnFinish);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                            startActivity(intent);
                            database=new DATABASE(Main2Activity.this, "diem.sqlite",null,1);
                            database.sqlDATA("insert into diem values('"+diemso+"')");
                        }
                    });
                    dialog.show();
                }else {
                cauhoi.execute();
                timeOut.execute();
                }
            }else {
                playSound(SOUND_QUEST_DAP_AN_SAI[dapan]);
                dialog = new Dialog(Main2Activity.this);
                dialog.setContentView(R.layout.layout_end_game);
                textViewdiem = (TextView) dialog.findViewById(R.id.tvFinish);
                textViewdiem.setText("Bạn đã dành được " + diemso + " điểm. Cảm ơn bạn đã tham gia chương trình. Chúc bạn thành công trong cuộc sống !!!");
                Button button = (Button) dialog.findViewById(R.id.btnFinish);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                        startActivity(intent);
                        database=new DATABASE(Main2Activity.this, "diem.sqlite",null,1);
                        database.sqlDATA("insert into diem values('"+diemso+"')");
                    }
                });
                dialog.show();
            }
        }
    }
    private class  TimeOut extends AsyncTask<Void,Integer,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=dem;i>=0;i--){
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
                if(isCancelled()){
                    dem=i;
                    return null;
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textViewtime.setText(values[0]+"");
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.time_out);
            imageViewclock.startAnimation(animation);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            playSound(R.raw.out_of_time);
            dialog=new Dialog(Main2Activity.this);
            dialog.setContentView(R.layout.layout_end_game);
            textViewdiem=(TextView)  dialog.findViewById(R.id.tvFinish);
            textViewdiem.setText("Bạn đã dành được "+ diemso +" điểm. Cảm ơn bạn đã tham gia chương trình. Chúc bạn thành công trong cuộc sống !!!");
            Button button=(Button) dialog.findViewById(R.id.btnFinish);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(intent);
                    database=new DATABASE(Main2Activity.this, "diem.sqlite",null,1);
                    database.sqlDATA("insert into diem values('"+diemso+"')");
                }
            });
            dialog.show();
        }
    }
    public void anhxa(){
        imageButton5050=(ImageButton) findViewById(R.id.help_5050);
        imageButtonkhangia=(ImageButton) findViewById(R.id.help_audience);
        imageButtoncall=(ImageButton) findViewById(R.id.help_call);
        imageButtonstop=(ImageButton) findViewById(R.id.help_stop);
        textViewtime =(TextView) findViewById(R.id.texttime);
        textViewcauhoi =(TextView) findViewById(R.id.tv_cauhoi);
        textViewdiemso=(TextView) findViewById(R.id.textdiem);
        buttonA=(Button) findViewById(R.id.butonA);
        buttonB=(Button) findViewById(R.id.buttonB);
        buttonC=(Button) findViewById(R.id.buttonC);
        buttonD=(Button) findViewById(R.id.buttonD);
        imageViewclock=(ImageView) findViewById(R.id.imclock);
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }
    public void playSound(int i){
        mediaPlayer=MediaPlayer.create(this,i);
        mediaPlayer.start();
    }
    public void database(){
        //tạo database
        database=new DATABASE(Main2Activity.this, "cauhoi.sqlite",null,1);
        //tạo bảng
        database.sqlDATA("create table if not exists cauhoi(socau integer primary key autoincrement,cauhoi varchar(200),dapanA varchar(200),dapanB varchar(200),dapanC varchar(200),dapanD varchar(200),ketqua varchar(1))");
        //thêm dữ liệu
        //database.sqlDATA("insert into cauhoi values(null,'Các vua Hùng đặt quốc hiệu nước ta là gì?','Văn Lang','Âu Lạc','Vạn Xuân','Đại Việt','A')");
        Cursor datacauhoi=database.getDATA("select *from cauhoi");

    }
}
