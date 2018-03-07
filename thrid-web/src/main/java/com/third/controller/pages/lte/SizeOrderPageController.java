package com.third.controller.pages.lte;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.constants.CoreConstants;
import com.third.exceptions.ProductNotFoundException;
import com.third.facade.data.ComboboxData;
import com.third.facade.data.OrderData;
import com.third.facade.data.OrderEntryData;
import com.third.facade.data.ProductData;
import com.third.facade.data.SizeAttributeData;
import com.third.facade.data.SizeAttributeGroupData;
import com.third.facade.data.TextMapper;
import com.third.facade.data.UserData;
import com.third.facade.order.OrderFacade;
import com.third.facade.populator.option.OrderOption;
import com.third.facade.user.UserFacade;
import com.third.facade.utils.TextMapperUtils;

@Controller
public class SizeOrderPageController extends AbstractPageController {
    private static final Logger LOG = Logger.getLogger(
            com.third.controller.pages.lte.SizeOrderPageController.class);
    private static final String ORDER_CODE_PATH_VARIABLE_PATTERN = "/{orderCode:.*}";
    private static final String ORDER_ENTRYPK_PATH_VARIABLE_PATTERN = "/{orderEntryPK:.*}";

    @Resource(name = "orderFacade")
    private OrderFacade orderFacade;

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @RequestMapping(value = "/orderentry/createorderentrypage/"
            + ORDER_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
    public String getCreateSizeOrderPage(
            @PathVariable(value = "orderCode") final String orderCode,
            @RequestParam(value = "itemCategory", required = false) final String itemCategory,
            Model model)
    {
        Map<String, SizeAttributeGroupData> sizeDatas = null;

        sizeDatas = orderFacade
                .getSizeAttributes(Integer.valueOf(itemCategory));
        // OrderEntryData entry = orderFacade.getSizeDatas(entryPK);
        // model.addAttribute("itemCategory", entry.getItemCategory());
        // sizeDatas = entry.getSizeDatas();

        // set up item category

        OrderData order = orderFacade.getOrderForOptions(orderCode,
                Arrays.asList(OrderOption.BASIC));

        OrderEntryData orderEntry = new OrderEntryData();
        orderEntry.setTryDate(order.getTryDate());
        orderEntry.setDeliveryDate(order.getDeliveryDate());
        orderEntry.setCustomerName(order.getCustomerName());
        orderEntry.setSizeDatas(sizeDatas);
        orderEntry.setOrderCode(order.getOrderCode());
        orderEntry.setItemCategoryText(
                TextMapperUtils.getItemCategoryText(itemCategory));
        orderEntry.setItemCategory(itemCategory);
        orderEntry.setOrderPK(order.getPk());
        model.addAttribute("message1", "新建" + orderEntry.getItemCategoryText());
        model.addAttribute("itemCategories",
                TextMapperUtils.getItemCategories());
        model.addAttribute("orderEntry", orderEntry);
        model.addAttribute("searchCategory", TextMapper.ItemCategory2Category
                .get(orderEntry.getItemCategory()));

        // this is for enable create size order button,for create page ,default
        // value is true
        model.addAttribute("editable", true);
        model.addAttribute("enableSaveBtn", true);
        fillProductGroupsInModel(model);
        fillStore2View(model, order.getStore().getCode());
        fillDeisgnerInModel(model, order.getStore().getCode(),
                StringUtils.EMPTY);

        return ControllerConstants.LTE.ORDERENTRYDETAILPAGE;
    }

    @RequestMapping(value = "/orderentry/modifyentrypage/"
            + ORDER_ENTRYPK_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
    public String modifySizeOrderPage(
            @PathVariable(value = "orderEntryPK") final String orderEntryPK,
            @ModelAttribute("message") final String message, Model model)
    {

        Map<String, SizeAttributeGroupData> sizeDatas = null;

        // set up item category
        OrderEntryData orderEntry = orderFacade.getOrderEntry(orderEntryPK);

        if (isSales())
        {
            UserData user = userFacade.getCurrentUser();

            if (!orderEntry.getSalesperson().equals(user))
                return ControllerConstants.LTE.NOAUTHPAGE;
        }

        if (!checkStoreAuthorization(orderEntry.getStore().getCode()))
            return ControllerConstants.LTE.NOAUTHPAGE;

        // set size details data
        OrderEntryData entry = orderFacade.getSizeDatas(orderEntry.getPk());
        orderEntry.setSizeDatas(entry.getSizeDatas());

        model.addAttribute("itemCategories",
                TextMapperUtils.getItemCategories());

        model.addAttribute("orderEntry", orderEntry);
        model.addAttribute("message1", "修改" + orderEntry.getItemCategoryText());
        model.addAttribute("statusText", "量身单状态:" + orderEntry.getStatusText());
        model.addAttribute("enableSaveBtn", isEditable(orderEntry.getStatus()));
        model.addAttribute("editable", orderEntry.getStatus()
                .equals(CoreConstants.OrderStatus.NEW.toString()));

        if (StringUtils.isNotEmpty(message))
            model.addAttribute("message", message);

        model.addAttribute("searchCategory", TextMapper.ItemCategory2Category
                .get(orderEntry.getItemCategory()));

        fillProductGroupsInModel(model);
        fillStore2View(model, orderEntry.getStore().getCode());
        fillDeisgnerInModel(model, orderEntry.getStore().getCode(),
                orderEntry.getDesigner().getUserId());
        return ControllerConstants.LTE.ORDERENTRYDETAILPAGE;
    }

    @RequestMapping(value = "/orderentry/scanpage", method = RequestMethod.GET)
    public String scanSizeOrderPage()
    {
        return ControllerConstants.LTE.ORDERENTRYSCANPAGE;
    }

    @RequestMapping(value = "/orderentry/updateactualtrydate", method = RequestMethod.POST)
    @ResponseBody
    public String updateActualTryDate(
            @RequestParam(value = "externalId", required = false) final String externalId,
            @RequestParam(value = "entryPK", required = false) final String entryPK)
    {
        OrderEntryData entry = new OrderEntryData();
        entry.setActualTryDate(new Date());
        entry.setExternalId(externalId);
        entry.setPk(entryPK);

        orderFacade.updateOrderEntryActualTryDate(entry);

        return "";
    }

    @RequestMapping(value = "/orderentry/saveorderentry")
    public String saveOrderEntry(
            @RequestParam(value = "orderCode", required = false) final String orderCode,
            @RequestParam(value = "orderPK", required = false) final String orderPK,
            @RequestParam(value = "entryPK", required = false) final String entryPK,
            @RequestParam(value = "itemCategory", required = false) final String itemCategory,
            @RequestParam(value = "style", required = false) final String style,
            @RequestParam(value = "productCode", required = true) final String productCode,
            @RequestParam(value = "productTitle", required = false) final String productTitle,
            @RequestParam(value = "quantity", required = false) final Integer quantity,
            @RequestParam(value = "designer", required = false) final String designer,
            @RequestParam(value = "customerName", required = false) final String customerName,
            @RequestParam(value = "externalId", required = false) final String externalId,
            @RequestParam(value = "deliveryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date deliveryDate,
            @RequestParam(value = "sizeDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date sizeDate,
            @RequestParam(value = "tryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date tryDate,
            @RequestParam(value = "sizeDetails", required = false) final String sizeDetails,
            @RequestParam(value = "comment", required = false) final String comment,
            RedirectAttributes attr)
    {
        // TODO:校验量身单状态,判断是否允许修改,如果是新建的,则只允许销售员修改
        // 如果是财务审核通过,只允许裁床修改,需要考虑是否允许admin修改
        // --通过在页面上隐藏按钮已经实现上述的点,允许admin修改
        OrderEntryData orderEntryData = new OrderEntryData();
        orderEntryData.setOrderCode(orderCode);
        orderEntryData.setPk(entryPK);
        orderEntryData.setItemCategory(itemCategory);
        orderEntryData.setStyle(style);
        orderEntryData.setProductTitle(productTitle);
        orderEntryData.setQuantity(1);
        orderEntryData.setOrderPK(orderPK);
        UserData user = new UserData();
        user.setUserId(designer);
        orderEntryData.setDesigner(user);
        orderEntryData.setDeliveryDate(deliveryDate);
        orderEntryData.setSizeDate(sizeDate);
        orderEntryData.setTryDate(tryDate);
        orderEntryData.setSizeDetails(sizeDetails);
        orderEntryData.setComment(comment);
        orderEntryData.setCustomerName(customerName);
        orderEntryData.setSizeDetails(sizeDetails);
        orderEntryData.setExternalId(externalId);

        ProductData product = new ProductData();
        product.setCode(productCode);
        orderEntryData.setProduct(product);

        if (StringUtils.isNotBlank(sizeDetails))
        {
            List<SizeAttributeData> sizeDatas = JSON.parseArray(sizeDetails,
                    SizeAttributeData.class);
        }

        try
        {
            if (StringUtils.isBlank(entryPK))
            {
                for (int i = 0; i < quantity; i++)
                    orderFacade.createOrderEntry(orderEntryData);
            } else
                orderFacade.updateOrderEntry(orderEntryData);
        } catch (ProductNotFoundException ex)
        {
            return ControllerConstants.LTE.ERRORPAGE;
        }
        attr.addFlashAttribute("message", "保存成功!");
        return REDIRECT_PREFIX + "/orderentry/modifyentrypage/"
                + orderEntryData.getPk();
    }

    @RequestMapping(value = "/sizeorder/uploadsizeimage", method = RequestMethod.POST)
    public @ResponseBody Object uploadSizeImage(
            @RequestParam(value = "fileUpload") final MultipartFile file,
            @RequestParam(value = "entryPK", required = false) final String entryPK,
            final HttpServletRequest request)
    {
        return orderFacade.uploadMediaForOrderEntry(entryPK, file);
    }

    protected boolean isEditable(final String statusString)
    {
        int status = Integer.valueOf(statusString);

        if (isAdmin())
            return true;
        if (isSales()||isManager())
            if (CoreConstants.OrderStatus.NEW.equals(status))
                return true;
            else
                return false;

        if (isFinicial())
            return false;

        if (isFactory())
            if (CoreConstants.OrderStatus.FINICIAL_APPROVE.equals(status))
                return true;
            else
                return false;

        return false;
    }

    protected void fillDeisgnerInModel(final Model model,
            final String storeCode, final String userId)
    {
        List<UserData> userDatas = userFacade.getDesignerForStore(storeCode);
        List<ComboboxData> comboboxDatas = new ArrayList<ComboboxData>();
        for (int i = 0; i < userDatas.size(); i++)
        {
            ComboboxData comboboxData = new ComboboxData();
            comboboxData.setCode(userDatas.get(i).getUserId());
            comboboxData.setText(userDatas.get(i).getName());
            // below code is for multiple select
            // if(StringUtils.isEmpty(userId)&&i==0)
            // comboboxData.setSelected(true);
            //
            // if(StringUtils.isNotEmpty(userId)&&comboboxData.getCode().equals(userId))
            // comboboxData.setSelected(true);

            comboboxDatas.add(comboboxData);
        }

        model.addAttribute("designers", comboboxDatas);
    }

}
