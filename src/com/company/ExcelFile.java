package com.company;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelFile {
  private String projectPath;
  private String excelFileName;
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
    projectPath = projectPath;

    /* .xls 확장자 지원 */
    wb = null;

    /* .xlsx 확장자 지원 */
    xssfWb = null;

    /* 엑셀 워크북(파일) 생성 */
    xssfWb = new XSSFWorkbook();

    /* excel 파일 이름 */
    excelFileName = "FMS";

    dirFile = new File(projectPath); // projectPath == Entity folder
    fileList = dirFile.listFiles();// fileLists, inside of Entity folder
  }

  public File[] getFileList() {
    return fileList;
  }

  public XSSFWorkbook getXssfWb() {
    return xssfWb;
  }

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
        if(tempFile == null) {
          throw new Exception();
        }
        if (tempFile.isFile()) {
          ExcelSheet excelSheet = new ExcelSheet(tempFile);
          TableData tableData = new TableData();

          /* Set excelSheet */
          excelSheet.initExcelSheet(xssfWb);
          excelSheet.createTitle(xssfWb);

          /* for Read Lines */
          LineReader lineReader = new LineReader(excelSheet.getFileName());
          BufferedReader br = new BufferedReader(new java.io.FileReader(excelSheet.getFilePath()));
          String line = null;
          /* Read codes line by line */
          while ((line = br.readLine()) != null) {
            try {
              if (!line.contains("private")) {
                /* if line has space, we cannot bring a content what we want */
                line = line.replace(" ", "");
                lineReader.caseElse(line);
              } else {
                tableData.pushRowData(lineReader.casePrivate(line));
                continue;
              }
            } catch(Exception e) {
              e.printStackTrace();
              throw new Exception(line, e);
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