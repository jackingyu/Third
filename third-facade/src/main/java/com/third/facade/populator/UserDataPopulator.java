package com.third.facade.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.third.facade.data.StoreData;
import com.third.facade.data.UserData;
import com.third.facade.data.UserGroupData;
import com.third.model.StoreModel;
import com.third.model.UserModel;

public class UserDataPopulator implements Populator<UserModel, UserData> {
	private StoreDataPopulator storeDataPopulator;
	private UserGroupDataPopulator userGroupDataPopulator;

	@Override
	public void populate(UserModel source, UserData target)
	{
		target.setPk(source.getPk());
		target.setUserId(source.getUserId());
		target.setName(source.getName());
		target.setBlocked(source.getBlocked()==null?false:source.getBlocked());

		List<StoreModel> storeLists = (List<StoreModel>) source.getStores();
		List<StoreData> storeDatas = new ArrayList<StoreData>();

		if (!CollectionUtils.isEmpty(storeLists))
		{
			storeLists.forEach(s -> {
				StoreData store = new StoreData();
				storeDataPopulator.populate(s, store);
				storeDatas.add(store);
			});

			target.setStores(storeDatas);
		}

		UserGroupData userGroup = new UserGroupData();
		userGroupDataPopulator.populate(source.getUserGroup(), userGroup);
		StoreData store = new StoreData();
		storeDataPopulator.populate(source.getStore(), store);
        target.setStore(store);
		target.setUserGroup(userGroup);
		// 不将真实的密码传递出去
		target.setPassword(source.getPassword());
	}

	public void setStoreDataPopulator(StoreDataPopulator storeDataPopulator)
	{
		this.storeDataPopulator = storeDataPopulator;
	}

	public void setUserGroupDataPopulator(
			UserGroupDataPopulator userGroupDataPopulator)
	{
		this.userGroupDataPopulator = userGroupDataPopulator;
	}
}
