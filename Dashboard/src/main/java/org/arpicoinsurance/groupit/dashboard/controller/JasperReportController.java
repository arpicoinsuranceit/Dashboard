package org.arpicoinsurance.groupit.dashboard.controller;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
@RestController
@CrossOrigin(origins = "*")
public class JasperReportController {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private HttpServletResponse response;
	
	
	@RequestMapping(path = "/pdf", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  report() {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        
        String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "performance_detail.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(REPORT));
                 
        	}catch (Exception ex) {
        		System.out.println("Exception...");
        	}

           
			try {
				JasperPrint jp;
				
				
				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());
				
				JRPdfExporter export = new JRPdfExporter();
		        export.setExporterInput(new SimpleExporterInput(jp));
		        //export.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		        export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		        //SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
		        //export.setConfiguration(config);
		        export.exportReport();
		        
		        //ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", "explorer d:\\performance_detail.pdf");
		        //processBuilder.start();
		        
		        /////////////////////////////////////////////
		        
		       
		        System.out.println("Success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        } catch (JRException ex) {
            ex.printStackTrace();   
        }catch (Exception ex) {
    		
    	}


        System.out.println(baos.toByteArray().length);
        return baos.toByteArray();
    } 

}
