package helper.main;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class ButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	
	
	public ButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		Methods.ButtonTags tag = (Methods.ButtonTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN://---------------------------------------------------
				switch (tag) {
					//
				case timer_history_actv_bt_back://--------------------------------
					//
					v.setBackgroundColor(Color.GRAY);
					
					break;
					
				}//switch (tag)
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP://---------------------------------------------------
			switch (tag) {
				//
				case timer_history_actv_bt_back:
					//
					v.setBackgroundColor(Color.WHITE);
					
					break;
					
				}//switch (tag)
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
