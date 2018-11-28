package com.third.facade.testdata.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;

import com.third.core.constants.CoreConstants;
import com.third.dao.user.UserDao;
import com.third.model.AddressModel;
import com.third.model.CityModel;
import com.third.model.CustomerModel;
import com.third.model.RegionModel;
import com.third.model.SourceModel;
import com.third.model.StoreModel;
import com.third.model.UserModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.SourceService;
import com.third.service.location.I18NService;
import com.third.service.store.StoreService;
import com.third.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerSourceDataBuilder implements DataBuilder {

    private String filename;

    @Autowired
    private SourceService sourceService;

    @Autowired
    private StoreService storeService;

    private List<SourceModel> sourceModels = new ArrayList<SourceModel>();
    ;

    @Override
    public void buildData() {
        List<String[]> results = ExcelFileReader.readFile(filename, 3);
        results.remove(0);
        results.forEach(r -> {
            SourceModel source = new SourceModel();
            source.setType(r[2]);
            source.setName(r[1]);
            sourceService.createSource(source);
        });

        buildStore(sourceService.getSources());
    }


    public void buildStore(List<SourceModel> sources) {
        List<StoreModel> stores = storeService.getAllStores();
        stores.forEach(s -> {
            StoreModel store = s;
            s.setSources(sources);
            storeService.saveStore(store);
        });
    }


    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }

}
