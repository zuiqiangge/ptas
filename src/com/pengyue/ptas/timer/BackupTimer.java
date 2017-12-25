package com.pengyue.ptas.timer;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class BackupTimer {
	public static void export(){
		try {
			Calendar c = Calendar.getInstance();
			String year = c.get(Calendar.YEAR)+"";
			int month =c.get(Calendar.MONTH);
			String day = c.get(Calendar.DAY_OF_MONTH)+"";
			month+=1;
			String months="";
			String days="";
			if((month+"").length()==1){
				months="0"+month;
			}else
				months=""+month;
			if((day+"").length()==1){
				days="0"+day;
			}else
				days=""+day;
			String date =year+months+days;
			Process process = Runtime.getRuntime().exec("cmd /c exp ptas/ptas@orcl file=c:\\ptas"+date+".dmp");
			process.waitFor();
			File file = new File("c:\\ptas"+date+".dmp");
			boolean isOk = file.exists();
			if(isOk)
				System.out.println(new Date().toLocaleString()+":备份成功!");
			else
				System.out.println(new Date().toLocaleString()+":备份失败!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public static void main(String[] args) {
		export();
	}
}


