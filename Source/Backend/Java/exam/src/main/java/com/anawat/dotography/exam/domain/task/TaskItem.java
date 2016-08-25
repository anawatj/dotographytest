package com.anawat.dotography.exam.domain.task;

import com.anawat.dotography.exam.domain.AbstractDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class TaskItem  extends AbstractDomain<Integer>{
	
	public TaskItem()
	{
		
	}
	private Integer taskId;
	private String itemSubject;
	private String itemDescription;
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getItemSubject() {
		return itemSubject;
	}
	public void setItemSubject(String itemSubject) {
		this.itemSubject = itemSubject;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	
}
