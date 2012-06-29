package helper.main;

import timer.main.S_01_TimerActivity;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

public class DialogButtonClickListener implements OnClickListener{

	//
	Activity actv;
	
	//
	Dialog dlg;

	public DialogButtonClickListener(Activity actv) {
		//
		this.actv = actv;

	}//public DialogButtonClickListener(Activity actv)

	public DialogButtonClickListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;

	}//public DialogButtonClickListener(Activity actv)

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		/*----------------------------
		 * 1. Get the tag
			----------------------------*/
		//
		S_01_TimerActivity.DialogButtonTag tagName = 
								(S_01_TimerActivity.DialogButtonTag) v.getTag();
		
		//
		switch (tagName) {
			/*----------------------------
			 * 1. dlg_set_message_ok
			 * 2. dlg_set_message_cancel
				----------------------------*/
			/*----------------------------
			 * 1. dlg_set_message_ok
				----------------------------*/
			case dlg_set_message_ok:
				//
				Methods.setMessage(actv, dlg);
				
			break;// dlg_set_message_ok

			case dlg_set_message_cancel:
				//
				dlg.dismiss();
				
			break;// dlg_set_message_cancel

		}//switch (tagName)
		
	}//public void onClick(View v)

}
