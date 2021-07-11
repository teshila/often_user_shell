package com.ye.dao;

import java.util.List;
import java.util.Map;

import com.ye.pojo.Content;

public interface ContentDao {

	public void save(Content content);

	public List<Content> findContentByPage(Map map);

	public List<Content> findContentByTitle(Map map);
}
