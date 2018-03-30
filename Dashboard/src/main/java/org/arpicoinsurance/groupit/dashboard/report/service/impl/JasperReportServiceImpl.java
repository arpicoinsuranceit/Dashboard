package org.arpicoinsurance.groupit.dashboard.report.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.arpicoinsurance.groupit.dashboard.report.service.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
		
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("agncod", advisor);
        params.put("loccod", branch);
        
        Resource resource = new ClassPathResource("mcfpr.jrxml");
        File file = resource.getFile();
        
        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "mcfpr.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] proposalRegister(String fromDate, String toDate, String zone, String region, String branch,
			String unl, String frequency) throws Exception {

		System.out.println(fromDate+","+toDate+","+zone+","+region+","+branch+","+unl+","+frequency);
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("loccod", branch);
        params.put("agncod", unl);
        params.put("catcod", frequency);
        
        Resource resource = new ClassPathResource("prop_register.jrxml");
        File file = resource.getFile();

        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "prop_register.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] pendingRequirements(String advisor, String branch, String region, String zone) throws Exception {
		System.out.println(advisor+","+branch+","+region+","+zone);
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("agncod", advisor);
        params.put("loccod", branch);
        params.put("rgncod", region);
        params.put("rgncod", region);
        params.put("loccod", branch);
        params.put("zoncod", zone);
        
        Resource resource = new ClassPathResource("Pend_req_advi.jrxml");
        File file = resource.getFile();

        System.out.println(file.getPath()+" Path.....");
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "Pend_req_advi.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] retentionUnit(String toDate, String zone, String region, String branch, String unl) throws Exception {
		System.out.println(toDate+","+zone+","+region+","+branch+","+unl);
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("loccod", branch);
        params.put("edate", toDate);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("agncod", unl);
        
        Resource resource = new ClassPathResource("reten_unl_sum.jrxml");
        File file = resource.getFile();

        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "reten_unl_sum.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] retentionCode(String date, String zone, String region, String branch, String code) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("loccod", branch);
        params.put("edate", date);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("pprsta", code);
        
        Resource resource = new ClassPathResource("pre_bas_persi_sum.jrxml");
        File file = resource.getFile();

        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "pre_bas_persi_sum.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] retentionBranch(String date, String zone, String region, String branch) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("loccod", branch);
        params.put("edate", date);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("pprsta", "%");
        
        Resource resource = new ClassPathResource("pre_bas_persi_sum.jrxml");
        File file = resource.getFile();

        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "pre_bas_persi_sum.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] detailsOfPolicies(String fromDate, String toDate, String ic, String ul, String branch, String region,
			String zone, String sp) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("loccod", branch);
        params.put("rgncod", region);
        params.put("zoncod", zone);
        params.put("sinprm", sp);
        params.put("agncod", ic);
        params.put("unlcod", ul);
        
        Resource resource = new ClassPathResource("detai_pol_list.jrxml");
        File file = resource.getFile();

        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "detai_pol_list.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] premiumDueReportLive(String asAtDate, String code, String branchCode, String regionCode, String zone)
			throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("pdate", asAtDate);
        params.put("loccod", branchCode);
        params.put("rgncod", regionCode);
        params.put("agncod", code);
        params.put("zoncod", zone);
        
        Resource resource = new ClassPathResource("Premium_due_rep_live.jrxml");
        File file = resource.getFile();
      

        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "Premium_due_rep_live.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] premiumDueReport(String asAtDate, String code, String branchCode, String regionCode, String zone)
			throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("pdate", asAtDate);
        params.put("loccod", branchCode);
        params.put("rgncod", regionCode);
        params.put("agncod", code);
        params.put("zoncod", zone);
      
        Resource resource = new ClassPathResource("Premium_due_rep.jrxml");
        File file = resource.getFile();
        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "Premium_due_rep.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] grantStmtBranch(String branch, String year, String month, String code, String status)
			throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("year", year);
        params.put("month", month);
        params.put("agnsta", status);
        params.put("agncod", code);
        params.put("loccod", branch);
        
        Resource resource = new ClassPathResource("comm_agnt_bra.jrxml");
        File file = resource.getFile();
      

        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "comm_agnt_bra.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] firstPremiumLapSummary(String fromDate, String toDate, String zone, String region, String branch)
			throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("loccod", branch);
      
        Resource resource = new ClassPathResource("first_prm_lap.jrxml");
        File file = resource.getFile();
        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "first_prm_lap.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] salesPerfSummaryCode(String fromDate, String toDate, String zone, String region, String branch,
			String frequency, String product) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("loccod", branch);
        params.put("prdcod", product);
        params.put("catcod", frequency);
        
        Resource resource = new ClassPathResource("performance_agn.jrxml");
        File file = resource.getFile();


        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "performance_agn.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] salesPerfSummary(String fromDate, String toDate, String zone, String region, String branch,
			String frequency, String product, String so) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("loccod", branch);
        params.put("prdcod", product);
        params.put("catcod", frequency);
        params.put("sinprm", so);
        
        Resource resource = new ClassPathResource("performance_brn.jrxml");
        File file = resource.getFile();


        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "performance_brn.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] unitIsPerfSummary(String fromDate, String toDate, String zone, String region, String branch,
			String unl, String type, String frequency, String product) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("loccod", branch);
        params.put("prdcod", product);
        params.put("catcod", frequency);
        params.put("agncod", unl);
        params.put("subtyp", type);
        params.put("unlcod", unl);
        
        Resource resource = new ClassPathResource("performance_summ_unl.jrxml");
        File file = resource.getFile();


        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "performance_summ_unl.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] salesPerfDetail(String fromDate, String toDate, String code, String zone, String region,
			String branch, String product, String frequency) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("loccod", branch);
        params.put("prdcod", product);
        params.put("catcod", frequency);
        params.put("agncod", code);

        Resource resource = new ClassPathResource("performance_detail.jrxml");
        File file = resource.getFile();
        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "performance_detail.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] unitIsPerfDetails(String fromDate, String toDate, String zone, String region, String branch,
			String unl, String type, String frequency, String product) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("sdate", fromDate);
        params.put("edate", toDate);
        params.put("zoncod", zone);
        params.put("rgncod", region);
        params.put("loccod", branch);
        params.put("prdcod", product);
        params.put("catcod", frequency);
        params.put("agncod", unl);
        params.put("subtyp", type);

        Resource resource = new ClassPathResource("performance_detail_unl.jrxml");
        File file = resource.getFile();
        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "performance_detail_unl.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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

	@Override
	public byte[] policyAcknowledgement(String branch, String year, String month) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
	    response.setContentType("application/pdf");
	    
		Map<String, Object> params = new HashMap<>();
        params.put("sbucod", "450");
        params.put("year", year);
        params.put("loccod", branch);
        params.put("period", month);
        
        Resource resource = new ClassPathResource("biz_gra_sub.jrxml");
        File file = resource.getFile();
        
        //String OUT_PUT = "D:\\performance_detail.pdf";
        String REPORT = "biz_gra_sub.jrxml";
        JasperReport jr=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
        	try {
        		 jr = JasperCompileManager.compileReport(
                         ClassLoader.getSystemResourceAsStream(file.getName()));
                 
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
