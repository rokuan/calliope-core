package com.rokuan.calliopecore.sentence;


/**
 * Created by LEBEAU Christophe on 22/02/2015.
 */
public interface IWord {
	String getValue();

	IVerbConjugation getVerbInfo();

	void setVerbInfo(IVerbConjugation verbInfo);

	public LanguageInfo getLanguageInfo();

	public void setLanguageInfo(LanguageInfo langInfo);

	public ColorInfo getColorInfo();

	public void setColorInfo(ColorInfo colorInfo);

	public CountryInfo getCountryInfo();

	public void setCountryInfo(CountryInfo countryInfo);

	public CityInfo getCityInfo();

	public void setCityInfo(CityInfo cityInfo);

	public TransportInfo getTransportInfo();

	public void setTransportInfo(TransportInfo transportInfo);

	public UnitInfo getUnitInfo();

	public void setUnitInfo(UnitInfo unitInfo);

	public CharacterInfo getCharacterInfo();

	public void setCharacterInfo(CharacterInfo characterInfo);

	public PlaceInfo getPlaceInfo();

	public void setPlaceInfo(PlaceInfo placeInfo);

	public TimePreposition getTimePreposition();

	public void setTimePreposition(TimePreposition timePreposition);

	public PlacePreposition getPlacePreposition();

	public void setPlacePreposition(PlacePreposition placePreposition);

	public WayPreposition getWayPreposition();

	public void setWayPreposition(WayPreposition wayPreposition);

	public PurposePreposition getPurposePreposition();

	public void setPurposePreposition(PurposePreposition purposePreposition);

	public CustomObject getCustomObject();

	public void setCustomObject(CustomObject customObject);

	public CustomPlace getCustomPlace();

	public void setCustomPlace(CustomPlace customPlace);
	
	public CustomPerson getCustomPerson();

	public void setCustomPerson(CustomPerson customPerson);

	public CustomMode getCustomMode();

	public void setCustomMode(CustomMode customMode);
}
