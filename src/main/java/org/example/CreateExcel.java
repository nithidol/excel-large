package org.example;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.example.bean.UserInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateExcel {
    public static void main(String[] args){
        try {
            generateExcelExample();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static void generateExcelExample() throws IOException {
        SXSSFWorkbook sxssWb = new SXSSFWorkbook(100);
        SXSSFSheet sxssSheet = (SXSSFSheet) sxssWb.createSheet("sheet1");
        sxssSheet.setRandomAccessWindowSize(100);

        Iterator<UserInfo> it = sampleData().stream().iterator();
        int rowNumber = 0;
        while (it.hasNext()) {
            UserInfo userInfo = (UserInfo) it.next();
            SXSSFRow row = (SXSSFRow) sxssSheet.createRow(rowNumber++);
            SXSSFCell cell = (SXSSFCell) row.createCell(0);
            cell.setCellValue(userInfo.getFirstName());
            cell = (SXSSFCell) row.createCell(1);
            cell.setCellValue(userInfo.getLastName());
        }

        //Support file name UTF-8.
        String originalNameEncode = URLEncoder.encode("excel.xlsx", "UTF-8");
        FileOutputStream outputStream = new FileOutputStream(originalNameEncode);
        sxssWb.write(outputStream);
        sxssWb.close();
        System.out.println("Generate excel successful.");
    }

    public static List<UserInfo> sampleData(){
        List<UserInfo> userInfos = new ArrayList<>();
        for(int i = 0 ; i < 1000000; i++) {
            userInfos.add(new UserInfo("Jane", "Doe"));
        }
        return userInfos;
    }
}
