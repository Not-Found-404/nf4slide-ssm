package com.qtu404.slide.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FileVo {
    private String fileName;
    private String fileContentType;
    private File file ;
    private String fileSaveDirPath;
    private String size;
    private String contextPath;
    private Integer userId;
    private MultipartFile uploadFile;

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    //文件的得到绝对物理地址
    public String getRealPath() {
        return contextPath + File.separator + fileSaveDirPath + File.separator + userId + File.separator + fileName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileSavePath() {
        return fileSaveDirPath + File.separator + userId + File.separator + fileName;
    }

    /**
     * 存储文件的上一级文件名
     * @return
     */
    public String getFileSaveDirPath() {
        return fileSaveDirPath;
    }

    public void setFileSaveDirPath(String fileSaveDirPath) {
        this.fileSaveDirPath = fileSaveDirPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
