package cn.gridlife.teletext;


import java.io.File;

import cn.gridlife.bzblibrary.utils.Utils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/05/10
 *     desc  :
 * </pre>
 */
public class Config {
    public static final String PKG      = "cn.gridlife.androidutilcode";
    public static final String TEST_PKG = "cn.gridlife.teletext";
    private static String teleTextApkPath;

    public static String getTeleTextApkPath() {
        if (teleTextApkPath == null)
            teleTextApkPath = Utils.getContext().getCacheDir().getAbsolutePath() + File.separatorChar + "apk" + File.separatorChar + "teletext.apk";
        return teleTextApkPath;
    }
}
