package code.jvn.traffic;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Nhat_configApp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nhat_config_app);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nhat_config_app, menu);
		return true;
	}

}
