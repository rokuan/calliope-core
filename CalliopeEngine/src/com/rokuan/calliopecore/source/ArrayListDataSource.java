package com.rokuan.calliopecore.source;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.CriterionObject;

public class ArrayListDataSource<DataType> extends ArrayList<DataType> implements DataSource<DataType> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1908414990889304385L;
	
	private int currentIndex = 0;

	public ArrayListDataSource() {
		super();
	}
	
	public ArrayListDataSource(int initialCapacity){
		super(initialCapacity);
	}

	public ArrayListDataSource(List<DataType> list){
		if(list != null){
			this.addAll(list);
		}
	}

	@Override
	public DataSource<DataType> getData(CountObject count){
		return this.getData(count, null);
	}

	@Override
	public DataSource<DataType> getData(CountObject count, List<CriterionObject> criteria) {
		ArrayListDataSource<DataType> result = new ArrayListDataSource<DataType>();

		if(count != null){
			switch(count.countType){
			case ALL:
				result.addAll(this);
				break;

			case LIMIT:
				switch(count.range){
				case FIRST:
					result.addAll(this.subList(0, (int)Math.min(this.size(), count.count)));
					break;

				case FIXED:
					result.add(this.get((int)count.position - 1));
					break;

				case LAST:
					result.addAll(this.subList((int)Math.max(0, this.size() - count.count), this.size()));
					break;
				}
				break;
				
			case INTERVAL:
				// TODO:
				break;
			}
		}

		if(criteria != null){
			// TODO: Filtrer/trier selon les criteres
		}

		return result;
	}
	
	@Override
	public DataType getData(int index){
		return this.get(index);
	}

	@Override
	public DataType previousData() throws NoSuchElementException {
		if(!hasPreviousData()){
			throw new NoSuchElementException();
		}

		//currentIndex--;
		return this.get(--currentIndex);
	}

	@Override
	public DataType currentData() {
		return this.get(currentIndex);
	}

	@Override
	public DataType nextData() throws NoSuchElementException {
		if(!hasNextData()){
			throw new NoSuchElementException();
		}

		//currentIndex++;
		return this.get(++currentIndex);
	}

	public boolean hasPreviousData(){
		return currentIndex > 0;
	}

	public boolean hasNextData(){
		return currentIndex < this.size() - 1;
	}

	@Override
	public DataSource<DataType> removeData(CountObject count) {
		return removeData(count, null);
	}

	@Override
	public DataSource<DataType> removeData(CountObject count,
			List<CriterionObject> criteria) {
		ArrayListDataSource<DataType> result = new ArrayListDataSource<DataType>();
		int fromIndex = 0, toIndex = 0;

		if(count != null){
			switch(count.countType){
			case ALL:
				result.addAll(this);
				this.clear();
				break;

			case LIMIT:
				switch(count.range){
				case FIRST:
					fromIndex = 0;
					toIndex = (int)Math.min(this.size(), count.count);
					result.addAll(this.subList(fromIndex, toIndex));
					this.removeRange(fromIndex, toIndex);
					break;

				case FIXED:
					result.add(this.remove((int)count.position - 1));
					break;

				case LAST:
					fromIndex = (int)Math.max(0, this.size() - count.count);
					toIndex = this.size();
					result.addAll(this.subList(fromIndex, toIndex));
					this.removeRange(fromIndex, toIndex);
					break;
				}
				break;
				
			case INTERVAL:
				// TODO:
				break;
			}
		}
		
		if(criteria != null){
			
		}
		
		return result;
	}

	@Override
	public DataType removeData(int index) {
		return this.remove(index);
	}
}