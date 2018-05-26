package com.github.common.util.io;



import java.io.*;
import java.util.Collection;

/**
 * 流工具类
 *
 * @author Wangxm
 */

public class StreamUtil {
    /**
     * 通用的关闭方法
     *
     * @param autoCloseables
     */
    public static void closeQuietly(final AutoCloseable... autoCloseables) {
        if (autoCloseables == null) {
            return;
        }
        for (AutoCloseable closeable : autoCloseables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (Throwable ignore) {
            }
        }
    }

    /**
     * 通用的关闭方法
     *
     * @param closeables
     */
    public static void close(final Closeable... closeables) {
        if (closeables != null) {
            closeQuietly(closeables);
        }
    }

    public static void close(final Collection<? extends Closeable> closeables) {
        if (closeables != null) {
            closeQuietly(closeables.toArray(new Closeable[closeables.size()]));
        }
    }

    /**
     * 通用的关闭方法
     *
     * @param closeables
     */
    public static void closeQuietly(final Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                    closeable = null;
                }
            } catch (Throwable ignore) {

            }
        }
    }

    /**
     * Read an input stream into validator string
     */
    public static String streamToString(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    /**
     * Read an input stream into validator byte[]
     */
    public static byte[] stream2Byte(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = is.read(b, 0, b.length)) != -1) {
            baos.write(b, 0, len);
        }
        byte[] buffer = baos.toByteArray();
        return buffer;
    }

    /**
     * @方法功能 InputStream 转为 byte
     */
    public static byte[] inputStream2Byte(InputStream inStream) throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        return b;
    }

    /**
     * @return InputStream
     * @throws Exception
     * @方法功能 byte 转为 InputStream
     */
    public static InputStream byte2InputStream(byte[] b) throws Exception {
        InputStream is = new ByteArrayInputStream(b);
        return is;
    }

    /**
     * 将流另存为文件
     */
    public static void streamSaveAsFile(InputStream is, File outfile) {
        try (FileOutputStream fos = new FileOutputStream(outfile)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写到流中
     *
     * @param content
     * @param os
     * @throws java.io.IOException
     */
    public static void write2Stream(String content, OutputStream os) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(os);
        out.write(content.getBytes());
        out.write('\r');
        out.flush();
    }
}
