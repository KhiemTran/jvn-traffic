package code.jvn.traffic.database;

import code.jvn.traffic.config;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ConfigDAO {
	 private static SQLiteDatabase database;
	 private MySQLiteHelper dbHelper;
	  
	 private static String[] allColumns = { MySQLiteHelper.COLUMN_IDCF,
	      MySQLiteHelper.COLUMN_IDDEVICECF,MySQLiteHelper.COLUMN_CALLCF, 
	      MySQLiteHelper.COLUMN_SIGNALCF,MySQLiteHelper.COLUMN_COIEF,
	      MySQLiteHelper.COLUMN_SMSCF,MySQLiteHelper.COLUMN_AMLUONGCF,
	      MySQLiteHelper.COLUMN_TUKHOACF};

	 public ConfigDAO(Context context) {
		    dbHelper = new MySQLiteHelper(context);
		  }

	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }

	 public void close() {
		    dbHelper.close();
		  }
	 public static Device_Config insertConfig(String idDevice,String call,String signal,
			 String coi,String sms,String amLuong,String tuKhoa) {
		  
		  ContentValues values = new ContentValues();
		  values.put(MySQLiteHelper.COLUMN_IDDEVICECF, idDevice);
		  values.put(MySQLiteHelper.COLUMN_CALLCF, call);
		  values.put(MySQLiteHelper.COLUMN_SIGNALCF, signal);
		  values.put(MySQLiteHelper.COLUMN_COIEF, coi);
		  values.put(MySQLiteHelper.COLUMN_SMSCF, sms);
		  values.put(MySQLiteHelper.COLUMN_AMLUONGCF, amLuong);
		  values.put(MySQLiteHelper.COLUMN_TUKHOACF, tuKhoa);
		  
		    int insertId = (int) database.insert(MySQLiteHelper.TABLE_COMMENTSCF, null,
		        values);
		    
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTSCF,
			        allColumns, MySQLiteHelper.COLUMN_IDCF + " = " + insertId, null,
			        null, null, null);
			    cursor.moveToFirst();
			    
			    Device_Config newComment = cursorToComment(cursor);
			    cursor.close();
			    
			    
		    return newComment;
		   
	  }

	  public void deleteConfig(Device_Config config) {
	    int id = config.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_COMMENTSCF, MySQLiteHelper.COLUMN_IDCF
	        + " = " + id, null);
	  }

	  public  Device_Config getConfig(int id) {
		  Device_Config configs = new Device_Config();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTSCF,
	        allColumns, "Select * from TABLE_PHONEUSERS where _id="+id, null, null, null, null);
	    while (!cursor.isAfterLast()) {
	    	Device_Config config = cursorToComment(cursor);
	    	configs=(config);
	      cursor.moveToNext();
	    }
	   
	    return configs;
	  }

	  private static Device_Config cursorToComment(Cursor cursor) {
		  Device_Config comment = new Device_Config();
	    comment.setId(cursor.getInt(0));
	    comment.setIdDevice(1);
	    comment.setcall(cursor.getInt(2));
	    comment.setSinal(cursor.getInt(3));
	    comment.setCoi(cursor.getInt(4));
	    comment.setSMS(cursor.getInt(5));
	    comment.setAmLuong(cursor.getInt(6));
	    comment.setTuKhoa(cursor.getInt(7));
	    return comment;
	  }
}
