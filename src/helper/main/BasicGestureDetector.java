package helper.main;

import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class BasicGestureDetector extends SimpleOnGestureListener {

	//
	Activity actv;

	public BasicGestureDetector(Activity actv) {
		//
		this.actv = actv;

	}//public BasicGestureDetector(Activity actv)
	
	/****************************************
	 * Methods
	 ****************************************/
	public   boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		
			/*************************
			 * Steps
			 * 1. 
			 *************************/
			//
			// Log
			float diff = e2.getX() - e1.getX();
			
			Log.d("BasicGestureDetector.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "diff => " + diff );
			// Log
			Log.d("BasicGestureDetector.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "velocityX => " + velocityX);
			
			
		return false;
	}//public   boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	
}//public class BasicGestureDetector extends SimpleOnGestureListener
