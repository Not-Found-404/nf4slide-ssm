package com.qtu404.folder.service;

import com.qtu404.folder.domain.Folder;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.service.BaseService;

public interface FolderService extends BaseService<Folder> {
    Folder getRootFolder(UserVo userVo);
}
