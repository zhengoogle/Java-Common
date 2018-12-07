package com.simple.common;

import com.simple.common.debug.http.OKHttpTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static Logger logger = LogManager.getLogger(Main.class.getName() + ".class");

    public static void main(String[] args) {
        OKHttpTest.testOkHttp();
//        ZipSolution.test();
//        System.exit(0);
    }
}
