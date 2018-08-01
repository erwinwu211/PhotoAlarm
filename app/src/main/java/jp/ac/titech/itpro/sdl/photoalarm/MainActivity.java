package jp.ac.titech.itpro.sdl.photoalarm;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 開始ボタン
        Button button = this.findViewById(R.id.button1);
        String str = "Alarm Start";
        button.setText(str);
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                // 時間をセットする
                Calendar calendar = Calendar.getInstance();
                int hour=calendar.get(Calendar.HOUR_OF_DAY);
                int minute=calendar.get(Calendar.MINUTE);
                // Calendarを使って現在の時間をミリ秒で取得
                //calendar.setTimeInMillis(System.currentTimeMillis());
                // 5秒後に設定
                //calendar.add(Calendar.SECOND, 30);
                timeDialog(hour,minute);
            }
        });
    }
    void timeDialog(int hour,int minute){
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c=Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                c.set(Calendar.MINUTE,minute);//明示的なBroadCast
                c.set(Calendar.SECOND,00);
                Intent intent = new Intent(getApplicationContext(),
                        AlarmBroadcastReceiver.class);
                PendingIntent pending = PendingIntent.getBroadcast(
                        getApplicationContext(), 0, intent, 0);
                // アラームをセットする
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                if(am != null){
                    am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pending);

                    Toast.makeText(getApplicationContext(),
                            "Set Alarm ", Toast.LENGTH_SHORT).show();
                }
            }
        },hour,minute,true);
        timePickerDialog.show();
    }

}