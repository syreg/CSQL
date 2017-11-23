package com.chinaoly.csql.model.expression;

import java.util.List;

import com.facebook.presto.sql.tree.ArithmeticUnaryExpression.Sign;

import com.chinaoly.csql.ESResultSet;
import com.chinaoly.csql.model.Column;

public class ColumnReference implements ICalculation{

	private Column column;
	private Sign sign = Sign.PLUS;
	private int offset = 0;
	
	public ColumnReference(Column column) {
		this.column = column;
	}
	
	public ColumnReference(Column column, int offset) {
		this.column = column;
		this.offset = offset;
	}

	@Override
	public Number evaluate(ESResultSet result, int rowNr) {
		if(rowNr + offset < 0 || rowNr + offset >= result.rowCount() ) return Float.NaN;
		List<Object> row = result.getRow(rowNr + offset);
		Object value = row.get(column.getIndex());
		if(value instanceof Boolean){
			if(((Boolean)value).booleanValue()) value = 1;
			else value = 0;
		}
		if(sign == Sign.MINUS) return -1 * ((Number)value).doubleValue();
		return (Number)value;
	}

	public Column getColumn(){
		return column;
	}
	
	public String toString(){
		return column.getFullName();
	}

	@Override
	public ColumnReference setSign(Sign sign) {
		this.sign = sign;
		return this;
	}
	
}
