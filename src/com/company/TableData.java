package com.company;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

public class TableData {

  private String[] headers = {"테이블명", "테이블 한글명", "컬럼명", "컬럼 한글명", "Null 여부", "데이터 타입", "길이", "PK", "FK", "제약 조건", "설명"};
  private List<RowData> rows; // = new ArrayList<RowData>();
  private int rowNo;

  public TableData() {
    rows = new ArrayList<RowData>();
    rowNo = 1;
  }

  public void pushRowData(RowData inputVal) {
    /* if columnName == "" then, it means fields doesn't have that variable so it did not append it to the inputVal */
    if(inputVal.getColumnName() != "")  rows.add(inputVal);
  }

  public int getSizeOfRows() {
    return rows.size();
  }

  private void printHeaders(XSSFWorkbook xssfWb, XSSFSheet xssfSheet, XSSFRow xssfRow, XSSFCell xssfCell) throws Exception {
    try {
      /* 테이블 헤더 스타일 설정 */
      CellStyle cellStyle_Header = xssfWb.createCellStyle();
      cellStyle_Header.setBorderTop(BorderStyle.THIN); //테두리 위쪽
      cellStyle_Header.setBorderBottom(BorderStyle.THIN); //테두리 아래쪽
      cellStyle_Header.setBorderLeft(BorderStyle.THIN); //테두리 왼쪽
      cellStyle_Header.setBorderRight(BorderStyle.THIN); //테두리 오른쪽
      cellStyle_Header.setAlignment(HorizontalAlignment.CENTER); // 수평 가운데 정렬

      /* 테이블 헤더 추가 */
      for (int i = 0; i < headers.length; i++) {
        xssfCell = xssfRow.createCell((short) i);
        xssfCell.setCellStyle(cellStyle_Header);
        xssfCell.setCellValue(headers[i]);
      }
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  private void printBody(XSSFWorkbook xssfWb, XSSFSheet xssfSheet, XSSFRow xssfRow, XSSFCell xssfCell) throws Exception {
    try {
      /* 셀 병합 (테이블 명, 테이블 한글명을 위한) */
      xssfSheet.addMergedRegion(new CellRangeAddress(rowNo - 1, rowNo + rows.size() - 2, 0, 0));
      xssfSheet.addMergedRegion(new CellRangeAddress(rowNo - 1, rowNo + rows.size() - 2, 1, 1));

      /* 병합 셀 스타일 설정 */
      CellStyle blockMergeCellStyle = xssfWb.createCellStyle();
      blockMergeCellStyle.setAlignment(HorizontalAlignment.CENTER);
      blockMergeCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

      /* 일반 셀 스타일 설정 */
      CellStyle blockCellStyle = xssfWb.createCellStyle();

      /* write cell */
      for (RowData row : rows) {
        try {
          row.printRowData(xssfRow, xssfCell, blockMergeCellStyle, blockCellStyle);
          xssfRow = xssfSheet.createRow(rowNo++);
        } catch (Exception e) {
          throw new Exception(row.toString(), e);
        }
      }
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  public void printExcel(XSSFWorkbook xssfWb, XSSFSheet xssfSheet, XSSFRow xssfRow, XSSFCell xssfCell) throws Exception {
    try {
      xssfRow = xssfSheet.createRow(rowNo++);
      printHeaders(xssfWb, xssfSheet, xssfRow, xssfCell);
      xssfRow = xssfSheet.createRow(rowNo++);
      printBody(xssfWb, xssfSheet, xssfRow, xssfCell);
    } catch (Exception e) {
      throw new Exception(e);
    }
  }
}
