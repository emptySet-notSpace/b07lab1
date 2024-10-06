package lab3;
import java.util.List;

public abstract class SpecialNumber {
	public abstract SpecialNumber add(SpecialNumber other) throws Lab3Exception;
	
	public abstract SpecialNumber divideByInt(int other) throws Lab3Exception;
	
	/**
	 * add up all nums and divide by the size of the list 
	 * to compute the average of the list
	 * 
	 * @param a list of SpecialNumber nums to calculate the average
	 * @return the average number of list of nums
	 * @throws Lab3Exception if null or empty
	 */
	public static SpecialNumber computeAverage(List<SpecialNumber> nums) throws Lab3Exception {
		if (nums == null || nums.isEmpty()) {
			throw new Lab3Exception("List cannot be empty");
		}
		SpecialNumber sum = nums.get(0);
		for (int i = 1; i < nums.size(); i++) {
			sum = sum.add(nums.get(i));
		}
		
		return sum.divideByInt(nums.size());
	}

}
