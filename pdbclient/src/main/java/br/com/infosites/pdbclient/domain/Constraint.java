package br.com.infosites.pdbclient.domain;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 12:52:17
 * To change this template use File | Settings | File Templates.
 */
public class Constraint {
	private Column column;

	private String name;

	public Constraint( Column column, String name ) {
		this.column = column;
		this.name = name;
	}
}
