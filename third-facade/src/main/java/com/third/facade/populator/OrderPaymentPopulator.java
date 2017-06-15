package com.third.facade.populator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.third.facade.data.OrderData;
import com.third.facade.data.PaymentData;
import com.third.model.OrderModel;
import com.third.model.PaymentModel;


public class OrderPaymentPopulator implements Populator<OrderModel, OrderData>
{
	private static final Logger LOG = Logger.getLogger(OrderPaymentPopulator.class);

	private PaymentDataPopulator paymentDataPopulator;

	@Override
	public void populate(OrderModel source, OrderData target)
	{
		List<PaymentData> payments = new ArrayList<PaymentData>();
		List<PaymentModel> paymentModels = (List<PaymentModel>) source.getPayments();

		if (!CollectionUtils.isEmpty(paymentModels))
			paymentModels.stream().filter( p -> p!=null ).forEach(p -> {
				PaymentData paymentData = new PaymentData();
				paymentDataPopulator.populate(p, paymentData);
				payments.add(paymentData);
			});

		target.setPayments(payments);
	}

	

	public void setPaymentDataPopulator(PaymentDataPopulator paymentDataPopulator)
	{
		this.paymentDataPopulator = paymentDataPopulator;
	}

}
