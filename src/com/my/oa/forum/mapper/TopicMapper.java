package com.my.oa.forum.mapper;


import com.my.oa.forum.domain.Topic;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 */
public interface TopicMapper {
    public void save(Topic topic);

    public Topic load(Integer id);

    public void delete(Topic topic);

    public void update(Topic topic);

    public List<Topic> findByForumId(Integer id);

}
