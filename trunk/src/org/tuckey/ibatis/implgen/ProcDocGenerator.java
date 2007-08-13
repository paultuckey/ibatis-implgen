package org.tuckey.ibatis.implgen;

import org.tuckey.ibatis.implgen.bean.ParsedProc;
import org.tuckey.ibatis.implgen.template.generated.GeneratedSqlMapInterfaceTemplate;
import org.tuckey.ibatis.implgen.template.generated.GeneratedProcDocTemplate;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;


/**
 * Generate all proc documentation, one html file per package.
 *
 * Walk through all files/folders in the docRoot and for each HTML file find @see proc:xxx
 * and replace with &lt;a href="proc.html#proc"&gt;Proc&lt;/a&gt;.
 */
public class ProcDocGenerator extends ProcGenerator {

    Util.Log log = Util.getLog();

    private Pattern procPattern = Pattern.compile("proc:\\s*(\\w+)");
    private Pattern rootJavaDocDirPattern = Pattern.compile("\"([./]+)overview-summary\\.html\"");

    /**
     *
     *
     * Specify proc references in this style
     * @see "proc:w_count_withdrawn_learners"
     *
     * @param file to replace refs to procs in
     * @throws java.io.IOException if file bad
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

    public void walkDir(File dir) throws IOException {
        if (!dir.isDirectory()) return;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) walkDir(file);
            if (file.getName().endsWith(".html")) {
                replaceContents(file);
            }
        }
    }


    File docRoot;

    public void processDirectory(ParsedProc[] procs, File directory) throws IOException {
        File generatedHtmlFile = new File(docRoot, directory.getName() + ".html");
        if (!generatedHtmlFile.exists()) generatedHtmlFile.createNewFile();
        if (!generatedHtmlFile.canWrite()) {
            throw new IOException("cannot write to " + generatedHtmlFile);
        }
        GeneratedProcDocTemplate template = new GeneratedProcDocTemplate();
        template.procs = procs;
        template.name = directory.getName();
        template.writeToFile(generatedHtmlFile);
    }
}
