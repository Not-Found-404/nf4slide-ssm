package com.qtu404.folder.dao.impl;

import com.qtu404.folder.dao.FolderDao;
import com.qtu404.folder.domain.Folder;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("folderDao")
public class FolderDaoImpl extends BaseDaoImpl<Folder> implements FolderDao {

    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Folder fetchById(int id) {
        Folder folder = null;// super.fetchById(id);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = getNamespaces() + ".fetchById";
        folder = sqlSession.selectOne(statement, id);
        List<Folder> delFodlers = new ArrayList<>();
        for (Folder child : folder.getChild()) {
            if (child.getFolderId() == null) {
                delFodlers.add(child);
            }
        }
        for (Folder child : delFodlers) {
            folder.getChild().remove(child);
        }

        List<SlideVo> slideVos = new ArrayList<>();
        for (SlideVo child : folder.getSlideVos()) {
            if (child.getSlideId() == null || child.getExit().equals("false")) {
                slideVos.add(child);
            }
        }
        for (SlideVo child : slideVos) {
            folder.getSlideVos().remove(child);
        }

        sqlSession.commit();
        sqlSession.close();
        return folder;
    }

    @Override
    public List<Folder> findChild(Integer folderId) {
        String id = getNamespaces() + "." + "findChild";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Folder> folders = sqlSession.selectList(id, folderId);

        sqlSession.commit();
        sqlSession.close();
        return folders;
    }

    @Override
    protected String getNamespaces() {
        return "com.qtu404.mapper.folderMapper";
    }

    @Override
    protected SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
