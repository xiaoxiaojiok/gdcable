package com.gdcable.epm.jbpm.helper;

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Map;

import javax.el.CompositeELResolver;
import javax.el.ELContext;

import org.jbpm.pvm.internal.model.ScopeInstanceImpl;

public class JVariableElResolver extends CompositeELResolver{
	
	private Map variable;
	
	public JVariableElResolver(Map variable) {
		this.variable = variable;
	}
	
	public Object getValue(ELContext context, Object base, Object property) {
	    // this resolver only resolves top level variable names to execution variable names.
	    // only handle if this is a top level variable
	    if (base==null) {
	      // we assume a NPE-check for property is not needed
	      // i don't think the next cast can go wrong.  can it?
	      String name = (String) property;

	      if (variable.containsKey(name)) {
	        context.setPropertyResolved(true);
	        System.out.println(name);
	        return variable.get(name);
	      }
	    }

	    return null;
	  }
	
	public boolean isReadOnly(ELContext context, Object base, Object property) {
	    // this resolver only resolves top level variable names to execution variable names.
	    // only handle if this is a top level variable
	    if (base==null) {
	      // we assume a NPE-check for property is not needed
	      // i don't think the next cast can go wrong.  can it?
	      String name = (String) property;

	      if (variable.containsKey(name)) {
	        return false;
	      }
	    }

	    return true;
	  }
	
	public void setValue(ELContext context, Object base, Object property, Object value) {
	    // this resolver only resolves top level variable names to execution variable names.
	    // only handle if this is a top level variable
	    if (base==null) {
	      // we assume a NPE-check for property is not needed
	      // i don't think the next cast can go wrong.  can it?
	      String variableName = (String) property;

	      if (variable.containsKey(variableName)) {
	    	  variable.put(variableName, value);
	      }
	    }
	  }
	
	public Class< ? > getType(ELContext context, Object base, Object property) {
	    return Object.class;
	  }
	  public Class< ? > getCommonPropertyType(ELContext context, Object base) {
	    return Object.class;
	  }
	  public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
	    return null;
	  }
}
