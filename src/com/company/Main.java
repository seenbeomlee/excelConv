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

    public static void main(String[] args) {
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

// sequential하게 작동하는 function 각각에 error throw
// error가 발생할 수 있는 action에 try catch
// => saveExcelFile에 지금 throw처리만 해준 상태
// => error가 걸렸을 때, stop이 아니라 그 부분 빼고 이어서 하도록

// line 68 => file을 읽지 못했을 경우 에러처리
// line 87에 caseElse에 throw 처리한 것처럼
// line 89에 throw 처리해서 while의 try / catch가 받도록