# Color Pyramid Problem

## About

This example is from the IOI. I summarized all the calculation methods I found in the ColorPyramidCalculator.java File.
The complexity of this problem with no optimization is

```
O(N) = N-1 * (N/2)
```

## The Problem itself

You have an array of colours. Each colour can be either **R**ed, **G**reen and **B**lue. Those are represented by the letters R, G and B or in the code by 1, 2 or 3.  

```
RGBG // INPUT
BRR
GR
B // OUTPUT
```


You mix every row until there is only one colour left which is the output.

### Mixing two colours

Are the two colours the same, then the mix of those is the same.
```
RR
R

GG
G

BB
B
```

Are the colours different, then the mix is the colour which is missing.
```
RG
B

BG
R

RB
G
```

### Mixing rows
Mix the first colour with the second one. The second one with the third one. The third one with the fourth one and so on.
Do this until you mix the N-1'th and N'th colour. Now you got a new row which is shorter by one.

```
RGBG
BRR
GR
B
```

This solution is the basic one, which can't handle large numbers. 

## Target
Your target is to write an optimization method, that can handle a billion colours within seconds.
