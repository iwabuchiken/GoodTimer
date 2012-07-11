package helper.main;

import timer.main.R;
import timer.main.S_01_TimerActivity;
import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class DialogOnItemClickListener implements OnItemClickListener {
	//
	Activity actv;
	
	//
	Dialog dlg;

	public DialogOnItemClickListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	@Override
	public void onItemClick(AdapterView<?> parent, 
												View view, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get time length
		 * 2. Dismiss dialog
		 * 3. Set timeSet variable
		 * 3-2. Set alarmMessage
		 * 4. Start timer
			----------------------------*/
		
		// 
		String time_len = (String) parent.getItemAtPosition(position);
		
		// Log
		Log.d("DialogOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "time_len => " + time_len);
		
		//
		dlg.dismiss();
		
		/*----------------------------
		 * 3. Set timeSet variable
			----------------------------*/
		S_01_TimerActivity.timeSet = Integer.parseInt(time_len);
		
		/*----------------------------
		 * 3-2. Set alarmMessage
			----------------------------*/
		S_01_TimerActivity.alarmMessage = actv.getString(R.string.message_timeup) + 
														" : " + String.valueOf(time_len) + " " + "•ª";
		
		/*----------------------------
		 * 4. Start timer
			----------------------------*/
		Methods.startTimer(actv, Integer.parseInt(time_len));

	}//public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)

}
