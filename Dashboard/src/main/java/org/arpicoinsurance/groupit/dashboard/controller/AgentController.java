package org.arpicoinsurance.groupit.dashboard.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.arpicoinsurance.groupit.dashboard.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@CrossOrigin(origins = "*")
public class AgentController {

	@Autowired
	private AgentService agentService;

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "F://temp//";

	@RequestMapping(method = RequestMethod.GET, value = "/getagent/{agentCode:.+}")
	public ResponseEntity<Object> getAgent(@PathVariable String agentCode) {
	   //System.out.println(agentCode + "Get agent");
		try {
			return new ResponseEntity<Object>(agentService.getAgent(agentCode), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/uploadProf") // //new annotation since 4.3
	public ResponseEntity<Object> singleFileUpload(@RequestParam("image") MultipartFile file, RedirectAttributes redirectAttributes) {

//		System.out.println("called");

		if (file.isEmpty()) {
			return new ResponseEntity<Object>("noFile", HttpStatus.OK);
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();

			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>("redirect:/uploadStatus", HttpStatus.OK);
	}

	@RequestMapping(path = "/downloadProfPic", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> getImage() throws IOException {

		File file = new File("F://temp//Lighthouse.jpg");

		String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));

		Map<String, String> jsonMap = new HashMap<>();

		jsonMap.put("content", encodeImage);
		return new ResponseEntity<Object>(jsonMap, HttpStatus.OK);
	}

	/*
	 * public ResponseEntity<Resource> download(String param) throws IOException {
	 * 
	 * File file2Upload = new File("F://temp//Penguins.jpg"); HttpHeaders headers =
	 * new HttpHeaders(); headers.add("Cache-Control",
	 * "no-cache, no-store, must-revalidate"); headers.add("Pragma", "no-cache");
	 * headers.add("Expires", "0"); InputStreamReader i = new InputStreamReader(new
	 * FileInputStream(file2Upload));
	 * System.out.println("The length of the file is : " + file2Upload.length());
	 * 
	 * Path path = Paths.get(file2Upload.getAbsolutePath()); ByteArrayResource
	 * resource = new ByteArrayResource(Files.readAllBytes(path));
	 * 
	 * return
	 * ResponseEntity.ok().headers(headers).contentLength(file2Upload.length())
	 * .contentType(MediaType.parseMediaType("image/png")).body(resource); }
	 */

}
