
module communicationsSystem {
	requires java.desktop;
	requires org.junit.jupiter.api;
	requires junit4;

	// Export the Testing package to the junit4 module.
	exports Testing to junit4;
}