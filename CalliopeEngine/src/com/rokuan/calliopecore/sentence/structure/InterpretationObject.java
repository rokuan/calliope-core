package com.rokuan.calliopecore.sentence.structure;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.json.InterpretationObjectSerialization;
import com.rokuan.calliopecore.sentence.structure.common.FullContent;

/**
 * Created by LEBEAU Christophe on 17/02/2015.
 */
public abstract class InterpretationObject extends FullContent {
	public enum RequestType {
		ORDER,
		QUESTION,
		AFFIRMATION
	}

	@Expose
	private RequestType requestType;

	protected InterpretationObject(RequestType t){
		requestType = t;
	}

	public static String toJSON(InterpretationObject object){		
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectSerialization.Serializer());
		return builder.create().toJson(object, InterpretationObject.class);
	}

	public static InterpretationObject fromJSON(String json){
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectSerialization.Deserializer());
		return builder.create().fromJson(json, InterpretationObject.class);
	}

	public RequestType getRequestType(){
		return requestType;
	}

	public static Class<? extends InterpretationObject> getClassFromRequestType(RequestType ty){
		Class<? extends InterpretationObject> clazz = null;

		switch(ty){
		case AFFIRMATION:
			clazz = AffirmationObject.class;
			break;
		case ORDER:
			clazz = OrderObject.class;
			break;			
		case QUESTION:
			clazz = QuestionObject.class;
			break;
		}

		return clazz;
	}
}
