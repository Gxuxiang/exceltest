import daomian.ExcelDataList;
import daomian.SheetData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: gxf
 * @Date: 2020/5/26 15:02
 */
public class ExcelModel {
    /**
     *
     * @param request
     * @param titleIndex 标题位置
     * @return
     */
  public ExcelDataList analysisExcelFile(HttpServletRequest request,int titleIndex ){

        ExcelDataList dataList = new ExcelDataList();
        List<SheetData> sheetlists = new ArrayList<>();

        MultiValueMap<String, MultipartFile> multiFileMap = ((StandardMultipartHttpServletRequest) request).getMultiFileMap();
        //遍历所有文件
        for (String key: multiFileMap.keySet()) {
            //拿到这个文件
            MultipartFile multipartFile = multiFileMap.getFirst(key);
            //拿到这个文件的名字
            String filename = multipartFile.getOriginalFilename();
            dataList.setFileName(filename);
            //校验文件类型
            String prefix = filename.substring(filename.lastIndexOf("."));
            if (!prefix.equals(".xlsx")) {
                throw new RuntimeException(filename+"文件必须为xlsx");
            }
            //拿到文件流
            InputStream inputStream=null;
            try {
                inputStream = multipartFile.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //得到wb
            XSSFWorkbook wb=null;
            try {
                wb = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 拿到这个文件sheet数量遍历它
            int numberOfSheets = wb.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                SheetData sheetData = new SheetData();

                XSSFSheet sheet = wb.getSheetAt(i);
                sheetData.setSheetName(sheet.getSheetName());
                //拿到行数
                int numberOfRows = sheet.getPhysicalNumberOfRows();
                sheetData.setRowCount(numberOfRows);
                //有一个参数来设置表头在第几行

                List<Map<String, Object>> list = new ArrayList<>();

                int lastCellNum=0;
                for (int j = titleIndex; j < numberOfRows ; j++) {
                    XSSFRow row = sheet.getRow(j);
                    Map<String, Object> map = new HashMap<>();
                    //是表头？
                    if (j==titleIndex){
                        lastCellNum = row.getLastCellNum();
                    }else {
                        for (int k = 0; k < lastCellNum ; k++) {
                            String titlename = sheet.getRow(titleIndex).getCell(k).getStringCellValue();
                            String rawValue = null;
                            if (row.getCell(k).getStringCellValue()!=null){
                                rawValue = row.getCell(k).getStringCellValue();
                            }else {
                                rawValue = "";
                            }
                            map.put(titlename,rawValue);
                        }
                        list.add(map);
                    }
                }
                sheetData.setData(list);
                sheetlists.add(sheetData);
            }
            dataList.setSheetData(sheetlists);
        }
        return dataList;
    }
}
