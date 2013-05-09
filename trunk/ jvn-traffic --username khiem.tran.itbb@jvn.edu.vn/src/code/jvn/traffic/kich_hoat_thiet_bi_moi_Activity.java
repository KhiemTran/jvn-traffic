package code.jvn.traffic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class kich_hoat_thiet_bi_moi_Activity extends Activity implements OnClickListener{
	
	Button btnBack;
	Button btnActive;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_thiet_bi);
        
        // set onclick listener
        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnActive = (Button)findViewById(R.id.btnActive);
        btnActive.setOnClickListener(this);
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
			case R.id.btnBack:
				onBackPressed();
				break;
			case R.id.btnActive:
				break;
		}
	}
}