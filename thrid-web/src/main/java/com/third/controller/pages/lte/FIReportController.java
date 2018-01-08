package com.third.controller.pages.lte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.third.facade.customer.SourceFacade;
import com.third.facade.data.DTResults;
import com.third.facade.data.SourceData;
import com.third.facade.data.StoreData;
import com.third.facade.store.FIReportFacade;
import com.third.facade.user.UserFacade;

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
        String[] storeCodes = { storeCode };
        sp.put("storeCodes", storeCodes);
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
        String[] storeCodes = { storeCode };
        sp.put("storeCodes", storeCodes);
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
        

        if ((this.isManger()||this.isSales()) && (storeCodes == null || storeCodes.length == 0))
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

}
