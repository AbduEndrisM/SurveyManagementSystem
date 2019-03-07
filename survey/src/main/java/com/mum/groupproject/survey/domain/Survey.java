package com.mum.groupproject.survey.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
public class Survey implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();
	
	private String name;
	
	private String description;
	
	private Date openDate;
	
	private Date endDate;
	
	private Timestamp recordedDate;
	
	private boolean deleted;
	
	public Survey() {
		this.recordedDate = new Timestamp(System.currentTimeMillis());
		this.deleted = Boolean.FALSE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Timestamp getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(Timestamp recordedDate) {
		this.recordedDate = recordedDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Survey other = (Survey) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
