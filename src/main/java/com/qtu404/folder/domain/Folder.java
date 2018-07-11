package com.qtu404.folder.domain;

import com.qtu404.slide.domain.SlideVo;

import java.util.List;

public class Folder {
    private String folderName;
    private Integer folderId;
    private Integer parent;
    private List<Folder> child;
    private List<SlideVo> slideVos;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public List<Folder> getChild() {
        return child;
    }

    public void setChild(List<Folder> child) {
        this.child = child;
    }

    public List<SlideVo> getSlideVos() {
        return slideVos;
    }

    public void setSlideVos(List<SlideVo> slideVos) {
        this.slideVos = slideVos;
    }

    public static Folder createRootFolder() {
        Folder folder = new Folder();
        folder.setFolderName("root");
        folder.setParent(null);
        return folder;
    }
}
