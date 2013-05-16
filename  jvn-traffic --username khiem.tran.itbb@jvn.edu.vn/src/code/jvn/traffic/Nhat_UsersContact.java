package code.jvn.traffic;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Nhat_UsersContact extends Activity implements OnClickListener {

	int flag = 0;		// 0: wait
						// 1: stop and success
						//-1: stop and false
	Button btnUsersSave;
	Button btBack;
	private ProgressDialog progressDialog; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nguoi_dung);
		
		btnUsersSave = (Button)findViewById(R.id.btnLuuLai);
		btnUsersSave.setOnClickListener(this);
		btBack = (Button)findViewById(R.id.buttonBack);
		btBack.setOnClickListener(this);
	}

	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.buttonBack:
			System.exit(0);
			break;
			case R.id.btnLuuLai:
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
				        		  if(Global.nguoi_dung_flag !=0)
				        			  i = iteration;
				        	  }
				        	  if (Global.nguoi_dung_flag==0)
				          		  Global.nguoi_dung_flag=2;
				             
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
	        	progressDialog = ProgressDialog.show(Nhat_UsersContact.this,"Ä�ang xá»­ lÃ½...",  
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
	                        if(Global.nguoi_dung_flag != 0)
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
	            
	            switch (Global.nguoi_dung_flag)
	            {
		            case 1:
		            	Global.nguoi_dung_flag = 1; 	// return origin state
		            	
		            	Intent intent = new Intent(Nhat_UsersContact.this, Nhat_ConfigApp.class);
		            	startActivity(intent);
		            	break;
		            case 2:
		            	Toast t = Toast.makeText(getApplicationContext(), "Thiết bị không phan hồi", 1000);
		            	t.show();
		            	Global.nguoi_dung_flag = 1; 	// return origin state
		            	break;
		            case 3:
		            	Toast.makeText(getApplicationContext(), "Thông số không đúng", 1000).show();
		            	Global.nguoi_dung_flag = 1; 	// return origin state
		            	break;
	            }
	        }  
	    }  


}
