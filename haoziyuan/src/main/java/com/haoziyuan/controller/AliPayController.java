//package com.haoziyuan.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipayTradePrecreateRequest;
//import com.alipay.api.response.AlipayTradePrecreateResponse;
//import com.haoziyuan.constant.AlipayConfig;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Fang WeiPing
// * @date 2020/6/28 16:46
// */
//@Controller
//public class AliPayController {
//
//    @GetMapping(value = "/alipayQrcode")
//    @ResponseBody
//    public void  alipayQrcode(@RequestParam(required = false) String token, @RequestParam Long totalprice,
//                              @RequestParam String orderNo, HttpServletRequest request, HttpServletResponse responses) throws AlipayApiException {
//        //获得初始化的AlipayClient
//        AlipayClient alipayClient = new DefaultAlipayClient(OrderInfoUtil.requestUrl, OrderInfoUtil.APP_ID, OrderInfoUtil.APP_PRIVATE_KEY, "json", AlipayUtil.charset, OrderInfoUtil.ALIPAY_PUBLIC_KEY, AlipayUtil.sign_type);
//        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类
//
//        Map<String,Object> map=new HashMap<>();
//        //设置回调地址
//        request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
//        //根据订单号查询订单信息
//        Map<String,Object> maps=new HashMap<>();
//        maps.put("out_trade_no",orderNo);
//        maps.put("total_amount",totalprice+"");
//        maps.put("subject","科谊达微课");
//        maps.put("store_id","NJ_001");
//        maps.put("timeout_express","90m");
//        //把订单信息转换为json对象的字符串
//        String postdata = JSONObject.fromObject(maps).toString();
//        request.setBizContent(postdata);
//        AlipayTradePrecreateResponse response = alipayClient.execute(request);
//        String body = response.getBody();
//        JSONObject jsonObject = JSONObject.fromObject(body);
//        String qr_code = jsonObject.getJSONObject("alipay_trade_precreate_response").getString("qr_code");
//        //流输出
//        ServletOutputStream sos = null;
//        try {
//            sos = responses.getOutputStream();
//            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
//            // 指定编码格式
//            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//            // 指定纠错级别(L--7%,M--15%,Q--25%,H--30%)
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//            // 编码内容,编码类型(这里指定为二维码),生成图片宽度,生成图片高度,设置参数
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(qr_code, BarcodeFormat.QR_CODE, 200, 200, hints);
//            //生成二维码
//            MatrixToImageWriter.writeToStream(bitMatrix, "png", sos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
