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


import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.tuckey.ibatis.implgen.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This ant task will
 * walk through all files/folders in the docRoot and for each HTML file find @see proc:xxx
 * and replace with &lt;a href="proc.html#proc"&gt;Proc&lt;/a&gt;. 
 */
public class JavaDocProcReplacer extends Task {

    Util.Log log = Util.getLog();
    
    protected boolean strict = true;

    protected File javadoc;
    protected File procs;
    Pattern procPattern = Pattern.compile("proc:\\s*(\\w+)");
    Pattern rootJavaDocDirPattern = Pattern.compile("\"([./]+)overview-summary\\.html\"");

    /**
     *
     *
     * Specify proc references in this style
     * @see "proc:w_count_withdrawn_learners"
     *
     * @param file to replace refs to procs in
     * @throws IOException if file bad
     */
    protected void replaceContents(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<String> fileContents = new ArrayList<String>();
        int matchCount = 0;
        String javaDcoRoot = null;
        while ((line = br.readLine()) != null) {
            Matcher rootMatcher = rootJavaDocDirPattern.matcher(line);
            boolean rootFound = rootMatcher.find();
            if (rootFound && rootMatcher.groupCount() > 0) {
                javaDcoRoot = rootMatcher.group(1);
            }
            Matcher matcher = procPattern.matcher(line);
            boolean found = matcher.find();
            /* if ( line.indexOf("proc:") > 0 ) {
                log(found + " " + matcher.groupCount());
                log(line);
            } */
            if (found && matcher.groupCount() > 0) {
                String procName = matcher.group(1);
                line = matcher.replaceAll("Stored Procedure <a href=\"" + javaDcoRoot + "stored-procedures.html#" +
                        procName + "\">" + procName + "</a>");
                log.debug("found ref to " + procName + " in " + file.getName());
                matchCount++;
            }
            fileContents.add(line);
        }
        br.close();
        if (matchCount > 0) {
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (String s : fileContents) {
                pw.println(s);
            }
            pw.close();
        }
    }


    public void execute() throws BuildException {
        log("javadoc " + javadoc.getAbsoluteFile());
        log("procs " + procs.getAbsoluteFile());
        try {
            walkDir(javadoc);
            processStoredProcsFile(procs, javadoc);
        } catch (IOException e) {
            throw new BuildException(e);
        } catch (SQLException e) {
            throw new BuildException(e);
        } catch (ClassNotFoundException e) {
            throw new BuildException(e);
        }
    }

    private void processStoredProcsFile(File srcDir, File javaDocDir)
            throws SQLException, IOException, ClassNotFoundException {
        // todo: enable config of alternative parser implementations
        ProcParser parser = new ProcParserSybase();
        ParsedProc[] procs = parser.processDir(srcDir);
        ProcDocGenerator docTem = new ProcDocGenerator();
        docTem.procs = procs;
        File procsDocFile = new File(javaDocDir, "stored-procedures.html");
        if (!procsDocFile.exists()) procsDocFile.createNewFile();
        if (!procsDocFile.canWrite()) {
            throw new IOException("cannot write to " + procsDocFile);
        }
        docTem.writeToFile(procsDocFile);
        String absolSrcPath = srcDir.getAbsolutePath();
        String absolJavadocPath = javaDocDir.getAbsolutePath();
        for (ParsedProc proc : procs) {
            String procAbsolPath = proc.getOriginalFile().getAbsolutePath();
            if (procAbsolPath.startsWith(absolSrcPath)) {
                File procCopy = new File(absolJavadocPath + procAbsolPath.substring(absolSrcPath.length()));
                log.debug(procCopy.getAbsolutePath());
            }
        }
    }

    private void walkDir(File dir) throws IOException {
        if (!dir.isDirectory()) return;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) walkDir(file);
            if (file.getName().endsWith(".html")) {
                replaceContents(file);
            }
        }
    }

    protected void doFileOperations() {
        log("doFileOperations NOTHING DONE", Project.MSG_ERR);
    }

    /**
     * The path to the javadoc directory that you want to check for references to procs in.
     *
     * @param javadoc dir
     */
    public void setJavadoc(File javadoc) {
        this.javadoc = javadoc;
    }

    /**
     * The path to the directory that contains the procs.
     *
     * @param procs dir
     */
    public void setProcs(File procs) {
        this.procs = procs;
    }

}

