package com.company;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;

public class ExcelSheet {
  private HSSFSheet sheet;
  private Row row;
  private Cell cell;

  private XSSFSheet xssfSheet;
  private XSSFRow xssfRow;
  private XSSFCell xssfCell;

  private String fileName;
  private String filePath;

  ExcelSheet() {}

  ExcelSheet(File tempFile) {
    /* .xls 확장자 지원 */
    sheet = null;
    row = null;
    cell = null;

    /* .xlsx 확장자 지원 */
    xssfSheet = null;
    xssfRow = null;
    xssfCell = null;

    /* 현재 읽고 있는 .java 파일 이름 */
    fileName = tempFile.getName();
    /* 현재 읽고 있는 .java 파일 절대경로 */
    filePath = tempFile.getAbsolutePath();
    System.out.println(fileName + filePath);
  }

  public XSSFSheet getXssfSheet() { return xssfSheet; }

  public XSSFRow getXssfRow() { return xssfRow; }

  public XSSFCell getXssfCell() { return xssfCell; }

  public String getFileName() {
    return fileName;
  }

  public String getFilePath() {
    return filePath;
  }

  public void initExcelSheet(XSSFWorkbook xssfWb) {

    /* 워크시트 이름 if inside of 'for' then make it => "xxx.java" if not => "FMS Table"*/
    xssfSheet = xssfWb.createSheet(fileName);
    /* 워크시트 Column size 조절 */
    xssfSheet.setColumnWidth(0, (xssfSheet.getColumnWidth(0)) + (short) 4096); // 0번째 컬럼(테이블명) 넓이 조절
    xssfSheet.setColumnWidth(1, (xssfSheet.getColumnWidth(1)) + (short) 4096); // 1번째 컬럼(테이블 한글명) 넓이 조절
    xssfSheet.setColumnWidth(2, (xssfSheet.getColumnWidth(2)) + (short) 4096); // 2번째 컬럼(컬럼명) 넓이 조절
    xssfSheet.setColumnWidth(3, (xssfSheet.getColumnWidth(3)) + (short) 4096); // 3번째 컬럼(컬럼 한글명) 넓이
    xssfSheet.setColumnWidth(5, (xssfSheet.getColumnWidth(5)) + (short) 2048); // 5번째 컬럼(데이터 타입) 넓이
  }

  public void createTitle(XSSFWorkbook xssfWb) {
    /* 타이틀용 폰트 스타일 */
    XSSFFont font = xssfWb.createFont();
    font.setFontName(HSSFFont.FONT_ARIAL); //폰트스타일
    font.setFontHeightInPoints((short) 14); //폰트크기
    font.setBold(true); //Bold 유무

    /* 셀 병합, (0번째 row의 0~10번째 column을 병합 */
    xssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
    /* 테이블 타이틀 스타일 설정 */
    CellStyle cellStyle_Title = xssfWb.createCellStyle();
    cellStyle_Title.setAlignment(HorizontalAlignment.CENTER); // cellStyle_Title 내용물 수평 가운데 정렬
    cellStyle_Title.setFont(font); // cellStyle_Title에 font 적용

    /* 0번째 row에 타이틀 생성 */
    xssfRow = xssfSheet.createRow(0); //0번째 row에 행 객체 추가
    xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 객체 추가
    xssfCell.setCellStyle(cellStyle_Title); // 셀에 스타일 지정
    xssfCell.setCellValue("FMS Table Definition"); // 데이터 입력
  }
}