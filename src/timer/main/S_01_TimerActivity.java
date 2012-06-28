package timer.main;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class S_01_TimerActivity extends Activity {
	/******************************************************************* 
	 * Class members
	 *******************************************************************/
	//
	static Context mContext;
	
	//
	static SeekBar sb;

	// Buttons
	static Button btnStart, btnStop;

	static Button btnBack, btnForward;
	// Time
	static int timeLeft = 0;

	// TextView tv
	static TextView tv;
	
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
		 * 
		 * 5. Set listeners
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
		 * 3. Buttons
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
        btnBack = (Button) findViewById(R.id.button_progress_backward);
        
        //
        if (timeLeft < 1) {
			btnBack.setEnabled(false);
		} else {//if (timeLeft < 1)
			btnBack.setEnabled(true);
		}//if (timeLeft < 1)
		
        
        // Forward
        btnForward = (Button) findViewById(R.id.button_progress_forward);
        
        //
        if (timeLeft < 900) {
        	//
			btnForward.setEnabled(true);
			
		} else {//if (timeLeft < 1)
			//
			btnForward.setEnabled(false);
			
		}//if (timeLeft < 1)
        
        /*----------------------------
		 * 5. Set listeners
			----------------------------*/
		setListeners();
        
    }//public void onCreate(Bundle savedInstanceState)

	private void setListeners() {
		/*----------------------------
		 * Steps
		 * 1. SeekBar
		 * 2. Buttons => Decrease
		 * 3. Buttons => Increase
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
				if (fromUser && (progress > 0)) {
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
					btnBack.setEnabled(false);
				} else {//if (timeLeft < 1)
					btnBack.setEnabled(true);
				}//if (timeLeft < 1)

		        //
		        if (timeLeft < 900) {
		        	//
					btnForward.setEnabled(true);
					
				} else {//if (timeLeft < 1)
					//
					btnForward.setEnabled(false);
					
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
		Button btnBack = (Button) findViewById(R.id.button_progress_backward);
		
		//
		btnBack.setOnClickListener(new OnClickListener(){

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
		Button btnForward = (Button) findViewById(R.id.button_progress_forward);
		
		//
		btnForward.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//
				sb.setProgress(sb.getProgress() + 1);
				
			}//public void onClick(View v)
		});
		
		
		
	}//private void setListeners()

	static void showTime(int timeSeconds) {
		// Format
		SimpleDateFormat form = new SimpleDateFormat("mm:ss");
		
		// Text view
		tv.setText(form.format(timeLeft * 1000));
		
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
    
    
}//public class S_01_TimerActivity extends Activity
