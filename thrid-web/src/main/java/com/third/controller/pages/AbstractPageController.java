package com.third.controller.pages;

public abstract class AbstractPageController
{
	protected Integer getStartIndex(Integer page, Integer rows)
	{
		return (page - 1) * rows;
	}

}
