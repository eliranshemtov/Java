package model;

import java.util.HashMap;

import model.domains.CommonSearchDomain;
import model.domains.MazeDomain;
import model.domains.SlidePuzzleDomain;

/**
 * SearchDomainsFactory class contains hashMap of available Domains <br>
 * this class maps between the String domain name to the relevant domain
 */
public class SearchDomainsFactory {
	
	private HashMap<String, DomainCreator> domains;
	
	/**
	 * default c'tor <br>
	 * initialize the hashMap and adds to it all it's properties using factory pattern
	 *
	 */
	public SearchDomainsFactory() {
		domains = new HashMap<String, DomainCreator>();
		domains.put("MazeDomain".toLowerCase(), new MazeDomainCreator());
		domains.put("SlidePuzzleDomain".toLowerCase(), new SlidePuzzleDomainCreator());
	}
	
	/**
	 * gets String domain name and creates an instance of the suitable domain <br>
	 * this method return the instance of the domain which was created.<br>
	 * if there isn't suitable domain the method will return null. 
	 * @param domainName (String)
	 * @param arguments (String)
	 * @return {@link CommonSearchDomain}
	 */
	public CommonSearchDomain createDomain(String domainName, String arguments)
	{
		DomainCreator creator = domains.get(domainName);
		if (creator != null)
			return creator.create(arguments);
		return null;
	}

	/**
	 * this interface define the function - "create()" which create an instance of an domain and returns it
	 */
	private interface DomainCreator
	{
		/**
		 * the function create an instance of the relevant domain and returns it
		 * @return CommonSearchDomain 
		 */
		public CommonSearchDomain create(String domainArguments); 
	}
	
	/**
	 * The Class implements {@link DomainCreator} <br>
	 * create Maze Domain
	 */
	private class MazeDomainCreator implements DomainCreator
	{
		@Override
		public CommonSearchDomain create(String domainArguments) {
			return new MazeDomain(domainArguments);
		}
		
	}
	
	/**
	 * The Class implements {@link DomainCreator} <br>
	 * create slide puzzle Domain
	 */
	private class SlidePuzzleDomainCreator implements DomainCreator
	{
		@Override
		public CommonSearchDomain create(String domainArguments) {
			return new SlidePuzzleDomain(domainArguments);
		}
	}	
	
}
