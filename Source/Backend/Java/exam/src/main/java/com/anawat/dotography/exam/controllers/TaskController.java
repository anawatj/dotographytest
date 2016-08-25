package com.anawat.dotography.exam.controllers;

import java.util.HashMap;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anawat.dotography.exam.ResponseResult;
import com.anawat.dotography.exam.domain.task.Task;
import com.anawat.dotography.exam.repository.ITaskRepository;

@Controller
@RequestMapping("/task")
public class TaskController {

	
	@Autowired(required=true)
	private ITaskRepository taskRepository;
	
	@CrossOrigin
	@RequestMapping(value="/tasks",method=RequestMethod.GET)
	@Transactional
	public @ResponseBody List<Task> getAll()
	{
		return taskRepository.findAll();
	}
	@CrossOrigin
	@RequestMapping(value="/tasks/{id}",method=RequestMethod.GET)
	@Transactional
	public @ResponseBody Task getOne(@PathVariable Integer id)
	{
		
		Task task = taskRepository.findByKey(id);
		if(task==null)
		{
			task = new Task();
			
		}
		return task;
	}
	@CrossOrigin
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@Transactional
	public @ResponseBody HashMap<String ,Object> save(@RequestBody Task entity)
	{
		
		HashMap<String,Object> ret=  new HashMap<String,Object>();
		
		try
		{
			 taskRepository.save(entity);
			 ret.put("result",ResponseResult.Success);
			 ret.put("message",ResponseResult.Success.toString());
		}catch(Exception ex)
		{
			ret.put("result",ResponseResult.Fail);
			ret.put("message",ex.getMessage());
		}
		return ret;
	}
	@CrossOrigin
	@RequestMapping(value="/saveBatch",method=RequestMethod.POST)
	@Transactional
	public @ResponseBody HashMap<String,Object> save(@RequestBody List<Task> entities)
	{
	HashMap<String,Object> ret=  new HashMap<String,Object>();
		
		try
		{
			 for(Task entity :entities)
			 {
				 taskRepository.save(entity);
			 }
			 ret.put("result",ResponseResult.Success);
			 ret.put("message",ResponseResult.Success.toString());
		}catch(Exception ex)
		{
			ret.put("result",ResponseResult.Fail);
			ret.put("message",ex.getMessage());
		}
		return ret;
	}
	
	@CrossOrigin
	@RequestMapping(value="/removeBatch" ,method=RequestMethod.POST)
	@Transactional
	public @ResponseBody HashMap<String,Object> remove(@RequestBody List<Task> tasks)
	{
		HashMap<String,Object> ret=  new HashMap<String,Object>();
		try
		{
			for(Task task : tasks)
			{
				taskRepository.remove(task.getId());
			}
			 ret.put("result",ResponseResult.Success);
			 ret.put("message",ResponseResult.Success.toString());
		}catch(Exception ex)
		{
			ret.put("result",ResponseResult.Fail);
			ret.put("message",ex.getMessage());
		}
		return ret;
		
	}
	@CrossOrigin
	@RequestMapping(value="/remove" ,method=RequestMethod.POST)
	@Transactional
	public @ResponseBody HashMap<String,Object> remove(@RequestBody Task task)
	{
		HashMap<String,Object> ret=  new HashMap<String,Object>();
		try
		{
			 taskRepository.remove(task.getId());
			 ret.put("result",ResponseResult.Success);
			 ret.put("message",ResponseResult.Success.toString());
		}catch(Exception ex)
		{
			ret.put("result",ResponseResult.Fail);
			ret.put("message",ex.getMessage());
		}
		return ret;
		
	}
	
}
