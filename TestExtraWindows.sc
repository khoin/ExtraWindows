/**
TestExtraWindows.new.run;
**/

TestExtraWindows : UnitTest {

	setUp {
	}

	tearDown {
	}

	// Windows must have value of 1 if its size is 1.
	test_size_one {
		Signal.listOfWindows.do({ arg wName;
			var window = Signal.perform(wName, 1);

			this.assertEquals(window.at(0),	1, "Size of 1 for " ++ wName.asString);

		});
	}

	// Reverse a window and compare to test symmetry.
	test_symmetry {
		Signal.listOfWindows.do({ arg wName;
			var length = [511, 512, 1023, 1024, 9999.rand];

			length.do({ arg len;
				var window = Signal.perform(wName, len, 0, true, 3);

				this.assertEquals(
					window.reverse,
					window,
					"Symmetry test for " ++ wName.asString ++ ", size " ++ len
				);
			});
		});
	}

	// Append the first sample to the Signal, then perform the symmetry test.
	test_periodic {
		Signal.listOfWindows.do({ arg wName;
			var length = [511, 512, 1023, 1024, 9999.rand];

			length.do({ arg len;
				var window = Signal.perform(wName, len, 0, false, 3);

				window = window ++ window.at(0);

				this.assertEquals(
					window.reverse,
					window,
					"Periodic test for " ++ wName.asString ++ ", size " ++ len
				);
			});
		});
	}
}
