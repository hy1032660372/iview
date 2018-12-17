package com.hy.spring.cloud.util.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName excelUtil
 * @Description TODO
 * @Author P1316771
 * @Date 12/12/2018
 **/
public class ExcelUtil {

    public static void importExcel(MultipartFile file,List<Map> sheetMapList) {

        Row row;
        Workbook workbook;
        Sheet sheet;
        int sheetCount;
        int lastRowNum;
        int maxColNum;
        int currentColNum = 0;

        String sheetName = "";
        List<Map> mapList = new ArrayList<>();
        List<String> labelList = new ArrayList();
        Map map;
        Map sheetMap;
        try{
            InputStream inputStream = file.getInputStream();
            workbook = WorkbookFactory.create(inputStream);
            sheetCount = workbook.getNumberOfSheets();

            for(int s = 0; s < sheetCount; s++){
                sheet = workbook.getSheetAt(0);
                sheetName = sheet.getSheetName();
                lastRowNum = sheet.getLastRowNum();
                sheetMap = new HashMap();

                //first row
                row = sheet.getRow(0);
                maxColNum = row.getPhysicalNumberOfCells();
                while(row.getCell(currentColNum) != null && !"".equals(row.getCell(currentColNum).getStringCellValue())){
                    labelList.add(row.getCell(currentColNum).getStringCellValue());
                    currentColNum++;
                }

                //maxColNum = labelList.size();
                for (int i = 1; i <= lastRowNum; i++) {
                    row = sheet.getRow(i);
                    map = new HashMap();
                    for(int j = 0; j < maxColNum; j++){
                        map.put(labelList.get(j),row.getCell(j).getStringCellValue());
                    }
                    mapList.add(map);
                }
                sheetMap.put("sheetName",sheetName);
                sheetMap.put("sheetList",mapList);
                sheetMapList.add(sheetMap);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
