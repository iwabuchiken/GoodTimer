package helper.main;

import timer.main.R;
import timer.main.S_01_TimerActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Methods {

	public static void showDialog_setMessageText(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Set listeners
		 * 3. Show dialog
			----------------------------*/
		
		//
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_set_message);
		
		//
		dlg.setTitle("アラームのメッセージ");
		
		/*----------------------------
		 * 2. Set listeners
			----------------------------*/
		//
		Button btnOk = (Button) dlg.findViewById(R.id.dlg_set_message_ok);
		Button btnCancel = (Button) dlg.findViewById(R.id.dlg_set_message_cancel);
		
		// Tags
		btnOk.setTag(S_01_TimerActivity.DialogButtonTag.dlg_set_message_ok);
		btnCancel.setTag(S_01_TimerActivity.DialogButtonTag.dlg_set_message_cancel);
		
		//
		btnOk.setOnClickListener(new DialogButtonClickListener(actv, dlg));
		btnCancel.setOnClickListener(new DialogButtonClickListener(actv, dlg));
		
		/*----------------------------
		 * 3. Show dialog
			----------------------------*/
		//
		dlg.show();
		
	}//public static void showDialog_setMessageText(Activity actv)

	public static void setMessage(Activity actv, Dialog dlg) {
		//
		EditText et = (EditText) dlg.findViewById(R.id.editText1);
		
		//
		S_01_TimerActivity.alarmMessage = et.getText().toString();
		
		//
		dlg.dismiss();
		
		// debug
		Toast.makeText(actv, 
				"Text set => " + et.getText().toString(), Toast.LENGTH_SHORT).show();
		
	}//public static void setMessage(actv, dlg)

	public static void dlg_quickStart(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Set listeners
		 * 3. Show dialog
			----------------------------*/
		
		//
		Dialog dlg = new Dialog(actv);
		
		//
//		dlg.setContentView(R.layout);
		
		//
		dlg.setTitle("アラームのメッセージ");
		
		
	}//public static void dlg_quickStart(Activity actv)
	
}//public class Methods
