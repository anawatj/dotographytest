package com.anawat.dotography.exam.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.anawat.dotography.exam.domain.task.Task;
import com.anawat.dotography.exam.repository.ITaskRepository;

@Component
public class TaskRepository implements ITaskRepository {
	
	
	@Autowired
	private SessionFactory factory;

	public List<Task> findAll() {
			
		Criteria criteria = factory.getCurrentSession().createCriteria(Task.class);
		return criteria.list();
	}

	public Task findByKey(Integer id) {
		Criteria criteria =factory.getCurrentSession().createCriteria(Task.class);
		criteria.setFetchMode("assignee", FetchMode.JOIN);
		criteria.setFetchMode("assigner",FetchMode.JOIN);
		criteria.setFetchMode("subtasks",FetchMode.JOIN);
		
		criteria.add(Restrictions.eq("id",id));
		
		
		List<Task> result = criteria.list();
		if(result!=null && result.size()>0)
		{
			return result.get(0);
		}else
		{
			return null;
		}
		
		
		
	}

	public void remove(Integer id) {
		Task entity =findByKey(id);
		factory.getCurrentSession().delete(entity);
		
	}

	public Task save(Task entity) {
		Task data =findByKey(entity.getId());
		Task result = (Task) factory.getCurrentSession().merge(entity);
		return result;
		
	}

}
