package com.rokuan.calliopecore.sentence.structure.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.rokuan.calliopecore.content.ITimeObject;
import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.time.SingleTimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.DateContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.DateDefinition;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeInterval;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeTense;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeUnit;
import com.rokuan.calliopecore.sentence.structure.data.time.TimePeriodObject;

/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class DateConverter {
	private static final int DEFAULT_DATE_FIELD_VALUE = -1;

	// (NUMBER | NUMERICAL_POSITION) (DATE_MONTH NUMBER?)?
	private static final WordPattern FROM_DATE_PATTERN = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.NUMERICAL_POSITION)),
			WordPattern.optional(WordPattern.sequence(
					WordPattern.simple(WordType.DATE_MONTH), 
					WordPattern.optional(WordPattern.simple(WordType.NUMBER))))
			);
	// (NUMBER | NUMERICAL_POSITION) DATE_MONTH NUMBER?
	private static final WordPattern TO_DATE_PATTERN = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.NUMERICAL_POSITION)),
			WordPattern.sequence(
					WordPattern.simple(WordType.DATE_MONTH), 
					WordPattern.optional(WordPattern.simple(WordType.NUMBER)))
			);

	public static final WordPattern FROM_TO_DATE_PATTERN = WordPattern.sequence(
			WordPattern.simple(Word.WordType.PREPOSITION_FROM),
			WordPattern.or(FROM_DATE_PATTERN, WordPattern.simple(WordType.DATE)),
			WordPattern.simple(Word.WordType.PREPOSITION_TO),
			WordPattern.or(TO_DATE_PATTERN, WordPattern.simple(WordType.DATE))
			);

	public static final WordPattern BETWEEN_DATE_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.PREPOSITION_BETWEEN),
			WordPattern.or(
					WordPattern.sequence(WordPattern.simple(WordType.DEFINITE_ARTICLE), FROM_DATE_PATTERN),
					WordPattern.simple(WordType.DATE)
					),
					WordPattern.simple(WordType.PREPOSITION_AND),
					WordPattern.or(
							WordPattern.sequence(WordPattern.simple(WordType.DEFINITE_ARTICLE), TO_DATE_PATTERN),
							WordPattern.simple(WordType.DATE))
			);

	/*public static WordPattern timePattern = WordPattern.sequence(
			WordPattern.simple(WordType.PREPOSITION_AT),
			WordPattern.simple(WordType.NUMBER),
			WordPattern.simple(WordType.DATE_UNIT_HOUR),
			WordPattern.optional(WordPattern.simple(WordType.NUMBER))
			);*/

	/*public static final WordPattern FIXED_DATE_PATTERN = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.INDEFINITE_ARTICLE, "du"), 
					WordPattern.sequence(WordPattern.optional(WordPattern.simple(WordType.ANY, "pour")), WordPattern.simple(WordType.DEFINITE_ARTICLE))
					),
					WordPattern.or(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.NUMERICAL_POSITION)),
					WordPattern.simple(WordType.DATE_MONTH),
					WordPattern.optional(WordPattern.simple(WordType.NUMBER))
					//WordPattern.optional(timePattern)
			);*/
	public static final WordPattern DATE_PREPOSITION_PATTERN = WordPattern.or(
			WordPattern.simple(new WordType[]{ WordType.TIME_PREPOSITION, WordType.CONTRACTED }),
			WordPattern.sequence(WordPattern.optional(WordPattern.simple(WordType.TIME_PREPOSITION)), WordPattern.simple(WordType.DEFINITE_ARTICLE))
			);

	public static final WordPattern FIXED_DATE_PATTERN = WordPattern.sequence(
			//WordPattern.sequence(WordPattern.optional(WordPattern.simple(WordType.ANY, "pour")), WordPattern.simple(WordType.DEFINITE_ARTICLE)),
			DATE_PREPOSITION_PATTERN,
			WordPattern.or(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.NUMERICAL_POSITION)),
			WordPattern.simple(WordType.DATE_MONTH),
			WordPattern.optional(WordPattern.simple(WordType.NUMBER))
			//WordPattern.optional(timePattern)
			);
	public static final WordPattern MINUTES_DEFINITION_PATTERN = WordPattern.or(
			WordPattern.sequence(
					WordPattern.or(WordPattern.simple(WordType.ANY, "moins"), WordPattern.simple(WordType.PREPOSITION_AND), WordPattern.simple(WordType.INDEFINITE_ARTICLE, "un(e?)"), WordPattern.simple(WordType.PREPOSITION_AND, "et")),
					WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)),
					WordPattern.or(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.ANY, "quart|demi(e?)"))
					));

	public static final String FULL_TIME_REGEX = "(\\d)+h(\\d)+";
	public static final String HOUR_ONLY_REGEX = "(\\d)+h";
	private static final WordPattern FULL_TIME_PATTERN = WordPattern.simple(WordType.ANY, FULL_TIME_REGEX);
	private static final WordPattern HOUR_ONLY_PATTERN = WordPattern.simple(WordType.ANY, HOUR_ONLY_REGEX);
	public static final WordPattern TIME_PATTERN = WordPattern.or(
			FULL_TIME_PATTERN,
			WordPattern.sequence(
					WordPattern.or(
							WordPattern.sequence(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.DATE_UNIT_HOUR, "heure(s?)")),
							HOUR_ONLY_PATTERN,
							WordPattern.simple(WordType.DATE_UNIT_HOUR)
							), 
							WordPattern.optional(WordPattern.or(WordPattern.simple(WordType.NUMBER), MINUTES_DEFINITION_PATTERN)))
			);

	public static final WordPattern TIME_DECLARATION_PATTERN = WordPattern.sequence(
			WordPattern.or(
					WordPattern.simple(WordType.PREPOSITION_AT),
					WordPattern.simple(WordType.TIME_PREPOSITION),
					//WordPattern.simple(WordType.ANY, "pour"), 
					WordPattern.sequence(WordPattern.simple(WordType.ANY, "quand"), WordPattern.simple(WordType.PERSONAL_PRONOUN, "il"), WordPattern.simple(WordType.VERB, "sera"))),
					TIME_PATTERN
			);


	// Indirect object patterns

	public static final WordPattern DIRECT_OBJECT_DATE_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.PREPOSITION_OF, "du"),
			WordPattern.or(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.NUMERICAL_POSITION)),
			WordPattern.simple(WordType.DATE_MONTH),
			WordPattern.optional(WordPattern.simple(WordType.NUMBER)),
			WordPattern.optional(WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_AT), TIME_PATTERN))
			);

	public static final WordPattern DIRECT_OBJECT_TIME_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.PREPOSITION_OF, "de"),
			TIME_PATTERN
			);

	public static final WordPattern RELATIVE_DATE_PATTERN = WordPattern.simple(WordType.DATE);

	public static boolean isATimeAdverbial(WordBuffer words){
		return WordPattern.syntaxStartsWith(words, FROM_TO_DATE_PATTERN)
				|| WordPattern.syntaxStartsWith(words, BETWEEN_DATE_PATTERN)
				|| WordPattern.syntaxStartsWith(words, FIXED_DATE_PATTERN)
				|| WordPattern.syntaxStartsWith(words, TIME_DECLARATION_PATTERN)
				|| WordPattern.syntaxStartsWith(words, RELATIVE_DATE_PATTERN);
		//|| WordPattern.syntaxStartsWith(words, timePattern); 
	}

	public static boolean isAnObjectDateData(WordBuffer words){
		return WordPattern.syntaxStartsWith(words, DIRECT_OBJECT_DATE_PATTERN)
				|| WordPattern.syntaxStartsWith(words, DIRECT_OBJECT_TIME_PATTERN);
	}

	public static ITimeObject parseTimeAdverbial(WordBuffer words){
		TimeAdverbial result = null;

		if(WordPattern.syntaxStartsWith(words, FROM_TO_DATE_PATTERN)){
			TimePeriodObject period = new TimePeriodObject();

			words.consume();	// PREPOSITION_FROM

			int[] fromDateFields = parseDate(words);

			words.consume();	// PREPOSITION_TO

			int[] toDateFields = parseDate(words);

			adjustInterval(fromDateFields, toDateFields);

			period.fromDateDefinition = DateDefinition.values()[fromDateFields[fromDateFields.length - 1]];
			period.toDateDefinition = DateDefinition.values()[toDateFields[toDateFields.length - 1]];
			period.from = buildDateFromArray(fromDateFields);
			period.to = buildDateFromArray(toDateFields);

			result = period;
		} else if(WordPattern.syntaxStartsWith(words, BETWEEN_DATE_PATTERN)){
			TimePeriodObject period = new TimePeriodObject();

			words.consume();	// PREPOSITION_BETWEEN
			words.consume();	// DEFINITE_ARTICLE

			int[] fromDateFields = parseDate(words);

			words.consume();	// PREPOSITION_AND
			words.consume();	// DEFINITE_ARTICLE			

			int[] toDateFields = parseDate(words);

			adjustInterval(fromDateFields, toDateFields);

			period.fromDateDefinition = DateDefinition.values()[fromDateFields[fromDateFields.length - 1]];
			period.toDateDefinition = DateDefinition.values()[toDateFields[toDateFields.length - 1]];
			period.from = buildDateFromArray(fromDateFields);
			period.to = buildDateFromArray(toDateFields);

			result = period;
		} else if(WordPattern.syntaxStartsWith(words, FIXED_DATE_PATTERN)){
			/*WordPattern.simple(new WordType[]{ WordType.TIME_PREPOSITION, WordType.CONTRACTED }),
			WordPattern.sequence(WordPattern.optional(WordPattern.simple(WordType.TIME_PREPOSITION)), WordPattern.simple(WordType.DEFINITE_ARTICLE))*/
			SingleTimeObject single = new SingleTimeObject();

			if(words.getCurrentElement().isOfType(WordType.TIME_PREPOSITION)){
				// TODO: parser la preposition
				single.preposition = words.getCurrentElement().getDatePreposition();
				words.consume();
			}

			if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
				words.consume();
			}
			//words.consume();

			int[] dateFields = parseDate(words);

			single.date = buildDateFromArray(dateFields);

			result = single;
		} else if(WordPattern.syntaxStartsWith(words, TIME_DECLARATION_PATTERN)){
			SingleTimeObject single = new SingleTimeObject();

			if(words.getCurrentElement().isOfType(WordType.PREPOSITION_AT)){
				single.preposition = DateContext.WHEN;
				words.consume();
			} else if(words.getCurrentElement().isOfType(WordType.TIME_PREPOSITION)){
				single.preposition = words.getCurrentElement().getDatePreposition();
				words.consume();
			} else {
				single.preposition = DateContext.WHEN;
				words.consume();
				words.consume();
				words.consume();
			}

			int[] fixedTimeFields = parseTime(words);

			single.dateDefinition = DateDefinition.TIME_ONLY; 
			single.date = buildDateFromArray(fixedTimeFields);

			result = single;
		} else if(WordPattern.syntaxStartsWith(words, RELATIVE_DATE_PATTERN)){
			//RelativeTimeObject
			result = parseRelativeDay(words.getCurrentElement().getValue());
			words.consume();
		}

		return result;
	}

	public static ITimeObject parseDirectObjectTimeAdverbial(WordBuffer words){
		if(words.syntaxStartsWith(DIRECT_OBJECT_DATE_PATTERN)){
			words.consume();	// PREPOSITION_OF

			SingleTimeObject single = new SingleTimeObject();
			int[] dateFields = parseDate(words);

			single.date = buildDateFromArray(dateFields);

			return single;
		} else if(words.syntaxStartsWith(DIRECT_OBJECT_TIME_PATTERN)){
			words.consume();	// PREPOSITION_OF			

			SingleTimeObject single = new SingleTimeObject();
			int[] fixedTimeFields = parseTime(words);

			single.dateDefinition = DateDefinition.TIME_ONLY; 
			single.date = buildDateFromArray(fixedTimeFields);

			return single;
		}

		return null;
	}

	private static int[] parseDate(WordBuffer words){
		// TODO: modifier si on prend en compte l'heure
		int[] date = new int[TimeUnit.values().length + 1];

		Arrays.fill(date, DEFAULT_DATE_FIELD_VALUE);

		date[date.length - 1] = DateDefinition.DATE_AND_TIME.ordinal();

		if(words.getCurrentElement().isOfType(WordType.NUMBER)){
			date[TimeUnit.DAY.ordinal()] = Integer.parseInt(words.getCurrentElement().getValue());
			words.consume();
		} else if(words.getCurrentElement().isOfType(WordType.NUMERICAL_POSITION)){
			date[TimeUnit.DAY.ordinal()] = (int)CountConverter.parsePosition(words.getCurrentElement().getValue());
			words.consume();
		}

		if(words.getCurrentElement().isOfType(WordType.DATE_MONTH)){
			// TODO: changer le local par celui de FRENCH ?
			try {
				Date monthDate = new SimpleDateFormat("MMMM", Locale.FRANCE).parse(words.getCurrentElement().getValue());
				Calendar calendar = Calendar.getInstance();

				calendar.setTime(monthDate);
				date[TimeUnit.MONTH.ordinal()] = calendar.get(Calendar.MONTH);
			} catch (ParseException e) {
				// TODO: gerer ce cas				
			}

			words.consume();
		}

		if(words.getCurrentElement().isOfType(WordType.NUMBER)){
			date[TimeUnit.YEAR.ordinal()] = Integer.parseInt(words.getCurrentElement().getValue());
			words.consume();
		}

		if(words.syntaxStartsWith(TIME_DECLARATION_PATTERN)){
			if(words.getCurrentElement().isOfType(WordType.PREPOSITION_AT)){
				words.consume();
			} else {
				words.consume();
				words.consume();
				words.consume();
			}

			int[] fixedTimeFields = parseTime(words);

			date[TimeUnit.HOURS.ordinal()] = fixedTimeFields[TimeUnit.HOURS.ordinal()];
			date[TimeUnit.MINUTES.ordinal()] = fixedTimeFields[TimeUnit.MINUTES.ordinal()];
			date[TimeUnit.SECONDS.ordinal()] = fixedTimeFields[TimeUnit.SECONDS.ordinal()];
		} else {
			date[date.length - 1] = DateDefinition.DATE_ONLY.ordinal();
		}

		return date;
	}

	private static int[] parseTime(WordBuffer words){
		int[] date = new int[TimeUnit.values().length];

		Arrays.fill(date, DEFAULT_DATE_FIELD_VALUE);

		//if(words.getCurrentElement().getValue().matches(fullTimeRegex)){
		if(words.syntaxStartsWith(FULL_TIME_PATTERN)){
			String[] fields = words.getCurrentElement().getValue().split("h");
			int hours = Integer.parseInt(fields[0]);
			int minutes = Integer.parseInt(fields[1]);

			date[TimeUnit.HOURS.ordinal()] = hours;
			date[TimeUnit.MINUTES.ordinal()] = minutes;

			words.consume();

			return date;
		}

		if(words.syntaxStartsWith(HOUR_ONLY_PATTERN)){
			//if(words.getCurrentElement().getValue().matches(hourOnlyRegex)){
			String value = words.getCurrentElement().getValue();
			int hours = Integer.parseInt(value.substring(0, value.length() - 1));

			date[TimeUnit.HOURS.ordinal()] = hours;

			words.consume();
		} else if(words.getCurrentElement().isOfType(WordType.NUMBER)){
			int hours = Integer.parseInt(words.getCurrentElement().getValue());

			date[TimeUnit.HOURS.ordinal()] = hours;

			words.consume();
			words.consume();	// heure(s)			
		} else if(words.getCurrentElement().isOfType(WordType.DATE_UNIT_HOUR)){
			date[TimeUnit.HOURS.ordinal()] = parseHourValue(words.getCurrentElement().getValue());
			words.consume();
		}

		if(words.isIntoBounds()){
			if(words.getCurrentElement().isOfType(WordType.NUMBER)){
				date[TimeUnit.MINUTES.ordinal()] = Integer.parseInt(words.getCurrentElement().getValue());
				words.consume();
			} else if(words.syntaxStartsWith(MINUTES_DEFINITION_PATTERN)){
				int factor = 1;

				if(words.getCurrentElement().getValue().equals("moins")){
					words.consume();
					factor = -1;
				} else if(words.getCurrentElement().isOfType(WordType.PREPOSITION_AND)){
					words.consume();
				}

				if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
					words.consume();
				}

				int minutes = 0;

				if(words.getCurrentElement().isOfType(WordType.NUMBER)){
					minutes = Integer.parseInt(words.getCurrentElement().getValue());
					words.consume();
				} else {
					minutes = parseMinutesValue(words.getCurrentElement().getValue());
					words.consume();
				}

				if(factor == -1){
					if(minutes != 0){
						date[TimeUnit.HOURS.ordinal()] = ((date[TimeUnit.HOURS.ordinal()] - 1) + 24) % 24;
						date[TimeUnit.MINUTES.ordinal()] = Math.max(0, 60 - minutes);
					}
				} else {
					minutes = Math.min(59, minutes);
					date[TimeUnit.MINUTES.ordinal()] = minutes;
				}
			}
		}

		return date;
	}

	private static Date buildDateFromArray(int[] dateArray){
		Calendar calendar = Calendar.getInstance();

		if(dateArray[TimeUnit.YEAR.ordinal()] != DEFAULT_DATE_FIELD_VALUE){
			calendar.set(Calendar.YEAR, dateArray[TimeUnit.YEAR.ordinal()]);
		}

		if(dateArray[TimeUnit.MONTH.ordinal()] != DEFAULT_DATE_FIELD_VALUE){
			calendar.set(Calendar.MONTH, dateArray[TimeUnit.MONTH.ordinal()]);
		}

		if(dateArray[TimeUnit.DAY.ordinal()] != DEFAULT_DATE_FIELD_VALUE){
			calendar.set(Calendar.DAY_OF_MONTH, dateArray[TimeUnit.DAY.ordinal()]);
		}

		if(dateArray[TimeUnit.HOURS.ordinal()] != DEFAULT_DATE_FIELD_VALUE){
			calendar.set(Calendar.HOUR_OF_DAY, dateArray[TimeUnit.HOURS.ordinal()]);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
		}

		if(dateArray[TimeUnit.MINUTES.ordinal()] != DEFAULT_DATE_FIELD_VALUE){
			calendar.set(Calendar.MINUTE, dateArray[TimeUnit.MINUTES.ordinal()]);
		} else {
			calendar.set(Calendar.MINUTE, 0);
		}

		if(dateArray[TimeUnit.SECONDS.ordinal()] != DEFAULT_DATE_FIELD_VALUE){
			calendar.set(Calendar.SECOND, dateArray[TimeUnit.SECONDS.ordinal()]);
		} else {
			calendar.set(Calendar.SECOND, 0);
		}

		return calendar.getTime();
	}

	private static void adjustInterval(int[] fromDateFields, int[] toDateFields){
		// Adjust month
		if(fromDateFields[TimeUnit.MONTH.ordinal()] == DEFAULT_DATE_FIELD_VALUE){
			fromDateFields[TimeUnit.MONTH.ordinal()] = toDateFields[TimeUnit.MONTH.ordinal()];
		}

		// Adjust year
		if(fromDateFields[TimeUnit.YEAR.ordinal()] == DEFAULT_DATE_FIELD_VALUE && toDateFields[TimeUnit.YEAR.ordinal()] != DEFAULT_DATE_FIELD_VALUE){
			if(fromDateFields[TimeUnit.MONTH.ordinal()] > toDateFields[TimeUnit.MONTH.ordinal()] 
					|| (fromDateFields[TimeUnit.MONTH.ordinal()] == toDateFields[TimeUnit.MONTH.ordinal()] && fromDateFields[TimeUnit.DAY.ordinal()] > toDateFields[TimeUnit.DAY.ordinal()])){
				// 'From' should be a year ago if fromDate (month/date) is after toDate(month/date)
				fromDateFields[TimeUnit.YEAR.ordinal()] = toDateFields[TimeUnit.YEAR.ordinal()] - 1;
			} else {
				// 'From' is the same year as 'To' otherwise
				fromDateFields[TimeUnit.YEAR.ordinal()] = toDateFields[TimeUnit.YEAR.ordinal()];
			}
		} else if(fromDateFields[TimeUnit.YEAR.ordinal()] != DEFAULT_DATE_FIELD_VALUE && toDateFields[TimeUnit.YEAR.ordinal()] == DEFAULT_DATE_FIELD_VALUE){
			if(toDateFields[TimeUnit.MONTH.ordinal()] < fromDateFields[TimeUnit.MONTH.ordinal()]
					|| (toDateFields[TimeUnit.MONTH.ordinal()] == fromDateFields[TimeUnit.MONTH.ordinal()] && toDateFields[TimeUnit.DAY.ordinal()] < fromDateFields[TimeUnit.DAY.ordinal()])){
				toDateFields[TimeUnit.YEAR.ordinal()] = fromDateFields[TimeUnit.YEAR.ordinal()] + 1;
			} else {
				toDateFields = fromDateFields;
			}
		}
	}

	private static int parseHourValue(String hourStr){
		if(hourStr.startsWith("minuit")){
			return 0;
		}

		if(hourStr.startsWith("midi")){
			return 12;
		}

		return 0;
	}

	private static int parseMinutesValue(String minutesStr){
		if(minutesStr.startsWith("quart")){
			return 15;
		}

		if(minutesStr.startsWith("demi")){
			return 30;
		}

		return 0;
	}

	private static SingleTimeObject parseRelativeDay(String dayStr){
		Calendar calendar = Calendar.getInstance();

		SingleTimeObject oneDay = new SingleTimeObject();

		oneDay.dateDefinition = DateDefinition.DATE_ONLY; 
		oneDay.interval = TimeInterval.SINGLE;

		if(dayStr.equals("aujourd'hui")){
			oneDay.tense = TimeTense.PRESENT;
		} else {
			String[] parts = dayStr.split("-");

			for(int i=0; i<parts.length; i++){
				if(parts[i].equals("avant") || parts[i].equals("hier")){
					calendar.add(Calendar.DAY_OF_MONTH, -1);
					oneDay.tense = TimeTense.PAST;
				} else if(parts[i].equals("après") || parts[i].equals("demain")){
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					oneDay.tense = TimeTense.FUTURE;
				}
			}
		}

		oneDay.date = calendar.getTime();

		return oneDay;
	}
}
