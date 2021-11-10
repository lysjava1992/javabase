package com.learn.drools.decisiontables;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *  drools 除了支持drl文件格式
 *   还支持 策略表xls
 */
public class DecisionTablesApp {
    public static void main(String[] args) throws FileNotFoundException {
        transformDrlToXls("table/table.drl");

    }

    private static String transformDrlToXls(String name) throws FileNotFoundException {
        String path=DecisionTablesApp.class.getClassLoader().getResource(name).getPath();
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String drl = compiler.compile(is, InputType.XLS);

        return drl;
    }
}
