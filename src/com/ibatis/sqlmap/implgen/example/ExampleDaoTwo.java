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

import com.ibatis.sqlmap.implgen.annotations.Update;

import java.sql.SQLException;

/**
 * Exampe showing use of abstract class.
 */
public abstract class ExampleDaoTwo {

    /**
     * Sample update stetement
     */
    @Update
    protected abstract void updateNameActual(int id, String name) throws SQLException /*sql{

        update table_of_names set name = #name:VARCHAR# where id = #id:NUMERIC#

    }*/;


    public void updateName(int id, String name) throws SQLException {
        //
        // do something special here to validate security etc
        //
        updateNameActual(id, name);
    }


}
