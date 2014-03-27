package com.gdcable.epm.jbpm.helper;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.FunctionMapper;
import javax.el.ValueExpression;
import javax.el.VariableMapper;

import org.jbpm.pvm.internal.el.JbpmElFactory;
import org.jbpm.pvm.internal.el.StaticTextExpression;

public class JElContext extends ELContext{
	
	ELResolver elResolver;
	FunctionMapper functionMapper;
	
	public JElContext(ELContext elContext){
		this.elResolver = elContext.getELResolver();
		this.functionMapper = elContext.getFunctionMapper();
	}
	public JElContext(ELResolver elResolver, FunctionMapper functionMapper) {
	    this.elResolver = elResolver;
	    this.functionMapper = functionMapper;
	  }

	public ELResolver getELResolver() {
		 return elResolver;
	}


	public FunctionMapper getFunctionMapper() {
		return functionMapper;
	}


	public VariableMapper getVariableMapper() {
			return null;
	}
}
