package com.gdcable.epm.jbpm.org.jbpm.jpdl.internal.repository;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jbpm.api.JbpmException;
import org.jbpm.internal.log.Log;
import org.jbpm.jpdl.internal.repository.JpdlDeployer;
import org.jbpm.jpdl.internal.xml.JpdlParser;
import org.jbpm.pvm.internal.repository.DeploymentImpl;
import org.jbpm.pvm.internal.repository.ProcessDeployer;
import org.jbpm.pvm.internal.util.XmlUtil;
import org.jbpm.pvm.internal.xml.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gdcable.epm.jbpm.org.jbpm.jpdl.internal.xml.NewJpdlParser;

public class NewJpdlDeployer extends ProcessDeployer {
	  
	  private static Log log = Log.getLog(JpdlDeployer.class.getName());
	  private static Parser parser = new Parser();
	  
	  static JpdlParser jpdlParser = new NewJpdlParser();
	  static final String jpdlExtension = ".jpdl.xml";
	  
	  public NewJpdlDeployer() {
	    super(jpdlExtension, jpdlParser);
	  }

	  public void updateResource(DeploymentImpl deployment, String resourceName, byte[] bytes) {
	    if (resourceName.endsWith(".jpdl.xml")) {
	      Document document = parser
	        .createParse()
	        .setInputStream(new ByteArrayInputStream(bytes))
	        .execute()
	        .getDocument();
	      Element documentElement = document.getDocumentElement();
	      String tagName = documentElement.getLocalName();
	      
	      if ("process-update".equals(tagName)) {
	        updateJpdlProcessResource(deployment, resourceName, document);
	        return;
	      }
	    }
	    
	    super.updateResource(deployment, resourceName, bytes);
	  }

	  public void updateJpdlProcessResource(DeploymentImpl deployment, String resourceName, Document updateDocument) {
	    byte[] processBytes = deployment.getBytes(resourceName);
	    Document processDocument = parser
	      .createParse()
	      .setInputStream(new ByteArrayInputStream(processBytes))
	      .execute()
	      .checkErrors("jPDL process update document")
	      .getDocument();
	    Element processElement = processDocument.getDocumentElement();
	    
	    Element processUpdateElement = updateDocument.getDocumentElement();
	    Element processUpdateDescriptionElement = XmlUtil.element(processUpdateElement, "description");
	    if (processUpdateDescriptionElement!=null) {
	      Element processDescriptionElement = XmlUtil.element(processElement, "description");
	      if (processDescriptionElement!=null) {
	        processElement.removeChild(processDescriptionElement);
	      }
	      Node clonedDescriptionElement = processUpdateDescriptionElement.cloneNode(true);
	      processDocument.adoptNode(clonedDescriptionElement);
	      processElement.appendChild(clonedDescriptionElement);
	    }
	    
	    updateActivities(processDocument, processElement, processUpdateElement);
	    
	    try {
	      Transformer transformer = TransformerFactory.newInstance().newTransformer();
	      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	      //initialize StreamResult with File object to save to file
	      StreamResult result = new StreamResult(new StringWriter());
	      DOMSource source = new DOMSource(processDocument);
	      transformer.transform(source, result);
	      
	      String updatedProcessXml = result.getWriter().toString();
	      log.debug("updated process xml: \n"+updatedProcessXml);
	      byte[] bytes = updatedProcessXml.getBytes();
	      deployment.addResourceFromInputStream(resourceName, new ByteArrayInputStream(bytes));
	    } catch (Exception e) {
	      throw new JbpmException("couldn't serialize updated process dom model", e);
	    }
	  }

	  private void updateActivities(Document processDocument, Element activityContainerElement, Element updateContainerElement) {
	    Set<String> activityNames = jpdlParser.getActivityTagNames();

	    Map<String, Element> processActivityMap = getActivityMap(activityContainerElement, activityNames);
	    Map<String, Element> updateActivityMap = getActivityMap(updateContainerElement, activityNames);
	    
	    for (String activityName: updateActivityMap.keySet()) {
	      Element updateActivity = updateActivityMap.get(activityName);
	      Element processActivity = processActivityMap.get(activityName);
	      
	      if (processActivity==null) {
	        throw new JbpmException("unmatching update activity "+activityName);
	      }
	      
	      Node clonedUpdateActivity = updateActivity.cloneNode(true);
	      processDocument.adoptNode(clonedUpdateActivity);
	      activityContainerElement.insertBefore(clonedUpdateActivity, processActivity);
	      activityContainerElement.removeChild(processActivity);
	      
//	      String updateTagName = XmlUtil.getTagLocalName(updateActivity);
	//
//	      activityContainerElement.removeChild(processActivity);
//	      Element mergedActivityElement = processDocument.createElement(updateTagName);
//	      
//	      mergeAttributes(mergedActivityElement, processActivity.getAttributes());
//	      mergeAttributes(mergedActivityElement, updateActivity.getAttributes());
//	      
//	      Map<String, List<Element>> processActivityContents = getElementsByTagName(processActivity);
//	      Map<String, List<Element>> updateActivityContents = getElementsByTagName(updateActivity);
//	      
//	      Set<String> allTagNames = new HashSet<String>(processActivityContents.keySet());
//	      allTagNames.addAll(updateActivityContents.keySet());
//	      
//	      for (String tagName: allTagNames) {
//	        List<Element> contentElements = processActivityContents.get(tagName);
//	        if (contentElements==null) {
//	          contentElements = new ArrayList<Element>();
//	        }
//	        List<Element> updateElements = updateActivityContents.get(tagName);
//	        for (int i=0; i<contentElements.size(); i++) {
//	          Element contentElement = contentElements.get(i);
//	          Element updateElement = (updateElements!=null && updateElements.size()>i ? updateElements.get(i) : null);
//	          if (updateElement!=null) {
//	            mergeAttributes(contentElement, updateElement.getAttributes());
//	          }
//	        }
//	      }
	    }
	  }

	  protected Map<String, Element> getActivityMap(Element containerElement, Set<String> activityNames) {
	    Map<String, Element> activityMap = new HashMap<String, Element>();
	    
	    for (Element element: XmlUtil.elements(containerElement)) {
	      String tagName = element.getLocalName();
	      if (activityNames.contains(tagName)) {
	        String activityName = element.getAttribute("name");
	        activityMap.put(activityName, element);
	      }
	    }
	    
	    return activityMap;
	  }
	}