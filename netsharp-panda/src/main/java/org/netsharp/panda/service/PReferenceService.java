package org.netsharp.panda.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.entity.PReference;
import org.netsharp.service.PersistableService;

@Service
public class PReferenceService extends PersistableService<PReference> implements IPReferenceService {

	public PReferenceService() {

		super();
		this.type = PReference.class;

	}

	@Override
	public PReference save(PReference entity) {
		EntityState state = entity.getEntityState();
		PReference old = this.byCode(entity.getCode());
		if (EntityState.New.equals(state)) {
			if (old != null) {
				throw new BusinessException("参照code重复");
			}
		} else if (EntityState.Persist.equals(state)) {
			if (old != null && old.getId() != entity.getId()) {
				throw new BusinessException("参照code重复");
			}
		}
		return super.save(entity);
	}

	public PReference byCode(String code) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("PReference.id");
			oql.setFilter("PReference.code = ?");

			oql.getParameters().add("@code", code, Types.VARCHAR);
		}

		PReference refernece = this.queryFirst(oql);

		if (refernece != null) {
			refernece = this.byId(refernece);
		}

		return refernece;
	}
}
