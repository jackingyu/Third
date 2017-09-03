package com.third.facade.customer.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.third.facade.customer.SourceFacade;
import com.third.facade.data.SourceData;
import com.third.facade.populator.SourceDataPopulator;
import com.third.model.SourceModel;
import com.third.service.customer.SourceService;

public class DefaultSourceFacade implements SourceFacade
{
	private SourceService sourceService;
	private SourceDataPopulator sourceDataPopulator;

	@Override
	public void saveSource(SourceData sourceData)
	{
		SourceModel source;
		if(StringUtils.isEmpty(sourceData.getPk()))
		{
			source = new SourceModel();
         source.setName(sourceData.getName());
         sourceService.createSource(source);
		}
		else 
		{
			source = sourceService.getSource(sourceData.getPk());
			source.setName(sourceData.getName());
			sourceService.saveSource(source);
		}
	}

	@Override
	public List<SourceData> getSourcesByName(String name)
	{
		List<SourceModel> sourceModels = sourceService.getSources(name);
		List<SourceData> sourceDatas = new ArrayList<SourceData>();
		
		sourceModels.forEach( s ->{
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

}
