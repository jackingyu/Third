package com.third.service.customer;

import java.util.List;

import com.third.model.SourceModel;

public interface SourceService {
	public List<SourceModel> getSources();
	
	public List<SourceModel> getExhibitions();

	public void createSource(SourceModel source);

	public SourceModel getSource(String pk);

	List<SourceModel> getSources(String name);

	void saveSource(SourceModel source);

}
