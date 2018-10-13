package com.why.filecopier.javaimpl;

import java.io.*;

/**
 * @Author :王皓月
 * @Date :2018/10/13 下午3:14
 * @Description : Java实现文件拷贝
 */

public class FileCopier {

    public static void main(String[] args) throws Exception {
        File inboxDirectory = new File("data/inbox");
        File outboxDirectory = new File("data/outbox");
        outboxDirectory.mkdir();
        File[] files = inboxDirectory.listFiles();
        for (File source : files) {
            if (source.isFile()) {
                File dest = new File(
                        outboxDirectory.getPath()
                                + File.separator
                                + source.getName());
                copyFile(source, dest);
            }
        }
    }

    private static void copyFile(File source, File dest)
            throws IOException {
        OutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[(int) source.length()];
        FileInputStream in = new FileInputStream(source);
        in.read(buffer);
        try {
            out.write(buffer);
        } finally {
            out.close();
            in.close();
        }
    }
}
