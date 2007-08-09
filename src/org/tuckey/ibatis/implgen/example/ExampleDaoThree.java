package org.tuckey.ibatis.implgen.example;

import java.sql.SQLException;
import java.util.List;

/**
 * Example showing auto un-annotated methods where SQL type detection will be used.
 */
public interface ExampleDaoThree {

    public void updateSomeTable(Long id, String colValue) throws SQLException /*sql{

        UPDATE table SET col = #colValue# WHERE id = #id#

    }*/;

    public List<String> getNames(Long id) throws SQLException /*sql{

        SELECT name FROM blah WHERE id = #id#

    }*/;

}
