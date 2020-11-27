package com.yafco.grab.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;






import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;






@RestController
@RequestMapping(value = "/grabController")
public class CommonController {
    public static Long request_time = 0L;
    public static void main(String[] args){
        HttpUtil.doGet("http://localhost:8080/grab_backend/grab/grabController/clearTime");
    }

    @RequestMapping(value = "/getRoundList", method = RequestMethod.POST)
    public String getRoundList(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response) throws IOException {
        File file = new File("G:\\document\\html\\grab_backend\\WebContent\\getRoundList.txt");
        FileInputStream os = new FileInputStream(file);
        String ret = "";
        while(true){
            int c = os.read();
            if(c==-1){
                break;
            }
            ret +=(char)c;
        }
        //ret = Encoder.encode(ret, "utf-8");
        return ret;
    }

    @RequestMapping(value = "/clearTime", method=RequestMethod.GET)
    public void clearTime(){
        request_time = 0L;
    }

    @RequestMapping(value = "/nowTime", method = RequestMethod.POST)
    public String nowTime(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response) throws IOException {
        File file = new File("G:\\document\\html\\grab_backend\\WebContent\\nowTime.txt");
        FileInputStream os = new FileInputStream(file);
        String ret = "";
        while(true){
            int c = os.read();
            if(c==-1){
                break;
            }
            ret +=(char)c;
        }
//		String resultString;
//		if(request_time == 0L){
//			resultString =ret;
//		 }else{
//			 resultString =  String.valueOf(System.currentTimeMillis() - request_time + Long.valueOf(ret));
//		 }
//		request_time = System.currentTimeMillis();
        //ret = Encoder.encode(ret, "utf-8");
        return ret;
    }
    @RequestMapping(value = "/getSheetList", method = RequestMethod.POST)
    public void getSheetList(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response) throws IOException {

        File file = new File("G:\\document\\html\\grab_backend\\WebContent\\getSheetList.txt");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setHeader("Transfer-Encoding", "chunked");
        OutputStream os = response.getOutputStream();


        os.write(readFromFile("G:\\document\\html\\grab_backend\\WebContent\\getSheetList.txt").getBytes());
        //response.setHeader("Transfer-Encoding", "chunked");

    }

    public String getParm(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        String line = null;
        try {
            //接收request数据流，并指定编码格式接收
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/doPick", method = RequestMethod.POST)
    public void doPick(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response) throws IOException {

        List<NameValuePair> pair_list = URLEncodedUtils.parse(getParm(request), Charset.forName("UTF-8") );
        Map<String,Object> pair_map = new HashMap<String,Object>();
        for(NameValuePair pair : pair_list){

            pair_map.put(pair.getName(), pair.getValue());

        }

        List<Map<String,Object>> success_list = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> fail_list = new ArrayList<Map<String,Object>>() ;
        for(int i=0;;i++){
            Map<String,Object> item = new HashMap<String,Object>();
            if(pair_map.get("settlePicks["+i+"].qty")==null){
                break;
            }
            int qty = Integer.parseInt((String)pair_map.get("settlePicks["+i+"].qty")) ;
            String settleReqId = (String) pair_map.get("settlePicks["+i+"].settleReqId");

            String classId = "CF";
            String whId = (String) pair_map.get("settlePicks["+i+"].whId");
            int realQty = qty;
            String message = "null";
            item.put("qty", qty);
            item.put("settleReqId", settleReqId);
            item.put("classId", classId);
            item.put("whId", whId);
            item.put("realQty", realQty);
            item.put("message", message);
            double ran = Math.random();
            if(ran>0.7){
                fail_list.add(item);
            }else{
                success_list.add(item);
            }

        }
        Map<String,Object> ret_map = new HashMap<String,Object>();
        ret_map.put("successItems", success_list);
        ret_map.put("failItems", fail_list);
        String ret_string = JSON.toJSONString(ret_map);
        File file = new File("G:\\document\\html\\grab_backend\\WebContent\\dopick.txt");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        //response.setHeader("Transfer-Encoding", "chunked");
        OutputStream os = response.getOutputStream();
        os.write(ret_string.getBytes());
        //response.setHeader("Transfer-Encoding", "chunked");

    }

    /**
     * ���ļ��ж�ȡ�ı�
     */
    public String readFromFile(String filename) {

        BufferedInputStream bufferedInput = null;
        byte[] buffer = new byte[1024];

        try {

            //����BufferedInputStream ����
            bufferedInput = new BufferedInputStream(new FileInputStream(filename));

            int bytesRead = 0;

            //���ļ��а��ֽڶ�ȡ���ݣ����ļ�β��ʱread����������-1
            StringBuilder  result = new StringBuilder();
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {

                //����ȡ���ֽ�תΪ�ַ�������
                String chunk = new String(buffer, 0, bytesRead);

                result = result.append(chunk);
            }
            return result.toString();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //�ر� BufferedInputStream
            try {
                if (bufferedInput != null)
                    bufferedInput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }
}
