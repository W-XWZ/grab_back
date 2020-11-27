package com.yafco.grab.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yafco.grab.entity.DopickRequest;
import com.yafco.grab.entity.SuccessItem;
import com.yafco.grab.entity.PickEty;

@Controller
public class NewGrabController {
	static public String detectWebRootPath() {
		try {
			String path = NewGrabController.class.getResource("/").toURI()
					.getPath();
			return new File(path)
					.getCanonicalPath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/ngmss-mem/member/option/getRoundList", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> getRoundList(HttpServletRequest request,
											HttpServletResponse response) throws IOException {
		File filePath = new File(detectWebRootPath()
				+ "\\newData\\getRoundList.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(filePath), "UTF-8"));
		if (!reader.ready()) {
			System.out.println("文件流暂时无法读取");
		}
		String line = null;
		String res = "";
		while ((line = reader.readLine()) != null) {
			res += line;
		}
		reader.close();
		Map<String, Object> result = JSON.parseObject(res);

		return result;
	}

	@RequestMapping(value = "/getTime", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> nowTime(HttpServletRequest request,
									   HttpServletResponse response) throws IOException {
	    File filePath = new File(detectWebRootPath()
                + "\\newData\\nowTime.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), "UTF-8"));
        if (!reader.ready()) {
            System.out.println("文件流暂时无法读取");
        }
        String line = null;
        String res = "";
        while ((line = reader.readLine()) != null) {
            res += line;
        }
        reader.close();
        Map<String, Object> result = JSON.parseObject(res);
		return result;
	}

	@RequestMapping(value = "/ngmss-mem/member/option/getSheetList", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> getSheetList(HttpServletRequest request,
											@RequestBody Map<String, Object> param, HttpServletResponse response)
			throws IOException {
		String classId = (String) param.get("classId");
		String filePath = null;

		filePath = detectWebRootPath() + "\\newData\\getSheetList-" + classId + ".txt";

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(filePath), "UTF-8"));
		if (!reader.ready()) {
			System.out.println("文件流暂时无法读取");
		}
		String line = null;
		String res = "";
		while ((line = reader.readLine()) != null) {
			res += line;
		}
		reader.close();
		Map<String, Object> result = JSON.parseObject(res);

		return result;

	}

	@RequestMapping(value = "/ngmss-mem/member/option/doPick", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> doPick(HttpServletRequest request,
									  @RequestBody DopickRequest dopickRequest,
									  HttpServletResponse response) throws IOException {

		Map<String, Object> finalResult = new HashMap<String, Object>();
		finalResult.put("code", 200);
		finalResult.put("message", "Ok");
		Map<String, Object> result = new HashMap<String, Object>();
		List<SuccessItem> successItems = new ArrayList<SuccessItem>();
		List<SuccessItem> failItems = new ArrayList<SuccessItem>();
		List<PickEty> settlePicks = dopickRequest.getSettlePicks();
		for (PickEty pickEty : settlePicks) {
			// 随机产生成功失败
			Boolean successFlag = Math.random() > 0.5 ? true : false;
			if (successFlag) {
				SuccessItem successItem = new SuccessItem();
				Integer settleReqId = pickEty.getSettleReqId();
				Integer qty = pickEty.getQty();
				// 成功数量随机
				Integer realQty = (int) Math.round(Math.random() * qty);
				successItem.setClassId(dopickRequest.getClassId());
				successItem.setQty(qty);
				successItem.setRealQty(realQty);
				successItem.setSettleReqId(settleReqId);
				successItems.add(successItem);
			} else {
				Integer settleReqId = pickEty.getSettleReqId();
				SuccessItem successItem = new SuccessItem();
				successItem.setSettleReqId(settleReqId);
				failItems.add(successItem);
			}
		}
		result.put("successItems", successItems);
		result.put("failItems", failItems);
		finalResult.put("result", result);
		return finalResult;
	}

}
