package org.tuckey.ibatis.implgen.annotations;


public @interface ParameterMap {
    String id();

    String classFor() default "";

    String extendsMap() default "";

    Parameter[] parameters();
}
