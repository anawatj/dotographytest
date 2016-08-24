package com.anawat.dotography.exam.domain.task;

import java.util.HashSet;
import java.util.Set;

import com.anawat.dotography.exam.domain.AbstractDomain;
import com.anawat.dotography.exam.domain.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class Task extends AbstractDomain<Integer> {

	
	public Task()
	{
		this.subtasks= new HashSet<Task>();
	}
	private String taskName;
	private String subject;
	private TaskStatus status;
	private User assignee;
	private User assigner;
	private Set<Task> subtasks;

	private String remark;
	private String taskDetail;
	
	public String getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	public User getAssigner() {
		return assigner;
	}
	public void setAssigner(User assigner) {
		this.assigner = assigner;
	}
	public Set<Task> getSubtasks() {
		return subtasks;
	}
	public void setSubtasks(Set<Task> subtasks) {
		this.subtasks = subtasks;
	}
	
	
}
