package com.github.common.util.collection;

import com.github.common.constant.GlobalConstant;
import com.github.common.util.lang.StringUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class MapUtil {
    /**
     * Map是否为空。如果传入的值为null或者集合不包含元素都认为为空。
     *
     * @param map Map
     * @return boolean 为空返回true，否则返回false。
     */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 取唯一的一个条目
     *
     * @param map
     * @return
     */
    public static Map.Entry getOnlyEntry(Map map) {
        if (isEmpty(map)) {
            return null;
        }
        Set<Map.Entry> set = map.entrySet();
        return CollectionUtil.getOnlyElement(set);
    }

    /**
     * 为Model增加属性，访问时有序(按属性加入的先后顺序)
     */
    public static void put(Map map, String k1, Object v1, String k2, Object v2) {
        map.put(k1, v1);
        map.put(k2, v2);
    }

    /**
     * 为Model增加属性，访问时有序(按属性加入的先后顺序)
     */
    public static void put(Map map, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
    }

    public static <T> T get(Map map, String attr) {
        return (T) (map.get(attr));
    }

    public static <T> T get(Map map, String attr, Object defaultValue) {
        Object result = map.get(attr);
        return (T) (result != null ? result : defaultValue);
    }

    public static String getStr(Map map, String attr) {
        Object object = map.get(attr);
        if (object == null) {
            return null;
        }

        if (object instanceof String) {
            return (String) object;
        } else {
            return object.toString();
        }
    }

    public static Integer getInt(Map map, String attr) {
        Object prop = map.get(attr);
        if (prop instanceof Integer) {
            return (Integer) map.get(attr);
        } else if (prop instanceof String) {
            String propStr = (String) prop;
            return StringUtil.isEmpty(propStr) ? 0 : Integer.valueOf(propStr);
        } else if (prop instanceof BigDecimal) {
            BigDecimal propStr = (BigDecimal) prop;
            return propStr.intValue();
        }
        return 0;
    }

    public static Long getLong(Map map, String attr) {

        Object prop = map.get(attr);
        if (prop instanceof Long) {
            return (Long) map.get(attr);
        } else if (prop instanceof String) {
            String propStr = (String) prop;
            return Long.valueOf(propStr);
        } else if (prop instanceof BigDecimal) {
            BigDecimal propStr = (BigDecimal) prop;
            return propStr.longValue();
        } else if (prop instanceof Integer) {
            Integer propStr = (Integer) prop;
            return propStr.longValue();
        } else if (prop instanceof BigInteger) {
            BigInteger propStr = (BigInteger) prop;
            return propStr.longValue();
        }
        return 0L;
    }

    /**
     * Get attribute of mysql type: bit, tinyint(1)
     */
    public static boolean getBoolean(Map map, String attr) {
        Object prop = map.get(attr);
        if (prop instanceof Boolean) {
            return (Boolean) map.get(attr);
        } else if (prop instanceof String) {
            return GlobalConstant.Booleans.TRUE_STR.equals(prop);
        } else if (prop instanceof Integer) {
            return Integer.valueOf(GlobalConstant.Booleans.TRUE_INT).equals(prop);
        }
        return false;
    }

    public static BigInteger getBigInteger(Map map, String attr) {
        return (BigInteger) map.get(attr);
    }

    public static Date getDate(Map map, String attr) {
        return (Date) map.get(attr);
    }

    public static java.sql.Time getTime(Map map, String attr) {
        return (java.sql.Time) map.get(attr);
    }

    public static java.sql.Timestamp getTimestamp(Map map, String attr) {
        return (java.sql.Timestamp) map.get(attr);
    }

    public static Double getDouble(Map map, String attr) {
        return (Double) map.get(attr);
    }

    public static Float getFloat(Map map, String attr) {
        return (Float) map.get(attr);
    }

    public static BigDecimal getBigDecimal(Map map, String attr) {
        return (BigDecimal) map.get(attr);
    }

    /**
     * 将map所有元素打印出来
     *
     * @param map
     * @return
     */
    public static String print(Map map) {
        StringBuffer buffer = new StringBuffer();
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {
            buffer.append(entry.getKey() + "::" + entry.getValue());
        }
        return buffer.toString();
    }
}
