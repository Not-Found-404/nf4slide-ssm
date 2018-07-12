package com.qtu404.folder.controller;

import com.alibaba.fastjson.JSON;
import com.qtu404.folder.domain.Folder;
import com.qtu404.folder.domain.TreeNode;
import com.qtu404.folder.service.FolderService;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.Result;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("folder/")
public class FolderController extends BaseController<Folder> {
    @Resource(name = "folderService")
    private FolderService folderService;

    /**
     * 获得当前登录用户的文件树
     *
     * @param session
     * @param response
     */
    @RequestMapping(value = "/getRootFolderWithAngular", method = RequestMethod.GET)
    public void getRootFolder(HttpSession session, HttpServletResponse response) {
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        Folder folder = null;
        if (userVo != null) {
            folder = folderService.getRootFolder(userVo);
        }
        TreeNode treeNode = TreeNode.createNzTreeNode(folder);
        writeResult(response, treeNode);
    }

    /**
     * 修改folder, 如改名字, 改父文件夹的位置
     *
     * @param body
     * @param request
     * @param response
     */
    @RequestMapping(value = "/modifyWithAngular", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public void modifyWithAngular(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
        Folder folder = JSON.parseObject(body, Folder.class);
        Result result = new Result();
        int rst = folderService.modify(folder);
        if (rst == 1) {
            result.setCode(200);
            result.setResult("modify success");
        } else {
            result.setCode(500);
            result.setResult("modify fail");
        }
        writeResult(response, result);
    }

    /**
     * 删除文件夹
     *
     * @param body
     * @param request
     * @param response
     @RequestMapping("/deleteWithAngular") public void deleteWithAngular(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
     Folder folder = JSON.parseObject(body, Folder.class);
     Result result = new Result();
     int rst = folderService.delete(folder);
     }*/


    /**
     * 通过id值，得到folder对象
     *
     * @param body
     * @param response
     */
    @RequestMapping("/fetchByIdWithAngular")
    public void fetchByIdWithAngular(@RequestBody String body, HttpServletResponse response) {
        Integer folderId = Integer.parseInt(body);
        Folder folder = folderService.fetchById(folderId);
        writeResult(response, folder);
    }

    @RequestMapping("/saveWithAngular")
    public void saveWithAngular(@RequestBody String body, HttpServletResponse response) {
        Folder dto = JSON.parseObject(body, Folder.class);
        dto = folderService.save(dto);
        if (dto.getFolderId() != null) {
            writeResult(response, null);
        }
        writeResult(response, dto);
    }

    @Override
    protected BaseService<Folder> getBaseService() {
        return folderService;
    }
}
