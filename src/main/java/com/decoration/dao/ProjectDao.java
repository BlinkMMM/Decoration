package com.decoration.dao;

import java.util.List;

import com.decoration.entity.Project;
import com.decoration.entity.User;

/**
 * @author zhenghan
 * 2017年3月25日 
 * 下午10:12:34
 *
 */
public interface ProjectDao {
	public Project findProByName(String proName);
	public List<Project> findAllProject();
}
