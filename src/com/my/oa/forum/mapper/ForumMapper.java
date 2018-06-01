package com.my.oa.forum.mapper;


import com.my.oa.forum.domain.Forum;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 */
public interface ForumMapper {
    public void save(Forum forum);

    public List<Forum> queryAll();

    public Forum load(Integer id);

    public void delete(Forum forum);

    public void update(Forum forum);

    public Forum findProByPosition(int position);

    public Forum findNestByPosition(int position);

    public List<Forum> findAll();

    public void updateByTopic(Forum forum);

    public Forum findById(Integer id);
}
