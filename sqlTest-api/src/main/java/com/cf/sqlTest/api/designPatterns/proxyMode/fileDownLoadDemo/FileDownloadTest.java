package com.cf.sqlTest.api.designPatterns.proxyMode.fileDownLoadDemo;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class FileDownloadTest {
    public static void main(String[] args) {
        DownloadProxy downloadProxy = new DownloadProxy("admin");
        downloadProxy.download("https://blog.devilwst.top");
    }
}
