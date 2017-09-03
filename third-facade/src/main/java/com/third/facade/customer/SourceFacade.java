package com.third.facade.customer;

import java.util.List;

import com.third.facade.data.SourceData;


public interface SourceFacade
{
	List<SourceData> getSourcesByName(final String name);

	void saveSource(SourceData sourceData);

	void removeSource(SourceData sourceData);

}
