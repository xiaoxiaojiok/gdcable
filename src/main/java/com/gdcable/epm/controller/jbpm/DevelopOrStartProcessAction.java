package com.gdcable.epm.controller.jbpm;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gdcable.epm.jbpm.service.JBPMProcess;

@Controller
@RequestMapping(value="/jbpm")
public class DevelopOrStartProcessAction 
{

	@Autowired
	JBPMProcess jBPMProcess;

	String name;
	String id;
	//文件上传
	File pdFile;
	String pdContextType;
	/**
	 * 上传流程定义文件
	 * @return
	 */
	@RequestMapping(value="/uploadProcessDefByXml")
	public void uploadProcessDefByXml(HttpServletRequest request,HttpServletResponse response) throws Exception{
		File file = new File("f:\\Project.jpdl.xml");
		String id = jBPMProcess.saveProcessDefinition(file);
		JSONObject json = new JSONObject();
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("id", id);
		json = JSONObject.fromObject(result);
		response.setHeader("Cache-Control", "no-cache");  
		PrintWriter pw = response.getWriter();
		json.write(pw);
		pw.close();
		return ;
	}

	/**
	 * 上传流程定义文件
	 * @return
	 */
	@RequestMapping(value="/uploadProcessDefiByZip")
	public String uploadProcessDefiByZip(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//上传流程定义文件
		try {
//			String id = repositoryService.createDeployment().addResourcesFromZipInputStream(new ZipInputStream(new FileInputStream(pdFile))).deploy();
			
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("id", null);;
			return "jbpm/processDefList";
		}
		request.setAttribute("id", id);;
		return "jbpm/processDefList";
	}
	
	
	/**
	 * 
	 * <pre>
	 * 删除流程定义 
	 * </pre>
	 * @return
	 */

	@RequestMapping(value="/deleteDeploymentCascade")
	public void deleteDeploymentCascade(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//将流程 实例删除
		//repositoryService.deleteDeploymentCascade(id);
		return ;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public File getPdFile() {
		return pdFile;
	}
	public void setPdFile(File pdFile) {
		this.pdFile = pdFile;
	}
	public String getPdContextType() {
		return pdContextType;
	}
	public void setPdContextType(String pdContextType) {
		this.pdContextType = pdContextType;
	}
}
