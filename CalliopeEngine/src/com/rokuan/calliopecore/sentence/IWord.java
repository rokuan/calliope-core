package com.rokuan.calliopecore.sentence;


/**
 * Created by LEBEAU Christophe on 22/02/2015.
 */
public interface IWord {
	String getValue();

	IVerbConjugation getVerbInfo();

	INameInfo getNameInfo();
	
	IAdjectiveInfo getAdjectiveInfo();
	
	ILanguageInfo getLanguageInfo();

	IColorInfo getColorInfo();

	ICountryInfo getCountryInfo();

	ICityInfo getCityInfo();

	ITransportInfo getTransportInfo();

	IUnitInfo getUnitInfo();

	ICharacterInfo getCharacterInfo();

	IPlaceInfo getPlaceInfo();

	ITimePreposition getTimePreposition();

	IPlacePreposition getPlacePreposition();

	IWayPreposition getWayPreposition();

	IPurposePreposition getPurposePreposition();

	CustomObject getCustomObject();

	CustomPlace getCustomPlace();
	
	CustomPerson getCustomPerson();

	CustomMode getCustomMode();
}
