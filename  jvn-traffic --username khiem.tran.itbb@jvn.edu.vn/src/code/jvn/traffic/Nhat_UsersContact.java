package code.jvn.traffic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Nhat_UsersContact extends Activity implements OnClickListener {

	Button btnUsersSave;
	private ProgressDialog progressDialog; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nguoi_dung);
		
		btnUsersSave = (Button)findViewById(R.id.btnLuuLai);
		btnUsersSave.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nguoi_dung, menu);
		return true;
	}
	final Context cont = this;
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		
			case R.id.btnLuuLai:
				startActivity(new Intent(cont, Nhat_LoadingScreenActivity.class));
				//System.exit(0);
				break;
			
		}
	}


}
