package org.tuckey.ibatis.implgen.example;

import java.sql.SQLException;
import java.util.List;

/**
 * Example showing auto SQL type detection.
 */
public interface ExampleDaoThree {

    public void updateSomeTable(Long id, String colValue) throws SQLException /*sql{

        UPDATE table SET col = #colValue# WHERE id = #id#

    }*/;

    public List<String> getNames(Long id) throws SQLException /*sql{

        SELECT name from blah where x = #id#

    }*/;

}
