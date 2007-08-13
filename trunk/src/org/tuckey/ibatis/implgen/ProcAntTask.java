package org.tuckey.ibatis.implgen;

import org.apache.tools.ant.Task;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

import java.io.IOException;
import java.io.File;
import java.sql.SQLException;


public class ProcAntTask extends Task {

    protected File javadoc;
    protected File procs;



    public void execute() throws BuildException {
        log("javadoc " + javadoc.getAbsoluteFile());
        log("procs " + procs.getAbsoluteFile());
        try {
            if ( javadoc != null ) {
                ProcDocGenerator procDocGenerator = new ProcDocGenerator();
                // todo: generate procdoc
                procDocGenerator.walkDir(javadoc);
            }
            if ( procs != null ) {
                IbatisInterfaceProcGenerator procGenerator = new IbatisInterfaceProcGenerator();
                procGenerator.processDir(procs);
            }
        } catch (IOException e) {
            throw new BuildException(e);
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
