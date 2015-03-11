package com.rokuan.calliopecore.test;

import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;

import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject.CountType;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject.Range;
import com.rokuan.calliopecore.source.ArrayListDataSource;

public class DataSourceTest extends TestCase {
	private ArrayListDataSource<String> dataSource = new ArrayListDataSource<String>();

	@Override
	public void setUp() throws Exception{
		super.setUp();

		dataSource.add("a");
		dataSource.add("b");
		dataSource.add("c");
		dataSource.add("d");
		dataSource.add("e");
		dataSource.add("f");
		dataSource.add("g");
		dataSource.add("h");
		dataSource.add("i");
		dataSource.add("j");
		dataSource.add("k");
		dataSource.add("l");
		dataSource.add("m");
		dataSource.add("n");
		dataSource.add("o");
		dataSource.add("p");
		dataSource.add("q");
		dataSource.add("r");
		dataSource.add("s");
		dataSource.add("t");
		dataSource.add("u");
		dataSource.add("v");
		dataSource.add("w");
		dataSource.add("x");
		dataSource.add("y");
		dataSource.add("z");
	}

	@Test
	public void testSingleElement(){
		CountObject count = new CountObject();

		// Le 5eme element
		count.countType = CountType.LIMIT;
		count.position = 5;
		count.count = 1;
		count.range = Range.FIXED;

		ArrayListDataSource<String> result = (ArrayListDataSource<String>)dataSource.getData(count);

		assertEquals(1, result.size());
		assertEquals("e", result.get(0));
	}

	@Test
	public void testFirst(){
		CountObject count = new CountObject();

		// Les 7 premiers elements
		count.countType = CountType.LIMIT;
		count.count = 7;
		count.range = Range.FIRST;

		ArrayListDataSource<String> result = (ArrayListDataSource<String>)dataSource.getData(count);

		if(!result.isEmpty()){
			String[] resultsArray = new String[result.size()];

			result.toArray(resultsArray);

			assertTrue(result.size() <= 7);
			assertTrue(Arrays.deepEquals(new String[]{ "a", "b", "c", "d", "e", "f", "g" }, resultsArray));
		}
	}

	@Test
	public void testLast(){
		CountObject count = new CountObject();

		// Les 3 derniers elements
		count.countType = CountType.LIMIT;
		count.count = 3;
		count.range = Range.LAST;

		ArrayListDataSource<String> result = (ArrayListDataSource<String>)dataSource.getData(count);

		if(!result.isEmpty()){
			String[] resultsArray = new String[result.size()];

			result.toArray(resultsArray);

			assertTrue(result.size() <= 7);
			assertTrue(Arrays.deepEquals(new String[]{ "x", "y", "z" }, resultsArray));
		}
	}

	@Test
	public void testAll(){
		CountObject count = new CountObject();
		
		count.countType = CountType.ALL;
		
		ArrayListDataSource<String> result = (ArrayListDataSource<String>)dataSource.getData(count);
		
		assertEquals(dataSource.size(), result.size());
	}
}
