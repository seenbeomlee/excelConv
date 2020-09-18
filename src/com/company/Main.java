package com.company;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class Main {

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

<<<<<<< HEAD
    public static void main(String[] args)  {
=======
    public static void main(String[] args) {
>>>>>>> 48237918ed15f8e6e0eb00df65f7b4d5dae51fb0
        try {
            String projectPath = args[0];
            if (projectPath.isEmpty()) {
                throw new Exception();
            }
            /* jsmooth로 exe 파일 만들어 실행 시, 한글이 깨지는 현상 방지 */
            jsmoothSetting();

            ExcelFile excelFile = new ExcelFile(projectPath);
            excelFile.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}