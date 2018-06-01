package com.my.oa.forum.mapper;


import com.my.oa.forum.domain.Reply;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 */
public interface ReplyMapper {
    public void save(Reply reply);

    public Reply load(Integer id);

    public void delete(Reply reply);

    public void update(Reply reply);

    public List<Reply> findByTopicId(Integer id);
}
