import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class Java8_Strams {

	/*
	 * A new java.util.stream has been added in Java 8 to perform filter/map/reduce
	 * like operations with the collection. Stream API will allow sequential as well
	 * as parallel execution Collection interface has been extended with stream()
	 * and parallelStream() default methods to get the Stream for sequential and
	 * parallel execution.
	 * 
	 * 
	 * 
	 * Notice that parallel processing values are not in order, so parallel
	 * processing will be very helpful while working with huge collections.
	 * 
	 * For supporting parallel execution in Java 8 Stream API, Spliterator interface
	 * is used. Spliterator trySplit method returns a new Spliterator that manages a
	 * subset of the elements of the original Spliterator.
	 * 
	 */

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		System.out.println("start time......." + startTime);

		long startTimeOfForLoop = System.currentTimeMillis();

		List<Integer> myList = new ArrayList<>();
		for (int i = 0; i < 20000000; i++)
			myList.add(i);

		long stopTimeOfForLoop = System.currentTimeMillis();

		long startTimeOfParallelStream = System.currentTimeMillis();
		// parallel stream
		Stream<Integer> parallelStream = myList.parallelStream();
		long stopTimeOfParallelStream = System.currentTimeMillis();

		long startTimeOfParallelStramFilter = System.currentTimeMillis();
		// using lambda with Stream API, filter example
		Stream<Integer> highNums = parallelStream.filter(p -> p > 1);
		// using lambda in forEach
		highNums.forEach(p -> System.out.println("High Nums parallel=" + p));
		long stopTimeOfParallelStreamFilter = System.currentTimeMillis();

		long startTimeOfSequentialStream = System.currentTimeMillis();
		// sequential stream
		Stream<Integer> sequentialStream = myList.stream();
		long stopTimeOfSequentialStream = System.currentTimeMillis();

		long startTimeOfSequentialStreamFilter = System.currentTimeMillis();
		Stream<Integer> highNumsSeq = sequentialStream.filter(p -> p > 1);
		highNumsSeq.forEach(p -> System.out.println("High Nums sequential=" + p));
		long stopTimeOfSequentialStreamFilter = System.currentTimeMillis();

		long stopTime = System.currentTimeMillis();
		System.out.println("total time taken forLoop......." + (stopTimeOfForLoop - startTimeOfForLoop));
		System.out.println(
				"total time taken parallel stream......." + (stopTimeOfParallelStream - startTimeOfParallelStream));
		System.out.println("total time taken parallel stream filter ......."
				+ (stopTimeOfParallelStreamFilter - startTimeOfParallelStramFilter));

		System.out.println("total time taken sequential stream......."
				+ (stopTimeOfSequentialStream - startTimeOfSequentialStream));
		System.out.println("total time taken sequential stream filter ......."
				+ (stopTimeOfSequentialStreamFilter - startTimeOfSequentialStreamFilter));

		System.out.println("total time taken ......." + (stopTime - startTime));

	}
}
