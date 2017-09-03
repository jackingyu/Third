package com.third.controller.pages.lte;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.customer.SourceFacade;
import com.third.facade.data.DTResults;
import com.third.facade.data.SourceData;


@Controller
public class SourceListController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(com.third.controller.pages.lte.SourceListController.class);

	@Resource(name = "sourceFacade")
	private SourceFacade sourceFacade;

	@RequestMapping(value = "/source/listpage", method = RequestMethod.GET)
	public String sourceListPage(Model model)
	{
		return ControllerConstants.LTE.SOURCELISTPAGE;
	}

	@RequestMapping(value = "/source/save", method = RequestMethod.POST)
	@ResponseBody
	public void saveSource(@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "pk", required = false) final String pk)
	{
		SourceData source = new SourceData();
		source.setName(name);
		
		if(StringUtils.isNotEmpty(pk))
			source.setPk(pk);
		
		sourceFacade.saveSource(source);
	}

	@RequestMapping(value = "/source/list")
	@ResponseBody
	public Object getSourceList(@RequestParam(value = "name", required = false) final String name,
			final DataTableCriterias criterias)
	{

		DTResults r = new DTResults();
		List<SourceData> sources = sourceFacade.getSourcesByName(name);

		r.setRecordsFiltered(sources.size());
		r.setRecordsTotal(sources.size());
		List<Object[]> results = new ArrayList<Object[]>();

		for (int i = 0; i < sources.size(); i++)
		{
			Object[] row =
			{ sources.get(i).getName(), sources.get(i).getPk() };
			results.add(row);
		}
		r.setData(results);

		return r;
	}

}
