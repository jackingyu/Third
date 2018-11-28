package com.third.facade.customer.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.third.facade.customer.SourceFacade;
import com.third.facade.data.SourceData;
import com.third.facade.populator.SourceDataPopulator;
import com.third.model.SourceModel;
import com.third.model.StoreModel;
import com.third.service.customer.SourceService;
import com.third.service.store.StoreService;
import com.third.service.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class DefaultSourceFacade implements SourceFacade {
	private SourceService sourceService;
	private StoreService storeService;
	private SourceDataPopulator sourceDataPopulator;

	@Override
	public void saveSource(SourceData sourceData)
	{
		SourceModel source;
		if (StringUtils.isEmpty(sourceData.getPk()))
		{
			source = new SourceModel();
			source.setName(sourceData.getName());
			source.setType(sourceData.getType());
			sourceService.createSource(source);
		} else
		{
			source = sourceService.getSource(sourceData.getPk());
			source.setName(sourceData.getName());
			source.setType(sourceData.getType());
			sourceService.saveSource(source);
		}
	}

	@Override
	public List<SourceData> getSourcesByName(String name)
	{
		List<SourceModel> sourceModels = sourceService.getSources(name);
		List<SourceData> sourceDatas = new ArrayList<SourceData>();

		sourceModels.forEach(s -> {
			SourceData source = new SourceData();
			sourceDataPopulator.populate(s, source);
			sourceDatas.add(source);
		});

		return sourceDatas;
	}

	@Override
	public void removeSource(SourceData sourceData)
	{
		// TODO Auto-generated method stub

	}

	public void setSourceService(SourceService sourceService)
	{
		this.sourceService = sourceService;
	}

	public void setSourceDataPopulator(SourceDataPopulator sourceDataPopulator)
	{
		this.sourceDataPopulator = sourceDataPopulator;
	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

	@Override
	public List<SourceData> getSourceForStoreCode(String storeCode)
	{
		StoreModel storeModel = storeService.getStoreForCode(storeCode);
		Collection<SourceModel> sourceModels = storeModel.getSources();
		List<SourceData> sourceDatas = new ArrayList<SourceData>();

		if (CollectionUtils.isNotEmpty(sourceModels))
			sourceModels.forEach(s -> {
				SourceData source = new SourceData();
				sourceDataPopulator.populate(s, source);
				sourceDatas.add(source);
			});

		return sourceDatas;
	}

	@Override
	public List<SourceData> getAllSources()
	{
		List<SourceModel> sourceModels = sourceService.getSources();
		List<SourceData> sourceDatas = new ArrayList<SourceData>();

		sourceModels.forEach(s -> {
			SourceData source = new SourceData();
			sourceDataPopulator.populate(s, source);
			sourceDatas.add(source);
		});

		return sourceDatas;
	}

	@Override
	public void assignSource2Store(List<String> sourcePKs, String storeCode)
	{
		StoreModel store = storeService.getStoreForCode(storeCode);
		List<SourceModel> oldSourceModels = (List<SourceModel>) store
				.getSources();
		HashMap<String, SourceModel> oldSourceMaps = new HashMap<String, SourceModel>();

		oldSourceModels.forEach(os -> {
			oldSourceMaps.put(os.getPk(), os);
		});

		sourcePKs.forEach(s -> {
			if (!oldSourceMaps.containsKey(s))
			{
				SourceModel source = sourceService.getSource(s);
				oldSourceModels.add(source);
				oldSourceMaps.put(s, source);
			}
		});

		store.setSources(oldSourceModels);

		// TODO need to add a method save
		storeService.saveStore(store);
	}

	public void removeSourceFromStore(String sourcePK, String storeCode)
	{

		StoreModel store = storeService.getStoreForCode(storeCode);
		List<SourceModel> oldSourceModels = (List<SourceModel>) store
				.getSources();
		List<SourceModel> newSourceModels = new ArrayList<SourceModel>();

		HashMap<String, SourceModel> oldSourceMaps = new HashMap<String, SourceModel>();

		oldSourceModels.forEach(os -> {
			if (!os.getPk().equals(sourcePK))
				newSourceModels.add(os);
		});

		store.setSources(newSourceModels);

		// TODO need to add a method save
		storeService.saveStore(store);

	}

	@Override
	public List<SourceData> getExhibitionsForStoreCode(String storeCode)
	{
		StoreModel storeModel = storeService.getStoreForCode(storeCode);
		Collection<SourceModel> sourceModels = storeModel.getSources();
		List<SourceData> sourceDatas = new ArrayList<SourceData>();

		if (CollectionUtils.isNotEmpty(sourceModels))
			sourceModels.forEach(s -> {
				if (sourceService.isExhibition(s))
				{
					SourceData source = new SourceData();
					sourceDataPopulator.populate(s, source);
					sourceDatas.add(source);
				}
			});

		return sourceDatas;
	}

	@Override
	public SourceData getSource(String pk)
	{
		SourceModel source = sourceService.getSource(pk);
		SourceData sourceData  = new SourceData();
		sourceDataPopulator.populate(source, sourceData);
		return sourceData;
	}

}
