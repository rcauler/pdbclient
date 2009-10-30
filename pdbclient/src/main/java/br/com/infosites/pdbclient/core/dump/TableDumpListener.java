package br.com.infosites.pdbclient.core.dump;

import br.com.infosites.pdbclient.domain.Table;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 16:32:41
 * To change this template use File | Settings | File Templates.
 */
public interface TableDumpListener {
	void onDumpTable( Table table );
}
