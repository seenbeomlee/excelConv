import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class ExcelTest {

    public static class RowData {
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

    public static class TableData {

        private String[] headers = {"테이블명", "테이블 한글명", "컬럼명", "컬럼 한글명", "Null 여부", "데이터 타입", "길이", "PK", "FK", "제약 조건", "설명"};
        private List<RowData> rows; // = new ArrayList<RowData>();
        private int rowNo;

        public TableData() {
            rows = new ArrayList<RowData>();
            rowNo = 1;
        }

        public void pushRowData(RowData inputVal) {
            rows.add(inputVal);
        }

        public int getSizeOfRows() {
            return rows.size();
        }

        private void printHeaders(XSSFWorkbook xssfWb, XSSFSheet xssfSheet, XSSFRow xssfRow, XSSFCell xssfCell) {
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
            ;
        }

        private void printBody(XSSFWorkbook xssfWb, XSSFSheet xssfSheet, XSSFRow xssfRow, XSSFCell xssfCell) {

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
                row.printRowData(xssfRow, xssfCell, blockMergeCellStyle, blockCellStyle);
                xssfRow = xssfSheet.createRow(rowNo++);
            }
        }

        public void printExcel(XSSFWorkbook xssfWb, XSSFSheet xssfSheet, XSSFRow xssfRow, XSSFCell xssfCell) {
            xssfRow = xssfSheet.createRow(rowNo++);
            printHeaders(xssfWb, xssfSheet, xssfRow, xssfCell);
            xssfRow = xssfSheet.createRow(rowNo++);
            printBody(xssfWb, xssfSheet, xssfRow, xssfCell);
        }
    }

    public static class ExcelFile {
        private String projectPath;
        private String excelFileName;
        private String localFile;

        /* this is for excelSheet */
        private String fileName;
        private String filePath;

        private HSSFWorkbook wb;
        private HSSFSheet sheet;
        private Row row;
        private Cell cell;

        private XSSFWorkbook xssfWb;
        private XSSFSheet xssfSheet;
        private XSSFRow xssfRow;
        private XSSFCell xssfCell;

        private File dirFile;
        private File file;
        private File[] fileList;

        private FileOutputStream fos;

        ExcelFile() {
            /* 프로젝트 경로 설정 */
            projectPath = System.getProperty("user.dir");

            /* .xls 확장자 지원 */
            wb = null;
            sheet = null;
            row = null;
            cell = null;

            /* .xlsx 확장자 지원 */
            xssfWb = null;
            xssfSheet = null;
            xssfRow = null;
            xssfCell = null;

            /* 엑셀 워크북(파일) 생성 */
            xssfWb = new XSSFWorkbook();

            /* excel 파일 이름 */
            excelFileName = "FMS";

            dirFile = new File(projectPath + "\\entity\\"); // Project folder > Entity folder
            fileList = dirFile.listFiles();// fileLists, inside of Entity folder
        }

        /* this is for excelSheet */
        public void updateFilePathAndFileName(File tempFile) {
            /* 현재 읽고 있는 .java 파일 이름 */
            fileName = tempFile.getName();
            /* 현재 읽고 있는 .java 파일 절대경로 */
            filePath = tempFile.getAbsolutePath();
            System.out.println(fileName + filePath);
        }

        public File[] getFileList() {
            return fileList;
        }

        public XSSFWorkbook getXssfWb() {
            return xssfWb;
        }

        public XSSFSheet getXssfSheet() {
            return xssfSheet;
        }

        public XSSFRow getXssfRow() {
            return xssfRow;
        }

        public XSSFCell getXssfCell() {
            return xssfCell;
        }

        /* this is for excelSheet */
        public String getFileName() {
            return fileName;
        }

        /* this is for excelSheet */
        public String getFilePath() {
            return filePath;
        }

        /* this is for excelSheet */
        public void initExcelSheet(String fileName) {

            /* 워크시트 이름 if inside of 'for' then make it => "xxx.java" if not => "FMS Table"*/
            xssfSheet = xssfWb.createSheet(fileName);
            /* 워크시트 Column size 조절 */
            xssfSheet.setColumnWidth(0, (xssfSheet.getColumnWidth(0)) + (short) 4096); // 0번째 컬럼(테이블명) 넓이 조절
            xssfSheet.setColumnWidth(1, (xssfSheet.getColumnWidth(1)) + (short) 4096); // 1번째 컬럼(테이블 한글명) 넓이 조절
            xssfSheet.setColumnWidth(2, (xssfSheet.getColumnWidth(2)) + (short) 4096); // 2번째 컬럼(컬럼명) 넓이 조절
            xssfSheet.setColumnWidth(3, (xssfSheet.getColumnWidth(3)) + (short) 4096); // 3번째 컬럼(컬럼 한글명) 넓이
            xssfSheet.setColumnWidth(5, (xssfSheet.getColumnWidth(5)) + (short) 2048); // 5번째 컬럼(데이터 타입) 넓이
        }

        public void saveExcelFile() {
            try {
                /* save it to localFile and close it */
                localFile = projectPath + "\\" + excelFileName + ".xlsx";
                file = new File(localFile);
                fos = null;
                fos = new FileOutputStream(file);
                xssfWb.write(fos);
                if (xssfWb != null) xssfWb.close();
                if (fos != null) fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* this is for excelSheet */
        public void createTitle() {
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

        public void execute() {
            /* jsmooth로 exe 파일 만들어 실행 시, 한글이 깨지는 현상 방지 */
            jsmoothSetting();

            try {
                for (File tempFile : getFileList()) {
                    if (tempFile.isFile()) {
                        /* these are for excelSheet */
                        TableData tableData = new TableData();

                        /* Update excelSheet */
                        updateFilePathAndFileName(tempFile);
                        initExcelSheet(getFileName());
                        createTitle();

                        /* for Read Lines */
                        LineReader lineReader = new LineReader(getFileName());
                        BufferedReader br = new BufferedReader(new java.io.FileReader(getFilePath()));

                        while (true) {
                            String line = br.readLine();
                            if (line == null) break;

                            if (line.contains("private")) {
                                tableData.pushRowData(lineReader.casePrivate(line));
                                continue;
                            } else {
                                /* if line has space, we cannot bring a content what we want */
                                line = line.replace(" ", "");
                                lineReader.caseElse(line);
                            }
                        }
                        br.close();

                        tableData.printExcel(getXssfWb(), getXssfSheet(), getXssfRow(), getXssfCell());
                    }
                }
                saveExcelFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class ExcelSheet {
        
    }

    public static class LineReader {
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
                columnName = "";
                columnKName = "";
                isNull = "Y";
                dataType = "";
                length = "";
                PK = "";
                FK = "";
        }

        public void blockInit() {
            columnName = "";
            columnKName = "";
            isNull = "Y";
            dataType = "";
            length = "";
            PK = "";
            FK = "";
        }

        public RowData casePrivate(String line) {
            String dataTypeAndcolumnName = StringUtils.substringBetween(line, "private ", ";");

            dataType = dataTypeAndcolumnName.substring(0, dataTypeAndcolumnName.indexOf(" "));
            columnName = dataTypeAndcolumnName.substring(dataTypeAndcolumnName.indexOf(" "));

            /* return RowData and push it to TableData's List<RowData> Rows */
            RowData rowData = new RowData(tableName, tableKName, columnName, columnKName, isNull, dataType, length, PK, FK);

            blockInit();
            return rowData;
        }

        public void caseElse(String line) {
            word = line.split("\\(|/");

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
        }
    }

    static void jsmoothSetting() {
        System.setProperty("file.encoding", "UTF-8");
        try {
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ExcelFile excelFile = new ExcelFile();
        excelFile.execute();
    }
}
