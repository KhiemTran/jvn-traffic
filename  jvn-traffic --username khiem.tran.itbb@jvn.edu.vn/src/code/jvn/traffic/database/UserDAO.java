package code.jvn.traffic.database;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {
	// Database fields
	  private static SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private static String[] allColumns = { MySQLiteHelper.COLUMN_ID,
	      MySQLiteHelper.COLUMN_NAME,MySQLiteHelper.COLUMN_EMAIL, MySQLiteHelper.COLUMN_PASSWORD };

	  public UserDAO(Context context) {
		    dbHelper = new MySQLiteHelper(context);
		  }

		  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
		    dbHelper.close();
		  }
	  public static  User insertUser(String name,String email,String passWord) {
		  
		  ContentValues values = new ContentValues();
		  values.put(MySQLiteHelper.COLUMN_NAME, name);
		  values.put(MySQLiteHelper.COLUMN_EMAIL, email);
		  values.put(MySQLiteHelper.COLUMN_PASSWORD, passWord);
		    int insertId = (int) database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
		        values);
		    
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
		        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    User newComment = cursorToComment(cursor);
		    cursor.close();
		    return newComment;
	  }

	  public void deleteUser(User user) {
	    int id = user.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }

	  public  static User getAllUsers() {
	  
	   Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
		        allColumns, null, null,
		        null, null, null);
		    cursor.moveToFirst();
		    User newComment = cursorToComment(cursor);
		    cursor.close();
	    
	    
	    return newComment;
	  }

	  private static User cursorToComment(Cursor cursor) {
	    User comment = new User();
	    comment.setId(cursor.getInt(0));
	    comment.setName(cursor.getString(1));
	    comment.setEmail(cursor.getString(2));
	    comment.setPassword(cursor.getString(3));
	    return comment;
	  }
}
