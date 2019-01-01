package org.netsharp.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.base.ICatEntityService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.entity.ArchiveEntity;
import org.netsharp.entity.CatEntity;
import org.netsharp.entity.CatEntity.ICatRender;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

@Service
public class CatEntityService implements ICatEntityService {
	protected final Log logger = LogFactory.getLog(CatEntityService.class);

	public void generatePathCode(String entityId) {

		Oql oql = new Oql();
		{
			oql.setEntityId(entityId);
			oql.setSelects("*");
		}

		IPersister<CatEntity> pst = PersisterFactory.create();
		List<CatEntity> list = pst.queryList(oql);
		List<CatEntity> tree = CatEntity.listToTree(list);

		if (tree.size() >= 100) {
			logger.warn("path code size more than 100 ," + entityId + "(" + tree.size() + ")");
		}

		for (int i = 0; i < tree.size(); i++) {

			CatEntity item = tree.get(i);

			String pathCode = StringManager.padLeft(String.valueOf(i + 1), CatEntity.PATH_CODE_SIZE, '0');

			item.setPathCode(pathCode);
			item.setPathName(item.getName());
		}

		ICatRender<CatEntity> render = new ICatRender<CatEntity>() {

			public boolean validate(CatEntity t) {

				if (t.getItems() == null) {
					t.setIsLeaf(true);
					return false;
				}

				if (t.getItems().size() == 0) {
					t.setIsLeaf(true);
					return false;
				}

				t.setIsLeaf(false);
				return true;
			}

			@Override
			public void execute(CatEntity t) {

				if (t.getItems().size() >= 100) {
					logger.warn("path code size more than 100, sub of '" + t.getPathName() + "' (" + t.getItems().size() + ")");
				}

				for (int i = 0; i < t.getItems().size(); i++) {

					CatEntity item = t.getItems().get(i);

					if (item.getId() == 2) {
						System.out.println("");
					}

					String pathCode = StringManager.padLeft(String.valueOf(i + 1), CatEntity.PATH_CODE_SIZE, '0');

					item.setPathCode(t.getPathCode() + pathCode);
					item.setPathName(t.getPathName() + " / " + item.getName());

					// System.out.println(item.getId()+"  "+ item.getPathCode()
					// +"    "+ item.getPathName());
				}
			}
		};

		for (int i = 0; i < tree.size(); i++) {

			CatEntity item = tree.get(i);
			CatEntity.render(item, render);
		}

		list = CatEntity.treeToList(tree);

		for (CatEntity item : list) {
			item.toPersist();
			pst.save(item);
		}
	}

	public void generateShorthand(String entityId) {

		Oql oql = new Oql();
		{
			oql.setEntityId(entityId);
			oql.setSelects("*");
		}

		IPersister<ArchiveEntity> apm = PersisterFactory.create();
		List<ArchiveEntity> aes = apm.queryList(oql);

		for (ArchiveEntity ae : aes) {
			ae.toPersist();

			apm.save(ae);
		}
	}
}
