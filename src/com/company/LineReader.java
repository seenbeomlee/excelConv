package com.company;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class LineReader {
  private String word[];

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

  LineReader(String fileName) {
    tableName = fileName.substring(0, fileName.indexOf(".java")); // 테이블명
    tableKName = "";
    /* blockInit elements */
    blockInit();
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

  public RowData casePrivate(String line) throws Exception {
    try {
      String[] dataTypeAndcolumnName = line.split(" ");

      dataType = dataTypeAndcolumnName[0];
      columnName = dataTypeAndcolumnName[1];

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
          if (line.contains("@Column(nullable=false)")) isNull = "N";
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