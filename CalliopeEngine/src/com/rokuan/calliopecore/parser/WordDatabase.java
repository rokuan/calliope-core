package com.rokuan.calliopecore.parser;

import com.rokuan.calliopecore.sentence.CharacterInfo;
import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.ColorInfo;
import com.rokuan.calliopecore.sentence.CountryInfo;
import com.rokuan.calliopecore.sentence.CustomMode;
import com.rokuan.calliopecore.sentence.CustomObject;
import com.rokuan.calliopecore.sentence.CustomPerson;
import com.rokuan.calliopecore.sentence.CustomPlace;
import com.rokuan.calliopecore.sentence.IVerbConjugation;
import com.rokuan.calliopecore.sentence.LanguageInfo;
import com.rokuan.calliopecore.sentence.PlaceInfo;
import com.rokuan.calliopecore.sentence.PlacePreposition;
import com.rokuan.calliopecore.sentence.PurposePreposition;
import com.rokuan.calliopecore.sentence.TimePreposition;
import com.rokuan.calliopecore.sentence.TransportInfo;
import com.rokuan.calliopecore.sentence.UnitInfo;
import com.rokuan.calliopecore.sentence.WayPreposition;
import com.rokuan.calliopecore.sentence.IWord;

public interface WordDatabase {
	boolean wordStartsWith(String q);

	IWord findWord(String q);
	
	LanguageInfo findLanguageInfo(String q);
	ColorInfo findColorInfo(String q);
	CityInfo findCityInfo(String q);
	CountryInfo findCountryInfo(String q);
	TransportInfo findTransportInfo(String q);
	UnitInfo findUnitInfo(String q);
	CharacterInfo findCharacterInfo(String q);
	PlaceInfo findPlaceInfo(String q);
	
	CustomObject findCustomObject(String q);
	CustomPlace findCustomPlace(String q);
	CustomMode findCustomMode(String q);
	CustomPerson findCustomPerson(String q);
	
	PlacePreposition findPlacePreposition(String q);
	TimePreposition findTimePreposition(String q);
	WayPreposition findWayPreposition(String q);
	PurposePreposition findPurposePreposition(String q);
	
	IVerbConjugation findConjugation(String q);
}
