package com.anawat.dotography.exam.domain;

import java.io.Serializable;
import java.util.Date;

public interface IDomain<K extends Serializable> {

	public K getId();
	public void setId(K id);
	
	public int getVersion();
	public void setVersion(int version);
	
	
	public String getCreatedBy();
	public void setCreatedBy(String createdBy);
	
	public Date getCreatedDate();
	public void setCreatedDate(Date createdDate);
	
	public String getUpdatedBy();
	public void setUpdatedBy(String updatedBy);
	
	public Date getUpdatedDate();
	public void setUpdatedDate(Date updatedDate);
	
}
