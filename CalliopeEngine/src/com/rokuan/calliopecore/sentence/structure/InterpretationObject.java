package com.rokuan.calliopecore.sentence.structure;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rokuan.calliopecore.json.InterpretationObjectDeserializer;
import com.rokuan.calliopecore.sentence.Adjective;
import com.rokuan.calliopecore.sentence.structure.common.FullContent;
import com.rokuan.calliopecore.sentence.structure.data.count.AllItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;

/**
 * Created by LEBEAU Christophe on 17/02/2015.
 */
public abstract class InterpretationObject extends FullContent {
	public enum RequestType {
		ORDER,
		QUESTION,
		AFFIRMATION
	}

	private RequestType requestType;
	public CountObject count = new AllItemsObject();
	public List<Adjective> adjectives = new ArrayList<Adjective>(); 

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
		Gson gson = new GsonBuilder().create();
		return gson.toJson(object);
	}

	public static InterpretationObject fromJSON(String json){
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectDeserializer());
		return builder.create().fromJson(json, InterpretationObject.class);
	}

	public RequestType getRequestType(){
		return requestType;
	}
}
