package excel;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LineReader {
  private String word[];

  /* to save reflection fields */
  private List<String> fields;

  private String tableName;
  private String tableKName;

  private String columnName;
  private String columnKName;
  private String isNull;
  private String dataType;
  private String length;
  private String PK;
  private String FK;

  LineReader() {
  }

  LineReader(String fileName, String classPath) throws Exception {
      tableName = fileName.substring(0, fileName.indexOf(".java")); // 테이블명
      tableKName = "";
      fields = new ArrayList<String>();
      /* blockInit elements */
      blockInit();
      extractFields(classPath);
  }

  public String getTableName() { return tableName; }
  public String getColumnName() { return columnName; }

  public void blockInit() {
    columnName = "";
    columnKName = "";
    isNull = "Y";
    dataType = "";
    length = "";
    PK = "";
    FK = "";
  }

  public void extractFields(String classPath) throws Exception {
    Class c = Class.forName(classPath + "." + tableName);

    Field[] classField = c.getDeclaredFields();

    for(int i = 0; i < classField.length; i++) {
      String fieldString = classField[i].toString();
      fields.add(fieldString.substring(fieldString.lastIndexOf(".")).replace(".", ""));
    }
  }

  public RowData caseCheckFields(String line) throws Exception {
    try {
      String[] dataTypeAndcolumnName = line.replace(";", "").split(" ");
      List<String> list = new ArrayList<String>(Arrays.asList(dataTypeAndcolumnName));
      list.removeAll(Arrays.asList(""));
      dataTypeAndcolumnName = list.toArray(dataTypeAndcolumnName);

      if(fields.contains(dataTypeAndcolumnName[2])){
        /* public String Name */
        /* public String appendName(...) */
        dataType = dataTypeAndcolumnName[1];
        columnName = dataTypeAndcolumnName[2];
      }

      /* return RowData and push it to TableData's List<RowData> Rows */
      RowData rowData = new RowData(tableName, tableKName, columnName, columnKName, isNull, dataType, length, PK, FK);

      blockInit();
      return rowData;
    } catch (Exception e) {
      throw new Exception(line, e);
    }
  }

  public void caseElse(String line) throws Exception {
    word = line.split("\\(|/");
    try {
      switch (word[0]) {
        case "@Id": {
          PK = "PK";
          break;
        }
        case "@ApiModelProperty": {
          if (line.contains("value"))
            columnKName = StringUtils.substringBetween(line, "value=\"", "\")");
          break;
        }
        case "@ApiModel": {
          tableKName = StringUtils.substringBetween(line, "value=\"", "\")");
          break;
        }
        case "@Column": {
          if (line.contains("nullable=false")) isNull = "N";
          break;
        }
        case "@Length": {
          length = StringUtils.substringBetween(line, "max=", ")");
          break;
        }
        case "@JoinColumn": {
          FK = "FK";
          break;
        }
        default: {

        }
      }
    } catch (Exception e) {
      throw new Exception(line, e);
    }
  }
}