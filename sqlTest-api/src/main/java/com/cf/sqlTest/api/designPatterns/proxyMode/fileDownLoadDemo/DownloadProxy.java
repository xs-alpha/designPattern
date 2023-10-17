package com.cf.sqlTest.api.designPatterns.proxyMode.fileDownLoadDemo;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class DownloadProxy implements FileDownloader{
    private FileDownloader fl;
    private String name;

    public DownloadProxy(String username){
        this.fl = new RealFileDownloader();
        this.name = username;
    }

    @Override
    public void download(String url) {
        // 在执行前做一些操作
        checkPermission(name);
        this.logDownload(url);

        this.fl.download(url);

        // 在执行后做一些操作
        this.logDownload(url+"下载完成");
    }

    private void checkPermission(String name){
        if (!"admin".equals(name)){
            new RuntimeException("权限校验失败");
        }
    }

    private void logDownload(String url){
        System.out.println("【记载：url】"+url);
    }
}
