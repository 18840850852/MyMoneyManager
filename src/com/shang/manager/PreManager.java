package com.shang.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreManager {
	public static final String START = "START";	//��������
	public static final String PASS = "PASS";//��������
	public static final String START_FIRST="START_FIRST";//�Ƿ��ǵ�һ�ν������
	/**
	 * ��ȡ����
	 * @param context
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static boolean get(Context context, String name, boolean defaultValue) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		boolean value = prefs.getBoolean(name, defaultValue);
		return value;
	}
	public static String get(Context context, String name, String defaultValue) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		String value = prefs.getString(name, defaultValue);
		return value;
	} 
	
	/**
	 * �����û�����
	 * @param context
	 * @param name
	 * @param value
	 * @return
	 */
	public static boolean set(Context context, String name, boolean value) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = prefs.edit();
		editor.putBoolean(name, value);
		return editor.commit();	//�ύ
	}

	public static boolean set(Context context, String name, String value) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = prefs.edit();
		editor.putString(name, value);
		return editor.commit();	//�ύ
	}
	//�洢����
	public static boolean saveData(Context context,String name,List<String> data){
		SharedPreferences sp=context.getSharedPreferences(name,Context.MODE_PRIVATE);
		Editor editor=sp.edit();
		int size=data.size();
		editor.putInt("size",size);//�ȷ�һ��������֪����ν��Ŷ��ٸ�����
		for(int i=0;i<size;++i){
			editor.putString(String.valueOf(i), data.get(i));//һ��һ��������
		}
		editor.commit();
		return true;
	}
	//�����shared�д��������ݣ�Ҳ�Ǹ���shared������Ҳ���ǲ����е���ô���ж�
	public static ArrayList<String> getData(Context context,String name){
		ArrayList<String> list=new ArrayList<String>();
		SharedPreferences sp=context.getSharedPreferences(name,Context.MODE_PRIVATE);
		int size=sp.getInt("size",0);
		for(int i=0;i<size;++i){
			list.add(sp.getString(String.valueOf(i),""));
		}
		return list;
	}
	//�洢Ǯ�Ĵ洢��ʽ�ͽ��
	public static void saveWayData(Context con,Map<String,String> data){
		SharedPreferences sp=con.getSharedPreferences("shared_way",Context.MODE_PRIVATE);
		Editor editor=sp.edit();
		Set<String> data_set=data.keySet();
		for(String s:data_set){
			editor.putString(s,data.get(s));
		}
		editor.commit();
	}
	//��ȡǮ�����еĴ洢��ʽ
	public static Map<String,String> getWayData(Context con){
		SharedPreferences sp=con.getSharedPreferences("shared_way",Context.MODE_PRIVATE);
		return (Map<String, String>) sp.getAll();
	}
	//�洢��Ǯ���߳�Ǯ����Ŀ
	private static void saveItemData(Context con,String item_name,Set<String> data){
		SharedPreferences sp=con.getSharedPreferences("shared_item",Context.MODE_PRIVATE);
		Editor editor=sp.edit();
		editor.putStringSet(item_name, data);
		editor.commit();
	}
	//�洢��Ǯ����Ŀ
	public static void saveInItem(Context con,Set<String> data){
		saveItemData(con, "shared_in", data);
	}
	//�洢��Ǯ����Ŀ
	public static void saveOutItem(Context con,Set<String> data){
		saveItemData(con, "shared_out", data);
	}
	//��ý�Ǯ���߳�Ǯ����Ŀ
	public static Set<String> getItemData(Context con,String key){
		SharedPreferences sp=con.getSharedPreferences("shared_item",Context.MODE_PRIVATE);
		return sp.getStringSet(key,new HashSet<String>());
	}
	//��ý�Ǯ����Ŀ
	public static Set<String> getInItem(Context con){
		SharedPreferences sp=con.getSharedPreferences("shared_item",Context.MODE_PRIVATE);
		return sp.getStringSet("shared_in",new HashSet<String>());
	}
	//��ó�Ǯ����Ŀ
	public static Set<String> getOutItem(Context con){
		SharedPreferences sp=con.getSharedPreferences("shared_item",Context.MODE_PRIVATE);
		return sp.getStringSet("shared_out",new HashSet<String>());
	}
}
