+ Signal {
	*prWindowFuncs {
		^(
			\bartlett		: { |a|
				{ |x|
					(1-(x-0.5).abs);
				}
			},
			\blackmanHarris	: { |a|
				{ |x|
					var c0 = 0.35875;
					var c1 = 0.48829;
					var c2 = 0.14128;
					var c3 = 0.01168;
					c0 - (c1*(2*x).cosPi) + (c2*(2*2*x).cosPi) - (c3*(3*2*x).cosPi);
				}
			},
			\blackmanNuttall: { |a|
				{ |x|
					var c0 = 0.3635819;
					var c1 = 0.4891775;
					var c2 = 0.1365995;
					var c3 = 0.0106411;
					c0 - (c1*(2*x).cosPi) + (c2*(2*2*x).cosPi) - (c3*(3*2*x).cosPi);
				}
			},
			\dolphChebyshev	: { |a, n|
				{ |x|
					var y = x - 0.5;
					var m = n - 1;
					var oor = 10.pow(a/(-1*20));
					var x0 = (oor.acosh/m).cosh;
					var sum = (1 .. m/2).sum { |i| m.chebyshevT(x0*(i*pi/n).cos)*(2*pi*y*m*i/n).cos };

					(oor + (2*sum))/n;
				}
			},
			\gaussian		: { |a|
				{ |x|
					((-0.5) * ((x-0.5) * a * 2).squared).exp;
				}
			},
			\hann			: { |a|
				{ |x|
					x.sinPi.squared;
				}
			},
			\hamming		: { |a|
				{ |x|
					var coeff = 0.53836;
					(coeff - ((1-coeff)*(2*x).cosPi));
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
			},
			\tukey			: { |a|
				{ |x|
					if(x < (a/2), {
						0.5*(1 + ((2*x/a) - 1        ).cosPi);
					},
					if(x > (1 - (a/2)), {
						0.5*(1 + ((2*x/a) - (2/a) + 1).cosPi);
					}, {
						1;
					}));
				}
			}
		);
	}

	*prWindowFactory { arg type, size, pad = 0, a = 3, sym = true;
		var win;

		if (size == 1, {
			^this.newClear(1).fill(1);
		});

		win = this.newClear(size-pad-(sym.asInteger))
					.fill(1)
					.waveFill(this.prWindowFuncs[type].value(a, size-pad));

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
	}

	*bartlettWindow			{ arg size, pad = 0, sym = true;
		^this.prWindowFactory(\bartlett	, size, pad, 0, sym);
	}

	*blackmanHarrisWindow	{ arg size, pad = 0, sym = true;
		^this.prWindowFactory(\blackmanHarris	, size, pad, 0, sym);
	}

	*blackmanNuttallWindow	{ arg size, pad = 0, sym = true;
		^this.prWindowFactory(\blackmanNuttall	, size, pad, 0, sym);
	}

	*dolphChebyshevWindow	{  arg size, pad = 0, a = -60, sym = true;
		^this.prWindowFactory(\dolphChebyshev	, size, pad, a, sym);
	}

	*gaussianWindow			{ arg size, pad = 0, a = 3, sym = true;
		^this.prWindowFactory(\gaussian			, size, pad, a, sym);
	}

	*hannWindow				{ arg size, pad = 0, sym = true;
		^this.prWindowFactory(\hann				, size, pad, 0, sym);
	}

	*kaiserWindow			{ arg size, pad = 0, a = 3, sym = true;
		^this.prWindowFactory(\kaiser			, size, pad, a, sym);
	}

	*lanczosWindow			{ arg size, pad = 0, sym = true;
		^this.prWindowFactory(\lanczos			, size, pad, 0, sym);
	}

	*tukeyWindow			{ arg size, pad = 0, a = 0.5, sym = true;
		^this.prWindowFactory(\tukey			, size, pad, a, sym);
	}
}
