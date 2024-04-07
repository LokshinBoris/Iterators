package bbl.iterator.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import bbl.iterator.RangePredicate;

class RangePredicateTest extends RangeTest
{
		@Test
		void iterableIteratorTest()
		{
			RangePredicate range = RangePredicate.getRange(10, 15);
			Integer[] rangeExpected = {10, 11, 12, 13, 14, 15};
			Integer[] rangeNullExpected = {null, null,null,null,null,null};
			assertArrayEquals(rangeExpected, toArrayFromIterable(new Integer[rangeExpected.length], range));
			RangePredicate rangeEvenOdd = RangePredicate.getRange(1, 7);
			RangePredicate rangeDevideThree= RangePredicate.getRange(10, 15);
			range.setPredicate(n-> n!=n-1);
			assertArrayEquals(rangeExpected, toArrayFromIterable(new Integer[rangeExpected.length], range));
			range.setPredicate( (n) -> n == n-1);
			assertArrayEquals(rangeNullExpected, toArrayFromIterable(new Integer[rangeExpected.length], range));

			Integer[] rangeOddExpected = {1, 3, 5, 7};
			Integer[] rangeEvenExpected = {2, 4, 6};
			Integer[] rangeDevideThreeExpected = {12, 15};

			rangeEvenOdd.setPredicate(n -> n % 2 != 0);
			assertArrayEquals(rangeOddExpected, toArrayFromIterable(new Integer[rangeOddExpected.length], rangeEvenOdd));
			rangeEvenOdd.setPredicate(n -> n % 2 == 0);
			assertArrayEquals(rangeEvenExpected, toArrayFromIterable(new Integer[rangeEvenExpected.length], rangeEvenOdd));
			rangeDevideThree.setPredicate(n-> n%3==0);
			assertArrayEquals(rangeDevideThreeExpected, toArrayFromIterable(new Integer[rangeDevideThreeExpected.length], rangeDevideThree));

		
		}
		@Test
		void iteratorIncorrectUsageTest()
		{
			RangePredicate rangePredicate = RangePredicate.getRange(1, 9);
			rangePredicate.setPredicate(n -> n % 10 == 0);
			Iterator<Integer> it = rangePredicate.iterator();
			assertThrowsExactly(NoSuchElementException.class, ()->it.next());
		}

}
