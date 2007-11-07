package org.tuckey.ibatis.implgen.annotations;


public @interface Parameter {

    String property();

    String javaType() default "";

    String jdbcType() default "";

    String mode() default "";

    String nullValue() default "_unspecified_";
}
