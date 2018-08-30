package com.qtu404.folder.service.impl;

import com.qtu404.folder.dao.FolderDao;
import com.qtu404.folder.domain.Folder;
import com.qtu404.folder.service.FolderService;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.dao.BaseDao;
import com.qtu404.util.web.ssm.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("folderService")
public class FolderServiceImpl extends BaseServiceImpl<Folder> implements FolderService {
    @Resource(name = "folderDao")
    FolderDao folderDao;

    @Override
    protected BaseDao<Folder> getBaseDao() {
        return folderDao;
    }

    @Override
    public Folder getRootFolder(UserVo userVo) {
        Folder folder = folderDao.fetchById(userVo.getFolderId());
        folder.setChild(getAllChild(folder));
        userVo.setRootFolder(folder);
        return folder;
    }

    private List<Folder> getAllChild(Folder f) {
        if (f == null) {
            return null;
        }
        List<Folder> folders;
        folders = folderDao.findChild(f.getFolderId());
        if (folders == null || folders.size() == 0) {
            return null;
        }
        for (Folder folder_child : folders) {
            folder_child.setChild(getAllChild(folder_child));
        }
        return folders;
    }
}
