package br.com.infosites.pdbclient.core.dump;

import br.com.infosites.pdbclient.domain.Table;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 16:33:46
 * To change this template use File | Settings | File Templates.
 */
public interface TableDumpDataListener {
	void onDumpRow( Table table, ResultSet rs, long current, long total );
}
