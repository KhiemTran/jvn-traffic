package code.jvn.traffic.Provider.SMS;

import java.util.ArrayList;
import java.util.List;
 
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
 
public class SmsReceiver extends BroadcastReceiver {
    static ArrayList<SmsInfo> listSms = new ArrayList<SmsInfo>();
    @Override
    public void onReceive(Context context, Intent intent) {
        // get SMS map from intent
        Bundle extras = intent.getExtras();
        // a notification message
        String messages = "";
        if ( extras != null ) {
            // get array data from SMS
            Object[] smsExtra = (Object[]) extras.get( "pdus" ); // "pdus" is the key
 
            for ( int i = 0; i < smsExtra.length; ++i ) {
                // get sms message
                SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i]);
                // get content and number
                String body = sms.getMessageBody();
                String address = sms.getOriginatingAddress();
                // create display message
                messages += "SMS from " + address + " :\n";
                messages += body + "\n";
 
                // store in the list
                listSms.add(new SmsInfo(address, body));
            }
 
            // better check size before continue
            if(listSms.size() > 0) {

            }
        }
    }
}