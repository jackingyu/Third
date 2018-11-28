package com.third.facade.utils;

import java.util.List;

import com.third.facade.testdata.builder.DataBuilder;
import org.springframework.stereotype.Service;

/**
 * test data generator,depends {@link DataBuilder}
 */
@Service("dataGenerator")
public class TestDataGenerator {
	private List<DataBuilder> dataBuilders;

	public void generateData()
	{
		dataBuilders.forEach(db -> db.buildData());
	}

	public void setDataBuilders(List<DataBuilder> dataBuilders)
	{
		this.dataBuilders = dataBuilders;
	}

}
