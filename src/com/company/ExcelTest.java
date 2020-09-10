import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Vector;
import java.util.Iterator;

class TableData{

    private String columnName;
    private String columnKName;
    private String isNull;
    private String dataType;
    private String length;
    private String PK;
    private String FK;

    public TableData(String columnName, String columnKName, String isNull, String dataType, String length, String PK, String FK) {
        this.columnName = columnName;
        this.columnKName = columnKName;
        this.isNull = isNull;
        this.dataType = dataType;
        this.length = length;
        this.PK = PK;
        this.FK = FK;
    }
    public TableData() {}

    public String getColumnName() {
        return columnName;
    }
    public String getColumnKName() {
        return columnKName;
    }
    public String getIsNull() {
        return isNull;
    }
    public String getDataType() {
        return dataType;
    }
    public String getLength() {
        return length;
    }
    public String getPK() {
        return PK;
    }
    public String getFK() {
        return FK;
    }

    public void print() {
        System.out.println("this is columnName: " + columnName);
        System.out.println("this is columnKName: " + columnKName);
        System.out.println("this is isNull: " + isNull);
        System.out.println("this is dataType: " + dataType);
        System.out.println("this is length: " + length);
        System.out.println("this is PK: " + PK);
        System.out.println("this is FK: " + FK);
    }
}

public class ExcelTest {
    public static final String projectPath = System.getProperty("user.dir");
    public static void main(String[] args) throws Exception {
        //.xls 확장자 지원
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        Row row = null;
        Cell cell = null;

        //.xlsx 확장자 지원
        XSSFWorkbook xssfWb = null; // .xlsx
        XSSFSheet xssfSheet = null; // .xlsx
        XSSFRow xssfRow = null; // .xlsx
        XSSFCell xssfCell = null;// .xlsx

        String file_name = "fmsEntitySheet";
        try {
            File dirFile = new File(projectPath+"\\entity\\"); // Project folder > Entity folder
            File[] fileList = dirFile.listFiles();// fileLists, inside of Entity folder

            /* count target row */
            int rowNo = 0;
            /* 엑셀 워크북(파일) 생성 */
            xssfWb = new XSSFWorkbook();
            /* 헤더용 폰트 스타일 */
            XSSFFont font = xssfWb.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL); //폰트스타일
            font.setFontHeightInPoints((short)14); //폰트크기
            font.setBold(true); //Bold 유무
            /* 워크시트 이름 if inside of 'for' then make it => "xxx.java" if not => "FMS"*/
            xssfSheet = xssfWb.createSheet("FMS Table");
            /* 워크시트 Column size 조절 */
            xssfSheet.setColumnWidth(0, (xssfSheet.getColumnWidth(0))+(short)4096); // 0번째 컬럼(테이블명) 넓이 조절
            xssfSheet.setColumnWidth(1, (xssfSheet.getColumnWidth(1))+(short)4096); // 1번째 컬럼(테이블 한글명) 넓이 조절
            xssfSheet.setColumnWidth(2, (xssfSheet.getColumnWidth(2))+(short)4096); // 2번째 컬럼(컬럼명) 넓이 조절
            xssfSheet.setColumnWidth(3, (xssfSheet.getColumnWidth(3))+(short)4096); // 3번째 컬럼(컬럼 한글명) 넓이
            xssfSheet.setColumnWidth(5, (xssfSheet.getColumnWidth(5))+(short)2048); // 5번째 컬럼(데이터 타입) 넓이

            /* 셀 병합, (0번째 row의 0~10번째 column을 병합 */
            xssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
            /* 테이블 타이틀 스타일 설정 */
            CellStyle cellStyle_Title = xssfWb.createCellStyle();
            cellStyle_Title.setAlignment(HorizontalAlignment.CENTER); // 수평 가운데 정렬
            cellStyle_Title.setFont(font); // cellStyle에 font 적용

            /* 테이블 헤더 스타일 설정 */
            CellStyle cellStyle_Header = xssfWb.createCellStyle();
            cellStyle_Header.setBorderTop(BorderStyle.THIN); //테두리 위쪽
            cellStyle_Header.setBorderBottom(BorderStyle.THIN); //테두리 아래쪽
            cellStyle_Header.setBorderLeft(BorderStyle.THIN); //테두리 왼쪽
            cellStyle_Header.setBorderRight(BorderStyle.THIN); //테두리 오른쪽
            cellStyle_Header.setAlignment(HorizontalAlignment.CENTER); // 수평 가운데 정렬

            /* 0번째 row에 타이틀 생성 */
            xssfRow = xssfSheet.createRow(rowNo++); //행 객체 추가
            xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 객체 추가
            xssfCell.setCellStyle(cellStyle_Title); // 셀에 스타일 지정
            xssfCell.setCellValue("FMS Table Definition"); // 데이터 입력

            int indexOfFirst = 0;

            for(File tempFile : fileList) {
                if(tempFile.isFile()) {
                    /* Table마다 한 줄 공백을 위해 빈 행 추가 */
                    xssfRow = xssfSheet.createRow(rowNo++);
                    /* 테이블 헤더 추가 */
                    xssfCell = xssfRow.createCell((short) 0);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("테이블명");
                    xssfCell = xssfRow.createCell((short) 1);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("테이블 한글명");
                    xssfCell = xssfRow.createCell((short) 2);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("컬럼명");
                    xssfCell = xssfRow.createCell((short) 3);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("컬럼 한글명");
                    xssfCell = xssfRow.createCell((short) 4);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("Null 여부");
                    xssfCell = xssfRow.createCell((short) 5);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("데이터 타입");
                    xssfCell = xssfRow.createCell((short) 6);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("길이");
                    xssfCell = xssfRow.createCell((short) 7);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("PK");
                    xssfCell = xssfRow.createCell((short) 8);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("FK");
                    xssfCell = xssfRow.createCell((short) 9);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("제약 조건");
                    xssfCell = xssfRow.createCell((short) 10);
                    xssfCell.setCellStyle(cellStyle_Header);
                    xssfCell.setCellValue("설명");
                    /* 테이블 헤더 추가 */
                    xssfRow = xssfSheet.createRow(rowNo++);

                    Vector<TableData> newBlockVector = new Vector<TableData>(); // Vector for get Data

                    String fileName = tempFile.getName();
                    String filePath = tempFile.getAbsolutePath();
                    System.out.println(fileName + filePath);

                    String tableName = fileName.substring(0, fileName.indexOf(".java"));
                    String tableKName = "";
                    BufferedReader br = new BufferedReader(new FileReader(filePath));

                    while(true) {
                        String line = br.readLine();
                        if (line.contains("class")) break;
                        if (line.contains("@ApiModel")) {
                            indexOfFirst = line.indexOf('"');
                            tableKName = line.substring(indexOfFirst+1, line.indexOf('"', indexOfFirst+1));
                        }
                    }

                    System.out.println(tableName);
                    System.out.println(tableKName);

                    StringBuffer sb = new StringBuffer();

                    while(true) {
                        String line = br.readLine();
                        if (line==null) break;
                        sb.append(line).append("\n");
                    }

                    String[] blocks = sb.toString().split("\n\n");

                    for(String block : blocks) {
                        if(!block.contains("private")) continue;

//                        System.out.println("this is block: " + block);

                        /* blockInit() */
                        String columnName = "";
                        String columnKName = "";
                        String isNull = "Y";
                        String dataType = "";
                        String length = "";
                        String PK = "";
                        String FK = "";

                        /* columnName and dataType check */
                        String dataTypeAndcolumnName = StringUtils.substringBetween(block, "private ", ";");
                        dataType = dataTypeAndcolumnName.substring(0, dataTypeAndcolumnName.indexOf(" "));
                        columnName = dataTypeAndcolumnName.substring(dataTypeAndcolumnName.indexOf(" "));

                        block = block.replace(" ", "");

                        /* columnKName check */
                        if(block.contains("@ApiModelProperty")) {
                            String line = StringUtils.substringBetween(block, "@ApiModelProperty", ")");
                            if(line.contains("required")) {
                                columnKName = StringUtils.substringBetween(line, "required=true", "\"").replace("\"", "");
                            }
                            else {
                                columnKName = StringUtils.substringBetween(block, "(value=", ")").replace("\"", "");
                            }

                        }

                        /* isNull check */
                        if(block.contains("@Column(nullable=false)")) {
                            isNull = "N";
                        }


                        /* length check */
                        if(block.contains("@Length")) {
                            length = StringUtils.substringBetween(block, "max=", ")");
                        }

                        /* PK check */
                        if(block.contains("@Id")) {
                            PK = "PK";
                        }

                        /* FK check */
                        if(block.contains("@JoinColumn")) {
                            FK = "FK";
                        }

                        TableData newBlock = new TableData(columnName, columnKName, isNull, dataType, length, PK, FK);
                        newBlockVector.add(newBlock);
                    } // block search
                    /* render it to excel file */
                    Iterator<TableData> iter = newBlockVector.iterator();
                    TableData temp;

                    /* Starting Write Entity on the excel sheet */

                    /* 테이블 스타일 설정 */
                    CellStyle cellStyle_Table_Center = xssfWb.createCellStyle();
                    cellStyle_Table_Center.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle_Table_Center.setVerticalAlignment(VerticalAlignment.CENTER);

                    /* 테이블 하단 스타일 설정 */
                    CellStyle cellStyle_Table_Center_b = xssfWb.createCellStyle();
                    cellStyle_Table_Center_b.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle_Table_Center_b.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle_Table_Center_b.setBorderBottom(BorderStyle.THIN); //테두리 아래쪽

                    /* 테이블 좌측 스타일 설정 */
                    CellStyle cellStyle_Table_Center_l = xssfWb.createCellStyle();
                    cellStyle_Table_Center_l.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle_Table_Center_l.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle_Table_Center_l.setBorderLeft(BorderStyle.THIN); //테두리 왼쪽

                    /* 테이블 우측 스타일 설정 */
                    CellStyle cellStyle_Table_Center_r = xssfWb.createCellStyle();
                    cellStyle_Table_Center_r.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle_Table_Center_r.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle_Table_Center_r.setBorderRight(BorderStyle.THIN); //테두리 오른쪽

                    /* 테이블 하단 + 우측 스타일 설정 */
                    CellStyle cellStyle_Table_Center_br = xssfWb.createCellStyle();
                    cellStyle_Table_Center_br.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle_Table_Center_br.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle_Table_Center_br.setBorderBottom(BorderStyle.THIN); //테두리 아래쪽
                    cellStyle_Table_Center_br.setBorderRight(BorderStyle.THIN); //테두리 오른쪽

                    /* 셀 병합 (테이블 명, 테이블 한글명) */
                    xssfSheet.addMergedRegion(new CellRangeAddress(rowNo - 1, rowNo + newBlockVector.size() - 2, 0, 0));
                    xssfSheet.addMergedRegion(new CellRangeAddress(rowNo - 1, rowNo + newBlockVector.size() - 2, 1, 1));

                    xssfCell = xssfRow.createCell((short) 0);
                    xssfCell.setCellStyle(cellStyle_Table_Center_b);
                    xssfCell.setCellValue(tableName);
                    xssfCell = xssfRow.createCell((short) 1);
                    xssfCell.setCellStyle(cellStyle_Table_Center_b);
                    xssfCell.setCellValue(tableKName);

                    CellStyle blockCellStyle = xssfWb.createCellStyle();

                    while(iter.hasNext()) {
                        System.out.println("===============block===============");
                        temp = iter.next();
                        temp.print();

                        /* excel에서 Table표 좌측 테두리를 위한 Style */
                        //xssfCell = xssfRow.createCell((short) 0);
                        //xssfCell.setCellStyle(cellStyle_Table_Center_l);
                        /* excel에서 Table표 우측 테두리를 위한 Style */
                        xssfCell = xssfRow.createCell((short) 10);
                        xssfCell.setCellStyle(cellStyle_Table_Center_r);


                        if(iter.hasNext()) blockCellStyle = cellStyle_Table_Center;
                        else {
                            blockCellStyle = cellStyle_Table_Center_b;
                            xssfCell = xssfRow.createCell((short) 0);
                            xssfCell.setCellStyle(cellStyle_Table_Center_b);
                            xssfCell = xssfRow.createCell((short) 1);
                            xssfCell.setCellStyle(cellStyle_Table_Center_b);
                            xssfCell = xssfRow.createCell((short) 9);
                            xssfCell.setCellStyle(cellStyle_Table_Center_b);
                            xssfCell = xssfRow.createCell((short) 10);
                            xssfCell.setCellStyle(cellStyle_Table_Center_br);
                        }

                        xssfCell = xssfRow.createCell((short) 2);
                        xssfCell.setCellStyle(blockCellStyle);
                        xssfCell.setCellValue(temp.getColumnName());
                        xssfCell = xssfRow.createCell((short) 3);
                        xssfCell.setCellStyle(blockCellStyle);
                        xssfCell.setCellValue(temp.getColumnKName());
                        xssfCell = xssfRow.createCell((short) 4);
                        xssfCell.setCellStyle(blockCellStyle);
                        xssfCell.setCellValue(temp.getIsNull());
                        xssfCell = xssfRow.createCell((short) 5);
                        xssfCell.setCellStyle(blockCellStyle);
                        xssfCell.setCellValue(temp.getDataType());
                        xssfCell = xssfRow.createCell((short) 6);
                        xssfCell.setCellStyle(blockCellStyle);
                        xssfCell.setCellValue(temp.getLength());
                        xssfCell = xssfRow.createCell((short) 7);
                        xssfCell.setCellStyle(blockCellStyle);
                        xssfCell.setCellValue(temp.getPK());
                        xssfCell = xssfRow.createCell((short) 8);
                        xssfCell.setCellStyle(blockCellStyle);
                        xssfCell.setCellValue(temp.getFK());

                        xssfRow = xssfSheet.createRow(rowNo++);
                    }
                    br.close();
                    /* render it to excel file */
                } // entity.java
            } // entity.java
            String localFile = "C:\\Users\\diehs\\Documents\\sstree\\excelConv\\" + "FMS" + ".xlsx";

            File file = new File(localFile);
            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            xssfWb.write(fos);

            if (xssfWb != null)	xssfWb.close();
            if (fos != null) fos.close();

            //ctx.put("FILENAME", "입고상세출력_"+ mapList.get(0).get("PRINT_DATE"));
            //if(file != null) file.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}