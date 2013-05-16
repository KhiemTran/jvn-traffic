package code.jvn.traffic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class JVN_TrafficActivity extends Activity implements OnClickListener{
	
	ImageButton btnAccount;
	ImageButton btnSetting;
	ImageButton btnSOS;
	ImageButton btnSearch;
	ImageButton btnLock;
	ImageButton btnStatus;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //
        btnAccount = (ImageButton)findViewById(R.id.imageAccount);
        btnAccount.setOnClickListener(this);
        btnSetting = (ImageButton)findViewById(R.id.imageSetting);
        btnSetting.setOnClickListener(this);
        btnSOS = (ImageButton)findViewById(R.id.imageSOS);
        btnSOS.setOnClickListener(this);
        btnSearch = (ImageButton)findViewById(R.id.imageSearch);
        btnSearch.setOnClickListener(this);
        btnLock = (ImageButton)findViewById(R.id.imageLock);
        btnLock.setOnClickListener(this);
        btnStatus = (ImageButton)findViewById(R.id.imageStatus);
        btnStatus.setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.imageAccount:
			break;
		case R.id.imageSetting:
			break;
		case R.id.imageSOS:
			break;
		case R.id.imageSearch:
			break;
		case R.id.imageLock:
			break;
		case R.id.imageStatus:
			break;
		}
	}
}