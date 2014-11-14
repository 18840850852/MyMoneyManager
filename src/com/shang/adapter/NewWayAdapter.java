package com.shang.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.shang.moneymanager.R;
/**
 * @author Administrator
 *�����½���ʽ��ʱ��ÿ�����յ�view��Ӧ��������
 *����������edittext��һ��ɾ����ť,���Ӱ�ť��Ҫ����
 */
public class NewWayAdapter extends BaseAdapter{
	private Context context=null;
	private ArrayList<Map<String,String>> data=null;
	private ViewHolder viewholder=null;
	
	//���캯��
	public NewWayAdapter(Context con,ArrayList<Map<String,String>> ways){
		this.context=con;
		this.data=ways;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=LayoutInflater.from(this.context).inflate(R.layout.item_new_way,null);
			viewholder = new ViewHolder();
			viewholder.edit_way=(EditText)convertView.findViewById(R.id.edit_way);
			viewholder.edit_number=(EditText)convertView.findViewById(R.id.edit_number);
			viewholder.iv_delete=(ImageButton)convertView.findViewById(R.id.iv_delete_item);
			viewholder.iv_add=(ImageButton)convertView.findViewById(R.id.iv_add_way);
			convertView.setTag(viewholder);
		}else{
			viewholder = (ViewHolder)convertView.getTag();
		}
		setData(position);
		return convertView;
	}
	private void setData(int posi){
		//�������ü�����
		viewholder.iv_delete.setClickable(false);
		viewholder.iv_delete.setOnClickListener(new DeleteListener(posi));		
		viewholder.iv_add.setOnClickListener(new AddListener());
		viewholder.edit_way.setText(data.get(posi).get("way"));
		viewholder.edit_way.addTextChangedListener(new EditWatcher(posi, true));
		viewholder.edit_number.setText(data.get(posi).get("number"));
		viewholder.edit_number.addTextChangedListener(new EditWatcher(posi, false));
	}
	private class ViewHolder{
		EditText edit_way,edit_number;
		ImageButton iv_delete,iv_add;
	}
	
	//������Ӱ�ť
	private void onClickAdd(){
		Map<String,String> map=new HashMap<String,String>();
		map.put("way", "");
		map.put("number", "");
		data.add(map);
		this.notifyDataSetChanged();
		System.out.println("������");
	}
	//����ɾ����ť,ɾ�����ݣ����½���
	private void onClickDelete(int posi){
		data.remove(posi);
		if(data.size()==0){
			Map<String,String> map=new HashMap<String,String>();
			map.put("way", "");
			map.put("number", "");
			data.add(map);
		}
		this.notifyDataSetChanged();
	}
	
	//�Զ����ɾ����ť�ļ������������ǵڼ�����λ��
	private class DeleteListener implements OnClickListener{
		int posi=-1;
		public DeleteListener(int position){
			this.posi=position;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			onClickDelete(posi);
		}
	}
	private class AddListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			onClickAdd();
		}
	}
	//�༭��ļ�����
	private class EditWatcher implements TextWatcher{
		int posi=-1;
		boolean way=true;
		public EditWatcher(int position,boolean flag) {
			this.posi=position;
			this.way=flag;
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			{
				data.get(posi).put(way==true?"way":"number", s.toString());
			}
		}
	}
	//�ж����ڵĴ洢��ʽ�ǲ����Ѿ�����
	private boolean judgeHasWay(String key){
		if(data.size()==1){
			return false;
		}
		for(Map<String,String> map:data){
			if(map.get("way").equals(key)){
				return true;
			}
		}
		return false;
	}
}
