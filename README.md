# ExtraWindows

A Quark that extends the [Signal](http://doc.sccode.org/Classes/Signal.html) class of [SuperCollider](https://supercollider.github.io/) to support more windowing functions. 

Developed by [@khoin](https://github.com/khoin) at [DXARTS](https://dxarts.uw.edu/), [University of Washington](https://uw.edu/) for an Independent Study (Winter 2019) under supervision of Dr. Joseph Anderson ([@joslloand](https://github.com/joslloand)).

## Installing

### Requirements
ExtraWindows depends on certain special functions only available in SuperCollider 3.10.0 and up. Make sure your SuperCollider is up-to-date.

Then, execute the following code:
```supercollider
Quarks.install("https://github.com/khoin/ExtraWindows.git");
```

## List of Supported Windows

Below are windows supported by this quark. Some window methods are overriden over SuperCollider's default so as to provide the symmetry parameter; they are as such marked below.

All implementations below are in terms of x from 0 to 1 exclusive.

### Bartlett (Triangular) 

Also named Fejér window. Its main lobe is twice as wide as the rectangular window that's one sample longer. Its first side lobe twice far down as retangular (-26 dB). [3] Its implementation:

```
|1 - (x - 0.5)|
```

### Blackman-Harris
### Blackman-Nuttall

These windows are of the cosine-sum family with four terms. The Blackman-Nuttall window has peak side-lobe level at -98 dB while Blackman-Harris is at -92 dB.

Heinzel, et al. describes their purposes as "suitable for the detection of small sinusoidal signals adjacent in frequenct to large signals \[...\], as a general-purpose window \[... for\] high dynamic range if amplitude accuracy \[...\] is not very important". [1]

### Gaussian 
From J.O. Smith's SASP [3], "The Gaussian ''bell curve'' is possibly the only smooth, nonzero function, known in closed form, that transforms to itself."

Its implementation in SuperCollider:
```supercollider
((-0.5) * ((x-0.5) * a * 2).squared).exp;
```

### Hann 
### Hamming

Again, these two windows are of the consine-sum family, but with two terms. The Hann window's first sidelobe is at -31.5 dB compared to rectangular's first side lobe at -13 dB. The Hamming window is defined such that the first side lobe of Hann's is cancelled down to a fifth of Hann's.


### Kaiser
Formulated by J.F. Kaiser [2], "The \[Kaiser\] window \[...\] is convenient to explore the tradeoffs between record length, spectral resolution, and leakage in digital spectrum analysis."

Its implementation in SuperCollider:
```supercollider
(0.cylBesselI(pi*a*(1-(2*x - 1).squared).sqrt)) / (0.cylBesselI(pi*a));
```

When a is zero, Kaiser window reduces to the retangular window.

The half-width of the mainlobe is shown to be: `sqrt(pi^2 + a^2)/(2pi*length)`.

The first side lobe ampltiude is: `20log10(sinh(a)/(0.217234*a))`. [2]

### Lanczos

The Lanczos window is essentially a window which terminates at the first zeroes about the origin of the sinc function.

### Tukey (Tapered Cosine)

Tukey window is the Hann window with 1's filled at its center.

... expand this ...

## References

1. Heinzel, G.; Rüdiger, A.; Schilling, R., "Spectrum and spectral density estimation by the Discrete Fourier transform (DFT), including a comprehensive list of window functions and some new flat-top windows" Max Planck Institute (MPI) für Gravitationsphysik / Laser Interferometry & Gravitational Wave Astronomy. http://edoc.mpg.de/395068
2. Kaiser, J.; Schafer, R., "On the use of the I0-sinh window for spectrum analysis," in IEEE Transactions on Acoustics, Speech, and Signal Processing, vol. 28, no. 1, pp. 105-107, February 1980. doi: 10.1109/TASSP.1980.1163349 http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=1163349&isnumber=26144
3. Smith, J.O., "Spectral Audio Signal Processing", online book, 2011 edition,http://ccrma.stanford.edu/~jos/sasp/

## License

See [LICENSE](LICENSE)