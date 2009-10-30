package br.com.infosites.pdbclient.dialects;

import br.com.infosites.pdbclient.domain.Table;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 15:40:43
 * To change this template use File | Settings | File Templates.
 */
public class MySqlDialect extends Dialect {
	@Override
	public String getColumnScapeBegin() {
		return "`";
	}

	@Override
	public String getColumnScapeEnd() {
		return "`";
	}

	@Override
	public String selectAll( Table table, long offset, int max ) {
		return "select * from " + table.getName() + " limit " + offset + ", " + max + ";";
	}

	@Override
	public String getStringChar() {
		return "'";
	}
}
