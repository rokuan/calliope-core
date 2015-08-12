package com.rokuan.calliopecore.sentence.structure;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.json.InterpretationObjectDeserializer;
import com.rokuan.calliopecore.json.InterpretationObjectSerializer;
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
	//public List<Adjective> adjectives = new ArrayList<Adjective>(); 

	protected InterpretationObject(RequestType t){
		requestType = t;
	}

	public String getDescription(){
		// TODO:
		String leftPart = (action == null) ? "" : action.toString();
		String rightPart = (what == null) ? "" : what.toString();
		//String rightPart = (what == null) ? "" : what;
		return leftPart + ':' + rightPart;
	}

	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();

		result.append("type=");
		result.append(requestType);
		result.append(";");
		result.append("action=");
		result.append(action);
		result.append(";");
		result.append("what=");
		result.append(what);

		return result.toString();
	}

	public static String toJSON(InterpretationObject object){		
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectSerializer());
		return builder.create().toJson(object, InterpretationObject.class);
	}

	public static InterpretationObject fromJSON(String json){
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectDeserializer());
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
