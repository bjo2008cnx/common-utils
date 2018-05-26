package com.github.common.util.collection;

import java.util.Set;

/**
 * Set工具类
 *
 * @author Wangxm
 * @date 2016/5/6
 */
public class SetUtil {

    /**
     * 向已有的set中添加新元素
     *
     * @param set
     * @param elements
     * @param <E>
     */
    public static <E> void addAll(Set<E> set, E... elements) {
        if (elements != null) {
            for (int i = 0; i < elements.length; i++) {
                set.add(elements[i]);
            }
        }
    }
}
