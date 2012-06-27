package timer.main;

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
import android.widget.SeekBar;

public class S_01_TimerActivity extends Activity {
	/******************************************************************* 
	 * Class members
	 *******************************************************************/
	//
	Context mContext;
	
	//
	SeekBar sb;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //
        mContext = this;
    
        //
        sb = (SeekBar) findViewById(R.id.seekBar1);
        
        sb.setBackgroundDrawable(drawScale());
        
        
    }//public void onCreate(Bundle savedInstanceState)

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
				
			} else {//if (i == 5 || i == 10 || i == 15)
				// Set color
				paint.setColor(Color.GRAY);
				
			}//if (i == 5 || i == 10 || i == 15)
			
			// Set stating point
//			path.moveTo(i * 16, 5);
			path.moveTo(i * 16, 0);
			
			// Set quadratic
//			path.quadTo(i * 16, 5, i * 16, 15);
//			path.quadTo(i * 16, 0, i * 16, 15);
//			path.quadTo(i * 16, 0, i * 16, 30);
			path.quadTo(i * 16, 0, i * 16, 50);
			
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
