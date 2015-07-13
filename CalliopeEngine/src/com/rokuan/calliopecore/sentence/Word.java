package com.rokuan.calliopecore.sentence;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject.DateContext;

/**
 * Created by LEBEAU Christophe on 22/02/2015.
 */
public class Word {
	public enum WordType {
		ANY,
		PROPER_NAME,
		COMMON_NAME,
		ADVERB,
		VERB,
		ADJECTIVE,
		ONOMATOPEIA,
		PREPOSITION,
		CONJUNCTION,
		AUXILIARY,
		DEMONSTRATIVE_PRONOUN,
		DEFINITE_ARTICLE,
		INDEFINITE_ADJECTIVE,
		INDEFINITE_PRONOUN,
		INTERROGATIVE_PRONOUN,
		RELATIVE_PRONOUN,
		NUMERICAL_ADJECTIVE,
		NUMBER,
		NUMERICAL_POSITION,
		DEMONSTRATIVE_ADJECTIVE,
		PERSONAL_PRONOUN,
		INDEFINITE_ARTICLE,
		POSSESSIVE_PRONOUN,
		POSSESSIVE_ADJECTIVE,
		EUPHONIOUS_LINK,
		INTERROGATIVE_ADJECTIVE,
		PREPOSITION_FROM,
		PREPOSITION_TO,
		DATE_MONTH,
		DATE,
		DATE_UNIT,
		TIME,
		// New
		QUANTITY,
		PREPOSITION_BETWEEN,	// entre
		PREPOSITION_AND,	// et
		PREPOSITION_AT,	// à/au
		PREPOSITION_OF,	// de
		PREPOSITION_IN,	// en
		PREPOSITION_WITH,	// avec
		CONJUGATION_LINK,	// t
		SUPERLATIVE,	// moins/plus
		TARGET_PRONOUN,	// moi/toi/me/te/...
		DATE_UNIT_HOUR,	// midi/minuit
		MEAN_OF_TRANSPORT, // pied/voiture/bus/avion
		PLACE_TYPE,	// restaurant/cinéma/...

		CITY,
		COUNTRY,
		FIRSTNAME,
		LANGUAGE,
		PERSON,
		PLACE,
		COLOR,
		OBJECT,
		ADDITIONAL_PLACE,

		CONTRACTED,

		TIME_PREPOSITION,
		PLACE_PREPOSITION
	}

	private Set<WordType> types = new HashSet<WordType>();
	private String value;
	private VerbConjugation verbInfo;
	private LanguageInfo langInfo;
	private CountryInfo countryInfo;
	private CityInfo cityInfo;
	private DateContext datePreposition;
	private PlaceContext placePreposition;
	private CustomObject customObject;
	private CustomPlace customPlace;

	public Word(String v, WordType t){
		types.add(t);
		value = v;
	}

	public Word(String v, List<WordType> ts){
		if(ts != null){
			types.addAll(ts);
		}

		value = v;
	}

	public Word(String v, WordType... ts){
		for(WordType t: ts){
			types.add(t);
		}

		value = v;
	}

	public boolean isOfType(WordType type){
		if(type == WordType.ANY){
			return true;
		}

		return types.contains(type);
	}

	public Set<WordType> getTypes(){
		return types;
	}

	public String getValue() {
		return value;
	}

	public VerbConjugation getVerbInfo() {
		return verbInfo;
	}

	public void setVerbInfo(VerbConjugation verbInfo) {
		this.verbInfo = verbInfo;
	}

	public LanguageInfo getLanguageInfo() {
		return langInfo;
	}

	public void setLanguageInfo(LanguageInfo langInfo) {
		this.langInfo = langInfo;
	}

	public CountryInfo getCountryInfo() {
		return countryInfo;
	}

	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}

	public CityInfo getCityInfo() {
		return cityInfo;
	}

	public void setCityInfo(CityInfo cityInfo) {
		this.cityInfo = cityInfo;
	}

	public DateContext getDatePreposition() {
		return datePreposition;
	}

	public void setDatePreposition(DateContext datePreposition) {
		this.datePreposition = datePreposition;
	}

	public PlaceContext getPlacePreposition() {
		return placePreposition;
	}

	public void setPlacePreposition(PlaceContext placePreposition) {
		this.placePreposition = placePreposition;
	}

	public CustomObject getCustomObject() {
		return customObject;
	}

	public void setCustomObject(CustomObject customObject) {
		this.customObject = customObject;
	}

	public CustomPlace getCustomPlace() {
		return customPlace;
	}

	public void setCustomPlace(CustomPlace customPlace) {
		this.customPlace = customPlace;
	}
}
