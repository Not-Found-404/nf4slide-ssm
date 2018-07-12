package com.qtu404.folder.dao;

import com.qtu404.folder.domain.Folder;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.dao.BaseDao;

import java.util.List;

public interface FolderDao extends BaseDao<Folder> {
    List<Folder> findChild(Integer folderId);
}
