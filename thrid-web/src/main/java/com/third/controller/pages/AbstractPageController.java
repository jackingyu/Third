package com.third.controller.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;

import com.third.controller.pages.lte.DTResultsV;
import com.third.core.util.DataTableCriterias;
import com.third.facade.data.CityData;
import com.third.facade.data.ComboboxData;
import com.third.facade.data.ListData;
import com.third.facade.data.ProductGroupData;
import com.third.facade.data.RegionData;
import com.third.facade.data.StoreData;
import com.third.facade.local.I18NFacade;
import com.third.facade.product.ProductFacade;
import com.third.facade.store.StoreFacade;
import com.third.facade.user.UserFacade;
import com.third.facade.utils.TextMapperUtils;


public abstract class AbstractPageController
{  
	public static final String REDIRECT_PREFIX = "redirect:";
	public static final String FORWARD_PREFIX = "forward:";
	@Resource(name="userFacade")
	private UserFacade userFacade;
	
	@Resource(name="i18NFacade")
	private I18NFacade i18NFacade;
	
	@Resource(name="storeFacade")
	private StoreFacade storeFacade;
	
	@Resource(name="productFacade")
	private ProductFacade productFacade;
	
	protected Integer getStartIndex(Integer page, Integer rows)
	{
		return (page - 1) * rows;
	}

	protected Integer getStartIndexForDT(final DataTableCriterias criterias)
	{
		return criterias.getStart();
	}

	protected Integer getPagesizeForDT(final DataTableCriterias criterias)
	{
		return criterias.getLength();
	}

	protected DTResultsV initDTResults(ListData results)
	{
		DTResultsV data = new DTResultsV();
		data.setRecordsFiltered(results.getTotal());
		data.setRecordsTotal(results.getTotal());
		return data;
	}
	
	/**
	 * only the store could be used by current user
	 * @param model
	 */
	protected void fillAllStore2View(final Model model){
		List<ComboboxData> stores = new ArrayList<ComboboxData>();
		List<StoreData> storeDatas=userFacade.getCurrentUser().getStores();
		
		for(int i = 0 ;i < storeDatas.size();i++){
			   StoreData s = storeDatas.get(i);
         	ComboboxData store = new ComboboxData();
         	store.setCode(s.getCode());
         	store.setText(s.getName());
         	
         	if(i==0)
         		store.setSelected(true);
         	
            stores.add(store);
		}
		
		model.addAttribute("stores",stores);
	}
	
	protected void fillAllStoreInView(final Model model,final List<StoreData> selectedStores){
		List<ComboboxData> stores = new ArrayList<ComboboxData>();
		List<StoreData> storeDatas= storeFacade.getAllStores();
		
		for(int i = 0 ;i < storeDatas.size();i++){
			StoreData s = storeDatas.get(i);
			ComboboxData store = new ComboboxData();
			store.setCode(s.getCode());
			store.setText(s.getName());
			
			if(CollectionUtils.isNotEmpty(selectedStores))
				for(int j = 0; j < selectedStores.size();j++)
				{
					if(selectedStores.get(j).getCode().equals(store.getCode()))
					{
						store.setSelected(true);
						break;
					}
				}
			
			stores.add(store);
		}
		
		model.addAttribute("stores",stores);
	}
	
	/**fill authorized store in view,set selected according the parameter storeCode
	 * @param model
	 * @param storeCode
	 */
	protected void fillStore2View(final Model model,final String storeCode){
		List<ComboboxData> stores = new ArrayList<ComboboxData>();
		List<StoreData> storeDatas=userFacade.getCurrentUser().getStores();

		for(int i = 0 ;i < storeDatas.size();i++){
		   StoreData s = storeDatas.get(i);
      	ComboboxData store = new ComboboxData();
      	store.setCode(s.getCode());
      	store.setText(s.getName());
      	if(s.getCode().equals(storeCode))
				store.setSelected(true);
         stores.add(store);
	   }
		
		model.addAttribute("stores",stores);
	}
	
	/**fill all the authorized store in the view
	 * @param model
	 */
	protected void fillStore2View(final Model model){
		List<ComboboxData> stores = new ArrayList<ComboboxData>();
		List<StoreData> storeDatas=userFacade.getCurrentUser().getStores();
		
		for(int i = 0 ;i < storeDatas.size();i++){
			StoreData s = storeDatas.get(i);
			ComboboxData store = new ComboboxData();
			store.setCode(s.getCode());
			store.setText(s.getName());
			if(i==0)
				store.setSelected(true);
			stores.add(store);
		}
		
		model.addAttribute("stores",stores);
	}
	
	protected void fillAddressInModel(final Model model,final String selectedRegion){
		List<ComboboxData> regions = new ArrayList<ComboboxData>();
		List<RegionData> regionDatas = i18NFacade.getRegions();
		String regionISOCode = StringUtils.isEmpty(selectedRegion)?regionDatas.get(0).getIsoCode():selectedRegion;
		
		for(int i = 0 ;i < regionDatas.size();i++){
			   RegionData r = regionDatas.get(i);
         	ComboboxData region = new ComboboxData();
         	region.setCode(r.getIsoCode());
         	region.setText(r.getName());
            
            if(regionISOCode.equals(r.getIsoCode()))
            	region.setSelected(true);
            	
            regions.add(region);
		}
		
		
		model.addAttribute("regions",regions);
		model.addAttribute("citys",getCityForRegion(regionISOCode));
	}
	
	protected List<ComboboxData> getCityForRegion(final String regionISOCode)
	{
		List<ComboboxData> citys = new ArrayList<ComboboxData>();
		List<CityData> cityDatas = i18NFacade.getCityForRegion(regionISOCode);
		
		for(int i = 0 ;i < cityDatas.size();i++){
			CityData c = cityDatas.get(i);
			ComboboxData city = new ComboboxData();
			city.setCode(c.getIsoCode());
			city.setText(c.getName());
			citys.add(city);
		}
		
		return citys;
	}
	
	protected void fillOrderStatus2View(final Model model){
		model.addAttribute("orderStatus",TextMapperUtils.getOrderStatus());
	}
	
	protected void fillPaymentMethods2View(final Model model){
		model.addAttribute("paymentMethods",TextMapperUtils.getPaymentMethods());
	}
	
	protected void fillProductGroupsInModel(Model model)
	{
		List<ComboboxData> productGroups = convertProductGrouptoCombobox(productFacade.getProductGroups());
		model.addAttribute("productGroups",productGroups);
	}
	
	protected List<ComboboxData> convertProductGrouptoCombobox(List<ProductGroupData> productGroups)
	{
      List<ComboboxData> comboboxs = new ArrayList<ComboboxData>();
      
		for(int i = 0; i < productGroups.size();i++)
		{
			ProductGroupData productGroup = productGroups.get(i);
			ComboboxData combobox = new ComboboxData();
			
			if(i == 0)
				combobox.setSelected(true);
			
			combobox.setCode(productGroup.getPk());
			combobox.setText(productGroup.getName());
			
			comboboxs.add(combobox);
		}
		
		return comboboxs;
		
	}
}
