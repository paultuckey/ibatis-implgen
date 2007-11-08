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
package com.ibatis.sqlmap.implgen.example;

import com.ibatis.sqlmap.implgen.annotations.CacheModel;
import com.ibatis.sqlmap.implgen.annotations.CacheModels;
import com.ibatis.sqlmap.implgen.annotations.Delete;
import com.ibatis.sqlmap.implgen.annotations.HasSql;
import com.ibatis.sqlmap.implgen.annotations.Parameter;
import com.ibatis.sqlmap.implgen.annotations.ParameterMap;
import com.ibatis.sqlmap.implgen.annotations.ParameterMaps;
import com.ibatis.sqlmap.implgen.annotations.Procedure;
import com.ibatis.sqlmap.implgen.annotations.Result;
import com.ibatis.sqlmap.implgen.annotations.ResultMap;
import com.ibatis.sqlmap.implgen.annotations.ResultMaps;
import com.ibatis.sqlmap.implgen.annotations.Select;
import com.ibatis.sqlmap.implgen.annotations.Update;

import java.sql.SQLException;
import java.util.List;

@HasSql(overrideXmlType = "procedure")
public interface ExampleDaoOne {

    // ***** parameter maps *************************************
    @ParameterMaps({
    @ParameterMap(id = "param-map-1", parameters = {
    @Parameter(property = "mamber1", mode="INOUT")
            }
    )
            })

    // ***** result maps ****************************************
    @ResultMaps({

    @ResultMap(id = "person-result", results = {
    @Result(property = "personId", column = "id", javaType = "Long", jdbcType = "NUMERIC", nullValue = "0"),
    @Result(property = "fullName", column = "name", javaType = "string", jdbcType = "VARCHAR", nullValue = ""),
    @Result(property = "dateOfBirth", column = "dob", javaType = "date", jdbcType = "TIMESTAMP")
            }),
    @ResultMap(id = "person-result-obj", results = {
    @Result(property = "personId", column = "id", javaType = "Long", jdbcType = "NUMERIC", nullValue = "0"),
    @Result(property = "fullName", column = "name", javaType = "string", jdbcType = "VARCHAR", nullValue = ""),
    @Result(property = "dateOfBirth", column = "dob", javaType = "date", jdbcType = "TIMESTAMP")
            })

            })

    // ***** caches ****************************************
    @CacheModels({

    @CacheModel(id = "oneDayCache", type = "LRU", flushIntervalHours = "24")

            })

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
