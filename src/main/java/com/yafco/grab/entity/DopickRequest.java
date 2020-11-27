package com.yafco.grab.entity;

import java.util.List;

public class DopickRequest {
	private List<PickEty> settlePicks;
	private String classId;
	private String commodityId;
	
	public List<PickEty> getSettlePicks() {
		return settlePicks;
	}

	public void setSettlePicks(List<PickEty> settlePicks) {
		this.settlePicks = settlePicks;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

}
//"settlePicks":[
//               {
//                   "settleReqId":16306,
//                   "qty":80,
//                   "sFirmId":"0183",
//                   "sCustomerId":"35203423",
//                   "whId":"0317"
//               },
//               {
//                   "settleReqId":13463,
//                   "qty":73,
//                   "sFirmId":"0055",
//                   "sCustomerId":"34245895",
//                   "whId":"0317"
//               },
//               {
//                   "settleReqId":16307,
//                   "qty":60,
//                   "sFirmId":"0183",
//                   "sCustomerId":"35203423",
//                   "whId":"0317"
//               },
//               {
//                   "settleReqId":16308,
//                   "qty":60,
//                   "sFirmId":"0183",
//                   "sCustomerId":"35203423",
//                   "whId":"0317"
//               },
//               {
//                   "settleReqId":13466,
//                   "qty":50,
//                   "sFirmId":"0055",
//                   "sCustomerId":"34245895",
//                   "whId":"0317"
//               },
//               {
//                   "settleReqId":13778,
//                   "qty":6,
//                   "sFirmId":"0055",
//                   "sCustomerId":"35358784",
//                   "whId":"1508"
//               }
//           ],
//           "classId":"SF",
//           "commodityId":"SF905"