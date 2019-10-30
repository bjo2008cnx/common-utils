package com.github.common.util.lang;

import org.junit.Assert;
import org.testng.annotations.Test;

public class StringUtilTest {
    @Test
    public void testRemoveIfStartsWith() {
        String result = StringUtil.removeIfStartsWith("/abc", '/');
        Assert.assertEquals("abc", result);

        result = StringUtil.removeIfStartsWith("abc", '/');
        Assert.assertEquals("abc", result);

        result = StringUtil.removeIfStartsWith(null, '/');
        Assert.assertEquals(null, result);

        result = StringUtil.removeIfStartsWith("", '/');
        Assert.assertEquals("", result);
    }
}
