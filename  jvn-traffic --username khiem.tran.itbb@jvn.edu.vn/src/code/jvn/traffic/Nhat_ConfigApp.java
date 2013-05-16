package code.jvn.traffic;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Nhat_ConfigApp extends Activity implements OnClickListener  {

	Button btBack;
	Button btLuuLai;
	Button btBoQua;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nhat__config_app);
		btBack = (Button)findViewById(R.id.buttonBack);
		btBack.setOnClickListener(this);
		btLuuLai = (Button)findViewById(R.id.btnLuuLai);
		btLuuLai.setOnClickListener(this);
		btBoQua = (Button)findViewById(R.id.btnLuuLai);
		btBoQua.setOnClickListener(this);
		
	}

	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
			case R.id.buttonBack:
				onBackPressed();
				break;
			case R.id.btnLuuLai:
				break;
			case R.id.btnBoQua:	
				break;
			
			
		}
	}

}
