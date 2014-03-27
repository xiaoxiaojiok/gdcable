package com.gdcable.epm.jbpm.org.jbpm.jpdl.internal.xml;

import org.jbpm.pvm.internal.wire.binding.WireDescriptorBinding;
import org.jbpm.pvm.internal.wire.descriptor.ObjectDescriptor;
import org.jbpm.pvm.internal.xml.Parse;
import org.jbpm.pvm.internal.xml.Parser;
import org.w3c.dom.Element;

import com.gdcable.epm.jbpm.org.jbpm.jpdl.internal.repository.NewJpdlDeployer;

public class NewJpdlDeployerBinding extends WireDescriptorBinding {

	  public NewJpdlDeployerBinding() {
	    super("newjpdl-deployer");
	  }

	  public Object parse(Element element, Parse parse, Parser parser) {
	    return new ObjectDescriptor(NewJpdlDeployer.class);
	  }
}
