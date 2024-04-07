package bbl.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range
{
	private Predicate<Integer> predicate=null;
	protected RangePredicate(int min, int max)
	{
		super(min, max);	
	}
	
	public void setPredicate(Predicate<Integer> predicate)
	{
		this.predicate = predicate;
	}
	
	
	public static RangePredicate getRange(int min, int max)
	{
		checkMinMax(min, max);
		return new RangePredicate(min, max);
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new RangePredicateIterator();
	}
	private class RangePredicateIterator implements Iterator<Integer>
	{
		private int current=min;
		@Override
		public boolean hasNext() 
		{
			// TODO Auto-generated method stub
			boolean bRes=false;
			if(predicate!=null)
			while ( current<=max && predicate.negate().test(current))
			{
			  current++;
			}
			bRes=current<=max; 
			return bRes;
		}

		@Override
		public Integer next()
		{
			if(!hasNext()) 
			{
				throw new NoSuchElementException();
			}
			return current++;	
		}
		
	}


}
