package com.third.facade.populator;

import org.apache.commons.lang3.StringUtils;

import com.third.facade.data.OrderEntryData;
import com.third.facade.data.ProductData;
import com.third.facade.data.StoreData;
import com.third.facade.data.TextMapper;
import com.third.facade.data.UserData;
import com.third.facade.utils.DateUtils;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.OrderEntryModel;
import com.third.model.UserModel;

public class OrderEntryDataPopulator
        implements Populator<OrderEntryModel, OrderEntryData> {

    private UserDataPopulator userDataPopulator;
    private StoreDataPopulator storeDataPopulator;
    private ProductDataPopulator productDataPopulator;

    @Override
    public void populate(OrderEntryModel source, OrderEntryData target)
    {
        target.setCode(source.getCode());
        target.setExternalId(source.getExternalId());
        target.setDeliveryDate(source.getDeliveryDate());
        target.setEntryNo(source.getEntryNo());
        target.setItemCategory(source.getItemCategory());
        target.setItemCategoryText(
                TextMapper.ItemCategory.get(source.getItemCategory()));
        target.setModificationTime(source.getModificationTime());
        target.setCreateTime(source.getCreateTime());
        target.setPk(source.getPk());
        target.setProductTitle(source.getProductTitle());
        target.setStyle(source.getStyle());
        target.setQuantity(source.getQuantity());
        target.setSizeDate(source.getSizeDate());
        target.setTryDate(source.getTryDate());
        target.setActualTryDate(source.getActualTryDate());
        target.setComment(source.getComment());
        target.setOrderCode(source.getOrder().getCode());
        target.setOrderPK(source.getOrder().getPk());
        target.setCustomerName(StringUtils.isBlank(source.getCustomerName())
                ? source.getOrder().getCustomerName()
                : source.getCustomerName());
        target.setSizeDetails(source.getSizeDetails());
        target.setSizeImageUrl(source.getSizeImage());
        target.setStatus(source.getStatus().toString());

        UserModel salesperson = source.getOrder().getSalesperson();
        UserData salespersonData = new UserData();
        userDataPopulator.populate(salesperson, salespersonData);
        target.setSalesperson(salespersonData);

        UserModel designer = source.getDesigner();

        UserData designerData = new UserData();
        if (designer != null)
        {
            userDataPopulator.populate(designer, designerData);
        }
        target.setDesigner(designerData);

        if (target.getStatus() != null)
            target.setStatusText(
                    TextMapperUtils.getOrderStatusText(source.getStatus()));

        StoreData store = new StoreData();
        storeDataPopulator.populate(source.getStore(), store);
        target.setStore(store);

        if (source.getCreatedBy() != null)
        {
            UserData user = new UserData();
            userDataPopulator.populate(source.getCreatedBy(), user);

            target.setCreateBy(user);
        }

        ProductData product = new ProductData();

        productDataPopulator.populate(source.getProduct(), product);

        target.setProduct(product);
    }

    public void setUserDataPopulator(UserDataPopulator userDataPopulator)
    {
        this.userDataPopulator = userDataPopulator;
    }

    public void setStoreDataPopulator(StoreDataPopulator storeDataPopulator)
    {
        this.storeDataPopulator = storeDataPopulator;
    }

    public void setProductDataPopulator(
            ProductDataPopulator productDataPopulator)
    {
        this.productDataPopulator = productDataPopulator;
    }

}
