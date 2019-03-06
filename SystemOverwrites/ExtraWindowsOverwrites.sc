+ Signal {
	*hanningWindow			{ arg size, pad = 0, sym = false;
		^this.prWindowFactory(\hann		, size, pad, 0, sym);
	}

	*hammingWindow				{ arg size, pad = 0, sym = false;
		^this.prWindowFactory(\hamming		, size, pad, 0, sym);
	}
}
