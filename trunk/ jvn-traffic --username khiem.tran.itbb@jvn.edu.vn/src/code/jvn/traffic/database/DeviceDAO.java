package code.jvn.traffic.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DeviceDAO {
	 private static SQLiteDatabase database;
	 private MySQLiteHelper dbHelper;
	  
	 private static String[] allColumns = { MySQLiteHelper.COLUMN_IDTB,
	      MySQLiteHelper.COLUMN_NAMETB,MySQLiteHelper.COLUMN_NUMBERTB};

	 public DeviceDAO(Context context) {
		    dbHelper = new MySQLiteHelper(context);
		  }

	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }

	 public void close() {
		    dbHelper.close();
		  }
	 public static Device insertDevice(String name,String phoneNumber) {
		  
		  ContentValues values = new ContentValues();
		  values.put(MySQLiteHelper.COLUMN_NAMETB, name);
		  values.put(MySQLiteHelper.COLUMN_NUMBERTB, phoneNumber);
		  int insertId = (int) database.insert(MySQLiteHelper.TABLE_COMMENTSTB, null,
		        values);
		  Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTSTB,
			        allColumns, MySQLiteHelper.COLUMN_IDTB + " = " + insertId, null,
			        null, null, null);
			    cursor.moveToFirst();
			    
			    Device newComment = cursorToComment(cursor);
			    cursor.close();
			    
			    
		    return newComment;
		   
	  }

	  public void deleteDevice(Device_Config config) {
	    int id = config.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_COMMENTSTB, MySQLiteHelper.COLUMN_IDTB
	        + " = " + id, null);
	  }
	  public static List<Device> getAllComments() {
		    List<Device> comments = new ArrayList<Device>();

		    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTSTB,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Device comment = cursorToComment(cursor);
		    	comments.add(comment);
		    	cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return comments;
		  }
	  public  static Device getDevice(int id) {
		 

		  Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTSTB,
			        allColumns, null, null,
			        null, null, null);
			    cursor.moveToFirst();
			    
			    Device newComment = cursorToComment(cursor);
			    cursor.close();
			    
			    
		    return newComment;
	   
	    
	  }

	  private static Device cursorToComment(Cursor cursor) {
		  Device comment = new Device();
	    comment.setId(cursor.getInt(0));
	    comment.setName(cursor.getString(1));
	    comment.setNumber(cursor.getString(2));
	    return comment;
	  }
}
