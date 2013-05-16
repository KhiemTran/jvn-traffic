package code.jvn.traffic;

import java.util.ArrayList;
import java.util.Properties;

import code.jvn.traffic.Provider.SMS.SMS_Provider;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
public class kich_hoat_thiet_bi_moi_Activity extends Activity implements OnClickListener{
	
	Button btnBack;
	Button btnActive;
				
	private ProgressDialog progressDialog; 
	
	MyBaseAdapter mba;
	ArrayList<String> title = new ArrayList<String>();
	String[] content = new String[6];
	ListView myList ;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kich_hoat_thiet_bi_moi);
        
        // set onclick listener
        myList = (ListView) findViewById(R.id.listView1);
        mba = new MyBaseAdapter(this,title);
        myList.setAdapter(mba);
        myList.setCacheColorHint(0);
        mba.addtoList("Số điện thoại thiết bị");
        mba.addtoList("Mã Pin cũ");
        mba.addtoList("Mã Pin mới");
        mba.addtoList("Password hiện tại");
        mba.addtoList("Tên thiết bị");
        mba.addtoList("Email");
        mba.notifyDataSetChanged();
        
        btnBack = (Button)findViewById(R.id.btn_Back);
        btnBack.setOnClickListener(this);
        btnActive = (Button)findViewById(R.id.btnActive);
        btnActive.setOnClickListener(this);
    }
    

	public class MyBaseAdapter extends BaseAdapter {
		ArrayList<String> arraylist = new ArrayList<String>();
		private LayoutInflater mInflater;
		public MyBaseAdapter(Context context, ArrayList<String> arraylist) {
			mInflater = LayoutInflater.from(context);
			this.arraylist = arraylist;

		}
		public int getCount() {
			return arraylist.size();
		}
		
		public String getItem(int position) {
			// TODO Auto-generated method stub
			String data;
			
			for (int i=0;i<position;i++)
			{
				EditText et = (EditText)this.getView(i,null,null).findViewById(R.id.editText);
				content[i] = et.getText().toString();
			}
			return null;
		}
		public void UpdateList(ArrayList<String> als) {
			arraylist = als;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public void remove(int i) {
			arraylist.remove(i);

		}
		public void addtoList(String str) {
			arraylist.add(str);
		}
		public View getView(int position, View convertView, ViewGroup parent) {
		    // Initialize the convertView here...
			LayoutInflater inflater = getLayoutInflater();
			View row;
			row = inflater.inflate(R.layout.rowlayout, parent,false);
		    
		    //\Button button = (Button) convertView.findViewById(R.id.button);
		    TextView tv = (TextView)row.findViewById(R.id.label);
		    tv.setText(arraylist.get(position));
		   
		    EditText et = (EditText)row.findViewById(R.id.editText);
		    et.setId(position);
		    if (position==0)
		    {
		    	et.setInputType(InputType.TYPE_CLASS_NUMBER);
		    }
		    else if (position ==1)
		    {
		    	et.setInputType (InputType.TYPE_CLASS_NUMBER);
		    	et.setTransformationMethod(PasswordTransformationMethod.getInstance());
		    }
		    else if (position ==2)
		    {
		    	et.setInputType (InputType.TYPE_CLASS_NUMBER);
		    	et.setTransformationMethod(PasswordTransformationMethod.getInstance());
		    }
		    else if (position ==3)
		    {
		    	et.setInputType (InputType.TYPE_CLASS_NUMBER);
		    	et.setTransformationMethod(PasswordTransformationMethod.getInstance());
		    }
		    else if (position ==5)
		    {
		    	 et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		    	
		    }

		    if (content!=null)
		    {
		    	et.setText(content[position]);
		    }
		    et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if (hasFocus==false)
					{
					String value = ((EditText)v).getText().toString();
					content[v.getId()] = value;
					}
				}
			});
//		    layout.setOnClickListener(new View.OnClickListener() {
//		        public void onClick(View v) {
//		       //     Toast.makeText(context, "Row clicked!", Toast.LENGTH_LONG).show();
//		        }
//		    });
//		    button.setOnClickListener(new View.OnClickListener() {
//		        public void onClick(View v) {
//		         //   Toast.makeText(context, "Button clicked!", Toast.LENGTH_LONG).show();
//		        }
//		    });
			return row;
		}
	}
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
			case R.id.btnBack:
				onBackPressed();
				break;
			case R.id.btnActive:
				for (int i=0;i<content.length;i++)
				{
					if (content[i] == null)
						content[i] = "";
				}
				
				for(int i=0;i<4;i++)
				{
					if(content[i] == "")
					{
						Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đu thông tin", 2000).show();
						return;
					}
				}
				
				if(content[4] == "")
				{
					content[4] = "No name";
				}
				
				String phoneNumber = content[0];
				String oldPin = content[1];
				String newPin = content[2];
				String pass = content[3];
				
				Global.kich_hoat_flag = 0;
				SMS_Provider.SMS_KichHoat(phoneNumber, oldPin, newPin, pass);
				
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
				        		  if(Global.kich_hoat_flag !=0)
				        			  i = iteration;
				        	  }
				        	  if (Global.kich_hoat_flag==0)
				          		  Global.kich_hoat_flag=2;
				        	  
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
        	progressDialog = ProgressDialog.show(kich_hoat_thiet_bi_moi_Activity.this,"Ä�ang xá»­ lÃ½...",  
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
                        if(Global.kich_hoat_flag != 0)
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
           
      	  
      	  switch(Global.kich_hoat_flag)
            {
	            case 1:
	            	// success in active ==> add device info into user contact
	            	//.....
	            	Global.kich_hoat_flag = 1; // return origin state
	            	
	            	// move to next activity
	            	Intent intent = new Intent(kich_hoat_thiet_bi_moi_Activity.this, Nhat_UsersContact.class);
	            	startActivity(intent);	            	
	            	break;
	            case 2:
	            	Toast.makeText(getApplicationContext(), "Thiết bị không phan hồi", 1000).show();
	            	Global.kich_hoat_flag = 1; // return origin state
	            	break;
	            case 3:
	            	Toast.makeText(getApplicationContext(), "Thông số kích hoạt không chính xác", 1000).show();
	            	Global.kich_hoat_flag = 1; // return origin state
	            	break;
            }
        }  
    }  
}