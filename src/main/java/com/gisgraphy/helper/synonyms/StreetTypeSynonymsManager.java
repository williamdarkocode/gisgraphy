package com.gisgraphy.helper.synonyms;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gisgraphy.helper.Language;

public class StreetTypeSynonymsManager {
	
	public static final Logger logger = LoggerFactory.getLogger(StreetTypeSynonymsManager.class);

	private static StreetTypeSynonymsManager instance = new StreetTypeSynonymsManager();
	
	private  Map<String,SynonymsFinder> synonymsFinders = new HashMap<String, SynonymsFinder>();
	
	 private static final String FILENAME_SUFFIX = "_street_types.txt";
	
	private StreetTypeSynonymsManager() {
		for (Language language : Language.values()){
			try {
				String lang = language.toString();
				SynonymsFinder sf = new SynonymsFinder("synonyms/"+lang.toLowerCase()+FILENAME_SUFFIX);
				logger.info("loading synonyms for "+lang);
				synonymsFinders.put(lang.toUpperCase(), sf);
			} catch (Exception e) {
				//ignore
			}
		}
		
	}
	
	public static StreetTypeSynonymsManager getInstance(){
		return instance ;
	}
	
	public  SynonymsFinder getSynonymsFinderFromLanguage(String lang){
		if (lang !=null){
			return synonymsFinders.get(lang.toUpperCase());
		}
		return null;
	}
	public  SynonymsFinder getSynonymsFinderFromLanguage(Language lang){
		if (lang !=null){
			return synonymsFinders.get(lang.toString().toUpperCase());
		}
		return null;
	}

}
