package com.thmr.polar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**  
* Java Polar SDK based on the Polar Flow API
*   
* @author Thomas Mayer
* @version 1.0 
*/ 
public class PolarSDK
{
	private CloseableHttpClient httpclient;
	private String userId;

	public PolarSDK()
	{
		super();
		httpclient = HttpClients.custom()
				.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()).build();
	}

	public boolean login(String email, String password)
	{
		boolean status = false;

		try
		{
			HttpPost loginRequest = new HttpPost(Config.LOGIN_URL);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("password", password));
			params.add(new BasicNameValuePair("email", email));
			loginRequest.setEntity(new UrlEncodedFormEntity(params));

			CloseableHttpResponse response1 = httpclient.execute(loginRequest);

			try
			{
				System.out.println(response1.getStatusLine());

				if (response1.getStatusLine().getStatusCode() == 200)
				{
					// extract user id
					Header[] header = response1.getAllHeaders();

					for (int i = 0; i < header.length; i++)
					{
						Header h = header[i];

						if (h.getName().equals("Set-Cookie"))
						{
							String v = h.getValue();
							int begin = v.indexOf("userId") + 7;
							int end = v.indexOf(";", begin);
							userId = v.substring(begin, end);
						}
					}

					status = true;
				}
			}
			finally
			{
				response1.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return status;
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param sportIds
	 *            filter for trainings, leave blank if all trainings should be
	 *            returned
	 * @return
	 */
	public TrainingList getTrainingData(Date fromDate, Date toDate, int[] sportIds)
	{
		TrainingList trainingList = new TrainingList();

		try
		{
			HttpPost trainingDataRequest = new HttpPost(Config.TRAINING_URL);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fromDateStr = sdf.format(fromDate);
			String toDateStr = sdf.format(toDate);
			String sportIdsStr = Arrays.toString(sportIds);
			String json = "{\"userId\":" + userId + ",\"fromDate\":\"" + fromDateStr + "\",\"toDate\":\"" + toDateStr
					+ "\",\"sportIds\":" + sportIdsStr + "}";
			StringEntity entityUserId = new StringEntity(json);

			trainingDataRequest.addHeader("Content-Type", "application/json");
			entityUserId.setContentType("application/json");
			trainingDataRequest.setEntity(entityUserId);

			CloseableHttpResponse trainingDataResponse = httpclient.execute(trainingDataRequest);
			try
			{
				// TODO remove
				System.out.println(trainingDataResponse.getStatusLine());

				HttpEntity trainingDataEntity = trainingDataResponse.getEntity();

				if (trainingDataEntity != null)
				{
					String trainingDataEntityString = EntityUtils.toString(trainingDataEntity);
					System.out.println(trainingDataEntityString); // TODO remove

					ObjectMapper mapper1 = new ObjectMapper();
					trainingList
							.setTrainings(Arrays.asList(mapper1.readValue(trainingDataEntityString, Training[].class)));
				}

			}
			finally
			{
				trainingDataResponse.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return trainingList;
	}
	
	public boolean downloadCSV(Training t, OutputStream os, boolean decompress)
	{
		return downloadFile(Config.CSV_URL + "/" + t.getId(), os, decompress);
	}

	public boolean downloadGPX(Training t, OutputStream os, boolean decompress)
	{
		return downloadFile(Config.GPX_URL + "/" + t.getId(), os, decompress);
	}
			
	public boolean downloadTCX(Training t, OutputStream os, boolean decompress)
	{
		return downloadFile(Config.TCX_URL + "/" + t.getId(), os, decompress);
	}

	private boolean downloadFile(String baseUrl, OutputStream os, boolean decompress)
	{
		String url = baseUrl + "?compress=true";

		try
		{
			CloseableHttpResponse response = httpclient.execute(new HttpGet(url));
			HttpEntity entity = response.getEntity();

			if (entity != null)
			{
				if (decompress)
				{
					ZipInputStream zis = new ZipInputStream(entity.getContent());
					ZipEntry ze = zis.getNextEntry();
					byte[] buffer = new byte[1024];

					while (ze != null)
					{

						int len;
						
						while ((len = zis.read(buffer)) > 0)
						{
							os.write(buffer, 0, len);
						}

						ze = zis.getNextEntry();
					}

				}
				else
				{
					entity.writeTo(os);
				}
			}
		}
		catch (Exception e)
		{
			// TODO fix
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
