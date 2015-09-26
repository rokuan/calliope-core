package com.rokuan.calliopecore.json;

import com.google.gson.GsonBuilder;
import com.rokuan.calliopecore.sentence.IAdjectiveInfo;
import com.rokuan.calliopecore.sentence.ICharacterInfo;
import com.rokuan.calliopecore.sentence.ICityInfo;
import com.rokuan.calliopecore.sentence.IColorInfo;
import com.rokuan.calliopecore.sentence.ICountryInfo;
import com.rokuan.calliopecore.sentence.ILanguageInfo;
import com.rokuan.calliopecore.sentence.INameInfo;
import com.rokuan.calliopecore.sentence.IPlaceInfo;
import com.rokuan.calliopecore.sentence.IPronoun;
import com.rokuan.calliopecore.sentence.ITransportInfo;
import com.rokuan.calliopecore.sentence.IUnitInfo;
import com.rokuan.calliopecore.sentence.IVerbConjugation;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;

public class FullGsonBuilder {
	public static GsonBuilder getSerializationGsonBuilder(){
		GsonBuilder builder = new GsonBuilder();
		
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectSerializer());
		builder.registerTypeAdapter(INominalObject.class, new NominalGroupSerializer());
		builder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialSerializer());
		builder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialSerializer());
		builder.registerTypeAdapter(IWayObject.class, new WayAdverbialSerializer());
		builder.registerTypeAdapter(IPurposeObject.class, new PurposeAdverbialSerializer());
		builder.registerTypeAdapter(IVerbConjugation.class, new VerbConjugationSerializer());
		builder.registerTypeAdapter(IPronoun.class, new PronounSerializer());
		builder.registerTypeAdapter(IAdjectiveInfo.class, new AdjectiveSerializer());
		builder.registerTypeAdapter(ICharacterInfo.class, new CharacterSerializer());
		builder.registerTypeAdapter(ICityInfo.class, new CitySerializer());
		builder.registerTypeAdapter(IColorInfo.class, new ColorSerializer());
		builder.registerTypeAdapter(ICountryInfo.class, new CountrySerializer());
		builder.registerTypeAdapter(ILanguageInfo.class, new LanguageSerializer());
		builder.registerTypeAdapter(INameInfo.class, new NameSerializer());
		builder.registerTypeAdapter(IPlaceInfo.class, new PlaceSerializer());
		builder.registerTypeAdapter(ITransportInfo.class, new TransportSerializer());
		builder.registerTypeAdapter(IUnitInfo.class, new UnitSerializer());
		
		return builder;
	}
	
	public static GsonBuilder getDeserializationGsonBuilder(){
		GsonBuilder builder = new GsonBuilder();
		
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectDeserializer());
		builder.registerTypeAdapter(INominalObject.class, new NominalGroupDeserializer());
		builder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialDeserializer());
		builder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialDeserializer());
		builder.registerTypeAdapter(IWayObject.class, new WayAdverbialDeserializer());
		builder.registerTypeAdapter(IPurposeObject.class, new PurposeAdverbialDeserializer());
		builder.registerTypeAdapter(CountObject.class, new CountObjectDeserializer());
		builder.registerTypeAdapter(IVerbConjugation.class, new VerbConjugationDeserializer());
		builder.registerTypeAdapter(IPronoun.class, new PronounDeserializer());
		builder.registerTypeAdapter(IAdjectiveInfo.class, new AdjectiveDeserializer());
		builder.registerTypeAdapter(ICharacterInfo.class, new CharacterDeserializer());
		builder.registerTypeAdapter(ICityInfo.class, new CityDeserializer());
		builder.registerTypeAdapter(IColorInfo.class, new ColorDeserializer());
		builder.registerTypeAdapter(ICountryInfo.class, new CountryDeserializer());
		builder.registerTypeAdapter(ILanguageInfo.class, new LanguageDeserializer());
		builder.registerTypeAdapter(INameInfo.class, new NameDeserializer());
		builder.registerTypeAdapter(IPlaceInfo.class, new PlaceDeserializer());
		builder.registerTypeAdapter(ITransportInfo.class, new TransportDeserializer());
		builder.registerTypeAdapter(IUnitInfo.class, new UnitDeserializer());
		
		return builder;
	}
}
