package ru.alber.wiley;

public class Test {
	
	private final int value;
	
	public Test(int value) {
		this.value = value;
	}
	
	public int getTest() {
		return this.value;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (this.getClass() != object.getClass())
			return false;
		if (this.hashCode() == ((Test) object).hashCode())
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return new StringBuffer().append(value).toString().hashCode();		
	}
	
	@Override
	public String toString() {
		return value + "";
	}
	
}
