package timer.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class AlarmDialog extends Activity {
	/******************************************************************* 
	 * Class members
	 *******************************************************************/
	//
	Vibrator vib;
	
	//
	Context mContext;
	
	//
	Ringtone rt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Content view
		setContentView(R.layout.alarmdialog);
	
		//
		mContext = this;
		
		// Initialize vibrator
		vib = (Vibrator) mContext.getSystemService(mContext.VIBRATOR_SERVICE);
		
		// Set listener
		((Activity) mContext).findViewById(R.id.button1).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// Stop ringtone
				if (rt != null) {
					//
					rt.stop();
					
				}//if (rt != null)
				
				// Stop vibrator
				vib.cancel();
				
				// Finish
				((Activity) mContext).finish();
				
			}//public void onClick(View v)
		});//((Activity) mContext).findViewById(R.id.button1).setOnClickListener()
		
		
		
		
		
	}//protected void onCreate(Bundle savedInstanceState)

	@Override
	protected void onResume() {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Prefs
			----------------------------*/
		
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
		/*----------------------------
		 * 2. Prefs
			----------------------------*/
		//
		SharedPreferences prefs;
		
		// Get prefs
//		prefs = this.getSharedPreferences("CountDownTimerPrefs", 0);
		prefs = this.getSharedPreferences("CountdownTimerPrefs", 0);
		
		// Get string
		String fn = prefs.getString("alarm", "");
		
		// Log
		Log.d("AlarmDialog.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "fn => " + fn);
		
		
		// Ringtone
		if (fn != "") {
			//
			rt = RingtoneManager.getRingtone(this, Uri.parse(fn));
		
			// Log
			Log.d("AlarmDialog.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "rt => " + rt.toString());
			
			
			// Play
			if (rt != null && !rt.isPlaying()) {
				// Play
				rt.play();
				
			}//if (rt != null && !rt.isPlaying())
			
		}//if (fn != "")
		
		// Vibrator
		if (prefs.getBoolean("vibrator", true)) {
			//
			vib.vibrate(new long[]{0, 1000, 500, 1000, 500, 1000}, -1);
			
		}//if (prefs.getBoolean("vibrator", true))
		
	}//protected void onResume()

}//public class AlarmDialog extends Activity
