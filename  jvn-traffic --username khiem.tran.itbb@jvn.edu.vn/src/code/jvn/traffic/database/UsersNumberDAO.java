package code.jvn.traffic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UsersNumberDAO {
	 private static SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  
	  private static String[] allColumns = { MySQLiteHelper.COLUMN_IDPU,
	      MySQLiteHelper.COLUMN_IDDEVICE,MySQLiteHelper.COLUMN_NUMBER1, 
	      MySQLiteHelper.COLUMN_NUMBER2,MySQLiteHelper.COLUMN_NUMBER3,
	      MySQLiteHelper.COLUMN_NUMBER4,MySQLiteHelper.COLUMN_NUMBER5};

	  public UsersNumberDAO(Context context) {
		    dbHelper = new MySQLiteHelper(context);
		  }

		  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
		    dbHelper.close();
		  }
	  public static UsersNumber insertNumberUsers(String idDevice,String number1,String number2,String number3,String number4,String number5) {
		  
		  ContentValues values = new ContentValues();
		  values.put(MySQLiteHelper.COLUMN_IDDEVICE, idDevice);
		  values.put(MySQLiteHelper.COLUMN_NUMBER1, number1);
		  values.put(MySQLiteHelper.COLUMN_NUMBER2, number2);
		  values.put(MySQLiteHelper.COLUMN_NUMBER3, number3);
		  values.put(MySQLiteHelper.COLUMN_NUMBER4, number4);
		  values.put(MySQLiteHelper.COLUMN_NUMBER5, number5);
		    int insertId = (int) database.insert(MySQLiteHelper.TABLE_PHONEUSERS, null,
		        values);
		    
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_PHONEUSERS,
			        allColumns, MySQLiteHelper.COLUMN_IDPU + " = " + insertId, null,
			        null, null, null);
			    cursor.moveToFirst();
			    
			    UsersNumber newComment = cursorToComment(cursor);
			    cursor.close();
			    
			    
		    return newComment;
		   
	  }

	  public void deleteNumberUsers(UsersNumber usernumber) {
	    int id = usernumber.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_PHONEUSERS, MySQLiteHelper.COLUMN_IDPU
	        + " = " + id, null);
	  }

	  public static  UsersNumber getNumberUsers(int idDevice) {
		  UsersNumber users = new UsersNumber();

		  Cursor cursor = database.query(MySQLiteHelper.TABLE_PHONEUSERS,
			        allColumns, MySQLiteHelper.COLUMN_IDDEVICE + " = " + idDevice, null,
			        null, null, null);
			    cursor.moveToFirst();
			    cursor.moveToFirst();
			    
			    UsersNumber newComment = cursorToComment(cursor);
			    cursor.close();
	  
	   
	    return newComment;
	  }

	  private static UsersNumber cursorToComment(Cursor cursor) {
	    UsersNumber comment = new UsersNumber();
	    comment.setId(cursor.getInt(0));
	    comment.setIdDevice(1);
	    comment.setNumber1(cursor.getString(2));
	    comment.setNumber2(cursor.getString(3));
	    comment.setNumber3(cursor.getString(4));
	    comment.setNumber4(cursor.getString(5));
	    comment.setNumber5(cursor.getString(6));
	    return comment;
	  }
}
