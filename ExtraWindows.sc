+ Signal {

	*gaussianWindow { arg size, pad = 0, std = 7, sym = 1;

		var win;
		var windowFunc = { arg x;
			// This exactly matches Octave's signal package
			// std is doubled because for the above reason.
			((-0.5) * ((x-0.5) * std * 2).squared).exp;
		};

		if (size == 1, {
			^this.newClear(1).fill(1);
		});

		win = this.newClear(size-pad-sym).fill(0.5).waveFill(windowFunc, 0, 1);

		if (sym == 1, {
			win = win ++ win.at(0);
		});

		if (pad != 0, {
			win = win ++ this.newClear(pad);
		});

		^win;
	}

	// n.cylBesselI(arg)
	// i.e, the value of nth-order modified Bessel function evaluated at arg
	*kaiserWindow { arg size, pad = 0, a = 3, sym = 1;
		var win;
		var windowFunc = { arg x;
			(0.cylBesselI(pi*a*(1-(2*x - 1).squared).sqrt)) / (0.cylBesselI(pi*a));
		};

		win = this.newClear(size-pad-sym).fill(0.5).waveFill(windowFunc, 0, 1);

		if (sym == 1, {
			win = win ++ win.at(0);
		});

		if (pad != 0, {
			win = win ++ this.newClear(pad);
		});

		^win;
	}
}
