package org.arpicoinsurance.groupit.dashboard.report.service.impl;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.arpicoinsurance.groupit.dashboard.report.service.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@Transactional
public class JasperReportServiceImpl implements JasperReportService{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private HttpServletResponse response;

	@Override
	public byte[] mcfpReport(String fromDate, String toDate, String advisor, String branch) throws Exception {
		System.out.println(fromDate+","+toDate+","+advisor+","+branch);
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("agncod", "%");
        params.put("loccod", branch);
        
        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "mcfpr.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(REPORT));
                 
        	}catch (Exception ex) {
        		ex.printStackTrace();
        		System.out.println("Exception...");
        	}

           
			try {
				JasperPrint jp;
				
				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());
				
				JRPdfExporter export = new JRPdfExporter();
		        export.setExporterInput(new SimpleExporterInput(jp));
		        export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		        export.exportReport();
		        
		       
			} catch (SQLException e) {
				e.printStackTrace();
			}
            
        } catch (JRException ex) {
            ex.printStackTrace();   
        }catch (Exception ex) {
        	ex.printStackTrace();  
    	}

        return baos.toByteArray();
	}
	
	

}
