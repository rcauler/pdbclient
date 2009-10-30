package br.com.infosites.pdbclient.domain;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 10:53:13
 * To change this template use File | Settings | File Templates.
 */
public class Column {
	private Table table;

	public Table getTable() {
		return table;
	}

	private String name;

	public String getName() {
		return name;
	}

	private ColumnType type;

	public ColumnType getType() {
		return type;
	}

	private List<Constraint> constraints;

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public Column( Table table, String name, ColumnType type ) {
		this.table = table;
		this.name = name;
		this.type = type;
	}
}
