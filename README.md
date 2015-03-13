# ibatis-implgen


Using new features in Java 1.5 it is possibly to specify most iBATIS SqlMap instructions directly in Java files.

Implgen is a Java annotation processor that generates iBATIS SqlMap Java and XML files from annotated interfaces or abstract classes. SQL is specified in multi-line blocks starting with /*sql{ and ending with }*/.

This is probably best illustrated by an example:

    @Update
    public void updateName(int id, String name) throws SQLException /*sql{

        update table_of_names set name = #name:VARCHAR# where id = #id:NUMERIC#

    }*/;

The generated XML:

    <update id="updateName" parameterClass="map">
        update table_of_names set name = #name:VARCHAR# where id = #id:NUMERIC#
    </update>

And the class:

    public void updateName(int id, String name) throws SQLException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("name", name);
        sqlMapper.queryForObject("ExampleDaoOne.updateName", params);
    }
The APT processor can be called via the APT ant task:

    <path id="annotation-classpath">
        <fileset file="dist/ibatis-implgen-0.0.2.jar"/>
        <fileset file="${java.home}/lib/tools.jar"/>
        <fileset file="lib/ibatis-2.3.0.677.jar"/>
    </path>
    <target name="generate-example" description="Generated implementations of annotated SQL methods">
        <apt srcdir="src" preprocessdir="build/generated"
             classpathref="annotation-classpath" compile="false" debug="true"
             factory="com.ibatis.sqlmap.implgen.IbatisImplGenAnnotationProcessor" />
    </target>
    
    
See the wiki for more info
https://github.com/paultuckey/ibatis-implgen/tree/wiki
