/**
 * Copyright 2007 Paul Tuckey
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.ibatis.sqlmap.implgen;

import org.apache.tools.ant.Task;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

import java.io.IOException;
import java.io.File;


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
