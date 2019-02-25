package com.github.common.util.lang;

/**
 * class 工具
 */
public class ClassUtil {
    /**
     * 获取类加载器
     * 通过Thread.getContextClassLoader()方法获取到的是线程绑定的类加载器，这个classloader是父线程在创建子线程的时候，
     * 通过Thread.setContextClassLoader()方法设置进去，用于该线程加载类和资源的，
     * 如果没有调用这个方法，那么直接使用父线程的classLoader；
     * 如果这个方法返回null，代表该线程直接使用的系统class loader或者bootstrap class loader
     *
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            //获取当前线程的context class loader
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
        }
        if (cl == null) {
            // 如果没有context loader，使用当前类的类加载器；
            cl = ClassUtil.class.getClassLoader();
            if (cl == null) {
                // 如果当前类加载器无法获取，获得bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                }
            }
        }
        return cl;
    }
}
