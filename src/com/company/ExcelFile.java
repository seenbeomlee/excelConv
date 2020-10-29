package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.*;
import java.io.*;
import java.util.*;

public class ExcelFile {
  private String projectPath;
  private String packagePath;
  private String excelFileName;
  /* to save ExcelFile in to local */
  private String localFile;

  private HSSFWorkbook wb;

  private XSSFWorkbook xssfWb;

  private File dirFile;
  private File file;
  private List<File> fileList;

  private FileOutputStream fos;

  ExcelFile() {}

  ExcelFile(String projectPath) {
    /* 프로젝트 경로 설정 */
    this.projectPath = projectPath;

    /* get packagePath by extracting string after word "src" from our projectpath */
    packagePath = projectPath.split("src")[1];

    /* .xls 확장자 지원 */
    wb = null;

    /* .xlsx 확장자 지원 */
    xssfWb = null;

    /* 엑셀 워크북(파일) 생성 */
    xssfWb = new XSSFWorkbook();

    /* excel 파일 이름 */
    excelFileName = projectPath.split("autocrypt.")[1].split("\\\\")[0];

    dirFile = new File(this.projectPath); // projectPath == Entity folder
    fileList = Arrays.asList(dirFile.listFiles());// fileLists, inside of Entity folder
  }

  public List<File> getFileList() {
    return Objects.isNull(fileList) ? Collections.EMPTY_LIST : fileList;
  }

  public XSSFWorkbook getXssfWb() {
    return xssfWb;
  }

  public String getPackagePath() { return packagePath; }

  public String getExcelFileName() { return excelFileName; }

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

  public void execute() throws Exception {
    for (File tempFile : getFileList()) {
      try {
        if (tempFile.isFile()) {

          ExcelSheet excelSheet = new ExcelSheet(tempFile);
          TableData tableData = new TableData();

          /* Set excelSheet */
          excelSheet.initExcelSheet(xssfWb);
          excelSheet.createTitle(xssfWb, getExcelFileName());

          /* for Read Lines */
          String classPath = projectPath.split("java\\\\")[1].replace("\\", ".");

          LineReader lineReader = new LineReader(excelSheet.getFileName(), classPath);
          BufferedReader br = new BufferedReader(new java.io.FileReader(excelSheet.getFilePath()));
          String line = null;

          /* Read codes line by line */
          while ((line = br.readLine()) != null) {
            try {
              if (!line.contains("private") && !line.contains("public") && !line.contains("protected")) {
                /* if line has space, we cannot bring a content what we want */
                line = line.replace(" ", "");
                lineReader.caseElse(line);
              } else {
                tableData.pushRowData(lineReader.caseCheckFields(line));
                continue;
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          br.close();

          /* write Data to excelFormat */
          tableData.printExcel(getXssfWb(), excelSheet.getXssfSheet(), excelSheet.getXssfRow(), excelSheet.getXssfCell());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    saveExcelFile();
  }
}