package com.shang.manager;

import help.DataItem;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract.Contacts.Data;

/**
 * @author Administrator
 *�������ݿ�Ĺ�����
 *һ�����ݿ���ֻ��һ�ű�
 *�������ÿһ������
 *�����ݿ���еĲ�����������ɾ���ģ���
 *���иĿ��԰�һ������Ŀ�޸ģ���Ļ�Ҳ���԰���������
 */
public class DatabaseManager {
	//�����µ�һ��
	public static boolean insertNewIem(Context con,String item,String dir,String way,String number,String time,String note){
		ContentValues cv = new ContentValues();
		cv.put(DatabaseHelper.ITEM,item);
		cv.put(DatabaseHelper.DIR, dir);
		cv.put(DatabaseHelper.WAY,way);
		cv.put(DatabaseHelper.NUMBER,number);
		cv.put(DatabaseHelper.TIME, time);
		cv.put(DatabaseHelper.NOTE, note);
		DatabaseHelper sdh = new DatabaseHelper(con);
		SQLiteDatabase db = sdh.getWritableDatabase();
		long result=db.insert(DatabaseHelper.TABLE_NAME,null,cv);
		db.close();
		if(result==-1){
			return false;
		}
		return true;
	}
	//ɾ��һ��
	public static boolean deleteItem(Context con,String id){
		 DatabaseHelper sdh = new DatabaseHelper(con);
		 SQLiteDatabase db = sdh.getWritableDatabase();
		 int result=db.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.ID+"=?", new String[]{id});	
		 db.close();
		 if(result==0){
			 return false;
		 }
		 return true;
	}
	//����id�޸�һ����¼
	public static boolean updateItem(Context con,DataItem item){
		 DatabaseHelper sdh = new DatabaseHelper(con);
		 SQLiteDatabase db = sdh.getWritableDatabase();
		    ContentValues cv = new ContentValues();
			cv.put(DatabaseHelper.ITEM,item.item);
			cv.put(DatabaseHelper.DIR, item.dir);
			cv.put(DatabaseHelper.WAY,item.way);
			cv.put(DatabaseHelper.NUMBER,item.number);
			cv.put(DatabaseHelper.TIME, item.time);
			cv.put(DatabaseHelper.NOTE, item.note);
			int result=db.update(DatabaseHelper.TABLE_NAME, cv, DatabaseHelper.ID+"=?", new String[]{item.id});
			if(result<0){
				return false;
			}
			return true;
	}
	//���ݽ��ͳ��ķ�ʽ����ѯ��
	private static List<DataItem> queryByDir(Context con,String dir){
		 DatabaseHelper sdh = new DatabaseHelper(con);
		 SQLiteDatabase db = sdh.getReadableDatabase();
		 Cursor cur = db.query(DatabaseHelper.TABLE_NAME, null, DatabaseHelper.DIR+"=?", new String[]{dir}, null, null, DatabaseHelper.TIME);
		 List<DataItem> items = new ArrayList<DataItem>();
		 if(cur == null){db.close();return items;}
		 while(cur.moveToNext()){
			 DataItem item = new DataItem();
			 item.id = cur.getString(cur.getColumnIndex(DatabaseHelper.ID));
			 item.item = cur.getString(cur.getColumnIndex(DatabaseHelper.ITEM));
			 item.number = cur.getString(cur.getColumnIndex(DatabaseHelper.NUMBER));
			 item.way = cur.getString(cur.getColumnIndex(DatabaseHelper.WAY));
			 item.note = cur.getString(cur.getColumnIndex(DatabaseHelper.NOTE));
			 item.time=cur.getString(cur.getColumnIndex(DatabaseHelper.TIME));
			 item.dir=dir;
			 items.add(item);
		 }
		 cur.close();
		 db.close();
		 return items;
	}
	//���ݽ�����ѯ��
	public static List<DataItem> queryIn(Context con){
		return queryByDir(con,"in");
	}
	//���ݳ�����ѯ��
	public static List<DataItem> queryOut(Context con){
		return queryByDir(con,"out");
	}
	//����һ�����������ѯ��
	public static List<DataItem> queryByItem(Context con,String item_name){
		 DatabaseHelper sdh = new DatabaseHelper(con);
		 SQLiteDatabase db = sdh.getReadableDatabase();
		 Cursor cur = db.query(DatabaseHelper.TABLE_NAME, null, DatabaseHelper.ITEM+"=?", new String[]{item_name}, null, null, DatabaseHelper.TIME);
		 List<DataItem> items = new ArrayList<DataItem>();
		 if(cur == null){db.close();return items;}
		 while(cur.moveToNext()){
			 DataItem item = new DataItem();
			 item.id = cur.getString(cur.getColumnIndex(DatabaseHelper.ID));
			 item.dir = cur.getString(cur.getColumnIndex(DatabaseHelper.DIR));
			 item.number = cur.getString(cur.getColumnIndex(DatabaseHelper.NUMBER));
			 item.way = cur.getString(cur.getColumnIndex(DatabaseHelper.WAY));
			 item.note = cur.getString(cur.getColumnIndex(DatabaseHelper.NOTE));
			 item.time=cur.getString(cur.getColumnIndex(DatabaseHelper.TIME));
			 item.item=item_name;
			 items.add(item);
		 }
		 cur.close();
		 db.close();
		 return items;
	}
	//���ݷ�ʽ����ѯ��
	public static List<DataItem> queryByWay(Context con,String way){
		 DatabaseHelper sdh = new DatabaseHelper(con);
		 SQLiteDatabase db = sdh.getReadableDatabase();
		 Cursor cur = db.query(DatabaseHelper.TABLE_NAME, null, DatabaseHelper.WAY+"=?", new String[]{way}, null, null, DatabaseHelper.TIME);
		 List<DataItem> items = new ArrayList<DataItem>();
		 if(cur == null){db.close();return items;}
		 while(cur.moveToNext()){
			 DataItem item = new DataItem();
			 item.id = cur.getString(cur.getColumnIndex(DatabaseHelper.ID));
			 item.dir = cur.getString(cur.getColumnIndex(DatabaseHelper.DIR));
			 item.number = cur.getString(cur.getColumnIndex(DatabaseHelper.NUMBER));
			 item.item = cur.getString(cur.getColumnIndex(DatabaseHelper.ITEM));
			 item.note = cur.getString(cur.getColumnIndex(DatabaseHelper.NOTE));
			 item.time=cur.getString(cur.getColumnIndex(DatabaseHelper.TIME));
			 item.way=way;
			 items.add(item);
		 }
		 cur.close();
		 db.close();
		 return items;
	}
	//��ѯ���������ݿ���е�����
	public static List<DataItem> queryAll(Context con){
		DatabaseHelper sdh = new DatabaseHelper(con);
		 SQLiteDatabase db = sdh.getReadableDatabase();
		 Cursor cur = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, DatabaseHelper.TIME);
		 List<DataItem> items = new ArrayList<DataItem>();
		 if(cur == null){db.close();return items;}
		 while(cur.moveToNext()){
			 DataItem item = new DataItem();
			 item.id = cur.getString(cur.getColumnIndex(DatabaseHelper.ID));
			 item.dir = cur.getString(cur.getColumnIndex(DatabaseHelper.DIR));
			 item.number = cur.getString(cur.getColumnIndex(DatabaseHelper.NUMBER));
			 item.item = cur.getString(cur.getColumnIndex(DatabaseHelper.ITEM));
			 item.note = cur.getString(cur.getColumnIndex(DatabaseHelper.NOTE));
			 item.time=cur.getString(cur.getColumnIndex(DatabaseHelper.TIME));
			 item.way=cur.getString(cur.getColumnIndex(DatabaseHelper.WAY));
			 items.add(item);
		 }
		 cur.close();
		 db.close();
		 return items;
	}
	//��ѯ��index���20����¼
	public static List<DataItem> queryAfter30(Context con,int index){
		DatabaseHelper sdh = new DatabaseHelper(con);
		 SQLiteDatabase db = sdh.getReadableDatabase();
		 String sql="select * from "+DatabaseHelper.TABLE_NAME+" order by "+DatabaseHelper.TIME+" desc limit "+index*30+",30;";
//		 Cursor cur = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, DatabaseHelper.TIME);

		 Cursor cur=db.rawQuery(sql, null);
		 List<DataItem> items = new ArrayList<DataItem>();
		 if(cur == null){db.close();return items;}
		 while(cur.moveToNext()){
			 DataItem item = new DataItem();
			 item.id = cur.getString(cur.getColumnIndex(DatabaseHelper.ID));
			 item.dir = cur.getString(cur.getColumnIndex(DatabaseHelper.DIR));
			 item.number = cur.getString(cur.getColumnIndex(DatabaseHelper.NUMBER));
			 item.item = cur.getString(cur.getColumnIndex(DatabaseHelper.ITEM));
			 item.note = cur.getString(cur.getColumnIndex(DatabaseHelper.NOTE));
			 item.time=cur.getString(cur.getColumnIndex(DatabaseHelper.TIME));
			 item.way=cur.getString(cur.getColumnIndex(DatabaseHelper.WAY));
			 items.add(item);
		 }
		 cur.close();
		 db.close();
		 return items;
	}
	//��ѯ��index������������20����¼
	public static List<DataItem> queryItemsAfter30(Context con,String itemName,boolean in,int index){
		DatabaseHelper sdh = new DatabaseHelper(con);
		SQLiteDatabase db = sdh.getReadableDatabase();
		String dir=(in==true?"in":"out");
		String sql="select * from "+DatabaseHelper.TABLE_NAME
				+" where ITEM='"+itemName+"' AND DIR='"+dir+"' order by "+DatabaseHelper.TIME+" desc limit "+index*30+",30;";
		Cursor cur=db.rawQuery(sql, null);
		 List<DataItem> items = new ArrayList<DataItem>();
		 if(cur == null){db.close();return items;}
		 while(cur.moveToNext()){
			 DataItem item = new DataItem();
			 item.id = cur.getString(cur.getColumnIndex(DatabaseHelper.ID));
			 item.dir = cur.getString(cur.getColumnIndex(DatabaseHelper.DIR));
			 item.number = cur.getString(cur.getColumnIndex(DatabaseHelper.NUMBER));
			 item.item = cur.getString(cur.getColumnIndex(DatabaseHelper.ITEM));
			 item.note = cur.getString(cur.getColumnIndex(DatabaseHelper.NOTE));
			 item.time=cur.getString(cur.getColumnIndex(DatabaseHelper.TIME));
			 item.way=cur.getString(cur.getColumnIndex(DatabaseHelper.WAY));
			 items.add(item);
		 }
		 cur.close();
		 db.close();
		 return items;
	}
	//��ѯһ��֧����������Ŀ�����ܶ�
	public static int getTotalByItem(Context con,String itemName,boolean in){
		DatabaseHelper sdh = new DatabaseHelper(con);
		SQLiteDatabase db = sdh.getReadableDatabase();
		String dir=(in==true?"in":"out");
		Cursor cur=db.query(DatabaseHelper.TABLE_NAME, null, "ITEM=? AND DIR=?", new String[]{
			itemName,	dir
		}, null, null, null);
		if(cur == null){db.close();return 0;}
		int total=0;
		while(cur.moveToNext()){
			total+=Integer.valueOf(cur.getString(cur.getColumnIndex(DatabaseHelper.NUMBER)));
		 }
		 cur.close();
		 db.close();
		 return total;
	}
}
