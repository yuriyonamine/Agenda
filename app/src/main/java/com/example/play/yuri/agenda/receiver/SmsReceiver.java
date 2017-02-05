package com.example.play.yuri.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.play.yuri.agenda.R;
import com.example.play.yuri.agenda.dao.StudentDAO;

import static android.telephony.SmsMessage.createFromPdu;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String format = (String) intent.getSerializableExtra("format");

        SmsMessage sms = createFromPdu(pdu,format);
        String phone = sms.getDisplayOriginatingAddress();

        StudentDAO dao = new StudentDAO(context);
        if(dao.isStudent(phone)){
            Toast.makeText(context, "Chegou sms !", Toast.LENGTH_LONG).show();
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.msg);
            mediaPlayer.start();
        }

        dao.close();
    }
}