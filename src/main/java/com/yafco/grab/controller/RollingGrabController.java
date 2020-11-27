package com.yafco.grab.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = "/ngmss-mem/dms")
public class RollingGrabController {
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
    @RequestMapping(value = "/settleResp/queryTodoSettleReqPage", method = RequestMethod.POST, produces = { "application/json" })
    @ResponseBody
    public Map<String, Object> queryTodosettleRespPage(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException {
        File filePath = new File(detectWebRootPath()
                + "\\rollingData\\queryTodoSettleReqPage.txt");
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
    
    @RequestMapping(value = "/settleResp/settleReqDetail", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public Map<String, Object> settleRespDetail(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException, InterruptedException {

        String settleReqId = request.getParameter("settleReqId");
        File filePath = new File(detectWebRootPath()
                + "\\rollingData\\" + settleReqId + ".txt");
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
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    
    @RequestMapping(value = "/settleReq/getClassList", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public Map<String, Object> getClassList(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException {
        File filePath = new File(detectWebRootPath()
                + "\\rollingData\\getClassList.txt");
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

    @PostMapping(value = "/settleReq/getCustomers", produces = { "application/json" })
    @ResponseBody
    public Map<String, Object> getCustomers(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException {
        File filePath = new File(detectWebRootPath()
                + "\\rollingData\\getCustList.txt");
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

    @PostMapping(value = "/settleResp/doResponse", produces = { "application/json" })
    @ResponseBody
    public Map<String, Object> doResponse(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException {
        File filePath = new File(detectWebRootPath()
                + "\\rollingData\\doResponse.txt");
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

    @PostMapping(value = "/settleResp/getSettleRespBatchQueryPage", produces = { "application/json" })
    @ResponseBody
    public Map<String, Object> getSettleRespBatchQueryPage(HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {
        File filePath = new File(detectWebRootPath()
                + "\\rollingData\\getSettleRespBatchQueryPage.txt");
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

}
