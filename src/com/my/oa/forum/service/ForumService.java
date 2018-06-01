package com.my.oa.forum.service;

import com.my.oa.forum.domain.Forum;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface ForumService {
    public void addForum(Forum forum);

    public List<Forum> findAllForums();

    public Forum findForumById(Integer id);

    public void deleteForum(Forum forum);

    public void modifyForum(Forum forum);

    public void moveUp(Integer id);

    public void moveDown(Integer id);

    public List<Forum> findAllForumsDetail();

    public Forum findForumDetailById(Integer id);
}
