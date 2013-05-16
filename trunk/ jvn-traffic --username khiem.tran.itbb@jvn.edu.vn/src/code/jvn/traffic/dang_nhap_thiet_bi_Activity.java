package code.jvn.traffic;

import code.jvn.traffic.Provider.SMS.SMS_Provider;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dang_nhap_thiet_bi_Activity extends Activity implements OnClickListener{
	
	Button btnBack;
	Button btnLogin;
	int flag = 0;		// 0: wait
						// 1: stop and success
						// 2: timeout
						// 3: stop and false
	private ProgressDialog progressDialog;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap_thiet_bi_cu);
        
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
				
				String phoneNumber = ((EditText)findViewById(R.id.ediPhoneNumber)).getText().toString();
				String pass = ((EditText)findViewById(R.id.ediPassword)).getText().toString();
				
				if(phoneNumber == null || phoneNumber == "")
				{
					Toast.makeText(getApplicationContext(), "Vui lòng nhập sdt thiết bị", 1000).show();
					return;
				}
				if(pass == null || pass == "")
				{
					Toast.makeText(getApplicationContext(), "Vui lòng nhập password thiết bị", 1000).show();
					return;
				}
				
				Global.dang_nhap_flag = 0;
				SMS_Provider.SMS_DangNhap(phoneNumber, pass);
				
				Thread thread = new Thread()
				{
				      @Override
				      public void run() {
				          try {
				        	  int period = 100;
				        	  int iteration = Global.wAITTING_TIME / period;
				        	  for (int i=0;i<iteration;i++)
				        	  {
				        		  sleep(period);
				        		  if(Global.dang_nhap_flag !=0)
				        			  i = iteration;
				        	  }
				        	  if (Global.dang_nhap_flag==0)
				          		  Global.dang_nhap_flag=2;
				        	  
				        	  if(Global.dang_nhap_flag == 1)
				        	  {
									// login success ==> add into user contact
									String name = ((EditText)findViewById(R.id.ediName)).getText().toString();
									String email = ((EditText)findViewById(R.id.editEmail)).getText().toString();
									if(name == null)
										name = "No name";
									if(email == null)
										email = "";
				        	  }
				        	  
				          } catch (InterruptedException e) {				          
				          }
				      }
				  };
				thread.start();
				new LoadViewTask().execute();
				break;
		}
	}
	
	private class LoadViewTask extends AsyncTask<Void, Integer, Void>  
    {  
        //Before running code in separate thread  
        @Override  
        protected void onPreExecute()  
        {  
        	progressDialog = ProgressDialog.show(dang_nhap_thiet_bi_Activity.this,"Ä�ang xá»­ lÃ½...",  
        		    "Vui lÃ²ng xin chá»�...", false, false);  
        }  
  
        //The code to be executed in a background thread.  
        @Override  
        protected Void doInBackground(Void... params)  
        {  
            /* This is just a code that delays the thread execution 4 times, 
             * during 850 milliseconds and updates the current progress. This 
             * is where the code that is going to be executed on a background 
             * thread must be placed. 
             */  
            try  
            {  
                //Get the current thread's token  
                synchronized (this)  
                {  
                    //Initialize an integer (that will act as a counter) to zero  
                    int counter = 0;  
                    //While the counter is smaller than four  
                    while(true)  
                    {  
                        //Wait 850 milliseconds  
                        this.wait(200);  
                        //Increment the counter  
                        counter++;  
                        if(Global.dang_nhap_flag != 0)
                        	break;
                        //Set the current progress.  
                        //This value is going to be passed to the onProgressUpdate() method.  
                        publishProgress(counter*25);  
                    }  
                }  
            }  
            catch (InterruptedException e)  
            {  
                e.printStackTrace();  
            }  
            return null;  
        }  
  
        //Update the progress  
        @Override  
        protected void onProgressUpdate(Integer... values)  
        {  
            //set the current progress of the progress dialog  
            progressDialog.setProgress(values[0]);  
        }  
  
        //after executing the code in the thread  
        @Override  
        protected void onPostExecute(Void result)  
        {  
            //close the progress dialog  
            progressDialog.dismiss();  
            //initialize the View  
            //setContentView(R.layout.activity_nhat__config_app);  

            switch(Global.dang_nhap_flag)
            {
	            case 1:
	            	Global.kich_hoat_flag = 1; // return origin state
	            	
	            	// move to next activity

	            	Intent intent = new Intent(dang_nhap_thiet_bi_Activity.this, Nhat_UsersContact.class);
	            	startActivity(intent);
	            	break;
	            case 2:
	            	Toast.makeText(getApplicationContext(), "Thiết bị không phan hồi", 1000).show();
	            	Global.kich_hoat_flag = 1; // return origin state
	            	break;
	            case 3:
	            	Toast.makeText(getApplicationContext(), "Thông số đăng nhập không chính xác", 1000).show();
	            	Global.kich_hoat_flag = 1; // return origin state
	            	break;
            }
        }  
    }  
}