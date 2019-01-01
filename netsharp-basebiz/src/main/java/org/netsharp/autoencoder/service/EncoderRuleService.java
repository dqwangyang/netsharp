package org.netsharp.autoencoder.service;

import org.netsharp.autoencoder.base.IEncoderRuleService;
import org.netsharp.autoencoder.entity.EncoderRule;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class EncoderRuleService extends PersistableService<EncoderRule> implements IEncoderRuleService {

	public EncoderRuleService() {
		super();
		this.type = EncoderRule.class;
	}
}