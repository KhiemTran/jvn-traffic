package code.jvn.traffic.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_COMMENTS = "User";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_EMAIL = "email";
  public static final String COLUMN_PASSWORD = "password";
  
  public static final String TABLE_COMMENTSTB = "Device";
  public static final String COLUMN_IDTB = "_id";
  public static final String COLUMN_IDUser = "_Userid";
  public static final String COLUMN_NAMETB = "name";
  public static final String COLUMN_NUMBERTB = "number";
  
  public static final String TABLE_COMMENTSCF = "Config";
  public static final String COLUMN_IDCF = "_id";
  public static final String COLUMN_IDDEVICECF = "iddevice";
  public static final String COLUMN_CALLCF = "call";
  public static final String COLUMN_SIGNALCF = "signal";
  public static final String COLUMN_COIEF = "coi";
  public static final String COLUMN_SMSCF = "sms";
  public static final String COLUMN_AMLUONGCF = "amluong";
  public static final String COLUMN_TUKHOACF = "tukhoa";
  
  public static final String TABLE_PHONEUSERS = "PHONEUSER";
  public static final String COLUMN_IDPU = "_id";
  public static final String COLUMN_IDDEVICE = "iddevice";
  public static final String COLUMN_NUMBER1 = "number1";
  public static final String COLUMN_NUMBER2 = "number2";
  public static final String COLUMN_NUMBER3 = "number3";
  public static final String COLUMN_NUMBER4 = "number4";
  public static final String COLUMN_NUMBER5 = "number5";
  
  
  

  private static final String DATABASE_NAME = "jvnTraffic.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_COMMENTS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_NAME
      + " text not null ,"+COLUMN_EMAIL
      + " text not null ,"+COLUMN_PASSWORD
      + " text not null);";

  private static final String DATABASE_CREATEDEVICE = "create table "
      + TABLE_COMMENTSTB + "(" + COLUMN_IDTB
      + " integer primary key autoincrement, " + COLUMN_IDUser
      + " integer ," + COLUMN_NAMETB
      + " text not null ,"+COLUMN_NUMBERTB
      + " text not null);";

  private static final String DATABASE_CREATECONFIG = "create table "
      + TABLE_COMMENTSCF + "(" + COLUMN_IDCF
      + " integer primary key autoincrement, " + COLUMN_IDDEVICECF
      + " integer ,"+COLUMN_CALLCF
      + " text not null ,"+COLUMN_SIGNALCF
      + " text not null ,"+COLUMN_COIEF
      + " text not null ,"+COLUMN_SMSCF
      + " text not null ,"+COLUMN_AMLUONGCF
      + " text not null ,"+COLUMN_TUKHOACF
      + " text not null);";

  private static final String DATABASE_CREATEUSERSPHONE = "create table "
      + TABLE_PHONEUSERS + "(" + COLUMN_IDPU
      + " integer primary key autoincrement, " + COLUMN_IDDEVICE
      + " integer ,"+COLUMN_NUMBER1
      + " text not null ,"+COLUMN_NUMBER2
      + " text not null ,"+COLUMN_NUMBER3
      + " text not null ,"+COLUMN_NUMBER4
      + " text not null ,"+COLUMN_NUMBER5
      + " text not null);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
    database.execSQL(DATABASE_CREATEDEVICE);
    database.execSQL(DATABASE_CREATECONFIG);
    database.execSQL(DATABASE_CREATEUSERSPHONE);
    
    UserDAO.insertUser("admin", "", "1234");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
    onCreate(db);
  }

} 