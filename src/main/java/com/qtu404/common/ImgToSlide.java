package com.qtu404.common;

import com.qtu404.slide.domain.FileVo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImgToSlide {
    private List<String> list;
    private FileVo fileVo;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setFileVo(FileVo fileVo) {
        this.fileVo = fileVo;
    }

    public ImgToSlide(List<String> list, FileVo fileVo) {
        this.list = list;
        this.fileVo = fileVo;
    }

    public String jpegToImgContent() {
        StringBuilder result_String = new StringBuilder("");
        for (int i = 0; i < list.size(); i++) {
            result_String.append("<div id=\"nf4-image-").append(i + 1).append("\" class=\"nf4-image nf4-module ui-resizable ui-draggable ui-draggable-handle\" style=\"position: absolute; z-index: 51;height:700px;width:960px; left: 0px; top: 0px;\"><img data-selected=\"false\" id=\"imgPre-").append(i + 1).append("\" class=\"nf4-image-editor\" style=\"height: 100%; width: 100%; cursor: move;\" src=\"");
            result_String.append(fileVo.getFileSaveDirPath()).append(File.separator).append(fileVo.getUserId()).append(File.separator).append(list.get(i)).append("\"><div class=\"ui-resizable-handle ui-resizable-nw\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-ne\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-sw\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-se\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-n\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-s\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-e\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-w\" style=\"display: none;\"></div></div>");
            result_String.append("<--nf4-->");
        }
        return result_String.toString();
    }

    public String jpegToImgPlay() {
        String result_String = null;
        StringBuilder playCode = new StringBuilder("");
        for (String aList : list) {
            playCode.append("<section> <div style=\"height:700px;width:960px\"><div style=\"position:absolute;top:0px;left:0px;width:960px;height:700px;z-index:51; transform:none;\"><img  style=\"width:100%;height:100%;opacity:1;\"src=\"").append(fileVo.getFileSaveDirPath()).append(File.separator).append(fileVo.getUserId()).append(File.separator).append(aList).append("\"></div></div></section>");

            /*playCode.append("<section> <div style=\"height:700px;width:960px\">");
            playCode.append("<div id=\"nf4-image-" + (i + 1) + "\" class=\"nf4-image nf4-module ui-resizable ui-draggable ui-draggable-handle\" style=\"position: absolute; z-index: 51;height:700px;width:960px; left: 0px; top: 0px;\"><img data-selected=\"false\" id=\"imgPre-" + (i + 1) + "\" class=\"nf4-image-editor\" style=\"height: 100%; width: 100%; cursor: move;\" src=\"");
            playCode.append(fileVo.getFileSaveDirPath() + File.separator + fileVo.getUserId() + File.separator + list.get(i) + "\"><div class=\"ui-resizable-handle ui-resizable-nw\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-ne\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-sw\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-se\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-n\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-s\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-e\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-w\" style=\"display: none;\"></div></div>");
            playCode.append("</div></section>");*/
        }
        result_String = playCode.toString();
        return result_String;
    }
}
