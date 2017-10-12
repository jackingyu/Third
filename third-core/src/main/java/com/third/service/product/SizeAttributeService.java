package com.third.service.product;

import java.util.List;

import com.third.model.SizeAttributeModel;

public interface SizeAttributeService {
	public void createSizeAttribute(SizeAttributeModel sizeAttributeModel);

	public List<SizeAttributeModel> getSizeAttributeForItemCategory(
			final Integer itemCategory);

	List<SizeAttributeModel> getSizeAttribute(Integer itemCategory,
			Integer sizeGroup);

}
