package com.anawat.dotography.exam.repository;

import java.util.List;

import com.anawat.dotography.exam.domain.task.Task;

public interface ITaskRepository extends Repository<Task,Integer> {

	List<Task> findAll();
	Task findByKey(Integer id);
	void remove(Integer id);
	Task save(Task entity);
}
