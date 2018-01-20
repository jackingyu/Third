package com.third.facade.populator;

import com.third.facade.data.PaymentData;
import com.third.facade.data.TextMapper;
import com.third.model.PaymentModel;

public class PaymentDataPopulator
		implements Populator<PaymentModel, PaymentData> {

	@Override
	public void populate(PaymentModel source, PaymentData target)
	{
		target.setPk(source.getPk());
		target.setAmount(source.getAmount());
		target.setOrderCode(source.getOrder().getCode());
		target.setOrderPK(source.getOrder().getPk());
		target.setPaidTime(source.getPaidTime());
		target.setPaymentType(source.getPaymentType());
		target.setPaymentMethod(source.getPaymentMethod());

		target.setPaymentTypeText(
				TextMapper.PaymentType.get(source.getPaymentType()));
		target.setPaymentMethodText(
				TextMapper.PaymentMethod.get(source.getPaymentMethod()));
	}

}
