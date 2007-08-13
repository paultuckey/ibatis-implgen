package org.tuckey.ibatis.implgen;

import org.tuckey.ibatis.implgen.bean.ParsedProc;
import org.tuckey.ibatis.implgen.proc.ProcParser;
import org.tuckey.ibatis.implgen.proc.ProcParserSybase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ProcGenerator {

    Util.Log log = Util.getLog();

    /**
     * Goes through directory and parses all .sql files looking for procs.
     *
     * @param dir directory to process
     * @return array of stored procs
     */
    public void processDir(File dir) throws IOException {
        log.debug("on dir " + dir.getName());
        File[] files = dir.listFiles();
        ArrayList<ParsedProc> procsInThisDirRaw = new ArrayList<ParsedProc>();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                processDir(file);
            } else if (file.getName().endsWith(".sql")) {
                log.info("parsing " + file.getName());
                ParsedProc sp = processProc(file);
                if (!sp.isValid()) {
                    // do nothing todo: error?
                } else {
                    procsInThisDirRaw.add(sp);
                }
            }
        }
        if ( procsInThisDirRaw.size() > 0 ) {
            ParsedProc[] procs = new ParsedProc[procsInThisDirRaw.size()];
            procsInThisDirRaw.toArray(procs);
            processDirectory(procs, dir);
        }
    }

    private ParsedProc processProc(File f) {
        ParsedProc sp = new ParsedProc();
        sp.setOriginalFile(f);
        try {
            parser.process(f, sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    ProcParser parser = new ProcParserSybase();

    public abstract void processDirectory(ParsedProc[] procs, File directory) throws IOException;

}
