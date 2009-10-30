package br.com.infosites.pdbclient.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 10:53:06
 * To change this template use File | Settings | File Templates.
 */
public class Table {
	private Database database;

	public Database getDatabase() {
		return database;
	}

	private String name;

	public String getName() {
		return name;
	}

	private List<Column> columns;

	public List<Column> getColumns() {
		return columns;
	}

	public Table( Database database, String name ) throws SQLException {
		this.database = database;
		this.name = name;

		ResultSet tableMetaData = database.getConnection().getMetaData().getColumns( null, null, name, "%" );

		columns = new ArrayList<Column>();

		while ( tableMetaData.next() ) {
			String columnName = tableMetaData.getString("COLUMN_NAME");
			String columnType = tableMetaData.getString("TYPE_NAME");

			Column column = new Column( this, columnName, ColumnType.parse( columnType ) );
			columns.add( column );
		}
	}
}
