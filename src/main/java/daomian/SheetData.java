package daomian;

import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.formula.functions.Count;

import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

/**
 * @Author: gxf
 * @Date: 2020/5/26 17:27
 */
public class SheetData {
    //sheetName
  private String sheetName;

    //数据
    private  List<Map<String,Object>> data;

  //行数
    private int rowCount;

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SheetData{" +
                "sheetName='" + sheetName + '\'' +
                ", data=" + data +
                ", rowCount=" + rowCount +
                '}';
    }
}
