package org.netsharp.wx.pa.startup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.startup.IStartup;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.sdk.mp.api.accesstoken.PAccount;
import org.netsharp.wx.sdk.mp.api.accesstoken.PaConfiguration;

/*微信初始化，读取微信公众号的配置*/
public class StartupWeixin implements IStartup {
	
	private static Log logger = LogFactory.getLog(PublicAccountManager.class.getSimpleName());

	@Override
	public boolean startCondition() {
		return true;
	}

	@Override
	public void startup() {
		
		List<PAccount> pas = new ArrayList<PAccount>();
		
		IPublicAccountService service = ServiceFactory.create(IPublicAccountService.class);
		
		Oql oql = new Oql();
		{
		    oql.setType(PublicAccount.class);
			oql.setSelects("*");
		}
		
		List<PublicAccount> publicAccounts = service.queryList(oql);
		
		logger.warn("微信初始化公众号个数:"+publicAccounts.size());
		
		for(PublicAccount publicAccount : publicAccounts){
			
			String message = String.format("公众号：名称[%s],originalId[%s]", publicAccount.getName(),publicAccount.getOriginalId());
			logger.warn(message);
			
			PAccount pa = PublicAccountManager.getInstance().toPaConfiguration(publicAccount);
			
			pas.add(pa);
		}
		
		PaConfiguration.initialize(pas);
	}

	@Override
	public void shutdown() {
		PaConfiguration.dispose();
	}

	@Override
	public String getName() {
		return "微信框架";
	}

}
