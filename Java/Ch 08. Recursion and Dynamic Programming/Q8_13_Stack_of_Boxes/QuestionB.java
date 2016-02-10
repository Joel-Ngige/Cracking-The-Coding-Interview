package Q8_13_Stack_of_Boxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionB {
	public static int createStack(List<Box> boxes) {
		Collections.sort(boxes, new BoxComparator());
		int maxHeight = 0;
		int[] stackMap = new int[boxes.size()];
		for (int i = 0; i < boxes.size(); i++) {
			int height = createStack(boxes, i, stackMap);
			maxHeight = Math.max(maxHeight, height);
		}
		return maxHeight;
	}

	public static int createStack(List<Box> boxes, int bottomIndex, int[] stackMap) {
		if (bottomIndex < boxes.size() && stackMap[bottomIndex] > 0) {
			return stackMap[bottomIndex];
		}
		
		Box bottom = boxes.get(bottomIndex);
		int maxHeight = 0;
		for (int i = bottomIndex + 1; i < boxes.size(); i++) {
			if (boxes.get(i).canBeAbove(bottom)) {
				int height = createStack(boxes, i, stackMap);
				maxHeight = Math.max(height, maxHeight);
			}
		}		
		maxHeight += bottom.height;
		stackMap[bottomIndex] = maxHeight;
		return maxHeight;
	}
		
	
	public static void main(String[] args) {
		List<Box> boxes = new ArrayList<>(Arrays.asList(
			new Box(6, 4, 4),
			new Box(8, 6, 2),
			new Box(5, 3, 3),
			new Box(7, 8, 3),
			new Box(4, 2, 2),
			new Box(9, 7, 3))
		);
		int height = createStack(boxes);
		System.out.println(height);
	}

}
