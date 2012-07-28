package timer.main;

import helper.main.Methods;
import helper.main.TimerService;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class S_01_TimerActivity extends Activity {
	/******************************************************************* 
	 * Class members
	 *******************************************************************/
	//
	public static Context mContext;
	
	//
	public static SeekBar sb;

	// Buttons
	public static Button btnStart, btnStop;

//	static Button btnBack, btnForward;
	public static ImageButton btnDecrease, btnIncrease;
	
	// Time
	public static int timeLeft = 0;

	// Time set
	public static int timeSet = -1;
	
	// TextView tv
	static TextView tv;

	// 
	static GestureDetector gestureDetector;

	//
	public static String alarmMessage = null;

	public static Vibrator vib;
	
//	public enum DialogButtonTag {
//		//
//		dlg_set_message_ok, dlg_set_message_cancel
//		
//	}//public enum DialogButtonTag
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. SeekBar
		 * 3. Buttons => start, stop
		 * 4. TextView
		 * 4-2. Buttons => increase, decrease
		 * 4-3. GestureDetector
		 * 
		 * 5. Set listeners
		 * 6. Vibrator
			----------------------------*/
		/*----------------------------
		 * 1. Set up
			----------------------------*/
		// Super
        super.onCreate(savedInstanceState);
        
        // Set content
        setContentView(R.layout.main);
        
        
        // 
        mContext = this;
        
        /*----------------------------
		 * 2. SeekBar
			----------------------------*/
        // SeekBar
        sb = (SeekBar) findViewById(R.id.seekBar1);
        
        // Background
        sb.setBackgroundDrawable(drawScale());
        
        /*----------------------------
		 * 3. Buttons => start, stop
			----------------------------*/
		// Start
        btnStart = (Button) findViewById(R.id.buttonStart);
        
        // Stop
        btnStop = (Button) findViewById(R.id.buttonStop);
        
        /*----------------------------
		 * 4. TextView
			----------------------------*/
		//
        tv = (TextView) findViewById(R.id.textView1);
        
        /*----------------------------
		 * 4-2. Buttons => increase, decrease
			----------------------------*/
		// Backward
        btnDecrease = (ImageButton) findViewById(R.id.ib_progress_decrease);
        
        //
        if (timeLeft < 1) {
			btnDecrease.setEnabled(false);
		} else {//if (timeLeft < 1)
			btnDecrease.setEnabled(true);
		}//if (timeLeft < 1)
		
        
        // Forward
        btnIncrease = (ImageButton) findViewById(R.id.ib_progress_increase);
        
        //
        if (timeLeft < 900) {
        	//
			btnIncrease.setEnabled(true);
			
		} else {//if (timeLeft < 1)
			//
			btnIncrease.setEnabled(false);
			
		}//if (timeLeft < 1)
        
//        /*----------------------------
//		 * 4-3. GestureDetector
//			----------------------------*/
//		//
////        gestureDetector = new GestureDetector(new BasicGestureDetector(this));
//        gestureDetector = new GestureDetector(this, new BasicGestureDetector(this));
        
        /*----------------------------
		 * 5. Set listeners
			----------------------------*/
		setListeners();
		
		/*----------------------------
		 * 6. Vibrator
			----------------------------*/
		vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        
    }//public void onCreate(Bundle savedInstanceState)

	private void setListeners() {
		/*----------------------------
		 * Steps
		 * 1. SeekBar
		 * 2. Buttons => Decrease
		 * 3. Buttons => Increase
		 * 4. Swipe text view
		 * 
		 * 5. Buttons => Start
		 * 6. Buttons => Stop
		 * 
		 * 7. Preferences
			----------------------------*/
		/*----------------------------
		 * 1. SeekBar
			----------------------------*/
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				/*----------------------------
				 * Steps
				 * 1. Set time left
				 * 2. From user?
				 * 2-2. Set time left (NOT from user)
				 * 3. Enable buttons => start, stop
				 * 4. Enable buttons => increase, decrease
					----------------------------*/
				// Log
				Log.d("S_01_TimerActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "fromUser => " + fromUser);
				// Log
				Log.d("S_01_TimerActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "progress => " + progress);
				
					
				/*----------------------------
				 * 1. Set time left
					----------------------------*/
				timeLeft = progress * 60;
				
				// Log
				Log.d("S_01_TimerActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "progress => " + progress);
				
				/*----------------------------
				 * 2. // From user?
					----------------------------*/
				// From user?
				if (fromUser) {
					showTime(progress * 60);
				}//if (fromUser)

				/*----------------------------
				 * 2-2. Set time left (NOT from user)
					----------------------------*/
				showTime(progress * 60);
				
				/*----------------------------
				 * 3. Enable buttons
					----------------------------*/
				// Start
//				if (fromUser && (progress > 0)) {
				if (progress > 0) {
					btnStart.setEnabled(true);
				} else {//if (fromUser && (progress > 0))
					btnStart.setEnabled(false);
				}//if (fromUser && (progress > 0))
				
				// Stop
				if (progress == 0) {
					btnStop.setEnabled(false);
				}//if (progress == 0)
				
				/*----------------------------
				 * 4. Enable buttons => increase, decrease
					----------------------------*/
				// Backward
		        if (timeLeft < 1) {
					btnDecrease.setEnabled(false);
				} else {//if (timeLeft < 1)
					btnDecrease.setEnabled(true);
				}//if (timeLeft < 1)

		        //
		        if (timeLeft < 900) {
		        	//
					btnIncrease.setEnabled(true);
					
				} else {//if (timeLeft < 1)
					//
					btnIncrease.setEnabled(false);
					
				}//if (timeLeft < 1)
		        
				
			}//public void onProgressChanged()

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO 自動生成されたメソッド・スタブ
				
			}});
		
		/*----------------------------
		 * 2. Buttons => Decrease
			----------------------------*/
		// Decrease
		btnDecrease = (ImageButton) findViewById(R.id.ib_progress_decrease);
		
		//
		btnDecrease.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//
				sb.setProgress(sb.getProgress() - 1);
				
			}//public void onClick(View v)
		});

		/*----------------------------
		 * 3. Buttons => Increase
			----------------------------*/
		// Increase
		btnIncrease = (ImageButton) findViewById(R.id.ib_progress_increase);
		
		//
		btnIncrease.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//
				sb.setProgress(sb.getProgress() + 1);
				
			}//public void onClick(View v)
		});
		
//		/*----------------------------
//		 * 4. Swipe text view
//			----------------------------*/
//		//
//		TextView tv_swipe = (TextView) findViewById(R.id.swipe_view);
//		
//		//
//		tv_swipe.setOnTouchListener(new OnTouchListener(){
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				//
//				return gestureDetector.onTouchEvent(event);
//				
////				return false;
//			}//public boolean onTouch(View v, MotionEvent event)
//		});
		
		/*----------------------------
		 * 5. Buttons => Start
			----------------------------*/
		//
		btnStart = (Button) findViewById(R.id.buttonStart);
		
		//
		btnStart.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				/*----------------------------
				 * Steps
				 * 1. Start service
				 * 2. Button status
					----------------------------*/
				
				// TODO 自動生成されたメソッド・スタブ
				//
				Intent i = new Intent(mContext, TimerService.class);

				//
				i.putExtra("counter", timeLeft);
				
				//
				startService(i);
				
				/*----------------------------
				 * 2. Button status
					----------------------------*/
				//
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
				sb.setEnabled(false);
				
				btnDecrease.setImageResource(R.drawable.decrease_disenabled_70x70);
				btnIncrease.setImageResource(R.drawable.inccrease_disenabled_70x70);
				
				btnDecrease.setEnabled(false);
				btnIncrease.setEnabled(false);
				
//				btnDecrease.setEnabled(false);
//				btnIncrease.setEnabled(false);
				
			}//public void onClick(View v)
		});
		
		/*----------------------------
		 * 6. Buttons => Stop
			----------------------------*/
		//
		btnStop = (Button) findViewById(R.id.buttonStop);
		
		//
		btnStop.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				/*----------------------------
				 * Steps
				 * 1. Start service
				 * 2. Button status
					----------------------------*/
				
				// TODO 自動生成されたメソッド・スタブ
				//
				Intent i = new Intent(mContext, TimerService.class);
				
				//
				mContext.stopService(i);
				
				/*----------------------------
				 * 2. Button status
					----------------------------*/
				//
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				sb.setEnabled(true);

				btnDecrease.setImageResource(R.drawable.decrease_70x70);
				btnIncrease.setImageResource(R.drawable.increase_70x70);
				
				btnDecrease.setEnabled(true);
				btnIncrease.setEnabled(true);

//				btnDecrease.setEnabled(true);
//				btnIncrease.setEnabled(true);
			}//public void onClick(View v)
		});

		/*----------------------------
		 * 7. Preferences
			----------------------------*/
		//
		((Button) findViewById(R.id.buttonSettings)).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// 
				Intent i = new Intent(mContext, Preferences.class);
				
				//
				startActivity(i);
				
			}//public void onClick(View v)
		
		});//((Button) findViewById(R.id.buttonSettings)).setOnClickListener()
		
	}//private void setListeners()

	static void showTime(int timeSeconds) {
		// Format
		SimpleDateFormat form = new SimpleDateFormat("mm:ss");
		
		// Text view
//		tv.setText(form.format(timeLeft * 1000));
		tv.setText(form.format(timeSeconds * 1000));
		
		// Log
		Log.d("S_01_TimerActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "timeLeft => " + timeLeft + "/" + "timeLeft * 1000 => " + timeLeft * 1000);
		
		
	}//static void showTime(int timeSeconds)

	private BitmapDrawable drawScale() {
		/*----------------------------
		 * Steps
		 * 1. Members
		 * 2. Instantiate
		 * 3. Draw path
		 * 4. BitmapDrawable
		 * 5. Return
			----------------------------*/
		
		// Members
		Paint paint;
		Path path;
		Canvas canvas;
		Bitmap bitmap;
		
		/*----------------------------
		 * 2. Instantiate
			----------------------------*/
		// Paint
		paint = new Paint();
		
		// Set up
		paint.setStrokeWidth(3);
//		paint.setStyle(Paint.Style.STROKE);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);

		// Bitmap
		bitmap=Bitmap.createBitmap(241, 30, Bitmap.Config.ARGB_8888);
		
		// Path
		path = new Path();
		
		// Canvas
		canvas = new Canvas(bitmap);
		
		/*----------------------------
		 * 3. Draw path
			----------------------------*/
		//
		for (int i = 0; i < 17; i++) {
//		for (int i = 0; i < 16; i++) {
			// Reset path
			path.reset();
			
			// Set color
			if (i == 0 || i == 5 || i == 10 || i == 15) {
				// Set color
//				paint.setColor(Color.WHITE);
				paint.setColor(Color.YELLOW);
				
				path.moveTo(i * 16, 0);
				path.quadTo(i * 16, 0, i * 16, 50);
				
			} else {//if (i == 5 || i == 10 || i == 15)
				// Set color
				paint.setColor(Color.GRAY);
	
				path.moveTo(i * 16, 0);
				path.quadTo(i * 16, 0, i * 16, 15);

			}//if (i == 5 || i == 10 || i == 15)
			
			// Set stating point
//			path.moveTo(i * 16, 5);
//			path.moveTo(i * 16, 0);
			
			// Set quadratic
//			path.quadTo(i * 16, 5, i * 16, 15);
//			path.quadTo(i * 16, 0, i * 16, 15);
//			path.quadTo(i * 16, 0, i * 16, 30);
//			path.quadTo(i * 16, 0, i * 16, 50);
			
			// Draw path on canvas
			canvas.drawPath(path, paint);
			
		}//for (int i = 0; i < 17; i++)
		
		/*----------------------------
		 * 4. BitmapDrawable
			----------------------------*/
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		
		/*----------------------------
		 * 5. Return
			----------------------------*/
		return bd;
		
	}//private BitmapDrawable drawScale()

	public static  void countdown(int counter) {
			/*************************
			 * Steps
			 * 1. 
			 *************************/
			// Log
			Log.d("S_01_TimerActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "counter => " + counter);
			
		
			//
			showTime(counter);
			
			//
			timeLeft = counter;
			
			// Set progress
			if (counter % 60 == 0) {
				//
				sb.setProgress(counter / 60);
				
			} else {//if (counter % 60 == 0)
				//
				sb.setProgress(counter / 60 + 1);
				
			}//if (counter % 60 == 0)
			
			//
			
			
			
		}//  void countdown(int counter)

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
		// Log
		Log.d("S_01_TimerActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onResume => Started");
		
	}//protected void onResume()

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}//public boolean onCreateOptionsMenu(Menu menu)

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		/*----------------------------
		 * Steps
		 * 1. 
			----------------------------*/
		
		vib.vibrate(Methods.vibLength);
		
        switch (item.getItemId()) {
			/*----------------------------
			 * Steps
			 * 1. 
			 * 9. Default
				----------------------------*/
        	/*----------------------------
			 * 1. case 0	=> 
				----------------------------*/
            case R.id.menu_main_create_message://--------------------------------
            	//
            	Methods.showDialog_setMessageText(this);
            	
            	break;//case 0
            
            case R.id.menu_main_quick_start://---------------------------------------
            	//
            	Methods.dlg_quickStart(this);
            	
            	break;//case 0
            
            case R.id.menu_main_timer_history://---------------------------------------
            	
            	Intent i = new Intent();
            	
            	i.setClass(this, TimerHistoryActivity.class);
            	
            	startActivity(i);
            	
            	break;
            	
        }//switch (item.getItemId())
        
		return true;
    }//public boolean onOptionsItemSelected(MenuItem item)

}//public class S_01_TimerActivity extends Activity
