/*
   Generated by JspTem JspEmitterWriter on Wed Nov 14 12:00:55 NZDT 2007
   Please do NOT modify.
 */

package com.ibatis.sqlmap.implgen.template.generated;

import com.ibatis.sqlmap.implgen.*;
import java.util.List;
import com.ibatis.sqlmap.implgen.bean.ParsedCacheModel;
import com.ibatis.sqlmap.implgen.bean.ParsedMethod;
import com.ibatis.sqlmap.implgen.bean.ParsedResultMap;
import com.ibatis.sqlmap.implgen.bean.ParsedClass;
import com.ibatis.sqlmap.implgen.bean.ParsedResult;
import com.ibatis.sqlmap.implgen.bean.ParsedParam;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.CharArrayWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class GeneratedSqlMapInterfaceTemplate{
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:9
 public ParsedClass parsedClass; // line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:10
 public List allItems; 
   private static final String[] _jspDeps = new String[] { "file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp" };

   public final String[] _jspGetDeps() {
      return _jspDeps;
   }

   public final long _jspGetCompilerVersion() {
      return 1L;
   }

   public void writeToFile(File f) throws IOException {
       write(new FileWriter(f));
   }

   public void writeToStream(OutputStream myStream) throws IOException {
       write(new PrintWriter(myStream));
   }

   public String writeToString() throws IOException {
       CharArrayWriter caw = new CharArrayWriter();
       write(caw);
       return caw.toString();
   }

    class JspWriter extends Writer {
        Writer originalWriter;

        public JspWriter(Writer originalWriter) {
            this.originalWriter = originalWriter;
        }

        public void write(String str) throws IOException {
            if ( str == null ) str = "";
            super.write(str);
        }
        public void write(char cbuf[], int off, int len) throws IOException {
            if ( cbuf == null) cbuf = "".toCharArray();
            originalWriter.write(cbuf, off, len);
        }
        public void flush() throws IOException {
            originalWriter.flush();
        }

        public void close() throws IOException {
            originalWriter.close();
        }
    }
   public void write(Writer originalWriter) throws IOException {
       final Writer out = new JspWriter(originalWriter);
       try {


// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:2
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:3
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:4
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:5
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:6
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:7
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:8
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:9
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:10
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:11
out.write("\n\npackage ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:12
out.write( parsedClass.getPackageStr() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:12
out.write(";\n\nimport java.sql.SQLException;\nimport com.ibatis.sqlmap.implgen.annotations.Procedure; \n\npublic interface ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:17
out.write( parsedClass.getName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:17
out.write(" {\n\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:20

    for ( Object obj : allItems ) {

     if ( obj instanceof ParsedCacheModel ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:23
out.write("\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:23
 ParsedCacheModel model = (ParsedCacheModel) obj; 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:24
out.write("\n        @CacheModel(id = \"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:24
out.write( model.getId() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:24
out.write("\", type=\"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:24
out.write( model.getType() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:24
out.write("\", flushIntervalHours = \"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:24
out.write( model.getFlushIntervalHours() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:24
out.write("\")\n    ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:26

        }
       if ( obj instanceof ParsedResultMap ) { 
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:28
 ParsedResultMap resultMap = (ParsedResultMap) obj; 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:28
out.write("@ResultMap(id = \"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:29
out.write( resultMap.getId() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:29
out.write("\", results = {");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:30
 for ( ParsedResult result : resultMap.getResults() ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:30
out.write("@Result(property = \"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write( result.getProperty());

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write("\", column = \"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write( result.getColumn());

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write("\", javaType = \"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write( result.getJavaType());

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write("\", jdbcType = \"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write( result.getJdbcType());

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write("\", nullValue = \"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write( result.getNullValue());

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:31
out.write("\"),\n            ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:32
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:33
out.write("\n            })\n       ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:35

      }
      if ( obj instanceof ParsedMethod ) { 
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:37
 ParsedMethod method = (ParsedMethod) obj; 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:38
out.write("\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:38
 if ( method.getType() == ParsedMethod.Type.DELETE ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:39
out.write("\n            @Delete\n            public void ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:40
out.write( method.getName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:40
out.write("() throws SQLException /*sql{\n\n                ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:42
out.write( method.getSql() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:43
out.write("\n\n            }*/;\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:45
 }
           if ( method.getType() == ParsedMethod.Type.PROCEDURE ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:47
out.write("\n            @Procedure\n            public void ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:48
out.write( method.getName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:48
out.write("(");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:50

                    boolean first = true;
                    for (ParsedParam param : method.getParams()) {
                    
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:53
out.write( first ? "": "," );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:53
out.write(" ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:53
out.write( param.getJavaTypeShort() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:53
out.write(" ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:53
out.write( param.getName() );
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:54
 first = false; 
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:56

                    }
                

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:57
out.write(" ) throws SQLException /*sql{\n\n                ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:59
out.write( method.getSql() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:60
out.write("\n\n            }*/;\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:62
 }
        }
    } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapInterfaceTemplate.jsp:65
out.write("\n\n\n}");


           out.flush();

        } catch (IOException e) {
             throw e;
        } catch (Throwable e) {
             e.printStackTrace();             throw new IOException(e.toString());
        }

   }

}
