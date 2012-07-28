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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}
	
}//public class TimerHistoryActivity extends ListActivity
