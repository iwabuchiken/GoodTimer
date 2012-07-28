package helper.main;

import timer.main.S_01_TimerActivity;
import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;

public class ButtonOnClickListener implements OnClickListener{

	//
	Activity actv;

	public static Vibrator vib;
	
	public ButtonOnClickListener(Activity actv) {
		//
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}//public DialogButtonClickListener(Activity actv)

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		/*----------------------------
		 * 1. Get the tag
			----------------------------*/

		Methods.ButtonTags tagName = (Methods.ButtonTags) v.getTag();
		
		vib.vibrate(Methods.vibLength);
		
		//
		switch (tagName) {
			case timer_history_actv_bt_back://------------------------------------------------------
				
				actv.finish();
				
				break;// dlg_set_message_ok

			default:
				break;
		}//switch (tagName)
		
	}//public void onClick(View v)

}
