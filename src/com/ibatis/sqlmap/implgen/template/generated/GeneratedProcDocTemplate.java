/*
   Generated by JspTem JspEmitterWriter on Wed Nov 14 12:00:55 NZDT 2007
   Please do NOT modify.
 */

package com.ibatis.sqlmap.implgen.template.generated;

import com.ibatis.sqlmap.implgen.bean.ParsedParam;
import com.ibatis.sqlmap.implgen.bean.ParsedProc;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.CharArrayWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class GeneratedProcDocTemplate{
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:3

    public ParsedProc[] procs;
    public String name;

   private static final String[] _jspDeps = new String[] { "file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp" };

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


// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:2
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:3
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:7
out.write("\n<html>\n<head>\n    <title>Stored Procedures in ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:9
out.write( name );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:9
out.write("</title>\n    <style type=\"text/css\">\n        .params td {\n            width: 200px;\n            border: 1px solid #eeeeee;\n        }\n    </style>\n</head>\n<body>\n\n<h1>Stored Procedures in ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:19
out.write( name );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:19
out.write("</h1>\n\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:21
 if (procs != null) {
    for (ParsedProc proc : procs) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:23
out.write("\n<a name=\"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:23
out.write( proc.getName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:23
out.write("\"></a>\n\n<h2>");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:25
out.write( proc.getName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:26
out.write("\n</h2>\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:27
 if (proc.isVersion()) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:28
out.write("\n<small>Version: ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:28
out.write( proc.getVersion() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:29
out.write("\n</small>\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:30
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:31
out.write("\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:31
 if (proc.isAuthor()) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:32
out.write("\n<small>Author: \" + proc.getAuthor() + \"</small>\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:33
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:34
out.write("\n<p>");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:34
out.write( proc.getDescription() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:35
out.write("\n</p>\n\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:37
 if (proc.isAnyParams()) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:38
out.write("\n\n<h3>Parameters</h3>\n<table class=\"params\">\n\n    ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:42
 for (int j = 0; j < proc.getParams().length; j++) {
        ParsedParam param = proc.getParams()[j];
    

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:45
out.write("\n    <tr>\n        <td><b>");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:46
out.write( param.getName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:46
out.write("</b></td>\n        <td>");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:47
out.write( param.getSqlType() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:47
out.write("</td>\n        <td>");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:48
out.write( param.getDescription() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:48
out.write("</td>\n    </tr>\n    ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:50
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:51
out.write("\n</table>\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:52
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:53
out.write("\n\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:54
 if (proc.isReturnDesc()) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:55
out.write("\n    <h3>Return</h3>\n    <p>");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:56
out.write( proc.getReturnDesc() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:56
out.write("</p>\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:57
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:58
out.write("\n<hr/>\n\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:61

        }
    }


// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/ProcDocTemplate.jsp:64
out.write("\n\n</body>\n</html>\n");


           out.flush();

        } catch (IOException e) {
             throw e;
        } catch (Throwable e) {
             e.printStackTrace();             throw new IOException(e.toString());
        }

   }

}
