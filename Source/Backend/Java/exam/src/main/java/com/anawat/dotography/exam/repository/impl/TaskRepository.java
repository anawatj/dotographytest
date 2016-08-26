package com.anawat.dotography.exam.repository.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anawat.dotography.exam.domain.task.Task;
import com.anawat.dotography.exam.domain.task.TaskStatus;
import com.anawat.dotography.exam.repository.ITaskRepository;

@Component
public class TaskRepository implements ITaskRepository {
	
	
	@Autowired
	private SessionFactory factory;

	@SuppressWarnings("unchecked")
	public List<Task> findAll() {
			
		Criteria criteria = factory.getCurrentSession().createCriteria(Task.class);
		criteria.setFetchMode("assigner",FetchMode.JOIN);
		criteria.setFetchMode("assignee",FetchMode.JOIN);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public Task findByKey(Integer id) {
		Criteria criteria =factory.getCurrentSession().createCriteria(Task.class);
		criteria.setFetchMode("assigner",FetchMode.JOIN);
		criteria.setFetchMode("assignee",FetchMode.JOIN);
		criteria.setFetchMode("items",FetchMode.JOIN);	
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

	public Task save(Task entity) throws Exception {
		
		
		
		if(entity.getStatus()==null)
		{
			entity.setStatus(TaskStatus.Draft);
		}
		
		Task data =findByKey(entity.getId());
		if(entity.getStatus()==TaskStatus.Pending && data.getStatus()!= TaskStatus.Draft)
		{
			throw new Exception("Status is not correct");
		}
		if(entity.getStatus()==TaskStatus.Done && data.getStatus()!= TaskStatus.Pending)
		{
			throw new Exception("Status is not correct");
		}
		if(entity.getStatus()==TaskStatus.Done || entity.getStatus()==TaskStatus.Pending)
		{
			entity.setItems(data.getItems());
		}
		if(entity.getStatus()==TaskStatus.Pending)
		{
			entity.setAssignDate(new Date());
		}
		if(entity.getStatus()==TaskStatus.Done)
		{
			entity.setFinishDate(new Date());
		}
		Task result = (Task) factory.getCurrentSession().merge(entity);
		return result;
		
	}

}
