package helper.main;

import java.util.Timer;
import java.util.TimerTask;

import timer.main.S_01_TimerActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class TimerService extends Service {
	
	/******************************************************************* 
	 * Class members
	 *******************************************************************/
	//
	Context mContext;

	//
	int counter;

	//
	public PowerManager.WakeLock wl;
	
	//
	Timer timer;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// Log
		Log.d("TimerService.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onBind => Started");
		
		
		
		return null;
	}//public IBinder onBind(Intent arg0)

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart(intent, startId);
		
		// Log
		Log.d("TimerService.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onStart => Started" + " (startId=" + startId + ")");
		
		//
		mContext = this;
		
		//
		counter = intent.getIntExtra("counter", 0);
		
		// Log
		Log.d("TimerService.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "counter => " + counter);
		
		//
		if (counter != 0) {
			//
			PowerManager pm = 
						(PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
			
			//
			wl = pm.newWakeLock(
							PowerManager.SCREEN_DIM_WAKE_LOCK + 
								PowerManager.ON_AFTER_RELEASE, 
							"My Tag");
			
			//
			wl.acquire();
			
			//
			startTimer();
			
		}//if (counter != 0)
		
	}//public void onStart(Intent intent, int startId)

	@Override
	public void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
		
		// Log
		Log.d("TimerService.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onDestroy => Started");

		// debug
		Toast.makeText(TimerService.this, "Destroying service...", Toast.LENGTH_SHORT).show();

		//
		timer.cancel();
		
		//
		if (wl.isHeld()) {
			//
			wl.release();
			
			// Log
			Log.d("TimerService.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "wl => Released");
			
		}//if (wl.isHeld())
		
	}//public void onDestroy()

	@Override
	public void onCreate() {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate();
		
		// debug
		Toast.makeText(TimerService.this, "Service started", Toast.LENGTH_SHORT).show();
	}

	public  void startTimer( ) {
			/*************************
			 * Steps
			 * 1. 
			 *************************/
			//
			if (timer != null) {
				timer.cancel();
			}//if (timer != null)
			
			//
			timer = new Timer();
			
			// Handler
			final android.os.Handler handler = new android.os.Handler();
			
			// Thread
			timer.schedule(new TimerTask(){

						@Override
						public void run() {
							// Log
							Log.d("TimerService.java"
									+ "["
									+ Thread.currentThread().getStackTrace()[2]
											.getLineNumber() + "]", "Runnable => Starts");
							
							// 
							handler.post(new Runnable(){

								@Override
								public void run() {
									// 
									if (counter == -1) {
										//
										timer.cancel();
										
										//
										if (wl.isHeld()) {
											//
											wl.release();
											
											// Log
											Log.d("TimerService.java" + "["
													+ Thread.currentThread().getStackTrace()[2].getLineNumber()
													+ "]", "wl => Released");
											
										}//if (wl.isHeld())
										
										//
										showAlarm();
										
									} else {//if (counter == -1)
										//
										S_01_TimerActivity.countdown(counter);
										
										//
										counter = counter - 1;
										
									}//if (counter == -1)
									
									
								}//public void run()
								
							});//handler.post()
							
						}//public void run()
					}, 
					0, 1000);//timer.schedule()
			
			
	}//public  void startTimer( )

	  void showAlarm( ) {
			/*************************
			 * Steps
			 * 1. 
			 *************************/
			//
			// debug
			Toast.makeText(TimerService.this, "时到了!", Toast.LENGTH_SHORT).show();
			
		}//  void showAlarm( )
}//public class TimerService extends Service
