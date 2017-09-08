package com.third.facade.customer;

import java.util.List;

import com.third.facade.data.SourceData;

public interface SourceFacade {
	List<SourceData> getSourcesByName(final String name);

	void saveSource(SourceData sourceData);

	void removeSource(SourceData sourceData);

	List<SourceData> getSourceForStoreCode(final String storeCode);

	List<SourceData> getAllSources();

	void assignSource2Store(List<String> sourcePKs, String storeCode);

	void removeSourceFromStore(String sourcePK, String storeCode);

}
