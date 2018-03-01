package com.third.controller.pages.lte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.constants.CoreConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.data.DTResults;
import com.third.facade.data.TextMapper;
import com.third.facade.order.OrderFacade;
import com.third.facade.user.UserFacade;
import com.third.facade.utils.DateUtils;
import com.third.facade.utils.ExcelUtils;
import com.third.facade.utils.TextMapperUtils;

@Controller
public class SizeOrderListController extends AbstractPageController {
    private static final Logger LOG = Logger.getLogger(
            com.third.controller.pages.lte.SizeOrderListController.class);

    @Resource(name = "orderFacade")
    private OrderFacade orderFacade;
    
    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @RequestMapping(value = "/orderentry/listpage", method = RequestMethod.GET)
    public String orderEntryListPage(Model model)
    {
        fillAuthorizedStoreInView(model);
        fillOrderStatus2View(model);
        return ControllerConstants.LTE.ORDERENTRYLISTPAGE;
    }

    @RequestMapping(value = "/orderentry/list")
    @ResponseBody
    public Object getOrderEntryList(
            @RequestParam(value = "externalId", required = false) final String externalId,
            @RequestParam(value = "customerName", required = false) final String name,
            @RequestParam(value = "storeCodes", required = false) final String storeCodes,
            @RequestParam(value = "orderEntryStatus", required = false) final String orderEntryStatus,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "startTryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTryDate,
            @RequestParam(value = "endTryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTryDate,
            @RequestParam(value = "onlyUnExported", required = false) boolean onlyUnExported,
            @RequestParam(value = "startActualTryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startActualTryDate,
            @RequestParam(value = "endActualTryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endActualTryDate,
            final DataTableCriterias criterias)
    {
        Map<String, String> sp = new HashMap<String, String>();
        sp.put("externalId", externalId);
        sp.put("name", name);
        if (orderEntryStatus != null && Integer.valueOf(orderEntryStatus) >= 0)
            sp.put("status", orderEntryStatus);

        if (StringUtils.isEmpty(storeCodes))
        {
            String storeCodes1 = userFacade.getCurrentUser().getStoreCodes();
            sp.put("storeCodes", storeCodes1);
        } else
            sp.put("storeCodes", storeCodes);

        if (startTryDate != null && endTryDate != null)
        {
            sp.put("tryDate", DateUtils.formatYYYYMMDD(startTryDate) + ","
                    + DateUtils.formatYYYYMMDD(endTryDate));
        }

        if (startActualTryDate != null && endActualTryDate != null)
        {
            sp.put("actualTryDate", DateUtils.formatYYYYMMDD(startActualTryDate)
                    + "," + DateUtils.formatYYYYMMDD(endActualTryDate));
        }

        if(onlyUnExported)
        {
            sp.put("exported", "0");
        }
        
        DTResults r = orderFacade.getOrderEntries(startDate, endDate,
                criterias.getStart(), criterias.getLength(), sp);

        return r;
    }

    @RequestMapping(value = "/orderentry/export")
    public void exportOrderEntryList(
            @RequestParam(value = "externalId", required = false) final String externalId,
            @RequestParam(value = "customerName", required = false) final String name,
            @RequestParam(value = "storeCodes", required = false) final String storeCodes,
            @RequestParam(value = "orderEntryStatus", required = false) final String orderEntryStatus,
            @RequestParam(value = "onlyUnExported", required = false) boolean onlyUnExported,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "startTryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTryDate,
            @RequestParam(value = "endTryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTryDate,
            @RequestParam(value = "startActualTryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startActualTryDate,
            @RequestParam(value = "endActualTryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endActualTryDate,
            final HttpServletRequest request,
            final HttpServletResponse response)
    {
        Map<String, String> sp = new HashMap<String, String>();
        sp.put("externalId", externalId);
        sp.put("name", name);
        if (orderEntryStatus != null && Integer.valueOf(orderEntryStatus) >= 0)
            sp.put("status", orderEntryStatus);

        if (startTryDate != null && endTryDate != null)
        {
            sp.put("tryDate", DateUtils.formatYYYYMMDD(startTryDate) + ","
                    + DateUtils.formatYYYYMMDD(endTryDate));
        }

        if (startActualTryDate != null && endActualTryDate != null)
        {
            sp.put("actualTryDate", DateUtils.formatYYYYMMDD(startActualTryDate)
                    + "," + DateUtils.formatYYYYMMDD(endActualTryDate));
        }
        
        if(onlyUnExported)
        {
            sp.put("exported", "0");
        }

        if (StringUtils.isEmpty(storeCodes))
        {
            String storeCodes1 = userFacade.getCurrentUser().getStoreCodes();
            sp.put("storeCodes", storeCodes1);
        } else
            sp.put("storeCodes", storeCodes);
        
        String[] sheetNames = new String[4];

        sp.put("itemCategory", CoreConstants.ItemCategory.Shirt);
        sheetNames[0] = TextMapperUtils
                .getItemCategoryText(CoreConstants.ItemCategory.Shirt);
        List<Object[]> r1 = orderFacade.exportOrderEntries(startDate, endDate,
                0, 10000, sp);

        sp.put("itemCategory", CoreConstants.ItemCategory.Trousers);
        sheetNames[1] = TextMapperUtils
                .getItemCategoryText(CoreConstants.ItemCategory.Trousers);
        List<Object[]> r2 = orderFacade.exportOrderEntries(startDate, endDate,
                0, 10000, sp);

        sp.put("itemCategory", CoreConstants.ItemCategory.Suit);
        sheetNames[2] = TextMapperUtils
                .getItemCategoryText(CoreConstants.ItemCategory.Suit);
        List<Object[]> r3 = orderFacade.exportOrderEntries(startDate, endDate,
                0, 10000, sp);

        sp.put("itemCategory", CoreConstants.ItemCategory.Vest);
        sheetNames[3] = TextMapperUtils
                .getItemCategoryText(CoreConstants.ItemCategory.Vest);
        List<Object[]> r4 = orderFacade.exportOrderEntries(startDate, endDate,
                0, 10000, sp);

        List<List<Object[]>> dataArrays = new ArrayList<List<Object[]>>();
        dataArrays.add(r1);
        dataArrays.add(r2);
        dataArrays.add(r3);
        dataArrays.add(r4);
        ExcelUtils.exportExcel("量身单列表", sheetNames, dataArrays, request,
                response);
    }

}
