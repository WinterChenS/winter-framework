package com.winterchen.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by shitou on 2017/6/6.
 */
public class StackTraceUtil {

    public static String getStackTrace(Throwable ex) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        ex.printStackTrace(writer);
        return stringWriter.toString();
    }
}
