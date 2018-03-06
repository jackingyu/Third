package com.third.controller.pages.lte;

import java.util.ArrayList;
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
import com.third.core.util.DataTableCriterias;
import com.third.facade.data.DTResults;
import com.third.facade.data.StoreData;
import com.third.facade.store.FIReportFacade;
import com.third.facade.user.UserFacade;
import com.third.facade.utils.ExcelUtils;

@Controller
public class FIReportController extends AbstractPageController {
    private static final Logger LOG = Logger
            .getLogger(com.third.controller.pages.lte.FIReportController.class);

    @Resource(name = "FIReportFacade")
    private FIReportFacade fiReportFacade;

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @RequestMapping(value = "/payment/listpage", method = RequestMethod.GET)
    public String paymentListPage(Model model)
    {

        this.fillAuthorizedStoreInView(model);
        fillSourceInModel(model);
        fillPaymentMethods2View(model);
        fillOrderStatus2View(model);
        model.addAttribute("salesPersons", getSalesPerson());
        model.addAttribute(ControllerConstants.LTE.TITLE, "财务明细报表");
        return ControllerConstants.LTE.PAYMENTLISTPAGE;
    }

    @RequestMapping(value = "/storedashboard/dashboardpage", method = RequestMethod.GET)
    public String getStoreDashboardPage(Model model)
    {

        fillAuthorizedStoreInView(model);
        fillAllSourceInView(model);

        return ControllerConstants.LTE.STOREDASHBOARDPAGE;
    }

    @RequestMapping(value = "/storedashboard/query", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDashboardResult(
            @RequestParam(value = "storeCodes", required = false) final String[] storeCodes,
            @RequestParam(value = "customerSources", required = false) final String[] customerSources,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            Model model)
    {
        Map<String, String[]> sp = new HashMap<String, String[]>();
        
        if (storeCodes == null || storeCodes.length == 0)
        {
            List<StoreData> stores = userFacade.getCurrentUser().getStores();
            String[] userStoreCodes = new String[stores.size()];
            for (int i = 0; i < stores.size(); i++)
            {
                userStoreCodes[i] = stores.get(i).getCode();
            }

            sp.put("storeCodes", userStoreCodes);
        }else
            sp.put("storeCodes", storeCodes);
        
        sp.put("customerSources", customerSources);

        return fiReportFacade.getStoreDashboardResult1(startDate, endDate, sp);
    }

    @RequestMapping(value = "/storedashboard/querypaymentdetails", method = RequestMethod.GET)
    @ResponseBody
    public Object queryPaymentDetails(
            @RequestParam(value = "storeCode", required = false) final String storeCode,
            @RequestParam(value = "customerSources", required = false) final String[] customerSources,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            Model model, final DataTableCriterias criterias)
    {
        Map<String, String[]> sp = new HashMap<String, String[]>();
        
        if (StringUtils.isEmpty(storeCode))
        {
            List<StoreData> stores = userFacade.getCurrentUser().getStores();
            String[] userStoreCodes = new String[stores.size()];
            for (int i = 0; i < stores.size(); i++)
            {
                userStoreCodes[i] = stores.get(i).getCode();
            }

            sp.put("storeCodes", userStoreCodes);
        }else
        {  
            String[] storeCodes = { storeCode };
            sp.put("storeCodes", storeCodes);
        }
        
        sp.put("customerSources", customerSources);

        return fiReportFacade.getDashboardPaymentDetails(startDate, endDate, sp,
                criterias.getStart(), criterias.getLength());
    }

    @RequestMapping(value = "/storedashboard/querypaymentpercentage", method = RequestMethod.GET)
    @ResponseBody
    public Object queryPaymentByMethod(
            @RequestParam(value = "storeCode", required = false) final String storeCode,
            @RequestParam(value = "customerSources", required = false) final String[] customerSources,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
    {
        Map<String, String[]> sp = new HashMap<String, String[]>();
        
        if (StringUtils.isEmpty(storeCode))
        {
            List<StoreData> stores = userFacade.getCurrentUser().getStores();
            String[] userStoreCodes = new String[stores.size()];
            for (int i = 0; i < stores.size(); i++)
            {
                userStoreCodes[i] = stores.get(i).getCode();
            }

            sp.put("storeCodes", userStoreCodes);
        }else
        {  
            String[] storeCodes = { storeCode };
            sp.put("storeCodes", storeCodes);
        }
        
        sp.put("customerSources", customerSources);

        return fiReportFacade.getDashboardPaymentsByMethod(startDate, endDate,
                sp);
    }

    @RequestMapping(value = "/payment/getlist")
    @ResponseBody
    public Object getPaymentList(
            @RequestParam(value = "storeCodes", required = false) final String[] storeCodes,
            @RequestParam(value = "sourcePKs", required = false) final String[] sourcePKs,
            @RequestParam(value = "paymentMethods", required = false) final String[] paymentMethods,
            @RequestParam(value = "orderStatus", required = false) final String[] orderStatus,
            @RequestParam(value = "salesPersons", required = false) final String[] salesPersons,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            final DataTableCriterias criterias)
    {

        Map<String, String[]> sp = new HashMap<String, String[]>();

        sp.put("sourcePKs", sourcePKs);
        sp.put("paymentMethods", paymentMethods);
        

        if (storeCodes == null || storeCodes.length == 0)
        {
            List<StoreData> stores = userFacade.getCurrentUser().getStores();
            String[] userStoreCodes = new String[stores.size()];
            for (int i = 0; i < stores.size(); i++)
            {
                userStoreCodes[i] = stores.get(i).getCode();
            }

            sp.put("storeCodes", userStoreCodes);
        }else
            sp.put("storeCodes", storeCodes);

        if (this.isSales())
        {
            String[] sales = { userFacade.getCurrentUser().getUserId() };
            sp.put("salesPersons", sales);
        } else
        {
            sp.put("salesPersons", salesPersons);
        }
        
        //if query order and set order status to all
        boolean ifAll = false;
        for(int i = 0; i < orderStatus.length;i++)
        {
            if(Integer.valueOf(orderStatus[i])<0)
            {
                ifAll = true;
                break;
            }
        }
        
        if(!ifAll)
         sp.put("orderStatus", orderStatus);

        DTResults r = fiReportFacade.getPaymentList(startDate, endDate,
                criterias.getStart(), criterias.getLength(), sp);

        return r;
    }
    @RequestMapping(value = "/payment/export")
    public void export(
            @RequestParam(value = "storeCodes", required = false) final String[] storeCodes,
            @RequestParam(value = "sourcePKs", required = false) final String[] sourcePKs,
            @RequestParam(value = "paymentMethods", required = false) final String[] paymentMethods,
            @RequestParam(value = "orderStatus", required = false) final String[] orderStatus,
            @RequestParam(value = "salesPersons", required = false) final String[] salesPersons,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            final HttpServletRequest request,
            final HttpServletResponse response)
    {
        
        Map<String, String[]> sp = new HashMap<String, String[]>();
        
        sp.put("sourcePKs", sourcePKs);
        sp.put("paymentMethods", paymentMethods);
        
        
        if (storeCodes == null || storeCodes.length == 0)
        {
            List<StoreData> stores = userFacade.getCurrentUser().getStores();
            String[] userStoreCodes = new String[stores.size()];
            for (int i = 0; i < stores.size(); i++)
            {
                userStoreCodes[i] = stores.get(i).getCode();
            }
            
            sp.put("storeCodes", userStoreCodes);
        }else
            sp.put("storeCodes", storeCodes);
        
        if (this.isSales())
        {
            String[] sales = { userFacade.getCurrentUser().getUserId() };
            sp.put("salesPersons", sales);
        } else
        {
            sp.put("salesPersons", salesPersons);
        }
        
        //if query order and set order status to all
        boolean ifAll = false;
        for(int i = 0; i < orderStatus.length;i++)
        {
            if(Integer.valueOf(orderStatus[i])<0)
            {
                ifAll = true;
                break;
            }
        }
        
        if(!ifAll)
            sp.put("orderStatus", orderStatus);
        
        DTResults r = fiReportFacade.getPaymentList(startDate, endDate,
                0, 100000, sp);
        
        
        List<Object[]> data = r.getData();
        String[] headerNames = {"门店","顾客","订单编码","销售员","付款方式","付款金额","付款日期","订单总金额","订单已付","订单剩余","订单日期","客户来源"};
        ExcelUtils.exportToExcel1("报表","财务报表",headerNames,data, request,response);
        
    }
    
    @RequestMapping(value = "/payment/getsummary")
    @ResponseBody
    public Object getPaymentSummary(
            @RequestParam(value = "storeCodes", required = false) final String[] storeCodes,
            @RequestParam(value = "sourcePKs", required = false) final String[] sourcePKs,
            @RequestParam(value = "paymentMethods", required = false) final String[] paymentMethods,
            @RequestParam(value = "orderStatus", required = false) final String[] orderStatus,
            @RequestParam(value = "salesPersons", required = false) final String[] salesPersons,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            final DataTableCriterias criterias)
    {
        
        Map<String, String[]> sp = new HashMap<String, String[]>();
        
        sp.put("sourcePKs", sourcePKs);
        sp.put("paymentMethods", paymentMethods);
        
        
        if (storeCodes == null || storeCodes.length == 0)
        {
            List<StoreData> stores = userFacade.getCurrentUser().getStores();
            String[] userStoreCodes = new String[stores.size()];
            for (int i = 0; i < stores.size(); i++)
            {
                userStoreCodes[i] = stores.get(i).getCode();
            }
            
            sp.put("storeCodes", userStoreCodes);
        }else
            sp.put("storeCodes", storeCodes);
        
        if (this.isSales())
        {
            String[] sales = { userFacade.getCurrentUser().getUserId() };
            sp.put("salesPersons", sales);
        } else
        {
            sp.put("salesPersons", salesPersons);
        }
        
        //if query order and set order status to all
        boolean ifAll = false;
        for(int i = 0; i < orderStatus.length;i++)
        {
            if(Integer.valueOf(orderStatus[i])<0)
            {
                ifAll = true;
                break;
            }
        }
        
        if(!ifAll)
            sp.put("orderStatus", orderStatus);
        
        return this.fiReportFacade.getPaymentListSummary(startDate, endDate, sp);
    }

}
