package com.scg.career.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarRentalCalculator {
	
	private List<Car> cars = Arrays.asList(new Car('L', 15, 12), new Car('M', 10, 8), new Car('S', 5, 5));
	
	public void calculateLowestCost(List<Character> combinations, int seats) {
		if (seats < 1) {
			System.out.println(combinations);
		} else {
			for (Car car : cars) {
				List<Character> newCombinations = new ArrayList<>(combinations);
				newCombinations.add(car.getSize());
				calculateLowestCost(newCombinations, seats - car.getSeats());
			}
		}
//		throw new UnsupportedOperationException();
	}
	
	public class Car {
		
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

		private CarRentalCalculator getOuterType() {
			return CarRentalCalculator.this;
		}
	}
	
	public static void main(String[] args) {
		CarRentalCalculator carRentalCalculator = new CarRentalCalculator();
		carRentalCalculator.calculateLowestCost(new ArrayList<>(), 15);
	}

}
