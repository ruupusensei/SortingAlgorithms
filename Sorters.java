import java.util.Random;

public class Sorters {

	/**
	* Public method which takes an array of integers as an argument and sorts
	* the array using the merge sort algorithm. Calls mergeSortRecursive and
	* merge as helper functions. Immediately calls the recursive function
	* with the arguments selecting the entire array.
	*
	* @param array	integer array to be sorted
	*/
	public void mergeSort(int[] array){

		this.mergeSortRecursive(array, 0, array.length - 1);
	}

	/**
	* Private helper funciton to perform merge sort. Uses recursion calling itself
	* to break down the array into the minimally sized subproblems, then calls the
	* merge function to merge the elements back in sorted order.
	*
	* @param array 	integer array to be sorted
	* @param low 	lowest index of the current subproblem
	* @param high	greatest index of the current subproblem
	*/
	private void mergeSortRecursive(int[] array, int low, int high) {

		if ( low < high ) {

			int middle = ( high + low ) / 2; //find the middle point to split the array
			this.mergeSortRecursive(array, low, middle); // call recursively to sort the lower half
			this.mergeSortRecursive(array, middle + 1, high); // call recursively to sort the upper half
			//once  the array is broken fully into subarrays of length 1, merge
			this.merge(array, low, middle, high);
		}
	}

	/**
	* Private helper funciton to perform merge sort. Performs the actual 'merging'
	* of the array into correct sorted order.
	*
	* @param array 	integer array to be sorted
	* @param low 	lowest index of the current subproblem
	* @param middle	midpoint index of the current subproblem
	* @param high	greatest index of the current subproblem
	*/
	private void merge(int[] array, int low, int middle, int high) {

		int[] leftArray = new int[ middle - low + 1]; //create left and right temp arrays
		int[] rightArray = new int[high - middle];

		for ( int i = 0; i < leftArray.length; i++) { //copy array data into temp arrays
			leftArray[i] = array[low + i];
		}
		for ( int i = 0; i < rightArray.length; i++) {
			rightArray[i] = array[i + middle + 1];
		}

		int left = 0; //index for tracking left sub array
		int right = 0; //index for tracking right sub array

		for ( int i = low; i < high + 1; i++) { // runs through entire array given in method parameter
			if ( left < leftArray.length && right < rightArray.length) { // stops if either index is out of bounds
				if (leftArray[left] <= rightArray[right]) { // if left array value is less than right, copy left into original array
					array[i] = leftArray[left];
					left++; // move up the sub array
				}
				else { // left is greater than right, so copy right value
					array[i] = rightArray[right];
					right++; // move up the sub array
				}
			}
			//only one of the below 2 conditions is ever met at a time
			else if (left < leftArray.length) { //copy any leftover values from left array
				array[i] = leftArray[left];
				left++;
			}
			else if (right < rightArray.length) { // copy any leftover values from right array
				array[i] = rightArray[right];
				right++;
			}
		}
	}

	/**
	* Public method which takes an array of integers as an argument and sorts
	* the array using the QuickSort algorithm. Calls quickSortRecursive and
	* partition as helper functions. Immediately calls the recursive function
	* with the arguments selecting the entire array.
	*
	* @param array	integer array to be sorted
	*/
	public void quickSort(int[] array){
		this.quickSortRecursive(array, 0, array.length -1);
	}

	/**
	* Private helper funciton to perform QuickSort. Uses recursion calling itself
	* to break down the array into the minimally sized subproblems. Each recurrance
	* the partition function is called and as a result the chosen partition index
	* will be in its correct sorted location after the partition call. The function
	* initially sorts the lower side of the array, then the upper side.
	*
	* @param array 	integer array to be sorted
	* @param low 	lowest index of the current subproblem
	* @param high	greatest index of the current subproblem
	*/
	private void quickSortRecursive(int[] array, int low, int high) {

		if (low < high + 1) {
			int partitionIndex = this.partition(array, low, high);
			this.quickSortRecursive(array, low, partitionIndex -1); //sort left partition
			this.quickSortRecursive(array, partitionIndex + 1, high); // sort right partition
		}
	}

	/**
	* Private helper funciton to implement the partition algorithm. This algorithm
	* performs the bulk of the work for quick sort; after execution the pivot
	* value will be in its proper place. All elements smaller than the pivot are
	* to the left of it, and all elements greater are to the right.
	* this function also calls 2 helper funcitons, swap and getRandomPivot.
	*
	* @param array 	integer array to be sorted
	* @param low 	lowest index of the current subproblem
	* @param high	greatest index of the current subproblem
	*/
	private int partition(int[] array, int low, int high) {

		this.swap(array, low, this.getRandomPivot(low, high));

		int current = low + 1;

		for (int i = current; i <= high; i++) {
			if (array[i] < array[low]) {
				swap(array, i, current);
				current++;
			}
		}
		swap(array, low, current -1);
		return current - 1;
	}

	/**
	* Exchanges the elements of 2 items in an array.
	*
	* @param array 	integer array being sorted
	* @param index1 first value being exchanged
	* @param index2	second value being exchanged
	*/
	private void swap(int[] array, int index1, int index2) {
		int temp;
		temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	/**
	* Generates a random value within the range necessary to create a
	* random pivot value for the partition function.
	*
	* @param low lower bound of value range
	* @param high	upper bound of value range
	*/
	private int getRandomPivot(int low, int high) {
		Random random = new Random();
		return random.nextInt((high - low) + 1 ) + low;
	}
}
