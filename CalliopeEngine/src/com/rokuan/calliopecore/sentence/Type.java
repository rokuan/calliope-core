package com.rokuan.calliopecore.sentence;

public class Type {
	public enum Tense {
        PAST,
        PRESENT,
        FUTURE
    }

    public enum ConjugationTense {
        PRESENT,
        PAST,
        PERFECT, //PASSE_COMPOSE,
        IMPERFECT, //IMPARFAIT,
        PAST_PERFECT, //PLUS_QUE_PARFAIT,
        SIMPLE_PAST, //PASSE_SIMPLE,
        PLUPERFECT, //PASSE_ANTERIEUR
        FUTURE,
        FUTURE_ANTERIOR //FUTUR_ANTERIEUR
    }

    public enum Form {
        INDICATIVE,
        CONDITIONAL,
        SUBJUNCTIVE,
        IMPERATIVE,
        INFINITIVE,
        PARTICIPLE,
    }

    public enum Pronoun {
        JE,
        TU,
        IL_ELLE_ON,
        NOUS,
        VOUS,
        ILS_ELLES,
        UNDEFINED
    }
    
    public static Pronoun parseSubjectPronoun(String str){
        try{
            Pronoun.valueOf(str.toUpperCase());
        } catch (Exception e){
            if(str.equals("on")){
                return Pronoun.NOUS;
            }else if(str.equals("il") || str.equals("elle") || str.equals("on")){

            }
        }

        return Pronoun.UNDEFINED;
    }

    public static Pronoun parseTargetPronoun(String str){
        if(str.equals("moi")){
            return Pronoun.JE;
        } else if(str.equals("toi")){
            return Pronoun.TU;
        } else if(str.equals("lui") || str.equals("le") || str.equals("la") || str.equals("y")){
            return Pronoun.IL_ELLE_ON;
        } else if(str.equals("nous")){
            return Pronoun.NOUS;
        } else if(str.equals("vous")){
            return Pronoun.VOUS;
        } else if(str.equals("les") || str.equals("leur")){
            return Pronoun.ILS_ELLES;
        }

        return Pronoun.UNDEFINED;
    }
}
