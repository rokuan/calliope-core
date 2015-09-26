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
	private static GsonBuilder serializationBuilder;
	private static GsonBuilder deserializationBuilder;

	public static GsonBuilder getSerializationGsonBuilder(){
		if(serializationBuilder == null){
			serializationBuilder = new GsonBuilder();

			serializationBuilder.excludeFieldsWithoutExposeAnnotation();
			serializationBuilder.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(INominalObject.class, new NominalGroupSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IWayObject.class, new WayAdverbialSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IPurposeObject.class, new PurposeAdverbialSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IVerbConjugation.class, new VerbConjugationSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IPronoun.class, new PronounSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IAdjectiveInfo.class, new AdjectiveSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(ICharacterInfo.class, new CharacterSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(ICityInfo.class, new CitySerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IColorInfo.class, new ColorSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(ICountryInfo.class, new CountrySerialization.Serializer());
			serializationBuilder.registerTypeAdapter(ILanguageInfo.class, new LanguageSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(INameInfo.class, new NameSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IPlaceInfo.class, new PlaceSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(ITransportInfo.class, new TransportSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IUnitInfo.class, new UnitSerialization.Serializer());
		}

		return serializationBuilder;
	}

	public static GsonBuilder getDeserializationGsonBuilder(){
		if(deserializationBuilder == null){
			deserializationBuilder = new GsonBuilder();

			deserializationBuilder.excludeFieldsWithoutExposeAnnotation();
			deserializationBuilder.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(INominalObject.class, new NominalGroupSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IWayObject.class, new WayAdverbialSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IPurposeObject.class, new PurposeAdverbialSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(CountObject.class, new CountObjectSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IVerbConjugation.class, new VerbConjugationSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IPronoun.class, new PronounSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IAdjectiveInfo.class, new AdjectiveSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(ICharacterInfo.class, new CharacterSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(ICityInfo.class, new CitySerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IColorInfo.class, new ColorSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(ICountryInfo.class, new CountrySerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(ILanguageInfo.class, new LanguageSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(INameInfo.class, new NameSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IPlaceInfo.class, new PlaceSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(ITransportInfo.class, new TransportSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IUnitInfo.class, new UnitSerialization.Deserializer());
		}

		return deserializationBuilder;
	}
}
