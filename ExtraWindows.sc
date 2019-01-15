+ Signal {
	
	*gaussianWindow { arg size, pad = 0, a = 0.4;

		var windowFunc = { arg x;
				(((x-0.5) / (a*0.5)).squared * (-1/2)).exp;
		};

		if (pad == 0, {
			^this.newClear(size).fill(0.5).waveFill(windowFunc, 0, 1);
		},{
			^this.newClear(size-pad).fill(0.5).waveFill(windowFunc, 0, 1) ++ this.newClear(pad);
		});
	}

	// *kaiserWindow { arg size, pad = 0;
	// 	"not implemented".warn;
	// 	^this.newClear(size);
	// }
}