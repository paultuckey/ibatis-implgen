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
package com.ibatis.sqlmap.implgen.bean;


public class ParsedCacheModel {
    private String id;
    private String type;
    private String flushIntervalHours;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlushIntervalHours() {
        return flushIntervalHours;
    }

    public void setFlushIntervalHours(String flushIntervalHours) {
        this.flushIntervalHours = flushIntervalHours;
    }

    public boolean isFlushInterval() {
        return flushIntervalHours != null && flushIntervalHours.length() > 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "ParsedCacheModel{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", flushIntervalHours='" + flushIntervalHours + '\'' +
                '}';
    }
}
