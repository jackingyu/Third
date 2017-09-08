package com.third.facade.populator;

import com.third.facade.data.UserGroupData;
import com.third.model.UserGroupModel;

public class UserGroupDataPopulator
		implements Populator<UserGroupModel, UserGroupData> {

	@Override
	public void populate(UserGroupModel source, UserGroupData target)
	{
		target.setPk(source.getPk());
		target.setGroupId(source.getGroupId());
		target.setName(source.getName());
	}

}
