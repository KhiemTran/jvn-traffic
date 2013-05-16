package code.jvn.traffic.Provider.SMS;

import code.jvn.traffic.Global;

public class SMS_Analysis {
	
	public static int Check_Active(String sms_body)
	{
		String str_success = "DAKICHHOAT";
		if(sms_body.contains(str_success) == true)
		{
			Global.kich_hoat_flag = 1;
			return 1;
		}
		else
			Global.kich_hoat_flag = 3;
		return 0;
	}
	public static int Check_Login(String sms_body)
	{
		String str_success = "DAKICHHOAT";
		if(sms_body.contains(str_success) == true)
		{
			Global.kich_hoat_flag = 1;
			return 1;
		}
		else
			Global.kich_hoat_flag = 3;
		return 0;
	}
}
