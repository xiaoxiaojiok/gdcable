package com.gdcable.epm.jbpm.org.jbpm.jpdl.internal.xml;

import java.net.URL;
import java.util.Enumeration;
import java.util.List;

import org.jbpm.api.model.Event;
import org.jbpm.internal.log.Log;
import org.jbpm.jpdl.internal.xml.JpdlBindingsParser;
import org.jbpm.jpdl.internal.xml.JpdlParser;
import org.jbpm.jpdl.internal.xml.UnresolvedTransitions;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.TimerDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.util.XmlUtil;
import org.jbpm.pvm.internal.xml.Bindings;
import org.jbpm.pvm.internal.xml.Parse;
import org.w3c.dom.Element;

public class NewJpdlParser extends JpdlParser{
	private static JpdlBindingsParser jpdlBindingsParser = new JpdlBindingsParser();
	
	private static final Log log = Log.getLog(NewJpdlParser.class.getName());
	public void parseTransitions(Element element, ActivityImpl activity, Parse parse) {
	    UnresolvedTransitions unresolvedTransitions =
	      parse.contextStackFind(UnresolvedTransitions.class);

	    List<Element> transitionElements = XmlUtil.elements(element, "transition");
	    for (Element transitionElement : transitionElements) {
	      String transitionName = XmlUtil.attribute(transitionElement, "name");
	      String gos = XmlUtil.attribute(transitionElement, "g");
	      Element timerElement = XmlUtil.element(transitionElement, "timer");
	      if (timerElement != null) {
	        TimerDefinitionImpl timerDefinitionImpl =
	          parseTimerDefinition(timerElement, parse, activity);
	        timerDefinitionImpl.setSignalName(transitionName);
	      }

	      TransitionImpl transition = activity.createOutgoingTransition();
	      transition.setName(transitionName);
	      if(gos!=null){
	    	  transition.setDescription(gos);
	      }
	      unresolvedTransitions.add(transition, transitionElement);
	      parseOnEvent(transitionElement, parse, transition, Event.TAKE);
	    }
	  }

	@Override
	protected void parseBindings() {
	    this.bindings = new Bindings();
	    String[] DEFAULT_BINDING_RESOURCES = {"jbpm.user.bindings.xml" , "jbpm.jpdl.bindings.xml"};
	    for (String activityResource : DEFAULT_BINDING_RESOURCES) {
	      Enumeration<URL> resourceUrls = getResources(activityResource);
	      if (resourceUrls.hasMoreElements()) {
	        while (resourceUrls.hasMoreElements()) {
	          URL resourceUrl = resourceUrls.nextElement();
	          log.trace("loading jpdl bindings from resource: " + resourceUrl);
	          jpdlBindingsParser.createParse()
	            .setUrl(resourceUrl)
	            .contextMapPut(Parse.CONTEXT_KEY_BINDINGS, bindings)
	            .execute()
	            .checkErrors("jpdl bindings from " + resourceUrl.toString());
	        }
	      }
	      else {
	        log.trace("skipping unavailable jpdl activities resource: " + activityResource);
	      }
	    }
	  }
	
	

}
