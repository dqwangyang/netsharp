//package org.netsharp. weixin.pay;
//
//import org.junit.Test;
//import org.netsharp.wx.pa.sdk.WeixinAccountConfig;
//import org.netsharp.wx.pa.sdk.mch.PrePayRequest;
//import org.netsharp.wx.pa.sdk.mch.PrePayResponse;
//import org.netsharp.wx.pa.sdk.pay.PayHelp;
//import org.netsharp.wx.pa.sdk.pay.PayOrder;
//
//public class PrePayTest {
//	
//	@Test
//	public void run(){
//		
//		PrePayRequest ppr = new PrePayRequest();
//		{
//			  PayOrder po = new PayOrder();
//		      {
//		            po.setCode( PayHelp.getTs() );
//		            po.setAttach( "MallOrder" );
//		            po.setBody(  "test" );
//		            po.setAmount( 0.01d);
//		            po.setMch_id(WeixinAccountConfig.getMchId());
//		            po.setClientIp("121.40.86.55");
//		            po.setNotify_Url(WeixinAccountConfig.getNotifyUrl());
//		            po.setAppId(WeixinAccountConfig.getAppId());
//		            po.setPaySignKey(WeixinAccountConfig.getPartnerKey());
//		            po.setOpenId("oHnZ4uKvy3lVOA22Z4rLvEDASXx0");
//		      }
//		        
//			 ppr.setOrder(po);
//		}
//
//         PrePayResponse x = ppr.getResponse();
//
//         System.out.println("调用成功，返回prepayid："+x.getPrePayId());
//         
////         JsPay jsPay =  x.getJsPay();
//	}
//}
