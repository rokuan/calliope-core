package com.rokuan.calliopecore.parser;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.ColorInfo;
import com.rokuan.calliopecore.sentence.CountryInfo;
import com.rokuan.calliopecore.sentence.CustomMode;
import com.rokuan.calliopecore.sentence.CustomObject;
import com.rokuan.calliopecore.sentence.CustomPerson;
import com.rokuan.calliopecore.sentence.CustomPlace;
import com.rokuan.calliopecore.sentence.LanguageInfo;
import com.rokuan.calliopecore.sentence.PlacePreposition;
import com.rokuan.calliopecore.sentence.PurposePreposition;
import com.rokuan.calliopecore.sentence.TimePreposition;
import com.rokuan.calliopecore.sentence.TransportInfo;
import com.rokuan.calliopecore.sentence.UnitInfo;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.WayPreposition;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.data.CountConverter;
import com.rokuan.calliopecore.sentence.structure.data.DateConverter;

/**
 * Created by LEBEAU Christophe on 23/02/2015.
 */
public final class SpeechParser {
	private WordDatabase db;

	public SpeechParser(WordDatabase database){
		db = database;
	}

	public final WordBuffer lexSpeech(String text){
		WordBuffer buffer = new WordBuffer();
		String[] words = text.split(" ");

		for(int i=0; i<words.length; i++){
			StringBuilder wordBuilder = new StringBuilder(words[i]);
			Word currentWord = getWord(words[i]);
			boolean shouldContinue = db.wordStartsWith(words[i]);

			if(currentWord != null && !shouldContinue){

			} else {
				if (!shouldContinue) {
					int charIndex = words[i].indexOf('\'');

					if (charIndex != -1) {
						String leftPart = words[i].substring(0, charIndex);
						String rightPart = words[i].substring(charIndex + 1, words[i].length());

						Word leftWord = getWord(leftPart);

						// Mot d'une autre langue
						if (leftWord == null) {
							buffer.add(new Word(words[i], Word.WordType.OTHER));
							continue;
						} else {
							buffer.add(leftWord);
							wordBuilder = new StringBuilder(rightPart);
							shouldContinue = true;
						}
					}

					String tmpPart = wordBuilder.toString();
					Word tmpWord = getWord(tmpPart);

					if (tmpWord == null) {
						// TODO: faire le split sur le tiret
						/*charIndex = tmpPart.indexOf('-');

                        if (charIndex != -1) {
                            String leftPart = tmpPart.substring(0, charIndex);
                            String rightPart = tmpPart.substring(charIndex + 1, words[i].length());

                            Word leftWord = getWord(leftPart);

                            // Mot d'une autre langue
                            if (leftWord == null) {
                                buffer.add(new Word(words[i], Word.WordType.OTHER));
                                continue;
                            } else {
                                buffer.add(leftWord);
                                wordBuilder = new StringBuilder(rightPart);
                                shouldContinue = true;
                            }
                        }*/
						String[] parts = tmpPart.split("-");

						for(String p: parts){
							Word partWord = getWord(p);

							if(partWord == null){
								buffer.add(new Word(words[i], Word.WordType.OTHER));
							} else {
								buffer.add(partWord);
							}
						}

						continue;
					} else {
						shouldContinue = true;
					}
				}

				int lastIndex = i;

				while (shouldContinue && lastIndex < words.length) {
					Word tmpWord = getWord(wordBuilder.toString());

					if(tmpWord != null) {
						currentWord = tmpWord;
						i = lastIndex;
					}

					lastIndex++;

					if (lastIndex < words.length) {
						wordBuilder.append(' ');
						wordBuilder.append(words[lastIndex]);
						shouldContinue = db.wordStartsWith(wordBuilder.toString());

						if (!shouldContinue) {
							lastIndex--;
						}
					}
				}
			}

			buffer.add(currentWord);
		}

		System.out.println("wordBuffer=" + buffer);

		return buffer;
	}

	private final InterpretationObject parseSpeech(WordBuffer words){
		return new Parser().parseInterpretationObject(words);
	}

	public InterpretationObject parseText(String text){
		return this.parseSpeech(this.lexSpeech(text));
	}

	private final Word getWord(String q){
		// TODO: PROPER_NAME, NUMBER
		if(q.matches(DateConverter.FULL_TIME_REGEX) || q.matches(DateConverter.HOUR_ONLY_REGEX)){
			return new Word(q, Word.WordType.TIME);
		}
		
		if(q.matches(CountConverter.REAL_REGEX)){
			return new Word(q, WordType.REAL);
		}
		
		if(q.matches(CountConverter.NUMBER_REGEX)){
			return new Word(q, WordType.NUMBER);
		}

		/*if(Character.isDigit(w.charAt(0))){
            return new Word(Word.WordType.NUMBER, w);
        }*/
		/*if(Character.isDigit(q.charAt(0))) {
            try {
                return new Word(q, Word.WordType.NUMBER);
            } catch (Exception e) {
                Matcher matcher = Pattern.compile("[0-9]+e").matcher(w);

                if (matcher.find()) {
                    String matchingValue = matcher.group(0);
                    long longValue = Long.parseLong(matchingValue.substring(0, matchingValue.length() - 1));
                    return new Word(String.valueOf(longValue), Word.WordType.NUMERICAL_POSITION);
                }
            }
        }*/

		try{
			Integer.parseInt(q);
			return new Word(q, Word.WordType.NUMBER);
		}catch(Exception e){
			// TODO: les positions de la forme [0-9]eme
			Matcher matcher = Pattern.compile("[0-9]+e").matcher(q);

			if (matcher.find()) {
				String matchingValue = matcher.group(0);
				long longValue = Long.parseLong(matchingValue.substring(0, matchingValue.length() - 1));
				return new Word(String.valueOf(longValue), Word.WordType.NUMERICAL_POSITION);
			}
		}

		Set<WordType> types = new HashSet<WordType>();
		//Word result = queryFirst(this, Word.class, Word.WORD_FIELD_NAME, q);
		Word result = db.findWord(q);
		LanguageInfo language = db.findLanguageInfo(q);
		ColorInfo color = db.findColorInfo(q);
		CityInfo city = db.findCityInfo(q);
		CountryInfo country = db.findCountryInfo(q);
		TransportInfo transport = db.findTransportInfo(q);
		UnitInfo unit = db.findUnitInfo(q);
		CustomObject object = db.findCustomObject(q);
		CustomPlace place = db.findCustomPlace(q);
		CustomMode mode = db.findCustomMode(q);
		CustomPerson person = db.findCustomPerson(q);
		PlacePreposition placePreposition = db.findPlacePreposition(q);
		TimePreposition timePreposition = db.findTimePreposition(q);
		WayPreposition wayPreposition = db.findWayPreposition(q);
		PurposePreposition purposePreposition = db.findPurposePreposition(q);
		VerbConjugation conjugation = db.findConjugation(q);

		if(Character.isUpperCase(q.charAt(0))){
			if(city == null && country == null) {
				types.add(Word.WordType.PROPER_NAME);
			}
		} else {
			if(CountConverter.isAPosition(q)){
				types.add(Word.WordType.NUMERICAL_POSITION);
			}
		}

		if(language != null){
			types.add(Word.WordType.LANGUAGE);
		}

		if(color != null){
			types.add(Word.WordType.COLOR);
		}

		if(city != null){
			types.add(Word.WordType.CITY);
		}

		if(country != null){
			types.add(Word.WordType.COUNTRY);
		}
		
		if(transport != null){
			types.add(WordType.MEAN_OF_TRANSPORT);
		}
		
		if(unit != null){
			types.add(WordType.UNIT);
		}

		if(object != null){
			types.add(Word.WordType.OBJECT);
		}

		if(place != null){
			types.add(Word.WordType.ADDITIONAL_PLACE);
		}
		
		if(mode != null){
			types.add(WordType.MODE);
		}
		
		if(person != null){
			types.add(WordType.PERSON);
		}

		if(placePreposition != null){
			types.add(Word.WordType.PLACE_PREPOSITION);
		}

		if(timePreposition != null){
			types.add(Word.WordType.TIME_PREPOSITION);
		}

		if(wayPreposition != null){
			types.add(Word.WordType.WAY_PREPOSITION);
		}

		if(purposePreposition != null){
			types.add(Word.WordType.PURPOSE_PREPOSITION);
		}

		if(conjugation != null){
			types.add(Word.WordType.VERB);

			if(conjugation.getVerb().isAuxiliary()){
				types.add(Word.WordType.AUXILIARY);
			}
		}

		if (result == null){
			if(!types.isEmpty()) {
				result = new Word(q, types);
			} else {
				return null;
			}
		} else {
			for(WordType t: types) {
				result.addType(t);
			}
		}

		if(result != null) {
			result.setLanguageInfo(language);
			result.setColorInfo(color);
			result.setCityInfo(city);
			result.setCountryInfo(country);
			result.setTransportInfo(transport);
			result.setUnitInfo(unit);
			
			result.setCustomObject(object);
			result.setCustomPlace(place);
			result.setCustomMode(mode);
			result.setCustomPerson(person);
			
			result.setVerbInfo(conjugation);
			
			result.setPlacePreposition(placePreposition);
			result.setTimePreposition(timePreposition);
			result.setWayPreposition(wayPreposition);
			result.setPurposePreposition(purposePreposition);
		}

		return result;
	}
}
