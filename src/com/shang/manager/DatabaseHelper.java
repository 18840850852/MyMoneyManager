package com.shang.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static int VERSION = 1;//���ݿ�İ汾��
	public static String DATABASE_NAME = "mm_database";//���ݿ������
	public static String TABLE_NAME="money_items";//�������
	public static String ID="_id",ITEM="ITEM",DIR="DIR",WAY="WAY",NUMBER="NUMBER",TIME="TIME",NOTE="NOTE";
	//���캯����ĳ���û����˺ž�������û���ϵ�����ݿ������
	public DatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);	
	}
	public DatabaseHelper(Context context){
		this(context,DATABASE_NAME,null,VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String[] items=new String[]{
				ITEM,DIR,WAY,NUMBER,TIME,NOTE
		};
		String[] types=new String[]{
				"text","varchar(5)","text","varchar(10)","varchar(20)","text"
		};
		createTable(db, items, types);
		System.out.println("�������ݿ�");
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
		System.out.println("�����ݿ�");
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		System.out.println("�������ݿ�");
		VERSION = arg2;//�������ݿ�İ汾��
	}
	//������
	private void createTable(SQLiteDatabase db,String[] tableitems,String[] tableitemstype){
			String sentence="create table if not exists "+TABLE_NAME+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,";
			for(int i=0;i<tableitems.length;++i){
				sentence += tableitems[i] + " " + tableitemstype[i];
				if(i == tableitems.length-1){
					break;
				}
				sentence+=",";
			}
			sentence+=")";
			System.out.println(sentence);
			db.execSQL(sentence);
		}
}