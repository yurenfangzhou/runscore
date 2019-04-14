package me.zohar.lottery.platform.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import me.zohar.lottery.dictconfig.DictHolder;
import me.zohar.lottery.platform.domain.PlatformOrder;

@Data
public class MyWaitReceivingOrderVO {
	
	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 收款渠道
	 */
	private String gatheringChannelCode;
	
	private String gatheringChannelName;

	/**
	 * 收款金额
	 */
	private Double gatheringAmount;

	/**
	 * 提交时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date submitTime;

	/**
	 * 订单状态
	 */
	private String orderState;

	private String orderStateName;

	public static List<MyWaitReceivingOrderVO> convertFor(List<PlatformOrder> platformOrders) {
		if (CollectionUtil.isEmpty(platformOrders)) {
			return new ArrayList<>();
		}
		List<MyWaitReceivingOrderVO> vos = new ArrayList<>();
		for (PlatformOrder platformOrder : platformOrders) {
			vos.add(convertFor(platformOrder));
		}
		return vos;
	}

	public static MyWaitReceivingOrderVO convertFor(PlatformOrder platformOrder) {
		if (platformOrder == null) {
			return null;
		}
		MyWaitReceivingOrderVO vo = new MyWaitReceivingOrderVO();
		BeanUtils.copyProperties(platformOrder, vo);
		vo.setGatheringChannelName(DictHolder.getDictItemName("gatheringChannel", vo.getGatheringChannelCode()));
		vo.setOrderStateName(DictHolder.getDictItemName("platformOrderState", vo.getOrderState()));
		return vo;
	}

}