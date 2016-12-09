package com.third.facade.populator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.third.facade.data.ReservationData;
import com.third.facade.data.StoreData;
import com.third.facade.data.TextMapper;
import com.third.model.ReservationModel;
import com.third.model.StoreModel;
import com.third.model.UserModel;


public class ReservationDataPopulator implements Populator<ReservationModel, ReservationData>
{
	@Resource(name="storeDataPopulator")
   private StoreDataPopulator storeDataPopulator;
	
	@Override
	public void populate(ReservationModel source, ReservationData target)
	{
		target.setChannel(source.getChannel());
		target.setName(source.getName());
		target.setCellphone(source.getCellphone());
		target.setReservationDate(source.getReservationDate());
		
		StoreData store = new StoreData();
		storeDataPopulator.populate(source.getStore(), store);
		target.setStore(store);
		target.setPk(source.getPk());
		target.setChannelText(TextMapper.ReservationChannel.get(source.getChannel()));
	}
	
}
