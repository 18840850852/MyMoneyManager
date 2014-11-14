package com.main;

import help.DataItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.shang.adapter.NewWayAdapter;
import com.shang.manager.DatabaseManager;
import com.shang.manager.PreManager;
import com.shang.moneymanager.MainActivity;
import com.shang.moneymanager.R;
/**
 * �����û���һ�ν��������Ҫ����Ľ���
 * ����������һ��gridview�����û������Լ�������Ǯ������Ͷ�Ӧ�Ľ��
 * ��һ��button����ʼ��������������
 * �ڵ��button�����û�������������������Ҫ�������ݿ��shared�ļ�
 * @author Administrator
 *
 */
public class StartActivity extends Activity{
	private GridView gv_way=null;
	private Button bt_start=null;
	private NewWayAdapter adapter_newWay=null;
	private ArrayList<Map<String,String>> data_ways=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		boolean isFirst=PreManager.get(getApplicationContext(), PreManager.START_FIRST, true);
		if(isFirst==false){//������ǵ�һ�ν��������ת����֤��������Ľ�����ȥ
			Intent intent=new Intent(this,UnlockGesturePasswordActivity.class);
			startActivity(intent);
			finish();
		}
		setContentView(R.layout.activity_start);
		//���ȳ�ʼ��������
		init();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		PreManager.set(getApplicationContext(), PreManager.START_FIRST, false);
	}
	/**
	 * �����Ǹ��ǵķ�activity�ķ���
	 */
	public void onStartClick(View v){
		//��ʼ���������棬���洢����
		if(judgeHasEmpty()==false){//���û�п�ֵ�Ļ����������ݿ⣬�洢����
			if(judgeHasSame()==false){
				onClickYes();
			}else{
//				showMoneyErrorDialog();
				Toast.makeText(getApplicationContext(), "��д��֧����ʽ���ظ�", 1000).show();
			}
		}else{//����п�ֵ�Ļ�����ʾ��ʾ��
			onShowDialogClick();
		}
	}
	
	/**
	 * �������Լ�����ķ����������汻����
	 */
	private void initData(){
		data_ways=new ArrayList<Map<String,String>>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("way", "�ֽ�");
		map.put("number", "");
		data_ways.add(map);
	}
	private void initViews(){
		gv_way=(GridView)findViewById(R.id.gv_way);
		bt_start=(Button)findViewById(R.id.bt_start);
		adapter_newWay=new NewWayAdapter(getApplicationContext(), data_ways);
		gv_way.setAdapter(adapter_newWay);
	}
	private void init(){
		initData();
		initViews();
	}
	//�ж��·�ʽ���Ƿ��пյ�ֵ
	private boolean judgeHasEmpty(){
		for(Map<String,String> map:data_ways){
			if(map.get("way").length()==0 || map.get("number").length()==0){
				return true;
			}
		}
		return false;
	}	
	//�ж��Ƿ����ظ��Ĵ洢��ʽ
	private boolean judgeHasSame(){
		List<String> item_name=new ArrayList<String>();
		for(Map<String,String> map:data_ways){
			if(item_name.contains(map.get("way"))==false){
				item_name.add(map.get("way"));
			}else{
				return true;
			}
		}
		return false;
	}
	//�ж������Ƿ����
	private boolean judgeNumberIllegal(){
		int number=0;
		for(Map<String,String> map:data_ways){
			try{
				number=Integer.parseInt(map.get("number"));
			}catch(NumberFormatException e){
				return false;
			}
			if(number<0){
				return false;
			}
		}
		return true;
	}
	//�洢way�Ͷ�Ӧ�Ľ��
	private void savePreData(){
		Map<String,String> way_number=new HashMap<String, String>();
		for(Map<String,String> map:data_ways){
			way_number.put(map.get("way"), map.get("number"));
		}
		PreManager.saveWayData(getApplicationContext(), way_number);
	}
	//�����ڵ���һ�����ǿյ�ʱ����ʾһ�����ѿ�
	 public void onShowDialogClick(){  
		   
        AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);  
        builder.setTitle("�������ݴ��ڿ�ֵ���Ƿ��Զ�����");  
        builder.setPositiveButton("��", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				onClickYes();
			}
        });
        builder.setNegativeButton("����Ҫ����",new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			}
        });
        AlertDialog ad = builder.create();  
        ad.show();
    }  
/*	private void showMoneyErrorDialog(){
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)  
		  .setTitle("��ʾ") 
		  .setMessage("����Ľ�����")
		  .setIcon(android.R.drawable.ic_dialog_info)  
		  .setPositiveButton("֪����", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		})  
		  .show();
	}*/
	//�����ȷ���İ�ť��ʱ�򴥷��˺���
	private void onClickYes(){
		new CreateDataBaseTask().execute();
	}
	//һ���µ��߳������������ݿ�
	private class CreateDataBaseTask extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			//�����ļ��洢
			savePreData();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//������activity�����϶���
			startActivity(new Intent(StartActivity.this,MainActivity.class));
			finish();
		}
	}
	private void getData(){
		for(Map<String,String> map:data_ways){
			if(map.get("way").length()==0 || map.get("number").length()==0){
				continue;
			}
		}
	}
}
