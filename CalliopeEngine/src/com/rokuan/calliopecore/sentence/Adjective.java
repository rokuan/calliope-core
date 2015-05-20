package com.rokuan.calliopecore.sentence;

public class Adjective {
	public enum AdjectiveValue {
		UNDEFINED,
		NEW,
		OLD,
		SMALL,
		LARGE,
		PREVIOUS,
		CURRENT,
		NEXT,		
	}
	
	private AdjectiveValue type = AdjectiveValue.UNDEFINED;
	private String value;
	
	public Adjective(String val){
		value = val;
		type = parseAdjectiveValue(val);
	}
	
	private static AdjectiveValue parseAdjectiveValue(String adjective){
		if(adjective == null){
			return AdjectiveValue.UNDEFINED;
		}
		
		String adj = adjective.toLowerCase();
		
		if(adj.startsWith("nouv")){
			return AdjectiveValue.NEW;
		} else if(adj.startsWith("ancien")){
			return AdjectiveValue.OLD;
		} else if(adj.startsWith("courant")){
			return AdjectiveValue.CURRENT;
		} else if(adj.startsWith("précédent") || adj.startsWith("passé")){
			return AdjectiveValue.PREVIOUS;
		} else if(adj.startsWith("suivant") || adj.startsWith("prochain")){
			return AdjectiveValue.NEXT;
		} else if(adj.startsWith("petit")){
			return AdjectiveValue.SMALL;
		} else if(adj.startsWith("grand")){
			return AdjectiveValue.LARGE;
		}
		
		return AdjectiveValue.UNDEFINED;
	}
	
	public String getValue(){
		return value;
	}
	
	public AdjectiveValue getType(){
		return type;
	}	
}
