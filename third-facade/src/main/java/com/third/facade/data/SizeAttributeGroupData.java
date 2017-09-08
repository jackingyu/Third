package com.third.facade.data;

import java.util.List;

public class SizeAttributeGroupData {
	private String groupId;
	private String groupText;
	private List<SizeAttributeData> attributes;

	public String getGroupId()
	{
		return groupId;
	}

	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	public String getGroupText()
	{
		return groupText;
	}

	public void setGroupText(String groupText)
	{
		this.groupText = groupText;
	}

	public List<SizeAttributeData> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(List<SizeAttributeData> attributes)
	{
		this.attributes = attributes;
	}

	public Integer compareTo(SizeAttributeGroupData s)
	{
		return Integer.valueOf(this.groupId) - Integer.valueOf(s.getGroupId());
	}
}
