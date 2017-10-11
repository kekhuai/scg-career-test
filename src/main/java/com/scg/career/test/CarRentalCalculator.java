package com.scg.career.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CarRentalCalculator {
	
	private final List<Car> cars = Arrays.asList(new Car('L', 15, 12), new Car('M', 10, 8), new Car('S', 5, 5));
	
	private Set<List<Car>> bestCombinations;

	public CarRentalCalculator() {
		super();
		bestCombinations = new HashSet<>();
	}

	public void calculateLowestCost(List<Car> combinations, int seats) {
		if (seats < 1) {
//			System.out.println(combinations);
			if (bestCombinations.size() == 0 || calculateCost(combinations) < calculateCost(bestCombinations.stream().findFirst().orElseThrow(IllegalArgumentException::new))) {
				bestCombinations.clear();
				Collections.sort(combinations);
				bestCombinations.add(combinations);
			} else if (calculateCost(bestCombinations.stream().findFirst().orElseThrow(IllegalArgumentException::new)) == calculateCost(combinations)) {
				Collections.sort(combinations);
				bestCombinations.add(combinations);
			}
		} else {
			for (Car car : cars) {
				List<Car> newCombinations = new ArrayList<>(combinations);
				newCombinations.add(car);
				calculateLowestCost(newCombinations, seats - car.getSeats());
			}
		}
//		throw new UnsupportedOperationException();
	}
	
	public void displayBestCombinations() {
		bestCombinations.stream()
		    .map(c -> c.stream()
		    		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
		    .forEach(i -> {
		    	    System.out.println(i.entrySet().stream().map(j -> String.format("%s x %d", j.getKey().getSize(), j.getValue())).reduce((first, second) -> first + ", " + second).orElse(""));
		    });
		System.out.println("cost: " + calculateCost(bestCombinations.stream().findFirst().orElseThrow(IllegalArgumentException::new)));
	}
	
	private int calculateCost(List<Car> combination) {
		return combination.stream().mapToInt(Car::getCost).sum();
	}
	
	public class Car implements Comparable<Car> {
		
		private char size;
		
		private int seats;
		
		private int cost;
		
		public Car(char size, int seats, int cost) {
			super();
			this.size = size;
			this.seats = seats;
			this.cost = cost;
		}

		public char getSize() {
			return size;
		}

		public int getSeats() {
			return seats;
		}

		public int getCost() {
			return cost;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + cost;
			result = prime * result + seats;
			result = prime * result + size;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Car other = (Car) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (cost != other.cost)
				return false;
			if (seats != other.seats)
				return false;
			if (size != other.size)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Car [size=" + size + "]";
		}

		private CarRentalCalculator getOuterType() {
			return CarRentalCalculator.this;
		}

		@Override
		public int compareTo(Car o) {
			return Integer.valueOf(cost).compareTo(o.getCost());
		}
	}
	
	public static void main(String[] args) {
//		for (int i = 0; i < 60; ++i) {
//			System.out.printf("==================%d seats==================%n", i);
//			CarRentalCalculator carRentalCalculator = new CarRentalCalculator();
//			carRentalCalculator.calculateLowestCost(new ArrayList<>(), i);
//			carRentalCalculator.displayBestCombinations();
//			System.out.println("============================================");
//		}
		CarRentalCalculator carRentalCalculator = new CarRentalCalculator();
		carRentalCalculator.calculateLowestCost(new ArrayList<>(), 6);
		carRentalCalculator.displayBestCombinations();
//		System.out.println(carRentalCalculator.getBestCombination());
	}

}
