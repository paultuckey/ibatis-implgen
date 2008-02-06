/*
   Generated by JspTem JspEmitterWriter on Thu Feb 07 10:12:29 NZDT 2008
   Please do NOT modify.
 */

package com.ibatis.sqlmap.implgen.template.generated;

import com.ibatis.sqlmap.implgen.bean.ParsedClass;
import com.ibatis.sqlmap.implgen.bean.ParsedMethod;
import com.ibatis.sqlmap.implgen.bean.ParsedParam;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.CharArrayWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class GeneratedSqlMapImplementationTemplate{
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:4
 public ParsedClass parsedClass; 
   private static final String[] _jspDeps = new String[] { "file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp" };

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


// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:2
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:3
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:4
out.write("\n");

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:5
out.write("\n\npackage ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:6
out.write( parsedClass.getPackageStr() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:6
out.write(";\n\nimport com.ibatis.dao.client.DaoManager;\nimport com.ibatis.sqlmap.client.SqlMapClient;\nimport com.ibatis.sqlmap.client.SqlMapClientBuilder;\nimport com.ibatis.common.resources.Resources;\nimport com.ibatis.dao.client.template.SqlMapDaoTemplate;\n\nimport java.util.HashMap;\nimport java.sql.SQLException;\nimport java.io.Reader;\nimport java.io.IOException;\n\n\n/**\n * Generated implementation of ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:21
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:21
out.write(".\n *\n * Generated by ibatis-implgen\n * http://code.google.com/p/ibatis-implgen/\n *\n * DO NOT EDIT\n *\n * @see ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:28
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:29
out.write("\n */\npublic class ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:30
out.write( parsedClass.getGeneratedJavaClassName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:30
out.write(" extends ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:30
out.write( parsedClass.getForceExtendClass() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:31
out.write("\n    ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:31
out.write( parsedClass.isClassAnInterface() ? "implements " + parsedClass.getName() : "" );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:31
out.write(" {\n\n    /**\n     * Constructor\n     *\n     * @param daoManager DaoManager\n     */\n    public ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:38
out.write( parsedClass.getGeneratedJavaClassName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:38
out.write("(DaoManager daoManager) {\n        super(daoManager);\n    }\n\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:42
 for (ParsedMethod method : parsedClass.getMethods()) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:43
out.write("\n    ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:43
 if ( method.isOkToOutputInImplClass() ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:44
out.write("\n    public ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:44
out.write( method.getReturns() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:44
out.write(" ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:44
out.write( method.getName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:44
out.write("(");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:45

            int i = 0;
            for (ParsedParam param : method.getParams()) { 
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:47
out.write( (i > 0 ? ", " : "") );
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:47
out.write( param.getJavaTypeShort() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:47
out.write(" ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:47
out.write( param.getName());
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:48

                i++;
            } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:49
out.write(") ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:49
out.write( method.isThrows() ? "throws " + method.getThrowsClass() : "" );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:49
out.write(" {\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:50
 if ( method.isAnyParameters() ) { 
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:51
 if ( method.isMultipleParameters() ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:51
out.write("HashMap<String, Object> params = new HashMap<String, Object>();");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:53
 for (ParsedParam param : method.getParams()) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:54
out.write("\n        params.put(\"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:54
out.write( param.getName());

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:54
out.write("\", ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:54
out.write( param.getName());

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:54
out.write(");");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:55
 } 
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:56
 } 
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:57
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:58
out.write("\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:58
 if ( method.isAlternativeThrows() ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:59
out.write("\n            try {\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:60
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:61
out.write("\n        //noinspection unchecked,UnnecessaryLocalVariable\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:62
 if ( !method.isReturnsVoid() ) { 
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:63
out.write( method.getReturns() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:63
out.write(" ret = (");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:63
out.write( method.getReturns() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:63
out.write(")");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:64
 } 
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:65
 if ( method.isReturnsList() ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:65
out.write(" queryForList(\"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write(".");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write( method.getIdForIbatis() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write("\", ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write( method.getParamsVarName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:66
out.write("); ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:67
 } else if (ParsedMethod.Type.UPDATE.equals(method.getType()) ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:67
out.write(" update(\"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write(".");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write( method.getIdForIbatis() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write("\", ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write( method.getParamsVarName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:68
out.write("); ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:69
 } else if (ParsedMethod.Type.INSERT.equals(method.getType()) ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:69
out.write(" insert(\"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:70
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:70
out.write(".");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:70
out.write( method.getIdForIbatis() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:70
out.write("\", ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:70
out.write( method.getParamsVarName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:70
out.write("); ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:71
 } else if (ParsedMethod.Type.DELETE.equals(method.getType()) ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:71
out.write(" delete(\"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:72
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:72
out.write(".");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:72
out.write( method.getIdForIbatis() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:72
out.write("\", ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:72
out.write( method.getParamsVarName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:72
out.write("); ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:73
 } else { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:73
out.write(" queryForObject(\"");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:74
out.write( parsedClass.getFullyQualifiedName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:74
out.write(".");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:74
out.write( method.getIdForIbatis() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:74
out.write("\", ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:74
out.write( method.getParamsVarName() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:74
out.write("); ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:75
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:76
out.write("\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:76
 if ( !method.isReturnsVoid() ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:77
out.write("\n        return ret;\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:78
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:79
out.write("\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:79
 if ( method.isAlternativeThrows() ) { 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:80
out.write("\n            } catch (SQLException e) {\n                throw new ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:81
out.write( method.getAlternativeThrowsClass() );

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:81
out.write("(e);\n            }\n        ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:83
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:84
out.write("\n    }\n    ");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:85
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:86
out.write("\n");
// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:86
 } 

// line:file:/C:/java/ibatis-implgen/src/com/ibatis/sqlmap/implgen/template/SqlMapImplementationTemplate.jsp:87
out.write("\n\n}");


           out.flush();

        } catch (IOException e) {
             throw e;
        } catch (Throwable e) {
             e.printStackTrace();             throw new IOException(e.toString());
        }

   }

}
