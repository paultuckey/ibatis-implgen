package org.tuckey.ibatis.implgen.proc;

import org.tuckey.ibatis.implgen.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ProcParser {

    Util.Log log = Util.getLog();

    /**
     * Goes through directory and parses all .sql files looking for procs.
     *
     * @param dir directory to process
     * @return array of stored procs
     */
    public ParsedProc[] processDir(File dir) {
        log.debug("on dir " + dir.getName());
        File[] files = dir.listFiles();
        ArrayList<ParsedProc> procsRaw = new ArrayList<ParsedProc>();
        if (files == null) return new ParsedProc[0];
        for (File file : files) {
            if (file.isDirectory()) {
                ParsedProc[] subDirProcs = processDir(file);
                procsRaw.addAll(Arrays.asList(subDirProcs));
            } else if (file.getName().endsWith(".sql")) {
                log.info("parsing " + file.getName());
                ParsedProc sp = processProc(file);
                if (!sp.isValid()) {
                    // do nothing
                } else {
                    procsRaw.add(sp);
                }
            }
        }
        ParsedProc[] procs = new ParsedProc[procsRaw.size()];
        procsRaw.toArray(procs);
        return procs;
    }


    public ParsedProc processProc(File f) {
        ParsedProc sp = new ParsedProc();
        sp.setOriginalFile(f);
        try {
            process(f, sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    protected abstract void process(File f, ParsedProc sp) throws IOException;
}
