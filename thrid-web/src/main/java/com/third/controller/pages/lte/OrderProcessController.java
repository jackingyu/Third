package com.third.controller.pages.lte;

import java.io.IOException;
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
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.constants.CoreConstants;
import com.third.core.util.DataTableCriterias;
import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.exceptions.NotFoundException;
import com.third.facade.data.DTResults;
import com.third.facade.data.OrderData;
import com.third.facade.data.StoreData;
import com.third.facade.order.OrderFacade;
import com.third.facade.order.OrderProcessFacade;
import com.third.facade.populator.option.OrderOption;
import com.third.facade.user.UserFacade;
import com.third.facade.utils.ExcelUtils;
import com.third.facade.utils.TextMapperUtils;

@Controller
public class OrderProcessController extends AbstractPageController {
    private static final Logger LOG = Logger.getLogger(
            com.third.controller.pages.lte.OrderProcessController.class);

    @Resource(name = "orderProcessFacade")
    private OrderProcessFacade orderProcessFacade;

    @Resource(name = "textMapperUtils")
    private TextMapperUtils textMapperUtils;

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @Resource(name = "orderFacade")
    private OrderFacade orderFacade;

    @RequestMapping(value = "/order/updatestatus")
    @ResponseBody
    public String processOrder(
            @RequestParam(value = "orderCode", required = false) final String orderCode,
            @RequestParam(value = "orderPK", required = false) final String orderPK,
            @RequestParam(value = "toStatus", required = true) final Integer toStatus,
            final HttpServletResponse response) throws IOException
    {
        if (isSales() || isManager())
        {
            List<StoreData> stores = userFacade.getCurrentUser().getStores();
            OrderData orderData = null;
            if (StringUtils.isEmpty(orderCode))
                orderData = orderFacade.getOrderForOptionsByPK(orderPK,
                        Arrays.asList(OrderOption.BASIC));
            else
                orderData = orderFacade.getOrderForOptions(orderCode,
                        Arrays.asList(OrderOption.BASIC));

            StoreData store = orderData.getStore();
            int i = 0;
            for (StoreData storeData : stores)
            {
                if (storeData.getCode().equals(store.getCode()))
                {
                    i = 1;
                    break;
                }
            }

            if (i != 1)
            {
                response.getWriter()
                        .write("该订单属于" + store.getName() + ",无权限处理");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return null;
            }
        }
        try
        {
            if (StringUtils.isNotEmpty(orderCode))
                orderProcessFacade.processOrder(orderCode, toStatus);
            else
                orderProcessFacade.processOrder(orderPK, toStatus);
            
            return TextMapperUtils.getOrderStatusText(toStatus);
        } catch (NoQualifiedTargetStatusException e)
        {
            response.getWriter().write("订单状态不正确");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return null;
        } catch (NotFoundException e)
        {
            response.getWriter().write("未找到订单!");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

    }

    @RequestMapping(value = "/order/getorderprocessrecord")
    @ResponseBody
    public Object getOrderProcessRecord(
            @RequestParam(value = "externalId", required = false) final String externalId,
            @RequestParam(value = "startProcessTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date startProcessTime,
            @RequestParam(value = "endProcessTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date endProcessTime,
            @RequestParam(value = "orderStatus", required = true) final String orderStatus,
            @RequestParam(value = "storeCodes", required = false) final String[] storeCodes,
            final DataTableCriterias criterias) throws IOException
    {
        Map<String, String[]> sp = new HashMap<String, String[]>();
        sp.put("storeCodes", storeCodes);
        String[] externalIds = { externalId };
        sp.put("externalIds", externalIds);
        String[] orderStatuses = { orderStatus };
        sp.put("orderStatus", orderStatuses);

        DTResults r = orderProcessFacade.getOrderProcessRecords(
                startProcessTime, endProcessTime, criterias.getStart(),
                criterias.getLength(), sp);
        return r;
    }

    @RequestMapping(value = "/order/getorderprocesspage")
    public String getOrderProcessRecordPage(final Model model,
            final HttpServletResponse response) throws IOException
    {
        this.fillAuthorizedStoreInView(model);
        this.fillOrderStatusWithoutAllInView(model);
        return ControllerConstants.LTE.ORDERPROCESSRECORDPAGE;
    }

    @RequestMapping(value = "/order/exportorderprocessrecord")
    public void exportOrderProcessRecord(
            @RequestParam(value = "externalId", required = false) final String externalId,
            @RequestParam(value = "startProcessTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date startProcessTime,
            @RequestParam(value = "endProcessTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date endProcessTime,
            @RequestParam(value = "orderStatus", required = true) final String orderStatus,
            @RequestParam(value = "storeCodes", required = false) final String[] storeCodes,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final DataTableCriterias criterias) throws IOException
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
        } else
            sp.put("storeCodes", storeCodes);

        String[] externalIds = { externalId };
        sp.put("externalIds", externalIds);
        String[] orderStatuses = { orderStatus };
        sp.put("orderStatus", orderStatuses);

        String[] sheetNames = new String[1];

        sheetNames[0] = TextMapperUtils
                .getOrderStatusText(Integer.valueOf(orderStatus));
        List<Object[]> r1 = orderProcessFacade.exportOrderProcessRecords(
                startProcessTime, endProcessTime, 0, 20000, sp);

        List<List<Object[]>> dataArrays = new ArrayList<List<Object[]>>();
        dataArrays.add(r1);

        ExcelUtils.exportExcel("订单处理列表", sheetNames, dataArrays, request,
                response);
    }

    @Deprecated
    @RequestMapping(value = "/orderentry/updatestatus")
    @ResponseBody
    public String processOrderEntry(
            @RequestParam(value = "entryPK", required = true) final String entryPK,
            @RequestParam(value = "toStatus", required = true) final Integer toStatus,
            final HttpServletResponse response) throws IOException
    {
        try
        {
            orderProcessFacade.processOrderEntry(entryPK, toStatus);
        } catch (NoQualifiedTargetStatusException e)
        {
            response.sendError(500);
        } catch (NotFoundException e)
        {
            // TODO Auto-generated catch block
            response.sendError(501);
        }

        return TextMapperUtils.getOrderStatusText(toStatus);
    }

}
