package code.jvn.traffic;

import android.app.Activity;
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
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}