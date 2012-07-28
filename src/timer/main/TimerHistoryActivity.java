package timer.main;

import helper.main.ButtonOnClickListener;
import helper.main.ButtonOnTouchListener;
import helper.main.Methods;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

public class TimerHistoryActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.timer_history_actv);
		
		initial_setup();

	}//public void onCreate(Bundle savedInstanceState)

	private void initial_setup() {
		/*----------------------------
		 * 1. Listeners
		 * 2. ListView => Set height
			----------------------------*/
		
		ListView lv = this.getListView();
		
		set_listeners();
		
		/*----------------------------
		 * 2. ListView => Set height
			----------------------------*/
		// Log
		Log.d("TimerHistoryActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "message");
		
		
		WindowManager wm = 
					(WindowManager)getSystemService(Context.WINDOW_SERVICE);
		

		int height = wm.getDefaultDisplay().getHeight();
		// Log
		Log.d("TimerHistoryActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "message");

//		lv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height / 4 * 3));
		
		// Log
		Log.d("TimerHistoryActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "message");
		
	}//private void initial_setup()

	private void set_listeners() {
		/*----------------------------
		 * 1. "Back" button
			----------------------------*/
		/*----------------------------
		 * 1. "Back" button
			----------------------------*/
		Button bt_back = (Button) findViewById(R.id.timer_history_actv_bt_back);
		
		bt_back.setTag(Methods.ButtonTags.timer_history_actv_bt_back);
		
		bt_back.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_back.setOnClickListener(new ButtonOnClickListener(this));
		
	}//private void set_listeners()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}
	
}//public class TimerHistoryActivity extends ListActivity
