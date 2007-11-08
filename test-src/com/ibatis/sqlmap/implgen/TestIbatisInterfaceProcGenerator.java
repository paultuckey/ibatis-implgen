package com.ibatis.sqlmap.implgen;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;


public class TestIbatisInterfaceProcGenerator extends TestCase {


    public void testBasic() throws IOException {
        IbatisInterfaceProcGenerator procGen = new IbatisInterfaceProcGenerator();
        procGen.registerSqlToJavaConversion("nsn", "java.lang.Long");
        procGen.registerSqlToJavaConversion("OBJECT_ID", "java.lang.Long");
        procGen.registerSqlToJavaConversion("CODE_2", "java.lang.Long");
        procGen.registerSqlToJavaConversion("yn", "java.lang.Boolean");
        procGen.registerSqlToJavaConversion("long_name", "java.lang.String");
        procGen.registerSqlToJavaConversion("name", "java.lang.String");
        procGen.registerSqlToJavaConversion("dt", "java.util.Date");
        procGen.processDir(new File("c:/web/projects-HEAD/javasrc/src/nz/govt/nzqa/web/persistence/sql/learner/web-eqa/"));
    }

}
