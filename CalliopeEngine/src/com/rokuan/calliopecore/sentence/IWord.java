package com.rokuan.calliopecore.sentence;


/**
 * Created by LEBEAU Christophe on 22/02/2015.
 */
public interface IWord {
	String getValue();

	IVerbConjugation getVerbInfo();

	void setVerbInfo(IVerbConjugation verbInfo);

	public LanguageInfo getLanguageInfo();

	public ColorInfo getColorInfo();

	public CountryInfo getCountryInfo();

	public CityInfo getCityInfo();

	public TransportInfo getTransportInfo();

	public UnitInfo getUnitInfo();

	public CharacterInfo getCharacterInfo();

	public PlaceInfo getPlaceInfo();

	public ITimePreposition getTimePreposition();

	public IPlacePreposition getPlacePreposition();

	public IWayPreposition getWayPreposition();

	public IPurposePreposition getPurposePreposition();

	public CustomObject getCustomObject();

	public CustomPlace getCustomPlace();
	
	public CustomPerson getCustomPerson();

	public CustomMode getCustomMode();
}
