package com.third.service.customer;

import com.third.model.SubscribeModel;

public interface SubscribeService {
	public SubscribeModel getSubscribeModel(String openId);

	public void updateSubscribeModel(SubscribeModel subscribeModel);

	public void create(SubscribeModel subscribeModel);

	public void save(SubscribeModel subscribeModel);
}
