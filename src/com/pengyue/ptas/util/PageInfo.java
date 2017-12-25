package com.pengyue.ptas.util;

import com.pengyue.ptas.bean.YyGzryxxb;

/***
 * 分页条件查询封装类
 * @author Administrator
 *
 * @param <T>
 */
public class PageInfo<T> {
	private T entity;
	private Integer begin;
	private Integer end;
	
	
	public PageInfo() {
		super();
	}

	public PageInfo(T entity, Integer begin, Integer end) {
		super();
		this.entity = entity;
		this.begin = begin;
		this.end = end;
	}
	
	

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Integer getBegin() {
		
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	
	public Integer getEnd() {
		return begin+end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	
	
}
