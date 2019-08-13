package mine;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AlipayServlet extends javax.servlet.http.HttpServlet {

    private String appID = "2016101000655055";
    private String priKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxN1yZ6J8nu0nkuenXzRLqhSwAIQFL3wSuhziUs0H848yXC+dJDASOp0psVCagCFZE2deVhgROrYCbk2H4/iTqaBcyDJWgqpNgH54rc6WNxP09lhF/p2JtNqnZm/9offKq7WFwFO05rH8HAtsLa0HFZYcEoVFYu0rPl+5FGtJn+MaCOkG7x8G05Z9px3OsPfxmhPx0YnXba3QsRHoiuemHhD8k12AdPpoG/zV7XjtsAIPdvtJRJSK1IZABIXHn+2YqHsxdmbbJkm4z0m1IrbRDtX3vEpo5dn/WauZLkndy6Xybxdv5Pu3JrJMYdr87zJRsB+6TmXPjvUyUipykmVrXAgMBAAECggEAQCzxPtcr06KSVt8wnLKqF/2T+pP5OTWRJ3bWeZsU2XTRIR3xatWMTPCuFd6/ghKi0xokZQR8SNWyDiToRNgcHDsHs1s1UFKVPikVCV0+5cEhiAzxV04RacVy6tgAPSHnIBkIwSMC6XDbK3nQQylbTxW+OGIG6GAi7lxxr9rPuVCWbiQADeEKJ9fXHU1SXcoQ++JRxSGDoP2nye3QgoHr0PTvewQGR4yggpF01XcneiHxCw9nmxA7WSbWVfFXlsiLADjDwkf9oZvo9qTu7sRiL4kdE12kIl4cc1VRTS/9cBxUrfXtFHCu8t2iyqf4jGJyuuC+DJrZQHOiVxExgD8B+QKBgQDgo42qXRke3mt73dGsJalNLxpv0/5XDTXs0G0mLBXhZgsIic5B8I/DnY57D9KDQahTJwpmjg/D5JzmQpV9eb8e9j8IM0BQQMZd0CY8TQkIgBKB0M4FuqsMNcb7+qXYXXwuobvf0trJIFcaj6RknmWBJyEOFqdL3ef5kp6YiRRkLQKBgQDJ9PRtq3424BPI3JXxhFqk0ZN0wvWUxhrxJel87X/yZZ2Uy5o/0qBGYBl1sDP7M5LIFT69849+Gj47TU/Nd8I38Sk8BsGDryuVpxRiY5+RwifZMlWKxsK/sLcFvbywg4zxtFQ6qmEhKSDFV9pP9CuuwWWAnMdKqpRN8OUS939JkwKBgHvAnmKkNxqSXZQ3dzLm7IXg1SeWGh/K31I+4GKPFt69YIarpD0fUZPqUHvrE4XLvfdRIqGs0XKRlv4i4EfnsipUbhUOZvfPN3inGulNZxSPuaJabaUqWOC5H43hX0v69FacMuvzNSRn9JRlXaMwv6qO697fC/r3nLwY0dYmbl1hAoGBALvMCtdIl29T41HuvYf+uYN2VxZGjLMxnLANvxcqisXO9D//LIqYw+1tQ3+KwGuhQ6bHCrb8G7z5jlD6zXCVIod+vAdTiPN5GqBo462yUhnqX7+67IzF0ycJnse57hJ94byJIaID+ZoqcozP6vRaa5xvvoFgSHoIMSxhC8MdXsH9AoGAMH5ty8kpvYNLL+CLa+zBFVc/kmUGxMFcIv44svJHW3IqQNdutLHHC7Io8G/rImk8EFunkrDwYTPahZupt04uXYs3d8P3f2xGdERho6b6eb3vkyZvyFQDefhGXzdt2NVf0TQ1g65ACYwHAaBqGCQ0aGcy6wRXOOUxa4dhfTz12h4=',";
    private String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu0fFgKWgPf1ku6ii8zmYGxwP3d2cYUBU4yBQ9IqARAqx1ObLJ2FJRLNdLJm8B33dHw228c2f67+i/Hxg4tj6GG4PATZ8YLmyJvXAfqkEVIM2RpREz+GPr4FMm9CrX0kGQCt2/MCo8GFxA3XxZGKVmKmR18pX6TH1aFVXkyMGIlxbGudvR02bcJMhJ9s+OHIlytZUavTttpvZJjZ+wTp+u4viNaFpVtbo8A6Gm72izp0AHUy729eFmN51rdNcbxUUGqs7essSyDuT2nkydKypANdTr/vPvFFAyPHp2Rrm0540IAHykyHafXeoZHly/hxGArZ1cfHgvTB1e9JXbsxeVwIDAQAB";
    private String netGate = "https://openapi.alipaydev.com/gateway.do";


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //方法一
//        Map<String, String> params = new HashMap<String, String>();
//        Map<String, String[]> requestParams = request.getParameterMap();
//        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i]
//                        : valueStr + values[i] + ",";
//            }
//            params.put(name, valueStr);
//        }
//        try {
//            boolean flag = AlipaySignature.rsaCheckV1(params, pubKey, "UTF-8", "RSA2");
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }



        //方法二
        Map<String,String> params = new HashMap<String,String>();

        params.put("gmt_create", "2019-08-12 15:45:18");
        params.put("charset", "UTF-8");
        params.put("seller_email", "ncxrrq3763@sandbox.com");
        params.put("subject", "xxx");
        params.put("sign", "Kwhr+16gO9CkB0eU3FGlFzdMgZKELIpet1OZ9uk7QhB2vdJieWaskyDaTT61MK6+g6gsRL+DvYzUiQcbBZ6sZMRELRrwdtxSnWgndoSDqrrZvJndBAzaT3v8mitBvGMdNvUlMJ713yLc7TwhVDtd4NJ71niCgqLGTW8A9TatPRlo8o3c36IE4/YsFQRoQtl7kR401GePTeLax/KCZqvVtaJ0tJwp5JSTUChQlWSxl248mWwFCrja0qXUOG0boiq4VZ/7UCjE8E4EG/zAs2S5kQXYuQRAavmVRxp69MS7qv7J7iU06qGmfpY+v/xQg81zz67FafzsWjNvb6EDe691zg==");
        params.put("body", "my body");
        params.put("buyer_id", "2088102179135715");
        params.put("invoice_amount", "0.01");
        params.put("notify_id", "2019081200222154519035711000474693");
        params.put("fund_bill_list", "[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]");
        params.put("notify_type", "trade_status_sync");
        params.put("trade_status", "TRADE_SUCCESS");
        params.put("receipt_amount", "0.01");
        params.put("app_id", "2016101000655055");
        params.put("buyer_pay_amount", "0.01");
        params.put("sign_type", "RSA2");
        params.put("seller_id", "2088102178971635");
        params.put("gmt_payment", "2019-08-12 15:45:19");
        params.put("notify_time", "2019-08-12 15:51:07");
        params.put("version", "1.0");
        params.put("out_trade_no", "1565595816574");
        params.put("total_amount", "0.01");
        params.put("trade_no", "2019081222001435711000050990");
        params.put("auth_app_id", "2016101000655055");
        params.put("buyer_logon_id", "aff***@sandbox.com");
        params.put("point_amount", "0.00");

        try {
            boolean flag = AlipaySignature.rsaCheckV1(params, pubKey, "UTF-8","RSA2");
            if (flag) {
                System.out.println("ok");
            } else {
                System.out.println("Failure");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        AlipayClient alipayClient = new DefaultAlipayClient(netGate, appID, priKey, "json", "UTF-8", pubKey, "RSA2");
        AlipayTradeAppPayRequest appPayRequest = new AlipayTradeAppPayRequest();


        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("my body");
        model.setSubject("xxx");
        model.setOutTradeNo(Long.toString(System.currentTimeMillis()));
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");

        appPayRequest.setBizModel(model);
        appPayRequest.setNotifyUrl("http://pc.yssp888.com/test.php");

        try {
            AlipayTradeAppPayResponse appPayResponse = alipayClient.sdkExecute(appPayRequest);
            if (appPayResponse.isSuccess()) {
                response.getWriter().println(appPayResponse.getBody());
            } else {
                response.getWriter().println("Failure");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
