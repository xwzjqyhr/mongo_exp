package com.xwzjqyhr.mongodb.util;

import com.xwzjqyhr.mongodb.pojo.Course;
import com.xwzjqyhr.mongodb.pojo.Student;
import com.xwzjqyhr.mongodb.pojo.Teacher;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelUtil {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    public static Workbook getWorkbok(String excelPath) throws IOException {
        File file = new File(excelPath);
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
    public static HashMap<String,List<?>> getAllSheet(String excelPath) throws IOException {
        HashMap<String,List<?>> hashMap = new HashMap<>();
        Workbook wb = getWorkbok(excelPath);
        int sheet_size = wb.getNumberOfSheets();
        for(int index = 0;index < sheet_size;index ++) {
            Sheet sheet = wb.getSheetAt(index);
            String sheetName = sheet.getSheetName();
            if(sheetName.contains("学生")) {
                 hashMap.put("students",getStudentList(sheet));
            }
            if(sheetName.contains("教师")) {
                 hashMap.put("teachers",getTeacherList(sheet));
            }
            if(sheetName.contains("课程")) {
                hashMap.put("courses",getCourseList(sheet));
            }
        }
        return hashMap;
    }
    /**
     * @return
     * @throws IOException
     */
    public static List<Teacher> getTeacherList(Sheet sheet) throws IOException {

            List<Teacher> teachers = new ArrayList<>();
            sheet.getSheetName();
            int rowNum = sheet.getLastRowNum();
            for (int i = 0; i < rowNum; i++) {
                Teacher teacher = new Teacher();
                int lastCellNum = sheet.getRow(i).getLastCellNum();
                for(int j = 0;j < lastCellNum; j++) {
                    String tmp = sheet.getRow(i).getCell(j).toString();
                    if(j == 0) { teacher.setTid(tmp); }
                    if(j == 1) { teacher.setName(tmp); }
                    if(j == 2) { teacher.setSex(tmp); }
                    if(j == 3) { teacher.setAge(Integer.parseInt(tmp)); }
                    if(j == 4) { teacher.setDname(tmp); }
                }
                teachers.add(teacher);
            }

        return teachers;
    }

    /**
     * @return
     * @throws IOException
     */
    public static List<Student> getStudentList(Sheet sheet) throws IOException {

        List<Student> students= new ArrayList<>();
        int rowNum = sheet.getLastRowNum();
        for (int i = 0; i < rowNum; i++) {
            Student student = new Student();
            int lastCellNum = sheet.getRow(i).getLastCellNum();
            for(int j = 0;j < lastCellNum; j++) {
                String tmp = sheet.getRow(i).getCell(j).toString();
                if(j == 0) { student.setSid(tmp); }
                if(j == 1) { student.setName(tmp); }
                if(j == 2) { student.setSex(tmp); }
                if(j == 3) { student.setAge(Integer.parseInt(tmp)); }
                if(j == 4) { student.setBirthday(tmp); }
                if(j == 5) {student.setDName(tmp);}
                if(j == 6) {student.setDClass(Integer.parseInt(tmp));}
            }
            students.add(student);
        }
        return students;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static List<Course> getCourseList(Sheet sheet) throws IOException  {

        List<Course> courses= new ArrayList<>();
        int rowNum = sheet.getLastRowNum();
        for (int i = 0; i < rowNum; i++) {
            Course course = new Course();
            int lastCellNum = sheet.getRow(i).getLastCellNum();
            for(int j = 0;j < lastCellNum; j++) {
                String tmp = sheet.getRow(i).getCell(j).toString();
                if(j == 0) { course.setCid(tmp); }
                if(j == 1) { course.setName(tmp); }
                if(j == 2) { course.setFCid(tmp); }
                if(j == 3) { course.setCredit(Double.parseDouble(tmp)); }
            }
            courses.add(course);
        }
        return courses;
    }
}
