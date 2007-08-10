/**
 * Copyright (c) 2005-2007, Paul Tuckey
 * All rights reserved.
 * ====================================================================
 * Licensed under the BSD License. Text as follows.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   - Neither the name tuckey.org nor the names of its contributors
 *     may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 */
package org.tuckey.ibatis.implgen.proc;

import java.io.*;

/**
 * For each directory of procs will generate an interface with calls to the procs in iBATIS impl-gen style.
 */
public class IBatisInterfaceProcGenerator {

    public ParsedProc[] procs;

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

    private Writer out = null;

    private void outWrite(String s) throws IOException {
        if (s == null) return;
        out.write(s);
    }

    private void write(Writer out) throws IOException {
        this.out = out;
        try {

            outWrite("\n<html>\n<head>\n    " +
                    "<title>Stored Procedures</title>\n" +
                    "<style type=\"text/css\">\n        " +
                    ".params td {\n width: 200px;\n border: 1px solid #eeeeee;\n }\n    " +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n\n\n\n" +
                    "<h1>Stored Procedures</h1>\n\n");

            if (procs != null) {
                for (ParsedProc proc : procs) {
                    outWrite("\n\n    <a name=\"");
                    outWrite(proc.getName());
                    outWrite("\"></a>\n    <h2>");
                    outWrite(proc.getName());
                    outWrite("</h2>");

                    if (proc.isVersion() || proc.isAuthor()) outWrite("<p>");
                    if (proc.isVersion()) {
                        outWrite("<small>Version: " + proc.getVersion() + "</small>");
                    }
                    if (proc.isAuthor()) {
                        if (proc.isVersion()) outWrite("<br/>");
                        outWrite("<small>Author: " + proc.getAuthor() + "</small>");
                    }
                    if (proc.isVersion() || proc.isAuthor()) outWrite("</p>");

                    outWrite("<p>");
                    outWrite(proc.getDescription());
                    outWrite("</p>\n\n    ");

                    if (proc.isAnyParams()) {

                        outWrite("\n<h3>Parameters</h3>\n        <table class=\"params\">\n");

                        for (int j = 0; j < proc.getParams().length; j++) {
                            ParsedProcParam param = proc.getParams()[j];
                            outWrite("\n<tr><td><b>");
                            outWrite(param.getName());
                            outWrite("</b></td><td>");
                            outWrite(param.getType());
                            outWrite("</td><td>");
                            outWrite(param.getDescription());
                            outWrite("</td></tr>");
                        }
                        outWrite("\n</table>\n");
                    }

                    if (proc.isReturnDesc()) {
                        outWrite("\n<h3>Return</h3>");
                        outWrite("\n<p>" + proc.getReturnDesc() + "</p>");
                    }
                    outWrite("\n<hr/>\n");
                }
            }

            outWrite("\n\n</body>\n</html>\n");
            out.flush();

        } catch (Throwable e) {
            throw new IOException(e.getMessage());
        }

    }

}