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
package org.tuckey.ibatis.implgen.example;

import org.tuckey.ibatis.implgen.annotations.CacheModel;
import org.tuckey.ibatis.implgen.annotations.Delete;
import org.tuckey.ibatis.implgen.annotations.Procedure;
import org.tuckey.ibatis.implgen.annotations.Result;
import org.tuckey.ibatis.implgen.annotations.ResultMap;
import org.tuckey.ibatis.implgen.annotations.Select;
import org.tuckey.ibatis.implgen.annotations.Update;

import java.sql.SQLException;
import java.util.List;


public interface ExampleDaoOne {

    // ***** result maps ****************************************

    @ResultMap(id = "person-result", results = {
    @Result(property = "personId", column = "id", javaType = "Long", jdbcType = "NUMERIC", nullValue = "0"),
    @Result(property = "fullName", column = "name", javaType = "string", jdbcType = "VARCHAR", nullValue = ""),
    @Result(property = "dateOfBirth", column = "dob", javaType = "date", jdbcType = "TIMESTAMP")
            })

    // ***** caches ****************************************

    @CacheModel(id = "oneDayCache", type="LRU", flushIntervalHours = "24")

    // ***** statements ****************************************

    /**
     * Sample update stetement
     */
    @Update
    public void updateName(int id, String name) throws SQLException /*sql{

        update table_of_names set name = #name:VARCHAR# where id = #id:NUMERIC#

    }*/;

    @Delete
    public void deleteName(int id) throws SQLException /*sql{

        delete from table_of_names where id = #id:NUMERIC#

    }*/;


    @Select
    public List<String> getListOfNames() throws SQLException /*sql{

        select name
        from table_of_names

    }*/;


    @Select(cacheModel = "oneDayCache")
    public List<String> getListOfCoutries() throws SQLException /*sql{

        select name
        from countries

    }*/;

    @Select(resultMap = "person-result")
    public List<Person> getListOfPeople() throws SQLException /*sql{

        select name, dob, id
        from table_of_names

    }*/;


    @Procedure
    public void myProc(String name, Long id) throws SQLException /*sql{

        call my_proc(#name:VARCHAR#, #id:NUMERIC#)

     }*/;


    // simple inner class for demo purposes only, you would normally use a proper bean!
    class Person {

    }
}
