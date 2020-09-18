package com.company;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelFile {
  private String projectPath;
  private String packagePath;
  private String excelFileName;
  private List<String> fields;
  /* to save ExcelFile in to local */
  private String localFile;

  private HSSFWorkbook wb;

  private XSSFWorkbook xssfWb;

  private File dirFile;
  private File file;
  private File[] fileList;

  private FileOutputStream fos;

  ExcelFile() {}

  ExcelFile(String projectPath) {
    /* 프로젝트 경로 설정 */
    this.projectPath = projectPath;
    packagePath = projectPath.split("src")[1];

    /* .xls 확장자 지원 */
    wb = null;

    /* .xlsx 확장자 지원 */
    xssfWb = null;

    /* 엑셀 워크북(파일) 생성 */
    xssfWb = new XSSFWorkbook();

    /* excel 파일 이름 */
    excelFileName = "FMS";

    dirFile = new File(this.projectPath); // projectPath == Entity folder
    fileList = dirFile.listFiles();// fileLists, inside of Entity folder

    fields = new ArrayList<String>();
  }

  public File[] getFileList() {
    return fileList;
  }

  public XSSFWorkbook getXssfWb() {
    return xssfWb;
  }

  public String getPackagePath() { return packagePath; }

  public void saveExcelFile() throws IOException {

      /* save it to localFile and close it */
      localFile = projectPath + "\\" + excelFileName + ".xlsx";
      file = new File(localFile);
      fos = null;
      fos = new FileOutputStream(file);
      xssfWb.write(fos);
      if (xssfWb != null) xssfWb.close();
      if (fos != null) fos.close();
  }

  public void execute() {
    try {
      for (File tempFile : getFileList()) {
        if (tempFile.isFile()) {
          if(tempFile == null) {
            throw new FileNotFoundException();
          }

          ExcelSheet excelSheet = new ExcelSheet(tempFile);
          TableData tableData = new TableData();

          /* Set excelSheet */
          excelSheet.initExcelSheet(xssfWb);
          excelSheet.createTitle(xssfWb);

          /* for Read Lines */
          LineReader lineReader = new LineReader(excelSheet.getFileName());
          BufferedReader br = new BufferedReader(new java.io.FileReader(excelSheet.getFilePath()));
          String line = null;

          /* line checker */
          try {
            Class c = Class.forName("com.company.entity." + lineReader.getTableName());

            Field[] classField = c.getDeclaredFields();

            for(int i = 0; i < classField.length; i++) {
              String fieldString = classField[i].toString();
              fields.add(fieldString.substring(fieldString.lastIndexOf(".")).replace(".", ""));
            }
          } catch (Exception e) {
            e.printStackTrace();
          }

          /* Read codes line by line */
          while ((line = br.readLine()) != null) {
            try {
              if (!line.contains("private") && !line.contains("public") && !line.contains("protected")) {
                /* if line has space, we cannot bring a content what we want */
                line = line.replace(" ", "");
                lineReader.caseElse(line);
              } else {
                if(fields.contains(lineReader.getColumnName())){
                  tableData.pushRowData(lineReader.casePrivate(line));
                }
                continue;
              }
            } catch(Exception e) {
              e.printStackTrace();
            }
          }
          br.close();

          /* write Data to excelFormat */
          tableData.printExcel(getXssfWb(), excelSheet.getXssfSheet(), excelSheet.getXssfRow(), excelSheet.getXssfCell());
        }
      }
      saveExcelFile();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}