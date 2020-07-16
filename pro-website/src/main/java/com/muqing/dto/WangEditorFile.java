package com.muqing.dto;

/**
 * Create by iFun on 2020/07/16
 */
public class WangEditorFile {
    private Integer errno;// 错误代码，0 表示没有错误。
    private String[] data;// 一个数组，返回若干图片的线上地址

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
