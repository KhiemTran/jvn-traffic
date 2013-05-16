package code.jvn.traffic.Provider.SMS;

import java.util.List;

import code.jvn.traffic.Global;

import android.telephony.SmsManager;
import android.widget.Toast;

public class SMS_Provider {
	static public int Send(String message, String phoneNumber)	
	{		
		//SmsManager smsManager = SmsManager.getDefault();
	    //smsManager.sendTextMessage(phoneNumber, null, message, null, null);
	    
		Toast.makeText(Global.appContext, message, 1000).show();
		return 1;
	}

	static public int SMS_KichHoat(String phoneNumber, String oldPin, String newPin, String pass)
	{		
		String message = pass + " KICHHOAT " + oldPin + " " + newPin;
		return Send(message, phoneNumber);	
	}

	static public int SMS_DangNhap(String phoneNumber, String pass)	
	{
		String message = pass + " DAKICHHOAT"; 
		return Send(message, phoneNumber);
	}	

	static public int SMS_ThemNguoiDung(String phoneNumber, List<String> numbers, String pass)	
	{
		String message = pass + " THEMSDT"; 
		for (String num : numbers) {
			message += " " + num;
		}
		return Send(message, phoneNumber);
	}	
	static public int SMS_XoaNguoiDung(String phoneNumber, List<String> numbers, String pass)	
	{
		String message = pass + " XOASDT"; 
		for (String num : numbers) {
			message += " " + num;
		}		
		return Send(message, phoneNumber);
	}	

	static public int SMS_CheDoBaoDong(String phoneNumber, String pass, String flags)	{
		String message = pass + " CDBD " + flags; 	
		return Send(message, phoneNumber);
	}
	
	static public int SMS_CoiTo(String phoneNumber,  String pass)
	{
		String message = pass + " COITO"; 	
		return Send(message, phoneNumber);		
	}

	static public int SMS_CoiNho(String phoneNumber,  String pass)
	{
		String message = pass + " COINHO"; 	
		return Send(message, phoneNumber);
		
	}
	static public int SMS_TrangThai(String phoneNumber, String pass)	{
		String message = pass + " trangthai";		
		return Send(message, phoneNumber);
	}
	
	static public int SMS_TimXe(String phoneNumber, String pass)	{
		String message = pass + " TIMXE";
		return Send(message, phoneNumber);
	}
	
	static public int SMS_SOS(String phoneNumber, String pass)	{
		String message = pass + " SOS";
		return Send(message, phoneNumber);
	}
	
	static public int SMS_TuKhoa(String phoneNumber, String pass)	{
		String message = pass + " TUKHOA";
		return Send(message, phoneNumber);
	}
	
	static public int SMS_TatTuKhoa(String phoneNumber, String pass)	{
		String message = pass + " TATTUKHOA";
		return Send(message, phoneNumber);
	}
	
	static public int SMS_BatDiaChi(String phoneNumber, String pass)	{
		String message = pass + " BATDIACHI";
		return Send(message, phoneNumber);
	}
	
	static public int SMS_NapTien(String phoneNumber, String pass, String code)	{
		String message = pass + " NAPTIEN " +  code;
		return Send(message, phoneNumber);
	}

	static public int SMS_TaiKhoan(String phoneNumber, String pass, String code)
	{
		String message = pass + " TAIKHOAN " + code;
		return Send(message, phoneNumber);
	}
	
	static public int SMS_DoiPin(String phoneNumber, String pass, String oldPin, String newPin)
	{
		String message = oldPin + " DOIPIN " +  newPin;
		return Send(message, phoneNumber);
	}
	
	static public int SMS_LayMatKhau(String phoneNumber, String pin)
	{
		String message = pin + " LAYMATKHAU";
		return Send(message, phoneNumber);
	}
	
	static public int SMS_DoiMatKhau(String phoneNumber, String oldPass, String newPass)
	{
		String message = oldPass + " DOIMATKHAU " +  newPass;
		return Send(message, phoneNumber);
	}
	
}
