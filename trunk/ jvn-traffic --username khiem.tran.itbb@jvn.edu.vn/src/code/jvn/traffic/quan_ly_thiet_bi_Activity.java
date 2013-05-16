package code.jvn.traffic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class quan_ly_thiet_bi_Activity extends Activity implements OnClickListener{
	
	Button btnBack;
	Button btnActive;
	Button btnLogin;
	
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
			case R.id.btnActive:
				Intent intent1 = new Intent(quan_ly_thiet_bi_Activity.this, kich_hoat_thiet_bi_moi_Activity.class);
				startActivity(intent1);
				break;
			case R.id.btnLogin:
				Intent intent2 = new Intent(quan_ly_thiet_bi_Activity.this, dang_nhap_thiet_bi_Activity.class);
				startActivity(intent2);
				break;
		}
	}
}