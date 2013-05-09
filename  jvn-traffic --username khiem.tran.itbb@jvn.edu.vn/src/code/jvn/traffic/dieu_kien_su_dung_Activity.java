package code.jvn.traffic;

import java.text.BreakIterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class dieu_kien_su_dung_Activity extends Activity implements OnClickListener {
    
	Button btnBack;
	Button btnAccept;
	Button btnQuit;
	
	boolean firsttime = true;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dieu_kien_su_dung);
        
        // set onclick listener
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		btnAccept = (Button)findViewById(R.id.btnok);
		btnAccept.setOnClickListener(this);
		btnQuit = (Button)findViewById(R.id.btncancel);
		btnQuit.setOnClickListener(this);
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
			case R.id.btnBack:
				if(firsttime == true)
				{
					System.exit(0);
				}
				else
				{
					
				}
				break;
			case R.id.btnok:
				if(firsttime == true)
				{
					Intent intent = new Intent(dieu_kien_su_dung_Activity.this, quan_ly_thiet_bi_Activity.class);
					startActivity(intent);
				}
				else
				{
					
				}
				break;
			case R.id.btncancel:
				System.exit(0);
				break;
		}
	}
}