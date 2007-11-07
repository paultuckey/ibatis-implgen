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
package org.tuckey.ibatis.implgen;

import java.util.regex.Pattern;


public class Util {


    public static boolean empty(String s) {
        return s == null || s.length() == 0;
    }

    public static String trimToNull(String s) {
        if ( s == null ) return null;
        s = s.trim();
        if ( s.length() == 0 ) return null;
        return s;
    }

    /**
     * a very very basic xml escaper.
     *
     * @param s string to escape
     * @return the escaped string
     */
    public static String escapeXML(String s) {
        if (s == null) {
            return null;
        }
        final int length = s.length();
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '&':
                    b.append("&amp;");
                    break;
                case '<':
                    b.append("&lt;");
                    break;
                case '>':
                    b.append("&gt;");
                    break;
                default:
                    b.append(c);
                    break;
            }
        }
        return b.toString();
    }


    public static boolean sqlStartsWithKeyword(String keyword, String sql) {
        Pattern keywordPattern = Pattern.compile("^[^\\w]*" + keyword + ".*$", Pattern.CASE_INSENSITIVE + Pattern.MULTILINE);
        return keywordPattern.matcher(sql).matches();
    }


    public static Log getLog() {
        return new Log();
    }

    /**
     * Quick and dirty logger (so that we don't have to have log4j in classpath)
     */
    public static class Log {
        private boolean debugEnabled = false;

        public void debug(String s) {
            if (!debugEnabled) return;
            System.out.println("DEBUG: " + s);
        }

        public void error(String s) {
            System.out.println("ERROR: " + s);
        }

        public void error(Object o) {
            if ( o!= null ) error(o.toString());
        }

        public void info(String s) {
            System.out.println("INFO: " + s);
        }

        public void setDebug(boolean b) {
            debugEnabled = b;
        }
    }



}
