package com.zqsweb.zqscommon.utils;

import android.app.Application;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 日志相关类:默认是测试环境<br>
 * <b>支持：存储Log日志文件到本地。发送Log日志信息到服务器</b>
 *
 * @since 2016-5-13 14:31:21
 */
public class LogUtils {

    public static boolean isDebug = ConstUtils.isDebug;

    private final static String APP_TAG = "mTest";

    private static String LOG_FILE_PATH ;

    public static void writeLogToFile(String text){
        writeLogToFile("default_log_name", text);
    }

    public static String getWriteLogPath(String fileName) {
        return LOG_FILE_PATH + fileName;
    }

    public static void init(Application application) {
        LOG_FILE_PATH = application.getExternalCacheDir().getAbsolutePath() + File.separator + "log__" + File.separator;
    }

    /**
     * 打开日志文件并写入日志
     * 新建或打开日志文件
     * @return
     **/
    public static void writeLogToFile(String fileName, String text) {
        String path = LOG_FILE_PATH + fileName;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                LogUtils.v("创建日志文件失败");
            }
        }
        try {
            //后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            FileWriter filerWriter = new FileWriter(file, false);
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(text);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
            v("日志写入成功:" + path);
        } catch (IOException e) {
            w("日志写入失败:" + e.getMessage() + "-------" + path);
            e.printStackTrace();
        }
    }

    /**
     * 获取相关数据:类名,方法名,行号等.用来定位行<br>
     * at cn.utils.MainActivity.onCreate(MainActivity.java:17) 就是用來定位行的代碼<br>
     *
     * @return [ Thread:main, at
     * cn.utils.MainActivity.onCreate(MainActivity.java:17)]
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(LogUtils.class.getName())) {
                    continue;
                }
                return "[ Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName()
                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";
            }
        }
        return null;
    }


    public static void v(String msg) {
        if (isDebug) {
            Log.v(APP_TAG, getMsgFormat(msg));
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, getMsgFormat(msg));
        }
    }


    public static void d(String msg) {
        if (isDebug) {
            Log.d(APP_TAG, getMsgFormat(msg));
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, getMsgFormat(msg));
        }
    }


    public static void i(String msg) {
        if (isDebug) {
            Log.i(APP_TAG, getMsgFormat(msg));
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, getMsgFormat(msg));
        }
    }



    public static void w(String msg) {
        if (isDebug) {
            Log.w(APP_TAG, getMsgFormat(msg));
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, getMsgFormat(msg));
        }
    }


    public static void e(String msg) {
        if (isDebug) {
            Log.e(APP_TAG, getMsgFormat(msg));
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, getMsgFormat(msg));
        }
    }


    public void write(String path,String content) {
        //Files.write(new File(path).toPath(), content.getBytes(), null);
    }

    /**
     * 输出格式定义
     */
    private static String getMsgFormat(String msg) {
        return msg + "\n" + getFunctionName();
    }

}
