package com.chinaoly.csql.model.expression;

import com.facebook.presto.sql.tree.ArithmeticUnaryExpression.Sign;

import com.chinaoly.csql.ESResultSet;

public interface ICalculation {

	public Number evaluate(ESResultSet result, int rowNr);
	
	public ICalculation setSign(Sign sign);
	
}
