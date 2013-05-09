package code.jvn.traffic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class dang_nhap_thiet_bi_Activity extends Activity implements OnClickListener{
	
	Button btnBack;
	Button btnLogin;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_thiet_bi);
        
        // set onclick listener
        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
			case R.id.btnBack:
				onBackPressed();
				break;
			case R.id.btnLogin:
				break;
		}
	}
}