package helper.main;

import java.util.ArrayList;
import java.util.List;

import timer.main.R;
import timer.main.S_01_TimerActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Methods {
	//
	static List<String> time_list;

	//
	static ArrayAdapter<String> adapter;

	public static int vibLength = 35;
	
	//
	public static enum DialogButtonTags {
		// dlg_quick_start.xml
		dlg_quick_start_cancel,
		
		// dlg_set_message.xml
		dlg_set_message_ok, dlg_set_message_cancel,
		
	}//public static enum DialogTags

	public static enum ButtonTags {
		// timer_history_actv.xml
		timer_history_actv_bt_back,
		
	}//public static enum ButtonTags
	
	public static void showDialog_setMessageText(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Set listeners
		 * 3. Show dialog
			----------------------------*/
		
		//
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_set_message);
		
		//
		dlg.setTitle("アラームのメッセージ");
		
		/*----------------------------
		 * 2. Set listeners
			----------------------------*/
		//
		Button btnOk = (Button) dlg.findViewById(R.id.dlg_set_message_ok);
		Button btnCancel = (Button) dlg.findViewById(R.id.dlg_set_message_cancel);
		
		// Tags
//		btnOk.setTag(S_01_TimerActivity.DialogButtonTag.dlg_set_message_ok);
//		btnCancel.setTag(S_01_TimerActivity.DialogButtonTag.dlg_set_message_cancel);
		btnOk.setTag(DialogButtonTags.dlg_set_message_ok);
		btnCancel.setTag(DialogButtonTags.dlg_set_message_cancel);
		
		//
		btnOk.setOnClickListener(new DialogButtonClickListener(actv, dlg));
		btnCancel.setOnClickListener(new DialogButtonClickListener(actv, dlg));
		
		/*----------------------------
		 * 3. Show dialog
			----------------------------*/
		//
		dlg.show();
		
	}//public static void showDialog_setMessageText(Activity actv)

	public static void setMessage(Activity actv, Dialog dlg) {
		//
		EditText et = (EditText) dlg.findViewById(R.id.editText1);
		
		//
		S_01_TimerActivity.alarmMessage = et.getText().toString();
		
		//
		dlg.dismiss();
		
		// debug
		Toast.makeText(actv, 
				"Text set => " + et.getText().toString(), Toast.LENGTH_SHORT).show();
		
	}//public static void setMessage(actv, dlg)

	public static void dlg_quickStart(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Set list
		 * 		1. Prepare data
		 * 		2. Set data to adapter
		 * 		3. Adapter to list view
		 * 3. Set listeners
		 * 		3.1. List item
		 * 		3.2. Cancel button
		 * 4. Show dialog
			----------------------------*/
		
		//
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_quick_start);
		
		//
		dlg.setTitle(actv.getString(R.string.dlg_quick_start_title));
		
		/*----------------------------
		 * 2.1. Prepare data
			----------------------------*/
//		int[] times = {5, 10, 15, 30, 60};
		int[] times = {1, 3, 5, 10, 15, 20, 30, 45};		// Unit => minutes
		
		time_list = new ArrayList<String>();
		
		for (int i = 0; i < times.length; i++) {
			time_list.add(String.valueOf(times[i]));
		}//for (int i = 0; i < times.length; i++)
		
		/*----------------------------
		 * 2. 2. Set data to adapter
			----------------------------*/
		adapter = new ArrayAdapter<String>(
				actv,
				android.R.layout.simple_list_item_1,
				time_list
				);
		
		
		/*----------------------------
		 * 2.3. Adapter to list view
			----------------------------*/
		//
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_quick_start_lv_list);
		
		lv.setAdapter(adapter);
		
		/*----------------------------
		 * 3. Set listeners
			----------------------------*/
		/*----------------------------
		 * 3.1. List item
			----------------------------*/
		//
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg));
		
		/*----------------------------
		 * 3.2. Cancel button
			----------------------------*/
		//
		Button bt_cancel = (Button) dlg.findViewById(R.id.dlg_quick_start_bt_cancel);
		
		//
		bt_cancel.setTag(DialogButtonTags.dlg_quick_start_cancel);
		
		bt_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		bt_cancel.setOnClickListener(new DialogButtonClickListener(actv, dlg));
		/*----------------------------
		 * 4. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_quickStart(Activity actv)
	
	public static void startTimer(Activity actv, int time_len) {
		//
		S_01_TimerActivity.btnStart = (Button) actv.findViewById(R.id.buttonStart);
		
		//
						/*----------------------------
				 * Steps
				 * 0. Set up
				 * 1. Start service
				 * 2. Button status
					----------------------------*/
				/*----------------------------
				 * 0. Set up
					----------------------------*/
				//
//				S_01_TimerActivity.timeLeft = time_len;
				S_01_TimerActivity.timeLeft = time_len * 60;
		
		
				// TODO 自動生成されたメソッド・スタブ
				//
				Intent i = new Intent(S_01_TimerActivity.mContext, TimerService.class);

				//
				i.putExtra("counter", S_01_TimerActivity.timeLeft);
				
				//
				S_01_TimerActivity.mContext.startService(i);
//				actv.startService(i);		//=> 異なるメソッドで定義されたインナー・クラス内で非 final 変数 actv を参照できません
				
				/*----------------------------
				 * 2. Button status
					----------------------------*/
				//
				S_01_TimerActivity.btnStart.setEnabled(false);
				S_01_TimerActivity.btnStop.setEnabled(true);
				S_01_TimerActivity.sb.setEnabled(false);
				
				S_01_TimerActivity.btnDecrease.setImageResource(R.drawable.decrease_disenabled_70x70);
				S_01_TimerActivity.btnIncrease.setImageResource(R.drawable.inccrease_disenabled_70x70);
				
				S_01_TimerActivity.btnDecrease.setEnabled(false);
				S_01_TimerActivity.btnIncrease.setEnabled(false);
				
//				btnDecrease.setEnabled(false);
//				btnIncrease.setEnabled(false);
				
	}//public static void startTimer(Activity actv, int time_len)
}//public class Methods
