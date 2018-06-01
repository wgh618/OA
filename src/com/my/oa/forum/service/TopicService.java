package com.my.oa.forum.service;

import com.my.oa.forum.domain.Forum;
import com.my.oa.forum.domain.Topic;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface TopicService {
    public void addTopic(Topic topic);

    public Topic findTopicById(Integer id);

    public void deleteTopic(Topic topic);

    public void modifyTopic(Topic topic);

    public List<Topic> findTopicsByForum(Forum forum);
}
