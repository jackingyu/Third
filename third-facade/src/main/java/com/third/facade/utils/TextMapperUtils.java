package com.third.facade.utils;

import java.util.ArrayList;
import java.util.List;

import com.third.facade.data.ComboboxData;
import com.third.facade.data.TextMapper;


public class TextMapperUtils
{
	public List<ComboboxData> getPaymentTypes()
	{
		List<ComboboxData> results = new ArrayList<ComboboxData>();
		TextMapper.PaymentType.entrySet().forEach(s -> {
			ComboboxData combobox = new ComboboxData();
			combobox.setCode(s.getKey());
			combobox.setText(s.getValue());
			results.add(combobox);
		});

		return results;
	}

	public List<ComboboxData> getPaymentMethods()
	{
		List<ComboboxData> results = new ArrayList<ComboboxData>();
		TextMapper.PaymentMethod.entrySet().forEach(s -> {
			ComboboxData combobox = new ComboboxData();
			combobox.setCode(s.getKey());
			combobox.setText(s.getValue());
			results.add(combobox);
		});

		return results;
	}

	public List<ComboboxData> getItemCategories()
	{
		List<ComboboxData> results = new ArrayList<ComboboxData>();
		TextMapper.ItemCategory.entrySet().forEach(s -> {
			ComboboxData combobox = new ComboboxData();
			combobox.setCode(s.getKey());
			combobox.setText(s.getValue());
			results.add(combobox);
		});

		return results;
	}

}
