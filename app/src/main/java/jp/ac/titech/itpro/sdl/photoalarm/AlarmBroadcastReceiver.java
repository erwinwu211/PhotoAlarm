package jp.ac.titech.itpro.sdl.photoalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // toast で受け取りを確認
        Intent intent2 = new Intent();
        intent2.setClass(context, Main2Activity.class);
        context.startActivity(intent2);
    }
}