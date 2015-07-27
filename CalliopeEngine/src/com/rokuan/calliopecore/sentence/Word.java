package com.rokuan.calliopecore.sentence;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject.DateContext;

/**
 * Created by LEBEAU Christophe on 22/02/2015.
 */
@DatabaseTable(tableName = "words")
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
		PLACE_PREPOSITION,
		
		OTHER
	}
	
	public static final String WORD_FIELD_NAME = "value";
	public static final String TYPES_FIELD_NAME = "types";

	@DatabaseField(columnName = WORD_FIELD_NAME, id = true, uniqueIndex = true)
	private String value;
	@DatabaseField(columnName = TYPES_FIELD_NAME, dataType = DataType.SERIALIZABLE)
	private HashSet<WordType> types = new HashSet<WordType>();
	private VerbConjugation verbInfo;
	private LanguageInfo langInfo;
	private CountryInfo countryInfo;
	private CityInfo cityInfo;
	private DateContext datePreposition;
	private PlaceContext placePreposition;
	private CustomObject customObject;
	private CustomPlace customPlace;
	
	public Word(){
		
	}
	
	private Word(String v){
		value = v;
	}

	public Word(String v, WordType t){
		this(v);
		types.add(t);
	}

	public Word(String v, List<WordType> ts){
		this(v);
		
		if(ts != null){
			types.addAll(ts);
		}
	}
	
	public Word(String v, Set<WordType> ts){
		this(v);
		
		if(ts != null){
			types.addAll(ts);
		}
	}

	public Word(String v, WordType... ts){
		this(v);
		
		for(WordType t: ts){
			types.add(t);
		}
	}

	public boolean isOfType(WordType type){
		if(type == WordType.ANY){
			return true;
		}

		return types.contains(type);
	}
	
	public void addType(WordType t){
		types.add(t);
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
	
	public String toString(){
		StringBuilder builder = new StringBuilder(value);
		
		builder.append('(');
		
		for(WordType ty: types){
			builder.append(ty);
			builder.append(',');
		}
		
		builder.deleteCharAt(builder.length() - 1);
		builder.append(')');
		
		return builder.toString();
	}
}
