package org.tuckey.ibatis.implgen.proc;

import org.tuckey.ibatis.implgen.Util;
import org.tuckey.ibatis.implgen.bean.ParsedProc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public interface ProcParser {

    public void process(File f, ParsedProc sp) throws IOException;

    void registerSqlToJavaConversion(String sqlType, String javaType);
}
