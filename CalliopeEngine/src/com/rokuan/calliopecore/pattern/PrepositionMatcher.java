package com.rokuan.calliopecore.pattern;


public abstract class PrepositionMatcher<FollowerType> implements WordMatcher {
	protected boolean matchContractedForm = false;
	protected FollowerType[] possibleFollowers = null;

	public static class PrepositionMatcherBuilder<FType> {
		private PrepositionMatcher<FType> matcher;		
		
		public PrepositionMatcherBuilder<FType> setMatchContractedForm(boolean shouldMatch){
			matcher.matchContractedForm = shouldMatch;
			return this;
		}
		
		public PrepositionMatcherBuilder<FType> setPossibleFollowers(FType... types){
			matcher.possibleFollowers = types;
			return this;
		}
		
		public PrepositionMatcher<FType> build(){
			return matcher;
		}
	}
	
	public PrepositionMatcher() {

	}
}
