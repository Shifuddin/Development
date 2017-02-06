package org.fml.startuplog.survey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.fml.startuplog.model.PhaseModel;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("startuplog");
	
	
	public int [] phase(HttpServletRequest request) {
		int[][] arr = new int[5][7];
 
		try {

			if (!request.getParameter("rf_1").equals("null")) {
				arr[0][0] = arr[0][0] + 1;
				arr[0][1] = arr[0][1] + 1;
			}
		} catch (Exception e) {
		}

		try {

			if (request.getParameter("rf_2").equals("on")) {
				arr[0][1] = arr[0][1] + 1;
				arr[0][1] = arr[0][2] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("rf_3").equals("on")) {
				arr[0][3] = arr[0][3] + 1;
			}
		} catch (Exception e) {
		}

		try {
			if (request.getParameter("rf_4").equals("on")) {
				arr[0][4] = arr[0][4] + 1;
				arr[0][5] = arr[0][5] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("rf_5").trim().equals("on")) {
				arr[0][5] = arr[0][5] + 1;
				arr[0][6] = arr[0][6] + 1;
			}
		} catch (Exception e) {
		}
		
		// standardization
		
		try {

			if (!request.getParameter("st_1").equals("null")) {
				arr[1][0] = arr[1][0] + 1;
				arr[1][1] = arr[1][1] + 1;
			}
		} catch (Exception e) {
		}

		try {

			if (request.getParameter("st_2").equals("on")) {
				arr[1][2] = arr[1][2] + 1;
				arr[1][3] = arr[1][3] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("st_3").equals("on")) {
				arr[1][4] = arr[1][4] + 1;
				arr[1][5] = arr[1][5] + 1;
			}
		} catch (Exception e) {
		}

		try {
			if (request.getParameter("st_4").equals("on")) {
				arr[1][5] = arr[1][5] + 1;
				arr[1][6] = arr[1][6] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("st_5").trim().equals("on")) {
				arr[1][6] = arr[1][6] + 1;
				
			}
		} catch (Exception e) {
		}
		
		//organizational structure
		
		try {

			if (!request.getParameter("os_1").equals("null")) {
				arr[2][0] = arr[2][0] + 1;
				arr[2][1] = arr[2][1] + 1;
			}
		} catch (Exception e) {
		}

		try {

			if (request.getParameter("os_2").equals("on")) {
				arr[2][2] = arr[2][2] + 1;
				arr[2][3] = arr[2][3] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("os_3").equals("on")) {
				arr[2][3] = arr[2][3] + 1;
				arr[2][4] = arr[2][4] + 1;
				arr[2][5] = arr[2][5] + 1;
			}
		} catch (Exception e) {
		}

		try {
			if (request.getParameter("os_4").equals("on")) {
				arr[2][5] = arr[2][5] + 1;
				arr[2][6] = arr[2][6] + 1;
			}
		} catch (Exception e) {
		}
		
		// Market uncertainty
		
		try {

			if (!request.getParameter("mu_1").equals("null")) {
				arr[3][0] = arr[3][0] + 1;
				
			}
		} catch (Exception e) {
		}

		try {

			if (request.getParameter("mu_2").equals("on")) {
				arr[3][1] = arr[3][1] + 1;
				
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("mu_3").equals("on")) {
				arr[3][2] = arr[3][2] + 1;
			}
		} catch (Exception e) {
		}

		try {
			if (request.getParameter("mu_4").equals("on")) {
				arr[3][3] = arr[3][3] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("mu_5").equals("on")) {
				arr[3][4] = arr[3][4] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("mu_6").equals("on")) {
				arr[3][5] = arr[3][5] + 1;
				arr[3][5] = arr[3][5] + 1;
			}
		} catch (Exception e) {
		}
		
		// Technology readiness
		
		try {

			if (!request.getParameter("tr_1").equals("null")) {
				arr[4][0] = arr[4][0] + 1;
				
			}
		} catch (Exception e) {
		}

		try {

			if (request.getParameter("tr_2").equals("on")) {
				arr[4][0] = arr[4][0] + 1;
				
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("tr_3").equals("on")) {
				arr[4][1] = arr[4][1] + 1;
			}
		} catch (Exception e) {
		}

		try {
			if (request.getParameter("tr_4").equals("on")) {
				arr[4][2] = arr[4][2] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("tr_5").equals("on")) {
				arr[4][2] = arr[4][2] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("tr_6").equals("on")) {
				arr[4][3] = arr[4][3] + 1;
			}
		} catch (Exception e) {
		}
		try {
			if (request.getParameter("tr_7").equals("on")) {
				arr[4][4] = arr[4][4] + 1;
				arr[4][5] = arr[4][5] + 1;
				arr[4][6] = arr[4][6] + 1;
			}
		} catch (Exception e) {
		}
		
		String urPhase = "";
		int [] result = new int [7];
		int tempSum;
		for (int j = 0; j < 7; j++) {
			tempSum = 0;
			for(int i = 0; i < 5; i++)
			{
				tempSum = tempSum + arr[i][j];
			}
			result [j] = tempSum;
		}
		int max = result[0];
		
		for (int i = 0; i < 7; i++)
		{
			if (result[i] > max)
			{
				max = result[i];
			}
		}
		
		int activePhase[] = new int[7];
		for (int i = 0; i < 7; i++)
		{
			if (result[i] == max)
			{
				activePhase[i] = 1;				
			}
		}

		return activePhase;
	}
	public String saveStage(String stage) throws Exception
	{
		storeDataAsync(stage).call();
		return "done";
	}
	/**
	 * Retrieve stat data from db on basis of JPQL asynchronously
	 * 
	 * @return List<Prime>
	 * 
	 */
	public Callable<Boolean> storeDataAsync(String stage) {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call () throws Exception {
            	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            	Date date = new Date();
            	Date today = formatter.parse(formatter.format(date));
            	
            	EntityManager em = emf.createEntityManager();
            	EntityTransaction tx = em.getTransaction();
            	
            	PhaseModel phaseModel = new PhaseModel();
            	
            	tx.begin();
            	phaseModel.setUser("shifuddin");
            	phaseModel.setDate(today.toString());
            	phaseModel.setStage(stage);
            	em.persist(phaseModel);

            	tx.commit();
            	em.close();
            	
        		return true;
            }
        };
        return callable;

	}
	public List<PhaseModel> currentStage(int number)
	{
		try {
			List<PhaseModel> list = retrieveStageAsync().call();
			
			for(PhaseModel p:list)
			{
				String [] arr = p.getStage().split(":");
				String phase = "";
				for(int i = 0; i < arr.length; i++)
				{
					if (arr[i].equals("true"))
					{
						switch(i)
						{
						case 0:
							phase = phase + "Pre-Seed, ";
							break;
						case 1:
							phase = phase + "Seed, ";
							break;
						case 2:
							phase = phase + "Startup, ";
							break;
						case 3:
							phase = phase + "First Phase, ";
							break;
						case 4:
							phase = phase + "Second Phase, ";
							break;
						case 5:
							phase = phase + "Third Phase, ";
							break;
						case 6:
							phase = phase + "Forth Phase, ";
							break;
						
						}
						
					}
					
				}
				phase = phase.substring(0, phase.lastIndexOf(","));
				p.setStage(phase);
			}
			
			Collections.reverse(list);
			List<PhaseModel> copyList = new ArrayList<PhaseModel>();
			
			if (list.size() > number)
			{
				for(int i = 0; i < number; i++)
				{
					copyList.add(list.get(i));
					
				}
				return copyList;
			}
			else
			{
				return list;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	private Callable<List<PhaseModel>> retrieveStageAsync() {
        Callable<List<PhaseModel>> callable = new Callable<List<PhaseModel>>() {
            @Override
            public List<PhaseModel> call () throws Exception {
            	EntityManager em = emf.createEntityManager();
        		Query query = em.createQuery("SELECT p FROM " + PhaseModel.class.getName()+" p");
        		return query.getResultList();
            }
        };
        return callable;

	}
}
