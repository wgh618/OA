package com.my.oa.forum.service.impl;

import com.my.oa.forum.domain.Forum;
import com.my.oa.forum.mapper.ForumMapper;
import com.my.oa.forum.service.ForumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("forumService")
public class ForumServiceImpl implements ForumService {

    @Resource(name = "forumMapper")
    private ForumMapper forumMapper;

    @Override
    public void addForum(Forum forum) {
        if (forum == null) {
            throw new IllegalArgumentException();
        }
        this.forumMapper.save(forum);
        forum.setPosition(forum.getId());
        this.modifyForum(forum);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Forum> findAllForums() {
        return this.forumMapper.queryAll();
    }

    @Override
    public Forum findForumById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.forumMapper.load(id);
    }

    @Override
    public void deleteForum(Forum forum) {
        if (forum == null) {
            throw new IllegalArgumentException("");
        }
        this.forumMapper.delete(forum);
    }

    @Override
    public void modifyForum(Forum forum) {
        if (forum == null) {
            throw new IllegalArgumentException("");
        }
        this.forumMapper.update(forum);
    }

    @Override
    public void moveUp(Integer id) {
        Forum forum = this.findForumById(id);
        Forum other = this.forumMapper.findProByPosition(forum.getPosition());

        int temp = forum.getPosition();
        forum.setPosition(other.getPosition());
        other.setPosition(temp);

        this.modifyForum(forum);
        this.modifyForum(other);
    }

    @Override
    public void moveDown(Integer id) {
        Forum forum = this.findForumById(id);
        Forum other = this.forumMapper.findNestByPosition(forum.getPosition());;

        int temp = forum.getPosition();
        forum.setPosition(other.getPosition());
        other.setPosition(temp);

        this.modifyForum(forum);
        this.modifyForum(other);
    }

    @Override
    public List<Forum> findAllForumsDetail() {
        return this.forumMapper.findAll();
    }

    @Override
    public Forum findForumDetailById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.forumMapper.findById(id);
    }
}
