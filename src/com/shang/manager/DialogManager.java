package com.shang.manager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shang.moneymanager.R;


public class DialogManager {

	/*public void popNewWay(Context con){
		  View v = LayoutInflater.from(con).inflate(R.layout.dialog_new_way, null);
		  final EditText ed = (EditText)v.findViewById(R.id.editname);
		  new AlertDialog.Builder(con)  
		  .setTitle((flag_in==true)?"�½�������Դ":"�½�������")  
		  .setIcon(android.R.drawable.ic_dialog_info)  
		  .setView(v)  
		  .setPositiveButton("ȷ��", listen)
		  .setNegativeButton("ȡ��", null)  
		  .show();
		  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(flag==0){
				if(judgeDiscuExists(ed.getText().toString())==false){//�ɹ�
					new AddDiscuTask().execute(ed.getText().toString());
				}else{
					Toast.makeText(getActivity(), "���������Ѿ�����	", 1000).show();
				}
				}else{
					if(judgeGroupExists(ed.getText().toString())==false){//�ɹ�
						new AddDiscuTask().execute(ed.getText().toString());
					}else{
						Toast.makeText(getActivity(), "����������Ѵ���	", 1000).show();
					}
				}
			}
		})  
		  
	  }*/
}
