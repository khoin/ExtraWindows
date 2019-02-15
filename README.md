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

Below are windows supported by this quark. Windows that aren't implemented yet will be marked so.

### Gaussian 
From J.O. Smith's SASP [2], "The Gaussian ''bell curve'' is possibly the only smooth, nonzero function, known in closed form, that transforms to itself."

... Here lies details about Gaussian ... 

### Kaiser
Formulated by J.F. Kaiser [1], "The \[Kaiser\] window \[...\] is convenient to explore the tradeoffs between record length, spectral resolution, and leakage in digital spectrum analysis."

.. Here lies details about Kaiser ... Remember to include parameters from the paper that are useful.

### Lanczos

Sinc window

### Tukey (Tapered Cosine)

Tukey window

## References

A conglomerate of references that will be properly numbered at some point later:

1. J. Kaiser and R. Schafer, "On the use of the I0-sinh window for spectrum analysis," in IEEE Transactions on Acoustics, Speech, and Signal Processing, vol. 28, no. 1, pp. 105-107, February 1980. doi: 10.1109/TASSP.1980.1163349 http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=1163349&isnumber=26144
2. Smith, J.O. Spectral Audio Signal Processing, http://ccrma.stanford.edu/~jos/sasp/, online book, 2011 edition, accessed 2019.

## Credits

## License

See [LICENSE](LICENSE)