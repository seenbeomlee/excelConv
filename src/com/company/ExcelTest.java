import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
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
        String sheet_name = "fmsEntitySheet";
        try {
            File dirFile = new File(projectPath+"\\entity\\"); // Project folder > Entity folder
            File[] fileList = dirFile.listFiles();// fileLists, inside of Entity folder
            int indexOfFirst = 0;
            for(File tempFile : fileList) {
                if(tempFile.isFile()) {
                    Vector<TableData> newBlockVector = new Vector<TableData>(); // Vector for get Data

                    String fileName = tempFile.getName();
                    String filePath = tempFile.getAbsolutePath();
                    System.out.println(fileName + filePath);

                    String tableName = fileName.substring(0, fileName.indexOf(".java"));
                    String tableKName = "None";
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

                    while(iter.hasNext()) {
                        System.out.println("=============new block==============");
                        temp = iter.next();
                        temp.print();
                    }
                    br.close();
                    /* render it to excel file */
                } // entity.java
            } // entity.java
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}