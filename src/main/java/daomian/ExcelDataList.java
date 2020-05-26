package daomian;

import java.util.List;

/**
 * @Author: gxf
 * @Date: 2020/5/26 17:25
 */
public class ExcelDataList {
    //文件名
   private   String fileName;
         //数据
   private List<SheetData> sheetData;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<SheetData> getSheetData() {
        return sheetData;
    }

    public void setSheetData(List<SheetData> sheetData) {
        this.sheetData = sheetData;
    }

    @Override
    public String toString() {
        return "ExcelDataList{" +
                "fileName='" + fileName + '\'' +
                ", sheetData=" + sheetData +
                '}';
    }
}
