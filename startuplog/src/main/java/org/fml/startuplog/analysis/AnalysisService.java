package org.fml.startuplog.analysis;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.fml.startuplog.model.AnalysisData;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AnalysisService {

	private List<List<String>> recordList;
	private List<String> record;
	private static final String FILE_PATH = "/csvfile/downloadtemplate/template.csv";
	private String dataDirectory;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("startuplog");
	public AnalysisService() {

	}

	private String validateContentFormat(String file) throws IOException {

		try {
			int lineNumber = 0;
			recordList = new ArrayList<List<String>>();
			record = new ArrayList<String>();
			FileReader fileReader = new FileReader(file);
			CSVParser parser = new CSVParser(fileReader, CSVFormat.RFC4180);

			for (CSVRecord csvRecord : parser) {

				lineNumber++;

				if (lineNumber == 1) {
					if (csvRecord.get(0).toLowerCase().equals("seller"))
					{
						if (csvRecord.get(1).toLowerCase().equals("customer"))
						{
							if (csvRecord.get(2).toLowerCase().equals("sales"))
							{
								if (csvRecord.get(3).toLowerCase().equals("region"))
								{
								}
								else
									return "Wrong header at column: 4 Expected: Region";
							}
							else
								return "Wrong header at column: 3 Expected: Sales";
						}
						else
							return "Wrong header at column: 2 Expected: Customer";
					}
					else
						return "Wrong header at column: 1 Expected: Seller";

				} else {

					if (csvRecord.size() != 4) {
						return "Column missing at row: " + lineNumber;
					} else {
						if (csvRecord.get(0) instanceof String) {
							if (csvRecord.get(1) instanceof String) {
								try {
									Integer.valueOf(csvRecord.get(2).toString());
									if (csvRecord.get(3) instanceof String) {

										record.add(csvRecord.get(0));
										record.add(csvRecord.get(1));
										record.add(csvRecord.get(2));
										record.add(csvRecord.get(3));
										recordList.add(record);
										record = new ArrayList<String>();
									} else
										return "Format Error at row: " + lineNumber + " column: 4";
								} catch (NumberFormatException nfe) {
									return "Format Error at row: " + lineNumber + " column: 3";
								}
							} else
								return "Format Error at row: " + lineNumber + " column: 2";
						} else
							return "Format Error at row: " + lineNumber + " column: 1";

					}
				}

			}
			if (lineNumber > 0)
				return "perfect";
			
			return "No record in the file";
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	private boolean isCsv(String file) {
		if (file.substring(file.lastIndexOf('.') + 1).equals("csv"))
			return true;

		return false;
	}

	public List<List<String>> getRecords() {
		return recordList;
	}

	public void download(HttpServletRequest request, HttpServletResponse response, String mediaType)
			throws IOException {
		dataDirectory = request.getServletContext().getRealPath("/WEB-INF/");
		File file = getFile(dataDirectory);
		InputStream in = new FileInputStream(file);

		response.setContentType(mediaType);
		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		response.setHeader("Content-Length", String.valueOf(file.length()));
		FileCopyUtils.copy(in, response.getOutputStream());
	}

	private File getFile(String dataDirectory) throws FileNotFoundException {

		File file = new File(dataDirectory + FILE_PATH);
		if (!file.exists()) {
			throw new FileNotFoundException("file with path: " + dataDirectory + FILE_PATH + " was not found.");
		}
		return file;
	}

	public String uploadFile(HttpServletRequest request, MultipartFile file) {
		dataDirectory = request.getServletContext().getRealPath("/WEB-INF/csvfile/upload/");
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				File dir = new File(dataDirectory);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				if (isCsv(dir.getAbsolutePath() + File.separator + file.getOriginalFilename())) {
					String result = validateContentFormat(
							dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
					return result;
				} else
					return "Please Upload file in csv format";
			} catch (Exception e) {
				return file.getOriginalFilename() + " => " + e.getMessage();
			}
		} else {
			return "empty file";
		}
	}
	
	public String storeToDb() throws Exception
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q1 = em.createNativeQuery("DELETE FROM analysis_data");
		q1.executeUpdate();
		tx.commit();
		em.close();
		storeDataAsync().call();
		return "done";
	}
	/**
	 * Retrieve stat data from db on basis of JPQL asynchronously
	 * 
	 * @return List<Prime>
	 * 
	 */
	public Callable<Boolean> storeDataAsync() {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call () throws Exception {
            	EntityManager em = emf.createEntityManager();
            	EntityTransaction tx = em.getTransaction();
            	AnalysisData data;
            	
            	int i = 1;
            	List<List<String>> recordList = getRecords();
            	
            	tx.begin();
            	for (List<String> record :recordList)
            	{
            		data = new AnalysisData();
            		data.setId(i);
            		data.setSeller(record.get(0));
            		data.setCustomer(record.get(1));
            		data.setSales(Double.valueOf(record.get(2)));
            		data.setRegion(record.get(3));
            		em.persist(data);

            		i++;
            	}
            	tx.commit();
            	em.close();
            	
        		return true;
            }
        };
        return callable;

	}

}
