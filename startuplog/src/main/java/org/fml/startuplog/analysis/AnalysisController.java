package org.fml.startuplog.analysis;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AnalysisController {

	private static final String APPLICATION_CSV = "application/csv";

	@Autowired
	AnalysisService csvService;

	@RequestMapping(value = "/analysis", method = RequestMethod.GET)
	public String showDownloadUploadUI() {
		return "preanalysis";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET, produces = APPLICATION_CSV)
	@ResponseBody
	public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) throws IOException {

		csvService.download(request, response, APPLICATION_CSV);

	}

	@RequestMapping(value = "/analysis", method = RequestMethod.POST)
	public String uploadTemplate(@RequestParam MultipartFile file, HttpServletRequest request, ModelMap model)
			throws Exception {
		if (csvService.uploadFile(request, file).equals("perfect")) {
			if (csvService.storeToDb().equals("done")) {
				model.put("recordList", csvService.getRecords());
				return "analysis";
			}
			else
			{
				model.put("uploadResult", "Error: " + "Failed to store data to db"+ "!!");
				return "preanalysis";
			}
		} else {
			model.put("uploadResult", "Error: " + csvService.uploadFile(request, file) + "!!");
			return "preanalysis";
		}
	}

}
