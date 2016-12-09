package com.third.facade.populator;

import org.apache.commons.lang3.StringUtils;

import com.third.facade.data.OrderEntryData;
import com.third.facade.data.TextMapper;
import com.third.facade.data.UserData;
import com.third.model.OrderEntryModel;


public class OrderEntryDataPopulator implements Populator<OrderEntryModel, OrderEntryData>
{

	private UserDataPopulator userDataPopulator;

	@Override
	public void populate(OrderEntryModel source, OrderEntryData target)
	{
		target.setDeliveryDate(source.getDeliveryDate());
		target.setDesigner(source.getDesigner());
		target.setEntryNo(source.getEntryNo());
		target.setItemCategory(source.getItemCategory());
		target.setItemCategoryText(TextMapper.ItemCategory.get(source.getItemCategory()));
		target.setModificationTime(source.getModificationTime());
		target.setCreateTime(source.getCreateTime());
		target.setPk(source.getPk());
		target.setProductTitle(source.getProductTitle());
		target.setStyle(source.getStyle());
		target.setQuantity(source.getQuantity());
		target.setSizeDate(source.getSizeDate());
		target.setTryDate(source.getTryDate());
		target.setComment(source.getComment());
		target.setOrderCode(source.getOrder().getCode());
		target.setStoreName(source.getOrder().getStore().getName());
		target.setCustomerName(StringUtils.isBlank(source.getCustomerName()) ? source.getOrder().getCustomerName() : source
				.getCustomerName());
      target.setSizeDetails(source.getSizeDetails());
      target.setSizeImageUrl(source.getSizeImage());
   
		if (source.getCreatedBy() != null)
		{
			UserData user = new UserData();
			userDataPopulator.populate(source.getCreatedBy(), user);

			target.setCreateBy(user);
		}
	}

	public void setUserDataPopulator(UserDataPopulator userDataPopulator)
	{
		this.userDataPopulator = userDataPopulator;
	}

}
