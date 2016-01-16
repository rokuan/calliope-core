package com.rokuan.calliopecore.json;

import com.google.gson.GsonBuilder;
import com.rokuan.calliopecore.sentence.IAction;
import com.rokuan.calliopecore.sentence.IAdjectiveInfo;
import com.rokuan.calliopecore.sentence.ICharacterInfo;
import com.rokuan.calliopecore.sentence.ICityInfo;
import com.rokuan.calliopecore.sentence.IColorInfo;
import com.rokuan.calliopecore.sentence.ICountryInfo;
import com.rokuan.calliopecore.sentence.ICustomMode;
import com.rokuan.calliopecore.sentence.ICustomObject;
import com.rokuan.calliopecore.sentence.ICustomPerson;
import com.rokuan.calliopecore.sentence.ICustomPlace;
import com.rokuan.calliopecore.sentence.ILanguageInfo;
import com.rokuan.calliopecore.sentence.INameInfo;
import com.rokuan.calliopecore.sentence.IPlaceInfo;
import com.rokuan.calliopecore.sentence.IPlacePreposition;
import com.rokuan.calliopecore.sentence.IPronoun;
import com.rokuan.calliopecore.sentence.IPurposePreposition;
import com.rokuan.calliopecore.sentence.ITimePreposition;
import com.rokuan.calliopecore.sentence.ITransportInfo;
import com.rokuan.calliopecore.sentence.IUnitInfo;
import com.rokuan.calliopecore.sentence.IWayPreposition;
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
			
			serializationBuilder.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectSerialization.Serializer());
			
			serializationBuilder.registerTypeAdapter(INominalObject.class, new NominalGroupSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IWayObject.class, new WayAdverbialSerialization.Serializer());
			serializationBuilder.registerTypeAdapter(IPurposeObject.class, new PurposeAdverbialSerialization.Serializer());
			
			serializationBuilder.registerTypeAdapter(IAction.class, new ActionSerialization.Serializer());
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
			
			serializationBuilder.registerTypeAdapter(ICustomObject.class, new CustomDataSerialization.CustomObject.Serializer());
			serializationBuilder.registerTypeAdapter(ICustomPlace.class, new CustomDataSerialization.CustomPlace.Serializer());
			serializationBuilder.registerTypeAdapter(ICustomPerson.class, new CustomDataSerialization.CustomPerson.Serializer());
			serializationBuilder.registerTypeAdapter(ICustomMode.class, new CustomDataSerialization.CustomMode.Serializer());
			
			serializationBuilder.registerTypeAdapter(IPlacePreposition.class, new PrepositionSerialization.PlacePreposition.Serializer());
			serializationBuilder.registerTypeAdapter(ITimePreposition.class, new PrepositionSerialization.TimePreposition.Serializer());
			serializationBuilder.registerTypeAdapter(IWayPreposition.class, new PrepositionSerialization.WayPreposition.Serializer());
			serializationBuilder.registerTypeAdapter(IPurposePreposition.class, new PrepositionSerialization.PurposePreposition.Serializer());
		}

		return serializationBuilder;
	}

	public static GsonBuilder getDeserializationGsonBuilder(){
		if(deserializationBuilder == null){
			deserializationBuilder = new GsonBuilder();
			
			deserializationBuilder.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectSerialization.Deserializer());
			
			deserializationBuilder.registerTypeAdapter(INominalObject.class, new NominalGroupSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IWayObject.class, new WayAdverbialSerialization.Deserializer());
			deserializationBuilder.registerTypeAdapter(IPurposeObject.class, new PurposeAdverbialSerialization.Deserializer());
			
			deserializationBuilder.registerTypeAdapter(CountObject.class, new CountObjectSerialization.Deserializer());
			
			deserializationBuilder.registerTypeAdapter(IAction.class, new ActionSerialization.Deserializer());
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
			
			deserializationBuilder.registerTypeAdapter(ICustomObject.class, new CustomDataSerialization.CustomObject.Deserializer());
			deserializationBuilder.registerTypeAdapter(ICustomPlace.class, new CustomDataSerialization.CustomPlace.Deserializer());
			deserializationBuilder.registerTypeAdapter(ICustomPerson.class, new CustomDataSerialization.CustomPerson.Deserializer());
			deserializationBuilder.registerTypeAdapter(ICustomMode.class, new CustomDataSerialization.CustomMode.Deserializer());
			
			deserializationBuilder.registerTypeAdapter(IPlacePreposition.class, new PrepositionSerialization.PlacePreposition.Deserializer());
			deserializationBuilder.registerTypeAdapter(ITimePreposition.class, new PrepositionSerialization.TimePreposition.Deserializer());
			deserializationBuilder.registerTypeAdapter(IWayPreposition.class, new PrepositionSerialization.WayPreposition.Deserializer());
			deserializationBuilder.registerTypeAdapter(IPurposePreposition.class, new PrepositionSerialization.PurposePreposition.Deserializer());
		}

		return deserializationBuilder;
	}
}
