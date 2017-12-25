package com.pengyue.ptas.util;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.bean.YyGzryxxb;

public class ExportUtil {
	public static List<Trained> listTrained;
	
	public static List<YyGzryxxb> listYyGzryxxb;
	
	public static List<SxPlan> listSxPlan;
	/**
	 * 导出受训对象信息导出excel
	 * @param list 需要导出的信息
	 * @param path 导出地址
	 * @return
	 */
	public static int exportExcelTrained (List<Trained> listTrainedExcel,String path) {
		int k=0;
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("学生表一");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("单位名称");  
        //cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("姓名");  
        //cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("性别");  
        //cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("身份证");  
        //cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("人员身份");
        cell = row.createCell((short) 5);
        cell.setCellValue("进入模式");
        cell = row.createCell((short) 6);
        cell.setCellValue("编制类型");
        cell = row.createCell((short) 7);
        cell.setCellValue("人员状态");
        cell = row.createCell((short) 8);
        cell.setCellValue("进入时间");
  
        for (int i = 0; i < listTrainedExcel.size(); i++){  
            row = sheet.createRow((int) i + 1);  
            // 第四步，创建单元格，并设置值  
            if(listTrainedExcel.get(i).getO_name()!=null){
            	row.createCell((short) 0).setCellValue(listTrainedExcel.get(i).getO_name().toString()); 
            }else{
            	row.createCell((short) 0).setCellValue("");
            }
            
            if(listTrainedExcel.get(i).getT_name()!=null){
            	row.createCell((short) 1).setCellValue(listTrainedExcel.get(i).getT_name().toString()); 
            }else{
            	row.createCell((short) 1).setCellValue("");
            }
            
            if(listTrainedExcel.get(i).getT_sex()!=null){
            	row.createCell((short) 2).setCellValue(listTrainedExcel.get(i).getT_sex().toString()); 
            }else{
            	row.createCell((short) 2).setCellValue("");
            }
            
            if(listTrainedExcel.get(i).getT_card()!=null){
            	row.createCell((short) 3).setCellValue(listTrainedExcel.get(i).getT_card().toString()); 
            }else{
            	row.createCell((short) 3).setCellValue("");
            }
            
            if(listTrainedExcel.get(i).getT_identity()!=null){
            	row.createCell((short) 4).setCellValue(listTrainedExcel.get(i).getT_identity().toString()); 
            }else{
            	row.createCell((short) 4).setCellValue("");
            }
             
            if(listTrainedExcel.get(i).getT_enter()!=null){
            	row.createCell((short) 5).setCellValue(listTrainedExcel.get(i).getT_enter().toString()); 
            }else{
            	row.createCell((short) 5).setCellValue("");
            }
            
            if(listTrainedExcel.get(i).getT_compile()!=null){
            	row.createCell((short) 6).setCellValue(listTrainedExcel.get(i).getT_compile().toString()); 
            }else{
            	row.createCell((short) 6).setCellValue("");
            }
            
            if(listTrainedExcel.get(i).getT_status()!=null){
            	row.createCell((short) 7).setCellValue(listTrainedExcel.get(i).getT_status().toString()); 
            }else{
            	row.createCell((short) 7).setCellValue("");
            }
            
            if(listTrainedExcel.get(i).getT_time()!=null){
            	row.createCell((short) 8).setCellValue(listTrainedExcel.get(i).getT_time().toString()); 
            }else{
            	row.createCell((short) 8).setCellValue("");
            }
        }  
        // 第六步，将文件存到指定位置  
        try {  
        	FileOutputStream fout = new FileOutputStream(path+":/TrainedObject-受训对象信息.xls"); 
            wb.write(fout);  
            fout.close();  
        } catch (Exception e){  
        	k=1;
        	System.out.println("excel导出异常："+e);
            e.printStackTrace();  
        }  
        return k;
    }  
	
	
	/**
	 * 导出工作人员信息导出excel
	 * @param list 需要导出的信息
	 * @param path 导出地址
	 * @return
	 */
	public static int exportExcelYyGzryxxb (List<YyGzryxxb> listYyGzryxxbExcel,String path) {
		int k=0;
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("学生表一");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("姓名");  
        //cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("性别");  
        //cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("在编情况");  
        //cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("擅长领域");  
        //cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("联系方式");
        cell = row.createCell((short) 5);
        cell.setCellValue("部门代码");
        cell = row.createCell((short) 6);
        cell.setCellValue("年龄");
        cell = row.createCell((short) 7);
        cell.setCellValue("身份证号");
  
        for (int i = 0; i < listYyGzryxxbExcel.size(); i++){  
            row = sheet.createRow((int) i + 1);  
            // 第四步，创建单元格，并设置值  
            if(listYyGzryxxbExcel.get(i).getName()!=null){
            	row.createCell((short) 0).setCellValue(listYyGzryxxbExcel.get(i).getName().toString()); 
            }else{
            	row.createCell((short) 0).setCellValue("");
            }
            
            if(listYyGzryxxbExcel.get(i).getXb()!=null){
            	row.createCell((short) 1).setCellValue(listYyGzryxxbExcel.get(i).getXb().toString()); 
            }else{
            	row.createCell((short) 1).setCellValue("");
            }
            
            if(listYyGzryxxbExcel.get(i).getZbflag()!=null){
            	row.createCell((short) 2).setCellValue(listYyGzryxxbExcel.get(i).getZbflag().toString()); 
            }else{
            	row.createCell((short) 2).setCellValue("");
            }
            
            if(listYyGzryxxbExcel.get(i).getScly()!=null){
            	row.createCell((short) 3).setCellValue(listYyGzryxxbExcel.get(i).getScly().toString()); 
            }else{
            	row.createCell((short) 3).setCellValue("");
            }
            
            if(listYyGzryxxbExcel.get(i).getLxfs()!=null){
            	row.createCell((short) 4).setCellValue(listYyGzryxxbExcel.get(i).getLxfs().toString()); 
            }else{
            	row.createCell((short) 4).setCellValue("");
            }
             
            if(listYyGzryxxbExcel.get(i).getBm()!=null){
            	row.createCell((short) 5).setCellValue(listYyGzryxxbExcel.get(i).getBm().toString()); 
            }else{
            	row.createCell((short) 5).setCellValue("");
            }
            
            if(listYyGzryxxbExcel.get(i).getAge()!=null){
            	row.createCell((short) 6).setCellValue(listYyGzryxxbExcel.get(i).getAge().toString()); 
            }else{
            	row.createCell((short) 6).setCellValue("");
            }
            
            if(listYyGzryxxbExcel.get(i).getSfzh()!=null){
            	row.createCell((short) 7).setCellValue(listYyGzryxxbExcel.get(i).getSfzh().toString()); 
            }else{
            	row.createCell((short) 7).setCellValue("");
            }
        }  
        // 第六步，将文件存到指定位置  
        try {  
        	FileOutputStream fout = new FileOutputStream(path+":/YyGzryxxb-工作人员信息.xls"); 
            wb.write(fout);  
            fout.close();  
        } catch (Exception e){  
        	k=1;
        	System.out.println("excel导出异常："+e);
            e.printStackTrace();  
        }  
        return k;
    }  
	
	/**
	 * 导出实训信息导出excel
	 * @param list 需要导出的信息
	 * @param path 导出地址
	 * @return
	 */
	public static int exportExcelSxPlan (List<SxPlan> listSxPlanExcel,String path) {
		int k=0;
        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("学生表一");  
        HSSFRow row = sheet.createRow((int) 0);  
        HSSFCellStyle style = wb.createCellStyle();  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("时间");  
        cell = row.createCell((short) 1);  
        cell.setCellValue("名称");  
        cell = row.createCell((short) 2);  
        cell.setCellValue("保留时间");  
        cell = row.createCell((short) 3);  
        cell.setCellValue("地点");  
        cell = row.createCell((short) 4);
        cell.setCellValue("教员");
        cell = row.createCell((short) 5);
        cell.setCellValue("受训对象名称");
        cell = row.createCell((short) 6);
        cell.setCellValue("受训单位名称");
  
        for (int i = 0; i < listSxPlanExcel.size(); i++){  
            row = sheet.createRow((int) i + 1);  
            if(listSxPlanExcel.get(i).getTime()!=null){
            	row.createCell((short) 0).setCellValue(listSxPlanExcel.get(i).getTime().toString()); 
            }else{
            	row.createCell((short) 0).setCellValue("");
            }
            
            if(listSxPlanExcel.get(i).getSxProject()!=null){
            	row.createCell((short) 1).setCellValue(listSxPlanExcel.get(i).getSxProject().toString()); 
            }else{
            	row.createCell((short) 1).setCellValue("");
            }
            
            if(listSxPlanExcel.get(i).getKeepTime()!=null){
            	row.createCell((short) 2).setCellValue(listSxPlanExcel.get(i).getKeepTime().toString()); 
            }else{
            	row.createCell((short) 2).setCellValue("");
            }
            
            if(listSxPlanExcel.get(i).getPlace()!=null){
            	row.createCell((short) 3).setCellValue(listSxPlanExcel.get(i).getPlace().toString()); 
            }else{
            	row.createCell((short) 3).setCellValue("");
            }
            
            if(listSxPlanExcel.get(i).getTeacher()!=null){
            	row.createCell((short) 4).setCellValue(listSxPlanExcel.get(i).getTeacher().toString()); 
            }else{
            	row.createCell((short) 4).setCellValue("");
            }
             
            if(listSxPlanExcel.get(i).getSxdxName()!=null){
            	row.createCell((short) 5).setCellValue(listSxPlanExcel.get(i).getSxdxName().toString()); 
            }else{
            	row.createCell((short) 5).setCellValue("");
            }
            
            if(listSxPlanExcel.get(i).getSxdwName()!=null){
            	row.createCell((short) 6).setCellValue(listSxPlanExcel.get(i).getSxdwName().toString()); 
            }else{
            	row.createCell((short) 6).setCellValue("");
            }
            
        }  
        try {  
        	FileOutputStream fout = new FileOutputStream(path+":/SxPlan-实训信息.xls"); 
            wb.write(fout);  
            fout.close();  
        } catch (Exception e){  
        	k=1;
        	System.out.println("excel导出异常："+e);
            e.printStackTrace();  
        }  
        return k;
    }  
	
}
