/* $Id$ */

package com.insta.integration.accounting.zoho.books.api;

import com.insta.integration.accounting.zoho.books.model.Organization;
import com.insta.integration.accounting.zoho.books.model.OrganizationList;
import com.insta.integration.accounting.zoho.books.parser.SettingsParser;
import com.insta.integration.accounting.zoho.books.util.ZohoHTTPClient;

import java.util.HashMap;


/**

* OrganizationsApi is used to create an Organization.

* It is used to get and update the details of an organization.
  
* Get the list of organizations for the user.

*/

public class OrganizationsApi extends API
{
	private static String url = baseURL+"/organizations"; //No I18N

	
	/**
	
	* Construct a new OrganizationsApi using user's authtoken.
	
	* @param authToken user's authToken. 
	
	*/

	public OrganizationsApi(String authToken)
	{
		
		super(authToken, "");
		
	}
	
	
	private SettingsParser settingsParser = new SettingsParser();
	
	
	/**
	
	* Get the list of organizations.
	
	* It returns the OrganizationList object.
	
	* @return Returns the OrganizationList object.
	
	*/
	
	public OrganizationList getOrganizations()throws Exception
	{
		
		String response = ZohoHTTPClient.get(url, getQueryMap());
		
		OrganizationList organizationList = settingsParser.getOrganizations(response);
		
		return organizationList;
	}
	
	/**
	
	* Get the details of an organization.
	
	* Pass the organizationId to get the detatils of a particular organization.
	
	* It returns the Organization object.
	
	
	* @param organizationid  ID of an organization.
	
	* @return Returns the Organization object.
	
	*/
	
	public Organization get(String organizationid)throws Exception
	{
		
		String urlString = url+"/"+organizationid;
		
		String response = ZohoHTTPClient.get(urlString, getQueryMap());
		
		Organization organization = settingsParser.getOrganization(response);
		
		return organization;
	}
	
	/**
	
	* Create an organization.
	
	* Pass the Organization object to create a new organization for the user.
	
	* The Organization object which contains name, currencyId, and timeZone are the mandatory parameters.
	
	* It returns the Organization object.
	
	
	* @param organization Organization object.
	
	* @return Returns the Organization object.
	
	*/
	
	public Organization create(Organization organization)throws Exception
	{
		
		HashMap<String, Object> requestBody = getQueryMap();
		
		requestBody.put("JSONString", organization.toJSON().toString());
		
		String response = ZohoHTTPClient.post(url, requestBody);
		
		return settingsParser.getOrganization(response);
	}
	
	/**
	
	* Update the details of an organization.
	
	* Pass the Organization object to update the details of an organization.
	
	* The Organization object must contain the organizationId for which organization details has to be updated. 
	
	* It returns the Organization object.
	
	
	* @param organization Organization object.
	
	* @return Returns the Organization object.
	
	*/
	
	public Organization update(Organization organization)throws Exception
	{
		
		String urlString = url+"/"+organization.getOrganizationId();
		
		HashMap<String, Object> requestBody = getQueryMap();
		
		requestBody.put("JSONString", organization.toJSON().toString());
		
		String response = ZohoHTTPClient.put(urlString, requestBody);
		
		return settingsParser.getOrganization(response);
	}
	
}
