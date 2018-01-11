package com.third.facade.testdata.builder;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.third.model.SizeAttributeModel;
import com.third.service.product.SizeAttributeService;

public class SizeAttributeDataBuilder implements DataBuilder {
	private String filename;
	@Resource(name = "sizeAttributeService")
	private SizeAttributeService sizeAttributeService;

	@Override
	public void buildData()
	{
//		List<SizeAttributeModel> attributes = JSON
//				.parseArray(sizeAttributesJson, SizeAttributeModel.class);
		List<String[]> results = ExcelFileReader.readFile(filename, 2);

		results.forEach(r -> {
			for(int i = 1; i < 3; i++ )
			{
				buildSizeAttribute(Double.valueOf(r[0]).intValue(),i*10,r[1]);
			}
		});
	}

	public void buildSizeAttribute(final Integer itemCategory,
			final Integer group, final String name)
	{
		SizeAttributeModel sizeAttributeModel = new SizeAttributeModel();
		sizeAttributeModel.setGroup(group);
		sizeAttributeModel.setItemCategory(itemCategory);
		sizeAttributeModel.setName(name);
		sizeAttributeService.createSizeAttribute(sizeAttributeModel);
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	private final String sizeAttributesJson = "[" +
	/***************************
	 * 西服
	 ****************************/
	// group-10-量 itemcategory-10-西服
			"{'group':'10','itemCategory':'10','name':'肩宽'}" + ","
			+ "{'group':'10','itemCategory':'10','name':'袖长'}" + ","
			+ "{'group':'10','itemCategory':'10','name':'衣长'}" + ","
			+ "{'group':'10','itemCategory':'10','name':'胸围'}" + ","
			+ "{'group':'10','itemCategory':'10','name':'腰围'}" + ","
			+ "{'group':'10','itemCategory':'10','name':'臀围'}" + ","
			+ "{'group':'10','itemCategory':'10','name':'胸宽'}" + ","
			+ "{'group':'10','itemCategory':'10','name':'背宽'}" + "," +
			// group-20-裁 itemcategory-10-西服
			"{'group':'20','itemCategory':'10','name':'衣长'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'前长'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'后长'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'肩宽'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'背宽'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'斜肩'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'平肩'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'高低肩'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'横开领'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'边位'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'胸围'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'腰围'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'臀围'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'挺胸'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'腰省'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'肚省'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'标位'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'袖长'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'袖山'}" + ","
			+ "{'group':'20','itemCategory':'10','name':'袖围'}" + "," +
			// group-30-试 itemcategory-10-西服
			"{'group':'30','itemCategory':'10','name':'码数'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'衣长'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'前长'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'后长'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'肩宽'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'背宽'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'斜肩'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'平肩'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'高低肩'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'横开领'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'边位'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'胸围'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'腰围'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'臀围'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'挺胸'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'腰省'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'肚省'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'标位'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'袖长'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'袖山'}" + ","
			+ "{'group':'30','itemCategory':'10','name':'袖围'}" + "," +
			/***************************
			 * 西裤
			 ****************************/
			// group-10-量 itemcategory-20-西裤
			"{'group':'10','itemCategory':'20','name':'腰围'}" + ","
			+ "{'group':'10','itemCategory':'20','name':'臀围'}" + ","
			+ "{'group':'10','itemCategory':'20','name':'裤长'}" + ","
			+ "{'group':'10','itemCategory':'20','name':'上档'}" + ","
			+ "{'group':'10','itemCategory':'20','name':'全档'}" + ","
			+ "{'group':'10','itemCategory':'20','name':'腿围'}" + ","
			+ "{'group':'10','itemCategory':'20','name':'膝围'}" + ","
			+ "{'group':'10','itemCategory':'20','name':'裤口'}" + "," +
			// group-20-裁 itemcategory-20-西裤
			"{'group':'20','itemCategory':'20','name':'腰围'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'臀围'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'腿围'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'膝围'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'脚口'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'直档'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'全档'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'前低'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'后翘'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'前中'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'后中'}" + ","
			+ "{'group':'20','itemCategory':'20','name':'后开门'}" + "," +
			// group-30-试 itemcategory-20-西裤
			"{'group':'30','itemCategory':'20','name':'码数'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'腰围'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'臀围'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'腿围'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'膝围'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'脚口'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'直档'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'全档'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'前低'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'后翘'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'前中'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'后中'}" + ","
			+ "{'group':'30','itemCategory':'20','name':'后开门'}" + "," +
			/***************************
			 * 衬衫
			 *****************************/
			// group-10-量 itemcategory-30-衬衫
			"{'group':'10','itemCategory':'30','name':'肩宽'}" + ","
			+ "{'group':'10','itemCategory':'30','name':'袖长'}" + ","
			+ "{'group':'10','itemCategory':'30','name':'衣长'}" + ","
			+ "{'group':'10','itemCategory':'30','name':'胸围'}" + ","
			+ "{'group':'10','itemCategory':'30','name':'腰围'}" + ","
			+ "{'group':'10','itemCategory':'30','name':'臀围'}" + ","
			+ "{'group':'10','itemCategory':'30','name':'领围'}" + "," +
			// group-30-试 itemcategory-30-衬衫
			"{'group':'30','itemCategory':'30','name':'肩宽'}" + ","
			+ "{'group':'30','itemCategory':'30','name':'袖长'}" + ","
			+ "{'group':'30','itemCategory':'30','name':'衣长'}" + ","
			+ "{'group':'30','itemCategory':'30','name':'胸围'}" + ","
			+ "{'group':'30','itemCategory':'30','name':'腰围'}" + ","
			+ "{'group':'30','itemCategory':'30','name':'臀围'}" + ","
			+ "{'group':'30','itemCategory':'30','name':'领围'}" + "," +
			/***************************
			 * 背心
			 ****************************/
			// group-30-试 itemcategory-40-背心
			"{'group':'30','itemCategory':'40','name':'码数'}" + ","
			+ "{'group':'30','itemCategory':'40','name':'前长'}" + ","
			+ "{'group':'30','itemCategory':'40','name':'后长'}" + ","
			+ "{'group':'30','itemCategory':'40','name':'上围'}" + ","
			+ "{'group':'30','itemCategory':'40','name':'中围'}" + "]";
}
