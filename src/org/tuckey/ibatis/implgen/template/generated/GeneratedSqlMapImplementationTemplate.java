/*
   Generated by JspTem JspEmitterWriter on Fri Aug 10 16:26:01 NZST 2007
   Please do NOT modify.
 */

package org.tuckey.ibatis.implgen.template.generated;

import org.tuckey.ibatis.implgen.*;
import org.tuckey.ibatis.implgen.bean.ParsedParam;
import org.tuckey.ibatis.implgen.bean.ParsedClass;
import org.tuckey.ibatis.implgen.bean.ParsedMethod;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.CharArrayWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class GeneratedSqlMapImplementationTemplate{
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:5
 public ParsedClass parsedClass; 
   private static final String[] _jspDeps = new String[] { "file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp" };

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

   public void write(Writer out) throws IOException {
       final Writer originalWriter = out;
       out = new Writer() {
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
       };       try {


// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:2
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:3
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:4
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:5
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:6
out.write("\n\npackage ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:7
out.write( parsedClass.getPackageStr() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:7
out.write(";\n\nimport com.ibatis.sqlmap.client.SqlMapClient;\nimport com.ibatis.sqlmap.client.SqlMapClientBuilder;\nimport com.ibatis.common.resources.Resources;\n\nimport java.util.HashMap;\nimport java.sql.SQLException;\nimport java.io.Reader;\nimport java.io.IOException;\n\n\n/**\n * Generated implementation of ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:20
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:20
out.write(".\n *\n * Generated by ibatis-implgen\n * http://code.google.com/p/ibatis-implgen/\n *\n * DO NOT EDIT\n *\n * @see ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:27
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:28
out.write("\n */\npublic class ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:29
out.write( parsedClass.getGeneratedJavaClassName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:29
out.write(" ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:29
out.write( parsedClass.getImplementsOrExtends() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:29
out.write(" ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:29
out.write( parsedClass.getName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:29
out.write(" {\n\n  private static SqlMapClient sqlMapper;\n\n  static {\n    try {\n      Reader reader = Resources.getResourceAsReader(\"");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:35
out.write( parsedClass.getGeneratedXmlFilePath() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:35
out.write("\");\n      sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);\n      reader.close();\n    } catch (IOException e) {\n      // Fail fast.\n      throw new RuntimeException(\"Something bad happened while building the SqlMapClient instance.\" + e, e);\n    }\n  }\n\n\n");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:45
 for (ParsedMethod method : parsedClass.getMethods()) { 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:46
out.write("\n    public ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:46
out.write( method.getReturns() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:46
out.write(" ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:46
out.write( method.getName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:46
out.write("(");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:47

            int i = 0;
            for (ParsedParam param : method.getParams()) { 
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:49
out.write( (i > 0 ? ", " : "") );
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:49
out.write( param.getTypeForJavaClass() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:49
out.write(" ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:49
out.write( param.getName());
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:50

                i++;
            } 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:51
out.write(") throws SQLException {\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:52
 if ( method.isAnyParameters() ) { 
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:53
 if ( method.isMultipleParameters() ) { 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:54
out.write("\n        HashMap<String, Object> params = new HashMap<String, Object>();");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:55
 for (ParsedParam param : method.getParams()) { 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:56
out.write("\n        params.put(\"");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:56
out.write( param.getName());

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:56
out.write("\", ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:56
out.write( param.getName());

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:56
out.write(");");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:57
 } 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:58
out.write("\n            ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:58
 } 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:59
out.write("\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:59
 } 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:60
out.write("\n\n        //noinspection unchecked,UnnecessaryLocalVariable\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:62
 if ( !method.isReturnsVoid() ) { 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:63
out.write("\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:63
out.write( method.getReturns() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:63
out.write(" ret = (");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:63
out.write( method.getReturns() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:63
out.write(")\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:64
 } 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:65
out.write("\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:65
 if ( method.isReturnsList() ) { 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write("\n        sqlMapper.queryForList(\"");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write(".");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write( method.getName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write("\", ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write( method.getParamsVarName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write(");\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:67
 } else { 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write("\n        sqlMapper.queryForObject(\"");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write(".");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write( method.getName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write("\", ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write( method.getParamsVarName() );

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write(");\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:69
 } 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:70
out.write("\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:70
 if ( !method.isReturnsVoid() ) { 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:71
out.write("\n        return ret;\n        ");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:72
 } 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:73
out.write("\n    }\n\n");
// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:75
 } 

// line:file:/C:/java/ibatis-implgen/src/org/tuckey/ibatis/implgen/template/SqlMapImplementationTemplate.jsp:76
out.write("\n\n}");


           out.flush();

        } catch (IOException e) {
             throw e;
        } catch (Throwable e) {
             e.printStackTrace();             throw new IOException(e.toString());
        }

   }

}
