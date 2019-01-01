package org.netsharp.wx.pa.response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;

//baidu api:http://developer.baidu.com/map/index.php?title=webapi/guide/webservice-geocoding
//http://api.map.baidu.com/geocoder/v2/?ak=您的密钥&callback=renderOption&output=json&address=百度大厦&city=北京市
public class WeixinLocationResponse implements IWeixinResponsor {

    protected static Log          logger      = LogFactory.getLog(WeixinLocationResponse.class.getSimpleName());

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public void setKey(String value) {

    }

    @Override
    public boolean validate(RequestMessage request) {
        // 禁用location处理器
//        if (request instanceof LocationEvent) {
//            return true;
//        }
        return false;
    }

    @Override
    public ResponseMessage response(RequestMessage request)  {
        try {
            return this.doResponse(request);
        } catch (Exception ex) {
            logger.error("", ex);
            return null;
        }
    }

    public ResponseMessage doResponse(RequestMessage request)  {

//        LocationEvent location = (LocationEvent) request;
//
//        //
//        GeocoderRequest geoRequest = new GeocoderRequest();
//        {
//            String address = String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude());
//            geoRequest.setLocation(address);
//        }

//        String address = "";
//        try {
//            GeocoderResponse response = geoRequest.invoke();
//            address = response.getResult().getFormatted_address();
//        } catch (Exception ex) {
//            logger.error(ex);
//        }
//
//        Fans fans = fansService.byOpenId(request.getFromUserName());
//        if (fans != null) {
//            fans.setLbsLatitude(location.getLatitude());
//            fans.setLbsLongitude(location.getLongitude());
//            fans.setLbsPrecision(location.getPrecision());
//            fans.setLbsDateTime(new Date());
//            fans.setAddress(address);
//
//            fans.toPersist();
//
//            fansService.save(fans);
//        }

        return null;
    }
}
