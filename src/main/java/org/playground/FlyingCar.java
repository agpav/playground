package org.playground;

interface OperateCar {
	// ...
	default public int startEngine(EncryptedKey key) {
		// Implementation
		return 0;
	}
}

interface FlyCar {
	// ...
	default public int startEngine(EncryptedKey key) {
		// Implementation
		return 1;
	}
}

public class FlyingCar implements OperateCar, FlyCar {
	// ...
	public int startEngine(EncryptedKey key) {
		// FlyCar.super.startEngine(key);
		// OperateCar.super.startEngine(key);
		return 2;
	}
}

class EncryptedKey {
}