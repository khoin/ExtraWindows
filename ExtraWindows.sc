+ Signal {
	*prWindowFuncs {
		^(
			\gaussian		: { |a|
				{ |x|
					((-0.5) * ((x-0.5) * a * 2).squared).exp;
				}
			},
			\kaiser			: { |a|
				{ |x|
					(0.cylBesselI(pi*a*(1-(2*x - 1).squared).sqrt)) / (0.cylBesselI(pi*a));
				}
			},
			\lanczos		: { |a|
				{ |x|
					((2*x - 1)*pi).sincPi;
				}
			}
		);
	}

	*prWindowFactory { arg type, size, pad = 0, sym = true, a = 3;
		var win;

		if (size == 1, {
			^this.newClear(1).fill(1);
		});

		win = this.newClear(size-pad-(sym.asInteger))
					.fill(1)
					.waveFill(this.prWindowFuncs[type].value(a));

		if (sym, {
			win = win ++ this.newClear(1).fill(win[0]);
		});

		if (pad != 0, {
			win = win ++ this.newClear(pad);
		});

		^win;
	}

	*listOfWindows {
		^this.prWindowFuncs.keys.collect({arg x; (x.asString ++ "Window").asSymbol})

		// Why would anyone do this?
		// Signal.perform(Signal.listOfWindows.choose, 512)
	}

	*gaussianWindow			{ arg size, pad = 0, sym = true, a = 3;
		^this.prWindowFactory(\gaussian			, size, pad, sym, a);
	}

	*kaiserWindow			{ arg size, pad = 0, sym = true, a = 3;
		^this.prWindowFactory(\kaiser			, size, pad, sym, a);
	}

	*lanczosWindow			{ arg size, pad = 0, sym = true, a = 0;
		^this.prWindowFactory(\lanczos			, size, pad, sym, a);
	}
}
