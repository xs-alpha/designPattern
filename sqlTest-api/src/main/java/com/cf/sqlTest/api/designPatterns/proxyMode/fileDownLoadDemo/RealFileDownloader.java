package com.cf.sqlTest.api.designPatterns.proxyMode.fileDownLoadDemo;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class RealFileDownloader implements FileDownloader{

    @Override
    public void download(String url) {
        System.out.println("开始下载"+url);
    }
}
