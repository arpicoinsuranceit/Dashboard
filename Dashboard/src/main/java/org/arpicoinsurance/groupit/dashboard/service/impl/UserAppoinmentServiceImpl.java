package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.arpicoinsurance.groupit.dashboard.dao.UserAppoinmentDao;
import org.arpicoinsurance.groupit.dashboard.helper.UserAppoinmentHelper;
import org.arpicoinsurance.groupit.dashboard.service.UserAppoinmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

@Service
public class UserAppoinmentServiceImpl implements UserAppoinmentService {

	@Autowired
	private UserAppoinmentDao userAppoinmentDao;

	@Override
	public byte[] createAppoinment(Integer usrCode) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Resource FontResource1 = new ClassPathResource("FONTDIR/times.ttf");
		File FONTNORMAL = FontResource1.getFile();

		Resource FontResource2 = new ClassPathResource("FONTDIR/timesbd.ttf");
		File FONTBOLD = FontResource2.getFile();

		// final String FONTNORMAL = "./src/main/resources/FONTDIR/times.ttf";

		// final String FONTBOLDITALIC = "./src/main/resources/FONTDIR/timesbi.ttf";

		// final String FONTBOLD = "./src/main/resources/FONTDIR/timesbd.ttf";

		PdfFont font = PdfFontFactory.createFont(FONTNORMAL.getPath(), PdfEncodings.IDENTITY_H);
		PdfFont fontB = PdfFontFactory.createFont(FONTBOLD.getPath(), PdfEncodings.IDENTITY_H);
		// PdfFont fontBI = PdfFontFactory.createFont(FONTBOLDITALIC,
		// PdfEncodings.IDENTITY_H);

		PdfWriter writer = new PdfWriter(baos);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf, PageSize.A4);
		document.setTopMargin(45);
		document.setBottomMargin(20);

		SimpleDateFormat patt = new SimpleDateFormat("dd-MM-yyyy");
		DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate currentDate = LocalDate.now();

		document.add(new Paragraph(currentDate.format(datePattern)).setFontSize(11).setFont(font));

		document.add(new Paragraph("\n"));

		String title = "";
		String shortName = "";
		String address1 = "";
		String address2 = "";
		String agentCode = "";
		String name = "";
		String lastName = "";
		String midName = "";
		String nic = "";
		String designation = "";
		String appointDate = "";

		// System.out.println("agent code : " + usrCode);

		UserAppoinmentHelper userAppoinmentHelper = userAppoinmentDao.findByAgtCod(usrCode);

		title = userAppoinmentHelper.getAgentTitle() != null ? userAppoinmentHelper.getAgentTitle() : "";

		address1 = userAppoinmentHelper.getAddress1() != null ? userAppoinmentHelper.getAddress1() : "";

		address2 = userAppoinmentHelper.getAddress2() != null ? userAppoinmentHelper.getAddress2() : "";

		lastName = userAppoinmentHelper.getLastName() != null ? userAppoinmentHelper.getLastName().toUpperCase() : "";

		midName = userAppoinmentHelper.getMiddleName() != null ? userAppoinmentHelper.getMiddleName().toUpperCase()
				: "";

		shortName = userAppoinmentHelper.getShortName() != null ? userAppoinmentHelper.getShortName() : "";

		agentCode = userAppoinmentHelper.getAgtCod() != null ? userAppoinmentHelper.getAgtCod() : "";

		name = userAppoinmentHelper.getAgentName() != null ? userAppoinmentHelper.getAgentName().toUpperCase() : "";

		nic = userAppoinmentHelper.getAgentNic() != null ? userAppoinmentHelper.getAgentNic().toUpperCase() : "";

		designation = userAppoinmentHelper.getDesignation() != null
				? userAppoinmentHelper.getDesignation().toUpperCase()
				: "";

		appointDate = patt.format(
				userAppoinmentHelper.getAppoinmentDate() != null ? userAppoinmentHelper.getAppoinmentDate() : "");

		document.add(new Paragraph(title + " . " + shortName).setFontSize(11).setFixedLeading(1).setFont(font));
		document.add(new Paragraph(address1).setFontSize(11).setFixedLeading(1).setFont(font));
		document.add(new Paragraph(address2).setFontSize(11).setFixedLeading(1).setFont(font));

		document.add(new Paragraph(""));

		document.add(new Paragraph("AGREEMENT – " + designation + " (Code No." + agentCode + ")").setFontSize(11)
				.setCharacterSpacing(1).setFont(fontB));

		document.add(new Paragraph("Further to your written request and the subsequent interview, "
				+ "Arpico Insurance PLC, (hereinafter called the “Company”) is pleased to enter into this agreement with "
				+ name + " " + midName + " " + lastName + " holder of NIC No " + nic + " as a/an " + designation + " "
				+ "(which bears the same meaning as a/an " + designation
				+ " and hereinafter referred to as ‘Agent’) with effect from " + appointDate
				+ " subject to the following agreed terms and conditions to procure Life Insurance. "
				+ "Your code number indicated above should be stated in all the documents as required by the Company.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10)
						.setFixedPosition(37, 615, 520).setFont(font));

		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph(""));

		float[] infoTblColsWidths = { 40, 50, 500 };
		Table info = new Table(infoTblColsWidths);

		Cell cell1 = new Cell();
		cell1.setBorder(Border.NO_BORDER);
		cell1.add(new Paragraph("1.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cell1);

		Cell cell2 = new Cell(0, 2);
		cell2.setBorder(Border.NO_BORDER);
		cell2.add(new Paragraph("General Status").setFontSize(11).setCharacterSpacing(1).setFont(fontB));
		info.addCell(cell2);

		Cell emty1 = new Cell();
		emty1.setBorder(Border.NO_BORDER);
		emty1.add(new Paragraph("").setFontSize(11).setItalic().setFont(font));
		info.addCell(emty1);

		Cell cell3 = new Cell(0, 2);
		cell3.setBorder(Border.NO_BORDER);
		cell3.add(new Paragraph("Your contract as a/an " + designation.toUpperCase()
				+ " (Agent) is subject to the conditions hereafter stated :-").setFontSize(11)
						.setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell3);

		info.startNewRow();

		Cell cell4 = new Cell(0, 2);
		cell4.setBorder(Border.NO_BORDER);
		cell4.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell4);

		Cell cell5 = new Cell();
		cell5.setBorder(Border.NO_BORDER);
		cell5.add(new Paragraph("Your contract as a/an " + designation.toUpperCase()
				+ " (Agent) will come into force on the effective date of this letter or the date on which you qualify the pre-recruitment test conducted by the Sri Lanka Insurance Institute (SLII) or any other body approved by the Insurance Board of Sri Lanka, which ever is later.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell5);

		info.startNewRow();

		Cell cell6 = new Cell(0, 2);
		cell6.setBorder(Border.NO_BORDER);
		cell6.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell6);

		Cell cell7 = new Cell();
		cell7.setBorder(Border.NO_BORDER);
		cell7.add(new Paragraph(
				"You are required to achieve the objectives set out in the attached sheet  in each agency year. The objectives can be re-set by the Company from time to time.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell7);

		info.startNewRow();

		Cell cell8 = new Cell(0, 2);
		cell8.setBorder(Border.NO_BORDER);
		cell8.add(new Paragraph("(c)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell8);

		Cell cell9 = new Cell();
		cell9.setBorder(Border.NO_BORDER);
		cell9.add(new Paragraph(
				"The agency year will be a period of 12 months commencing 1st January provided however, that where your appointment is made during a year, your target will be pro-rata.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell9);

		info.startNewRow();

		Cell cellA = new Cell();
		cellA.setBorder(Border.NO_BORDER);
		cellA.add(new Paragraph("2.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cellA);

		Cell cellB = new Cell(0, 3);
		cellB.setBorder(Border.NO_BORDER);
		cellB.add(new Paragraph("Non-fulfillment of quotas").setFontSize(11).setCharacterSpacing(1).setFont(fontB));
		info.addCell(cellB);

		info.startNewRow();

		Cell cellC = new Cell(0, 2);
		cellC.setBorder(Border.NO_BORDER);
		cellC.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellC);

		Cell cellD = new Cell();
		cellD.setBorder(Border.NO_BORDER);
		cellD.add(new Paragraph(
				"If you fail to complete the requisite volume and value of business and other objectives specified in clause 1 (b) above, in any agency year, your agency will stand automatically terminated from the beginning of  the agency year following, provided  however, that if you show valid cause for your failure to complete the requisite volume of business to our satisfaction (of which the Company shall be the sole judge), the agency will stand renewed.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellD);

		info.startNewRow();

		Cell cellE = new Cell(0, 2);
		cellE.setBorder(Border.NO_BORDER);
		cellE.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellE);

		Cell cellF = new Cell();
		cellF.setBorder(Border.NO_BORDER);
		cellF.add(new Paragraph(
				"In computing your business for purposes of Clause 1 or 2 (a) above, all business done by you will be taken into account. However, the basis of such reckoning will be decided by the Management of the company")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellF);

		info.startNewRow();

		Cell cellG = new Cell();
		cellG.setBorder(Border.NO_BORDER);
		cellG.add(new Paragraph("3.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cellG);

		Cell cellH = new Cell(0, 3);
		cellH.setBorder(Border.NO_BORDER);
		cellH.add(new Paragraph("Remuneration by commission").setFontSize(11).setCharacterSpacing(1).setFont(fontB));
		info.addCell(cellH);

		info.startNewRow();

		Cell cellI = new Cell(0, 2);
		cellI.setBorder(Border.NO_BORDER);
		cellI.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellI);

		Cell cellJ = new Cell();
		cellJ.setBorder(Border.NO_BORDER);
		cellJ.add(new Paragraph(
				"As remuneration for services rendered by you to the Company with regard to your agency, you will be paid commission as per rates applicable and in force from time to time. The company reserves the absolute right to change the rates of commission at will and not be liable to notify in writing. The Company also reserves the right and authority to withdraw payment of commission any time it considers it is advisable to do so.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellJ);

		info.startNewRow();

		Cell cellK = new Cell(0, 2);
		cellK.setBorder(Border.NO_BORDER);
		cellK.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellK);

		Cell cellL = new Cell();
		cellL.setBorder(Border.NO_BORDER);
		cellL.add(new Paragraph(
				"The Agent shall immediately refund to the Company or grant authority to recover any commission paid in respect of any premium returned to the insured by the Company.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellL);

		info.startNewRow();

		Cell cellM = new Cell();
		cellM.setBorder(Border.NO_BORDER);
		cellM.add(new Paragraph("4.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cellM);

		Cell cellN = new Cell(0, 3);
		cellN.setBorder(Border.NO_BORDER);
		cellN.add(new Paragraph("Collection of money, rebating etc.	").setFontSize(11).setFont(fontB)
				.setCharacterSpacing(1));
		info.addCell(cellN);

		info.startNewRow();

		Cell cellO = new Cell(0, 2);
		cellO.setBorder(Border.NO_BORDER);
		cellO.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellO);

		Cell cellP = new Cell();
		cellP.setBorder(Border.NO_BORDER);
		cellP.add(new Paragraph(
				"As an Agent you are not authorized to collect moneys, accept risks or bind the Company in any way, nor are you authorized or allowed to advance premiums to the company on behalf of policyholders or to become an assignee, except with the prior permission in writing of the Manager in Charge, under policies on the lives of persons other than your own or your very near relatives such as wife or minor children if they are members of a family, or to get assigned to such very near relatives policies on the lives of persons other than near relatives.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellP);

		info.startNewRow();

		Cell cellQ = new Cell(0, 2);
		cellQ.setBorder(Border.NO_BORDER);
		cellQ.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellQ);

		Cell cellR = new Cell();
		cellR.setBorder(Border.NO_BORDER);
		cellR.add(new Paragraph(
				"You are also not authorized to collect or to issue receipts for moneys paid towards premia, in respect of which remittances should be made to the Company and receipt in the Company’s Official form obtained.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellR);

		Cell cellS = new Cell(0, 2);
		cellS.setBorder(Border.NO_BORDER);
		cellS.add(new Paragraph("(c)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellS);

		Cell cellT = new Cell();
		cellT.setBorder(Border.NO_BORDER);
		cellT.add(new Paragraph(
				"You are also not authorized to grant credit, except strictly in accordance with instructions that you may be issued with. In respect of any unauthorized collections, you will be acting as an Agent of the party concerned and not as an Agent of the Company and you alone will be answerable to the party for the consequences of such unauthorized action.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellT);

		info.startNewRow();

		Cell cellU = new Cell(0, 2);
		cellU.setBorder(Border.NO_BORDER);
		cellU.add(new Paragraph("(d)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellU);

		Cell cellV = new Cell();
		cellV.setBorder(Border.NO_BORDER);
		cellV.add(new Paragraph(
				"Further, you are prohibited from allowing or offering to allow either directly or indirectly as an inducement to any person to effect or renew a policy of insurance any rebate of the whole or part of the commission payable.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellV);

		info.startNewRow();

		Cell page = new Cell(0, 3);
		page.setBorder(Border.NO_BORDER);
		page.add(new Paragraph("page 1 of 3 ").setFontSize(9).setTextAlignment(TextAlignment.CENTER).setFont(fontB));
		info.addCell(page);

		info.startNewRow();

		Cell emty = new Cell(0, 3);
		emty.setBorder(Border.NO_BORDER);
		emty.add(new Paragraph("").setFontSize(11).setItalic().setHeight(30).setFont(font));
		info.addCell(emty);

		Cell cellW = new Cell();
		cellW.setBorder(Border.NO_BORDER);
		cellW.add(new Paragraph("5.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cellW);

		Cell cellX = new Cell(0, 2);
		cellX.setBorder(Border.NO_BORDER);
		cellX.add(new Paragraph("Lien on money due from the Company").setFontSize(11).setFont(fontB)
				.setCharacterSpacing(1));
		info.addCell(cellX);

		Cell emty3 = new Cell();
		emty3.setBorder(Border.NO_BORDER);
		emty3.add(new Paragraph("").setFontSize(11).setItalic().setFont(font));
		info.addCell(emty3);

		Cell cellY = new Cell(0, 2);
		cellY.setBorder(Border.NO_BORDER);
		cellY.add(new Paragraph(
				"The Company will have a first lien and charge on all sums payable to you whether this appointment or any previous appointment with the insurer or insurers whose business has been taken over by the Company, for recovery of any indebtedness due to the company or the insurer or insurers as aforesaid, and may apply any such sums directly towards realization of such debts.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellY);

		info.startNewRow();

		Cell cellZ = new Cell();
		cellZ.setBorder(Border.NO_BORDER);
		cellZ.add(new Paragraph("6.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cellZ);

		Cell cella = new Cell(0, 3);
		cella.setBorder(Border.NO_BORDER);
		cella.add(new Paragraph("Renewal of policies, maintenance of records and lapsed policies").setFontSize(11)
				.setFont(fontB).setCharacterSpacing(1));
		info.addCell(cella);

		info.startNewRow();

		Cell cellb = new Cell(0, 2);
		cellb.setBorder(Border.NO_BORDER);
		cellb.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellb);

		Cell cellc = new Cell();
		cellc.setBorder(Border.NO_BORDER);
		cellc.add(new Paragraph(
				"In addition to securing new business, you are expected to ensure that the Policies   you introduce are kept in force by payment of premiums on the due date/s to the Company. You are also required to maintain the Value Persistency Retention of 80% in the 1st year and 70% thereafter."
						+ "You should also maintain records of the Policies introduced by you with particulars of the dates on which renewal premiums fall due and produce same for inspection as and when it is requested by the Company.")
								.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10)
								.setFont(font));
		info.addCell(cellc);

		info.startNewRow();

		Cell celld = new Cell(0, 2);
		celld.setBorder(Border.NO_BORDER);
		celld.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(celld);

		Cell celle = new Cell();
		celle.setBorder(Border.NO_BORDER);
		celle.add(new Paragraph(
				"If the premium in respect of a life policy remains unpaid and the policy lapses, the Company shall have the right to remove such policy from your account and transfer it to any other Agent as the Company may deem necessary. In the event of such transfer, you will be not entitled to receive any payments and / or commissions arising from the premiums paid after such date of transfer.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(celle);

		info.startNewRow();

		Cell cellf = new Cell();
		cellf.setBorder(Border.NO_BORDER);
		cellf.add(new Paragraph("7.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cellf);

		Cell cellg = new Cell(0, 3);
		cellg.setBorder(Border.NO_BORDER);
		cellg.add(new Paragraph("Indemnifying Company against incorrect advice").setFontSize(11).setFont(fontB)
				.setCharacterSpacing(1));
		info.addCell(cellg);

		info.startNewRow();

		Cell cellh = new Cell(0, 2);
		cellh.setBorder(Border.NO_BORDER);
		cellh.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellh);

		Cell celli = new Cell();
		celli.setBorder(Border.NO_BORDER);
		celli.add(new Paragraph(
				"You shall acquire and maintain a standard of knowledge and competence considered to be essential in order to enable you to function efficiently and effectively. You shall attend all seminars, conferences and training programmes arranged by the Company from time to time whenever required by the Company to do so. Your failure to attend any such seminars, conference and training programmes without reason acceptable to the Company will be treated as a breach of the terms and conditions of this agreement.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(celli);

		info.startNewRow();

		Cell cellj = new Cell(0, 2);
		cellj.setBorder(Border.NO_BORDER);
		cellj.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellj);

		Cell cellk = new Cell();
		cellk.setBorder(Border.NO_BORDER);
		cellk.add(new Paragraph(
				"You shall keep the Company indemnified against all claims, losses, costs, charges, and expenses which the Company may suffer by reason of any act or omission of incorrect advice, deficiency or short coming on your part in the course of any transaction with regard to insurance business.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellk);

		info.startNewRow();

		Cell celll = new Cell();
		celll.setBorder(Border.NO_BORDER);
		celll.add(new Paragraph("8.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(celll);

		Cell cellm = new Cell(0, 3);
		cellm.setBorder(Border.NO_BORDER);
		cellm.add(new Paragraph("Prohibition against functioning as an agent for others or transfer of agency")
				.setFont(fontB).setFontSize(11).setCharacterSpacing(1));
		info.addCell(cellm);

		info.startNewRow();

		Cell celln = new Cell(0, 2);
		celln.setBorder(Border.NO_BORDER);
		celln.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(celln);

		Cell cello = new Cell();
		cello.setBorder(Border.NO_BORDER);
		cello.add(new Paragraph("You cannot function as an Agent for any other insurer, principal agent or broker.")
				.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cello);

		info.startNewRow();

		Cell cellp = new Cell(0, 2);
		cellp.setBorder(Border.NO_BORDER);
		cellp.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellp);

		Cell cellq = new Cell();
		cellq.setBorder(Border.NO_BORDER);
		cellq.add(new Paragraph("You shall not be permitted to assign/transfer your Agency to any other person.")
				.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellq);

		info.startNewRow();

		Cell cellr = new Cell();
		cellr.setBorder(Border.NO_BORDER);
		cellr.add(new Paragraph("9.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cellr);

		Cell cells = new Cell(0, 2);
		cells.setBorder(Border.NO_BORDER);
		cells.add(new Paragraph("Not an employee").setFontSize(11).setCharacterSpacing(1).setFont(fontB));
		info.addCell(cells);

		Cell emty4 = new Cell();
		emty4.setBorder(Border.NO_BORDER);
		emty4.add(new Paragraph("").setFontSize(11).setItalic().setFont(font));
		info.addCell(emty4);

		Cell cellt = new Cell(0, 2);
		cellt.setBorder(Border.NO_BORDER);
		cellt.add(new Paragraph(
				"You are not an employee of the Company and shall not be entitled to any benefits or emoluments statutory or otherwise to which an employee of the Company is entitled.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellt);

		info.startNewRow();

		Cell cellu = new Cell();
		cellu.setBorder(Border.NO_BORDER);
		cellu.add(new Paragraph("10.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cellu);

		Cell cellv = new Cell(0, 3);
		cellv.setBorder(Border.NO_BORDER);
		cellv.add(new Paragraph("Maintenance of confidentiality and code of conduct").setFontSize(11)
				.setCharacterSpacing(1).setFont(fontB));
		info.addCell(cellv);

		info.startNewRow();

		Cell cellw = new Cell(0, 2);
		cellw.setBorder(Border.NO_BORDER);
		cellw.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellw);

		Cell cellx = new Cell();
		cellx.setBorder(Border.NO_BORDER);
		cellx.add(new Paragraph(
				"You shall observe and maintain secrecy in respect of all transactions of the Company and shall not disclose to any person, body or entity, any confidential information relating to the Company and / or policy holders   acquired by you in the capacity of an Agent of the Company.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellx);

		info.startNewRow();

		Cell celly = new Cell(0, 2);
		celly.setBorder(Border.NO_BORDER);
		celly.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(celly);

		Cell cellz = new Cell();
		cellz.setBorder(Border.NO_BORDER);
		cellz.add(new Paragraph(
				"You are required to comply with all regulations and code of conduct applicable to the Agents under the provisions of Control of Insurance Act and the regulations made there under.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellz);

		Cell cell10c = new Cell(0, 2);
		cell10c.setBorder(Border.NO_BORDER);
		cell10c.add(new Paragraph("(c)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell10c);

		Cell cell10Cdes = new Cell();
		cell10Cdes.setBorder(Border.NO_BORDER);
		cell10Cdes.add(new Paragraph(
				"You should at all times and in all places conduct yourself with propriety and decorum consistent with this agreement and shall not be guilty of intemperate behaviour, of any act or conduct which may reflect adversely on your ability or integrity or which may cause loss or damage to the Company, its business, property or reputation.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell10Cdes);

		info.startNewRow();

		Cell cell10d = new Cell(0, 2);
		cell10d.setBorder(Border.NO_BORDER);
		cell10d.add(new Paragraph("(d)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell10d);

		Cell cell10des = new Cell();
		cell10des.setBorder(Border.NO_BORDER);
		cell10des.add(new Paragraph(
				"You will be required to adhere to the Code of Conduct and Administrative Regulations made by the Company from time to time.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell10des);

		info.startNewRow();

		Cell cell11 = new Cell();
		cell11.setBorder(Border.NO_BORDER);
		cell11.add(new Paragraph("11.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cell11);

		Cell cell11T = new Cell(0, 2);
		cell11T.setBorder(Border.NO_BORDER);
		cell11T.add(new Paragraph("Reasons for automatic disqualification and termination").setFontSize(11)
				.setCharacterSpacing(1).setFont(fontB));
		info.addCell(cell11T);

		Cell emty5 = new Cell();
		emty5.setBorder(Border.NO_BORDER);
		emty5.add(new Paragraph("").setFontSize(11).setItalic().setFont(font));
		info.addCell(emty5);

		Cell cell11des = new Cell(0, 2);
		cell11des.setBorder(Border.NO_BORDER);
		cell11des.add(new Paragraph(
				"Your contract as an Agent will stand automatically terminated if at any time  you become subject to any of the disqualification enumerated below, effective as from the date of commencement of such disqualification, namely  :-	")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell11des);

		info.startNewRow();

		Cell cell11a = new Cell(0, 2);
		cell11a.setBorder(Border.NO_BORDER);
		cell11a.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell11a);

		Cell cell11aDes = new Cell();
		cell11aDes.setBorder(Border.NO_BORDER);
		cell11aDes.add(
				new Paragraph("if you have been or have become legally incapable of entering into legal transactions")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell11aDes);

		info.startNewRow();

		Cell cell11b = new Cell(0, 2);
		cell11b.setBorder(Border.NO_BORDER);
		cell11b.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell11b);

		Cell cell11bDes = new Cell();
		cell11bDes.setBorder(Border.NO_BORDER);
		cell11bDes.add(new Paragraph(
				"if you become physically incapable of discharging the obligations and duties referred to herein")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell11bDes);

		info.startNewRow();

		Cell cell11C = new Cell(0, 2);
		cell11C.setBorder(Border.NO_BORDER);
		cell11C.add(new Paragraph("(c)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell11C);

		Cell cell11CDes = new Cell();
		cell11CDes.setBorder(Border.NO_BORDER);
		cell11CDes.add(new Paragraph("if you  are  declared insolvent by  a  court of competent jurisdiction")
				.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell11CDes);

		Cell cell11D = new Cell(0, 2);
		cell11D.setBorder(Border.NO_BORDER);
		cell11D.add(new Paragraph("(d)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell11D);

		Cell cell11DDes = new Cell();
		cell11DDes.setBorder(Border.NO_BORDER);
		cell11DDes.add(new Paragraph("if you are found to be of unsound mind by a Court of competent jurisdiction")
				.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell11DDes);

		Cell cell11E = new Cell(0, 2);
		cell11E.setBorder(Border.NO_BORDER);
		cell11E.add(new Paragraph("(e)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell11E);

		Cell cell11EDes = new Cell();
		cell11EDes.setBorder(Border.NO_BORDER);
		cell11EDes.add(new Paragraph("if you are convicted by a court of law").setFontSize(11)
				.setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell11EDes);

		Cell cell11F = new Cell(0, 2);
		cell11F.setBorder(Border.NO_BORDER);
		cell11F.add(new Paragraph("(f)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell11F);

		Cell cell11FDes = new Cell();
		cell11FDes.setBorder(Border.NO_BORDER);
		cell11FDes.add(new Paragraph("if in the opinion of the Company you have committed fraud").setFontSize(11)
				.setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell11FDes);

		info.startNewRow();

		Cell emtLine = new Cell(0, 3);
		emtLine.setBorder(Border.NO_BORDER);
		emtLine.add(new Paragraph("").setFontSize(11).setItalic().setFont(font));
		info.addCell(emtLine);

		Cell cell12 = new Cell();
		cell12.setBorder(Border.NO_BORDER);
		cell12.add(new Paragraph("12.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cell12);

		Cell cell12T = new Cell(0, 3);
		cell12T.setBorder(Border.NO_BORDER);
		cell12T.add(new Paragraph("Termination").setFontSize(11).setCharacterSpacing(1).setFont(fontB));
		info.addCell(cell12T);

		info.startNewRow();

		Cell cell12A = new Cell(0, 2);
		cell12A.setBorder(Border.NO_BORDER);
		cell12A.add(new Paragraph("(a)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell12A);

		Cell cell12ADes = new Cell();
		cell12ADes.setBorder(Border.NO_BORDER);
		cell12ADes.add(new Paragraph(
				"This agreement shall be valid until such time it is terminated by either party giving one month’s notice in writing to the other party or upon the death of the Agent.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell12ADes);

		info.startNewRow();

		Cell cell12B = new Cell(0, 2);
		cell12B.setBorder(Border.NO_BORDER);
		cell12B.add(new Paragraph("(b)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell12B);

		Cell cell12Bdes = new Cell();
		cell12Bdes.setBorder(Border.NO_BORDER);
		cell12Bdes.add(new Paragraph(
				"In the event of your acting in a manner detrimental to the interests of the Company or in violation of any of the provisions herein contained (of which the Company shall be the sole judge), the Company reserves the right of terminating your agency forthwith without notice and you shall not be entitled to claim any money by way damages.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell12Bdes);

		Cell cell12C = new Cell(0, 2);
		cell12C.setBorder(Border.NO_BORDER);
		cell12C.add(new Paragraph("(c)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell12C);

		Cell cell12CDes = new Cell();
		cell12CDes.setBorder(Border.NO_BORDER);
		cell12CDes.add(new Paragraph(
				"If your performance is unsatisfactory or you are shown to have been negligent in the conduct of your business or you are otherwise found to be incapable of discharging your duties satisfactorily.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell12CDes);

		info.startNewRow();

		Cell cell12D = new Cell(0, 2);
		cell12D.setBorder(Border.NO_BORDER);
		cell12D.add(new Paragraph("(d)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell12D);

		Cell cell12DDes = new Cell();
		cell12DDes.setBorder(Border.NO_BORDER);
		cell12DDes.add(new Paragraph(
				"On termination of your agency at any time and for any reason whatsoever, you will have no claim against the company for any remuneration or compensation except that you will become entitled to settlement of your account as at the date of termination, and the benefits that may accrue to you according to law. ")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell12DDes);

		info.startNewRow();

		Cell cell12E = new Cell(0, 2);
		cell12E.setBorder(Border.NO_BORDER);
		cell12E.add(new Paragraph("(e)").setFontSize(11).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell12E);

		Cell cell12EDes = new Cell();
		cell12EDes.setBorder(Border.NO_BORDER);
		cell12EDes.add(new Paragraph(
				"Upon termination of this agreement you shall immediately return to the Company all materials and documents issued to you by the Company.")
						.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell12EDes);

		info.startNewRow();

		Cell cell13 = new Cell();
		cell13.setBorder(Border.NO_BORDER);
		cell13.add(new Paragraph("13.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cell13);

		Cell cell13T = new Cell(0, 2);
		cell13T.setBorder(Border.NO_BORDER);
		cell13T.add(new Paragraph("Advertisements and other publicity").setFontSize(11).setFont(fontB)
				.setCharacterSpacing(1));
		info.addCell(cell13T);

		Cell emty6 = new Cell();
		emty6.setBorder(Border.NO_BORDER);
		emty6.add(new Paragraph("").setFontSize(11).setItalic().setFont(font));
		info.addCell(emty6);

		Cell cell13Des = new Cell(0, 2);
		cell13Des.setBorder(Border.NO_BORDER);
		cell13Des.add(new Paragraph(
				"You shall not advertise, print, publish or distribute any material including business cards"
						+ "in which any reference has been made to the Company and / or its products, without the Company’s prior written approval. This is especially so as you are not an employee of the Company.")
								.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10)
								.setFont(font));
		info.addCell(cell13Des);

		info.startNewRow();

		Cell cell14 = new Cell();
		cell14.setBorder(Border.NO_BORDER);
		cell14.add(new Paragraph("14.").setFontSize(11).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(cell14);

		Cell cell14T = new Cell(0, 2);
		cell14T.setBorder(Border.NO_BORDER);
		cell14T.add(new Paragraph("Changes in the above terms").setFontSize(11).setFont(fontB).setCharacterSpacing(1));
		info.addCell(cell14T);

		Cell emty7 = new Cell();
		emty7.setBorder(Border.NO_BORDER);
		emty7.add(new Paragraph("").setFontSize(11).setItalic().setFont(font));
		info.addCell(emty7);

		Cell cell14Des = new Cell(0, 2);
		cell14Des.setBorder(Border.NO_BORDER);
		cell14Des.add(new Paragraph(
				"You shall not advertise, print, publish or distribute any material including business cards"
						+ "in which any reference has been made to the Company and / or its products, without the Company’s prior written approval. This is especially so as you are not an employee of the Company.")
								.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10)
								.setFont(font));
		info.addCell(cell14Des);

		document.add(info);

		document.add(new Paragraph("\n"));

		document.add(new Paragraph("Yours faithfully,").setFontSize(11).setCharacterSpacing(1).setFont(font));

		document.add(new Paragraph("\n"));

		document.add(new Paragraph("...........................................").setFontSize(11).setCharacterSpacing(1)
				.setFont(font));
		document.add(new Paragraph("Melanga A. Doolwala").setFontSize(11).setCharacterSpacing(1).setFixedLeading(1)
				.setFont(font));
		document.add(new Paragraph("Deputy General Manager - Operations").setFontSize(11).setCharacterSpacing(1)
				.setFixedLeading(1).setFont(font));

		document.add(new Paragraph(""));

		final SolidLine lineDrawer = new SolidLine(1f);
		document.add(new LineSeparator(lineDrawer));

		document.add(new Paragraph(""));

		document.add(new Paragraph(
				"I having read the above terms and conditions, accept to abide by them and consent for the agreement.")
						.setFontSize(11).setCharacterSpacing(1).setFixedLeading(10).setFont(font)
						.setTextAlignment(TextAlignment.LEFT));

		document.add(new Paragraph("\n"));

		float[] endColsWidths = { 100, 20 };
		Table signTbl = new Table(endColsWidths);

		Cell sign = new Cell();
		sign.setBorder(Border.NO_BORDER);
		sign.add(new Paragraph("Signature").setFontSize(11).setFont(font));
		signTbl.addCell(sign);

		Cell signDes = new Cell();
		signDes.setBorder(Border.NO_BORDER);
		signDes.add(new Paragraph("....................................").setFontSize(11).setFont(font));
		signTbl.addCell(signDes);

		Cell et1 = new Cell(0, 2);
		et1.setBorder(Border.NO_BORDER);
		et1.add(new Paragraph("").setFontSize(11).setFont(font));
		signTbl.addCell(et1);

		Cell nme = new Cell();
		nme.setBorder(Border.NO_BORDER);
		nme.add(new Paragraph("Name").setFontSize(11).setFont(font));
		signTbl.addCell(nme);

		Cell nmeDes = new Cell();
		nmeDes.setBorder(Border.NO_BORDER);
		nmeDes.add(new Paragraph("....................................").setFontSize(11).setFont(font));
		signTbl.addCell(nmeDes);

		Cell et2 = new Cell(0, 2);
		et2.setBorder(Border.NO_BORDER);
		et2.add(new Paragraph("").setFontSize(11).setFont(font));
		signTbl.addCell(et2);

		Cell date = new Cell();
		date.setBorder(Border.NO_BORDER);
		date.add(new Paragraph("Date ").setFontSize(11).setFont(font));
		signTbl.addCell(date);

		Cell dateDes = new Cell();
		dateDes.setBorder(Border.NO_BORDER);
		dateDes.add(new Paragraph("....................................").setFontSize(11).setFont(font));
		signTbl.addCell(dateDes);

		document.add(signTbl);

		// document.add(new Paragraph("Page 3 of 3").setFixedPosition(280, 10,
		// 500).setFontSize(9).setFont(fontB));

		int n1 = 2;

		int n = pdf.getNumberOfPages();

		document.showTextAligned(new Paragraph(String.format("page %s of %s", 3, n)).setFontSize(9).setFont(fontB), 280,
				20, n, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);

		document.showTextAligned(new Paragraph(String.format("page %s of %s", 2, n)).setFontSize(9).setFont(fontB), 280,
				20, n1, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);

		//

		document.close();

		return baos.toByteArray();
	}

	@Override
	public byte[] createTravelling(Integer usrCode) throws Exception {

		Resource FontResource1 = new ClassPathResource("FONTDIR/times.ttf");
		File FONTNORMAL = FontResource1.getFile();

		Resource FontResource2 = new ClassPathResource("FONTDIR/timesbd.ttf");
		File FONTBOLD = FontResource2.getFile();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		DecimalFormat formatter = new DecimalFormat("###,###.00");

		// final String DEST = "./src/main/resources/traveling.pdf";

		// final String FONTNORMAL = "./src/main/resources/FONTDIR/times.ttf";
		// final String FONTBOLD = "./src/main/resources/FONTDIR/timesbd.ttf";

		PdfFont font = PdfFontFactory.createFont(FONTNORMAL.getPath(), PdfEncodings.IDENTITY_H);
		PdfFont fontB = PdfFontFactory.createFont(FONTBOLD.getPath(), PdfEncodings.IDENTITY_H);

		PdfWriter writer = new PdfWriter(baos);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf, PageSize.A4);
		// document.setTopMargin(45);
		// document.setBottomMargin(20);
		document.setMargins(70, 70, 70, 70);

		DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy - MM - dd");
		LocalDate currentDate = LocalDate.now();

		document.add(new Paragraph(currentDate.format(datePattern)).setFontSize(11).setFont(font));

		document.add(new Paragraph("\n"));

		String title = "";
		String shortName = "";
		String address1 = "";
		String address2 = "";
		String agentCode = "";
		String designation = "";
		Double allowance = 0.0;

		UserAppoinmentHelper userAppoinmentHelper = userAppoinmentDao.findByAgtCod(usrCode);

		title = userAppoinmentHelper.getAgentTitle() != null ? userAppoinmentHelper.getAgentTitle() : "";
		shortName = userAppoinmentHelper.getShortName() != null ? userAppoinmentHelper.getShortName().toUpperCase()
				: "";
		address1 = userAppoinmentHelper.getAddress1() != null ? userAppoinmentHelper.getAddress1() : "";
		address2 = userAppoinmentHelper.getAddress2() != null ? userAppoinmentHelper.getAddress2() : "";
		agentCode = userAppoinmentHelper.getAgtCod() != null ? userAppoinmentHelper.getAgtCod() : "";
		designation = userAppoinmentHelper.getDesignation() != null
				? userAppoinmentHelper.getDesignation().toUpperCase()
				: "";
		allowance = userAppoinmentHelper.getAgentAllowance() != null ? userAppoinmentHelper.getAgentAllowance()
				: allowance;

		document.add(new Paragraph(title + " . " + shortName).setFontSize(12).setFixedLeading(1).setFont(font));
		document.add(new Paragraph(address1).setFontSize(12).setFixedLeading(1).setFont(font));
		document.add(new Paragraph(address2).setFontSize(12).setFixedLeading(1).setFont(font));

		document.add(new Paragraph(""));

		document.add(new Paragraph("ANNEXURE TO AGREEMENT – " + designation + " (Code No." + agentCode + ") DATED "
				+ currentDate.format(datePattern)).setFontSize(11).setCharacterSpacing(1).setFixedLeading(10)
						.setFont(fontB));

		document.add(new Paragraph(
				"In addition to the terms and conditions stated in the above referred agreement, you will be reimbursed with the following amount until further notice, subject to minimum level of performance of Incentive Objective as indicated in the challenge sheet.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));

		document.add(new Paragraph(""));

		document.add(new Paragraph(" • Travel Reimbursement / Allowance of Rs. " + formatter.format(allowance))
				.setFixedLeading(10).setFontSize(12).setCharacterSpacing(1).setMarginLeft(50).setFont(font));

		document.add(new Paragraph(
				"The above reimbursement shall be made at the total discretion of the Company who may opt to withhold / withdraw it at any given time without prior notice.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));

		document.add(new Paragraph("\n"));

		float[] infoTblColsWidths = { 50, 50, 800 };
		Table info = new Table(infoTblColsWidths);

		Cell cell1 = new Cell(0, 3);
		cell1.setBorder(Border.NO_BORDER);
		cell1.add(new Paragraph("Other Conditions").setFontSize(12).setCharacterSpacing(1)
				.setTextAlignment(TextAlignment.LEFT).setFont(fontB).setBorder(Border.NO_BORDER));
		info.addCell(cell1);

		info.startNewRow();

		Cell cell2 = new Cell(0, 2);
		cell2.setBorder(Border.NO_BORDER);
		cell2.add(new Paragraph("1. ").setFontSize(12).setCharacterSpacing(1).setMarginLeft(20).setFont(fontB));
		info.addCell(cell2);

		Cell cell3 = new Cell();
		cell3.setBorder(Border.NO_BORDER);
		cell3.add(new Paragraph(" In your capacity as a/an " + designation.toUpperCase()
				+ " you are required to identify and recommend suitable individuals to be recruited as Insurance Consultants / Agents as applicable. As their Unit Leader you shall direct, motivate, lead and control a unit of the required manpower at any given month.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell3);

		info.startNewRow();

		Cell cell4 = new Cell(0, 2);
		cell4.setBorder(Border.NO_BORDER);
		cell4.add(new Paragraph("2. ").setFontSize(12).setCharacterSpacing(1).setMarginLeft(20).setFont(fontB));
		info.addCell(cell4);

		Cell cell5 = new Cell();
		cell5.setBorder(Border.NO_BORDER);
		cell5.add(new Paragraph(
				" Your nomination will be valid for a period of one year commencing from 01-06-2018 which shall be ipso facto renewed at the end of every 12 months’ period unless the company notifies you of it’s cancellation.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell5);

		info.startNewRow();

		Cell cell6 = new Cell(0, 2);
		cell6.setBorder(Border.NO_BORDER);
		cell6.add(new Paragraph("3. ").setFontSize(12).setCharacterSpacing(1).setMarginLeft(20).setFont(fontB));
		info.addCell(cell6);

		Cell cell7 = new Cell();
		cell7.setBorder(Border.NO_BORDER);
		cell7.add(new Paragraph(
				" The Company however reserves the right to terminate your nomination at any point of time without adducing any reason and / or prior notice.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cell7);

		info.startNewRow();

		Cell cell8 = new Cell(0, 3);
		cell8.setBorder(Border.NO_BORDER);
		cell8.add(new Paragraph("4. ").setFontSize(12).setCharacterSpacing(1).setMarginLeft(20).setFont(fontB));
		info.addCell(cell8);

		Cell cell4a = new Cell(0, 2);
		cell4a.setBorder(Border.NO_BORDER);
		cell4a.add(new Paragraph("(a)").setFontSize(12).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cell4a);

		Cell cell9 = new Cell();
		cell9.setBorder(Border.NO_BORDER);
		cell9.add(new Paragraph(
				"You shall generate a minimum amount of business as per the attached objectives setting sheet.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setMarginLeft(30)
						.setFont(font));
		info.addCell(cell9);

		info.startNewRow();

		Cell celletya = new Cell();
		celletya.setBorder(Border.NO_BORDER);
		celletya.add(new Paragraph("").setFontSize(12).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(celletya);

		Cell celletyb = new Cell();
		celletyb.setBorder(Border.NO_BORDER);
		celletyb.add(new Paragraph("").setFontSize(12).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(celletyb);

		Cell cellA = new Cell();
		cellA.setBorder(Border.NO_BORDER);
		cellA.add(new Paragraph(
				"Individual Sales Category shall be entitled for an incentive of 10% on achievement in excess of the Incentive Objective.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setMarginLeft(30)
						.setFont(font));
		info.addCell(cellA);

		info.startNewRow();

		Cell cellB = new Cell(0, 3);
		cellB.setBorder(Border.NO_BORDER);
		cellB.add(new Paragraph("OR").setFontSize(12).setCharacterSpacing(1).setTextAlignment(TextAlignment.CENTER)
				.setMarginLeft(100).setFont(fontB));
		info.addCell(cellB);

		info.startNewRow();

		Cell celletyc = new Cell();
		celletyc.setBorder(Border.NO_BORDER);
		celletyc.add(new Paragraph("").setFontSize(12).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(celletyc);

		Cell celletyd = new Cell();
		celletyd.setBorder(Border.NO_BORDER);
		celletyd.add(new Paragraph("").setFontSize(12).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(celletyd);

		Cell cell4c = new Cell();
		cell4c.setBorder(Border.NO_BORDER);
		cell4c.add(new Paragraph(
				"Unit Sales Category shall be entitled for an incentive of a percentage based on commission income of the Insurance Consultants subject to the number of active IC’s in the unit and achievement of the monthly Incentive Objective.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setMarginLeft(30).setFixedLeading(10)
						.setFont(font));
		info.addCell(cell4c);

		info.startNewRow();

		Cell cellC = new Cell(0, 2);
		cellC.setBorder(Border.NO_BORDER);
		cellC.add(new Paragraph("(b)").setFontSize(12).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellC);

		Cell cellD = new Cell();
		cellD.setBorder(Border.NO_BORDER);
		cellD.add(new Paragraph(
				"The objectives will be set and / or reset from time to time at the sole discretion of the Company. All deficits in target / income will be carried over.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setMarginLeft(30).setFixedLeading(10)
						.setFont(font));
		info.addCell(cellD);

		info.startNewRow();

		Cell cellE = new Cell(0, 2);
		cellE.setBorder(Border.NO_BORDER);
		cellE.add(new Paragraph("(c)").setFontSize(12).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellE);

		Cell cellF = new Cell();
		cellF.setBorder(Border.NO_BORDER);
		cellF.add(new Paragraph(
				"Target credit in respect of specific types of policies will be as determined by the Company from time to time.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setMarginLeft(30).setFixedLeading(10)
						.setFont(font));
		info.addCell(cellF);

		info.startNewRow();

		Cell cellI = new Cell(0, 2);
		cellI.setBorder(Border.NO_BORDER);
		cellI.add(new Paragraph("(d)").setFontSize(12).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellI);

		Cell cellJ = new Cell();
		cellJ.setBorder(Border.NO_BORDER);
		cellJ.add(new Paragraph(
				"The calculation of incentive payments will be generally done at the end of every calendar month. When incentives are paid, the excess production / incentive will not be carried over for future incentive calculations.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setMarginLeft(30).setFixedLeading(10)
						.setFont(font));
		info.addCell(cellJ);

		info.startNewRow();

		Cell cellK = new Cell(0, 2);
		cellK.setBorder(Border.NO_BORDER);
		cellK.add(new Paragraph("(e)").setFontSize(12).setTextAlignment(TextAlignment.RIGHT).setFont(fontB));
		info.addCell(cellK);

		Cell cellL = new Cell();
		cellL.setBorder(Border.NO_BORDER);
		cellL.add(new Paragraph(
				"You will be entitled to commission on your personal business, subject to having achieved your cumulative unit target. If you are below the target at any given time, commission will be withheld until such time you have achieved your cumulative target.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setMarginLeft(30).setFixedLeading(10)
						.setHeight(80).setFont(font));
		info.addCell(cellL);

		info.startNewRow();

		Cell cellM = new Cell(0, 2);
		cellM.setBorder(Border.NO_BORDER);
		cellM.add(new Paragraph("5.").setFontSize(12).setMarginLeft(20).setFont(fontB));
		info.addCell(cellM);

		Cell cellN = new Cell();
		cellN.setBorder(Border.NO_BORDER);
		cellN.add(new Paragraph(
				"The Company however reserves the right either to withhold the incentive/commission or to cancel your nomination in the event of your failure to fulfill your objectives / targets.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellN);

		info.startNewRow();

		Cell cellW = new Cell(0, 2);
		cellW.setBorder(Border.NO_BORDER);
		cellW.add(new Paragraph("6.").setFontSize(12).setMarginLeft(20).setFont(fontB));
		info.addCell(cellW);

		Cell cellX = new Cell();
		cellX.setBorder(Border.NO_BORDER);
		cellX.add(new Paragraph(
				"The conditions contained herein shall have effect in addition to and not in substitution for the conditions stated in your agreement.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellX);

		info.startNewRow();

		Cell cellZ = new Cell(0, 2);
		cellZ.setBorder(Border.NO_BORDER);
		cellZ.add(new Paragraph("7.").setFontSize(12).setMarginLeft(20).setFont(fontB));
		info.addCell(cellZ);

		Cell cellb = new Cell();
		cellb.setBorder(Border.NO_BORDER);
		cellb.add(new Paragraph(
				"Nothing contained in this letter shall be construed so as to constitute or to create a contract of employment with the Company.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellb);

		info.startNewRow();

		Cell cellf = new Cell(0, 2);
		cellf.setBorder(Border.NO_BORDER);
		cellf.add(new Paragraph("8.").setFontSize(12).setMarginLeft(20).setFont(fontB));
		info.addCell(cellf);

		Cell cellh = new Cell();
		cellh.setBorder(Border.NO_BORDER);
		cellh.add(new Paragraph(
				"If you do not achieve the set objectives and / or if you fail to build your unit by recruiting the required number of Insurance Consultants / Agents as applicable, the Company reserves the right to terminate your contract and hence your continuation of the agreement will depend entirely on the fulfillment of your set objectives and / or the required recruitments.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cellh);

		info.startNewRow();

		Cell celll = new Cell(0, 2);
		celll.setBorder(Border.NO_BORDER);
		celll.add(new Paragraph("").setFontSize(12).setBorder(Border.NO_BORDER).setFont(fontB));
		info.addCell(celll);

		Cell cello = new Cell();
		cello.setBorder(Border.NO_BORDER);
		cello.add(new Paragraph(
				"Whilst taking this opportunity to congratulate you on your contract, we are confident that you will reach the set objectives set for yourself as described in the attached objectives setting sheet.")
						.setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED).setFixedLeading(10).setFont(font));
		info.addCell(cello);

		document.add(info);

		document.add(new Paragraph("\n"));

		document.add(new Paragraph("Yours faithfully,").setFontSize(12).setCharacterSpacing(1).setFont(font));

		document.add(new Paragraph("\n"));

		document.add(new Paragraph("...........................................").setFontSize(12).setCharacterSpacing(1)
				.setFont(font));
		document.add(new Paragraph("Melanga A. Doolwala").setFontSize(12).setCharacterSpacing(1).setFixedLeading(1)
				.setFont(font));
		document.add(new Paragraph("Deputy General Manager - Operations").setFontSize(12).setCharacterSpacing(1)
				.setFixedLeading(1).setFont(font));

		document.add(new Paragraph(""));

		final SolidLine lineDrawer = new SolidLine(1f);
		document.add(new LineSeparator(lineDrawer));

		document.add(new Paragraph(""));

		document.add(new Paragraph(
				"I having read the above terms and conditions, accept to abide by them and consent for the agreement.")
						.setFontSize(12).setCharacterSpacing(1).setFixedLeading(10).setFont(font)
						.setTextAlignment(TextAlignment.LEFT));

		document.add(new Paragraph("\n"));

		float[] endColsWidths = { 100, 20 };
		Table signTbl = new Table(endColsWidths);

		Cell sign = new Cell();
		sign.setBorder(Border.NO_BORDER);
		sign.add(new Paragraph("Signature").setFontSize(12).setFont(font));
		signTbl.addCell(sign);

		Cell signDes = new Cell();
		signDes.setBorder(Border.NO_BORDER);
		signDes.add(new Paragraph("....................................").setFontSize(12).setFont(font));
		signTbl.addCell(signDes);

		Cell et1 = new Cell(0, 2);
		et1.setBorder(Border.NO_BORDER);
		et1.add(new Paragraph("").setFontSize(12).setFont(font));
		signTbl.addCell(et1);

		Cell nme = new Cell();
		nme.setBorder(Border.NO_BORDER);
		nme.add(new Paragraph("Name").setFontSize(12).setFont(font));
		signTbl.addCell(nme);

		Cell nmeDes = new Cell();
		nmeDes.setBorder(Border.NO_BORDER);
		nmeDes.add(new Paragraph("....................................").setFontSize(12).setFont(font));
		signTbl.addCell(nmeDes);

		Cell et2 = new Cell(0, 2);
		et2.setBorder(Border.NO_BORDER);
		et2.add(new Paragraph("").setFontSize(12).setFont(font));
		signTbl.addCell(et2);

		Cell date = new Cell();
		date.setBorder(Border.NO_BORDER);
		date.add(new Paragraph("Date ").setFontSize(12).setFont(font));
		signTbl.addCell(date);

		Cell dateDes = new Cell();
		dateDes.setBorder(Border.NO_BORDER);
		dateDes.add(new Paragraph("....................................").setFontSize(12).setFont(font));
		signTbl.addCell(dateDes);

		document.add(signTbl);

		int n1 = 1;

		int n = pdf.getNumberOfPages();

		document.showTextAligned(new Paragraph(String.format("page %s of %s", 1, n)).setFontSize(9).setFont(fontB), 280,
				40, n1, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);

		document.showTextAligned(new Paragraph(String.format("page %s of %s", 2, n)).setFontSize(9).setFont(fontB), 280,
				40, n, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);

		document.close();

		return baos.toByteArray();
	}

}
