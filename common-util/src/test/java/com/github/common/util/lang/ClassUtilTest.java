package com.github.common.util.lang;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ClassUtilTest {
    @Test
    public void testClassLoader() {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(this.getClass().getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
    }

}