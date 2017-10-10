package com.scg.career.test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ContainerSizeCalculator {
    
    public int calculateWaterWithinContainer(int[] container) {
        if (container == null) {
            throw new IllegalArgumentException();
        }
        
        if (!canKeepWater(container)) {
            return 0;
        } else {
            int leftWallIndex = findLeftWallIndex(container);
            int rightWallIndex = findRightWallIndex(leftWallIndex, container);
            int[] subContainer = Arrays.copyOfRange(container, leftWallIndex, rightWallIndex + 1);
            int[] tail = Arrays.copyOfRange(container, rightWallIndex, container.length);
            return findWaterWithinSubContainer(subContainer) + calculateWaterWithinContainer(tail);
        }
    }
    
    private boolean canKeepWater(int[] container) {
        return Arrays.stream(container).filter(i -> i > 0).count() > 1;
    }
    
    private int findLeftWallIndex(int[] container) {
        int leftWall = 0;
        for (int i = 0; i < container.length; ++i) {
            if (container[i] < container[leftWall]) {
                break;
            } else {
                leftWall = i;
            }
        }
        return leftWall;
    }
    
    private int findRightWallIndex(final int leftWallIndex, int[] container) {
        int highestWall = Arrays.stream(container).max().getAsInt();
        if (container[leftWallIndex] == highestWall) {
            return IntStream.range(leftWallIndex + 1, container.length).boxed().max((i, j) -> container[i] == container[j] ? 0 : container[i] > container[j] ? 1 : -1).get();
        } else {
            return IntStream.range(leftWallIndex + 1, container.length).filter(i -> container[i] >= container[leftWallIndex]).findFirst().orElse(0);
        }
    }
    
    private int findWaterWithinSubContainer(int[] subContainer) {
        int water = 0;
        int pivot = subContainer[0];
        if (subContainer[subContainer.length - 1] < subContainer[0]) {
            pivot = subContainer[subContainer.length - 1];
        }
        for (int i = 1; i < subContainer.length - 1; ++i) {
            water += pivot - subContainer[i];
        }
        return water;
    }
}
