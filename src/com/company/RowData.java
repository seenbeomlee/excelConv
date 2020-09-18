package com.company;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class RowData {
  private String tableName;
  private String tableKName;
  private String columnName;
  private String columnKName;
  private String isNull;
  private String dataType;
  private String length;
  private String PK;
  private String FK;

  public RowData(String tableName, String tableKName, String columnName, String columnKName, String isNull, String dataType, String length, String PK, String FK) {
    this.tableName = tableName;
    this.tableKName = tableKName;
    this.columnName = columnName;
    this.columnKName = columnKName;
    this.isNull = isNull;
    this.dataType = dataType;
    this.length = length;
    this.PK = PK;
    this.FK = FK;
  }

  public RowData() {
  }

  public String getColumnName() { return columnName; }

  public void printRowData(XSSFRow xssfRow, XSSFCell xssfCell, CellStyle blockMergeCellStyle, CellStyle blockCellStyle) {
    xssfCell = xssfRow.createCell((short) 0);
    xssfCell.setCellStyle(blockMergeCellStyle);
    xssfCell.setCellValue(tableName);
    xssfCell = xssfRow.createCell((short) 1);
    xssfCell.setCellStyle(blockMergeCellStyle);
    xssfCell.setCellValue(tableKName);
    xssfCell = xssfRow.createCell((short) 2);
    xssfCell.setCellStyle(blockCellStyle);
    xssfCell.setCellValue(columnName);
    xssfCell = xssfRow.createCell((short) 3);
    xssfCell.setCellStyle(blockCellStyle);
    xssfCell.setCellValue(columnKName);
    xssfCell = xssfRow.createCell((short) 4);
    xssfCell.setCellStyle(blockCellStyle);
    xssfCell.setCellValue(isNull);
    xssfCell = xssfRow.createCell((short) 5);
    xssfCell.setCellStyle(blockCellStyle);
    xssfCell.setCellValue(dataType);
    xssfCell = xssfRow.createCell((short) 6);
    xssfCell.setCellStyle(blockCellStyle);
    xssfCell.setCellValue(length);
    xssfCell = xssfRow.createCell((short) 7);
    xssfCell.setCellStyle(blockCellStyle);
    xssfCell.setCellValue(PK);
    xssfCell = xssfRow.createCell((short) 8);
    xssfCell.setCellStyle(blockCellStyle);
    xssfCell.setCellValue(FK);
  }
}
